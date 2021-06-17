import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static int MOD = (int)Math.pow(2, 8);
    static int LIMIT = 50_000_000;
    static int T, M, C, I;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        while(0 < T--) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            I = Integer.parseInt(st.nextToken());
            brainFuck(new int[M], br.readLine().toCharArray(), br.readLine().toCharArray());
        }
        System.out.println(sb.toString());
    }

    private static int[] setBraces(char[] program) {
        int[] braces = new int[C];
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < C; i++) {
            if(program[i] == '[') {
                stack.push(i);
            } else if(program[i] == ']') {
                int index = stack.pop();
                braces[index] = i;
                braces[i] = index;
            }
        }

        return braces;
    }

    private static int getPointer(int pointer) {
        if(pointer < 0) return M - 1;
        else if(pointer == M) return 0;
        return pointer;
    }

    private static void brainFuck(int[] memory, char[] program, char[] input) {
        int pointer = 0;
        int count = 0;
        int i = 0, j = 0;
        int[] braces = setBraces(program);
        char command;
        int step = 2;
        int maxLocation = -1;
        while(0 < step--) {
            count = 0;
            maxLocation = -1;
            while(count < LIMIT && i < C) {
                maxLocation = Math.max(maxLocation, i);
                command = program[i];
                switch(command) {
                    case '-':
                        memory[pointer] = (memory[pointer] - 1) % MOD;
                        break;
                    case '+':
                        memory[pointer] = (memory[pointer] + 1) % MOD;
                        break;
                    case '<':
                        pointer = getPointer(pointer - 1);
                        break;
                    case '>':
                        pointer = getPointer(pointer + 1);
                        break;
                    case '.':
                        break;
                    case ',':
                        if(j == I) memory[pointer] = 255;
                        else memory[pointer] = input[j++];
                        break;
                }
    
                if((command == '[' && memory[pointer] == 0) || (command == ']' && memory[pointer] != 0)) i = braces[i] + 1;
                else i++;
                count++;
            }
    
            if(i == C || count != LIMIT) {
                sb.append("Terminates\n");
                return;
            }
        }

        sb.append("Loops " + braces[maxLocation] + " " + maxLocation + "\n");
    }
}

// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.util.Stack;
// import java.util.StringTokenizer;

// public class Main {
//     static int T, SM, SC, SI, pointer, MOD = 256;
//     static int[] memory;
//     static char[] commands, input;
//     public static void main(String[] args) throws IOException {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         StringBuilder sb = new StringBuilder();
//         StringTokenizer st;

//         T = Integer.parseInt(br.readLine());
//         while(0 < T--) {
//             st = new StringTokenizer(br.readLine());
//             SM = Integer.parseInt(st.nextToken());
//             SC = Integer.parseInt(st.nextToken());
//             SI = Integer.parseInt(st.nextToken());

//             int inputPointer = 0;
//             pointer = 0;
//             memory = new int[SM];
//             commands = br.readLine().toCharArray();
//             input = br.readLine().toCharArray();
            
//             // braces
//             Stack<Integer> bracesStack = new Stack<>();
//             int[][] braces = new int[SC][2];
//             int top = 0;
//             for(int i = 0; i < SC; i++) {
//                 if(commands[i] == '[') {
//                     bracesStack.push(i);
//                 } else if(commands[i] == ']') {
//                     braces[top][0] = bracesStack.pop();
//                     braces[top++][1] = i;
//                 }
//             }
            
//             int idx = 0, count = 0, loopHead = 0;
//             while(idx < SC) {
//                 if(50_000_000 <= count) break;

//                 switch(commands[idx]) {
//                     case '-':
//                         memory[pointer] = (memory[pointer] - 1) % MOD;
//                         break;
//                     case '+':
//                         memory[pointer] = (memory[pointer] + 1) % MOD;
//                         break;
//                     case '<':
//                         pointer = (pointer - 1 < 0) ? SM-1 : pointer - 1;
//                         break;
//                     case '>':
//                         pointer = (pointer + 1 == SM) ? 0 : pointer + 1;
//                         break;
//                     case '[':
//                         if(memory[pointer] == 0) {
//                             int tmp = 0;
//                             while(true) {
//                                 if(braces[tmp][0] == idx) {
//                                     idx = braces[tmp][1];
//                                     break;
//                                 }
//                                 tmp++;
//                             }
//                         }
//                         break;
//                     case ']':
//                         if(memory[pointer] != 0) {
//                             int tmp = 0;
//                             while(tmp < SC) {
//                                 if(braces[tmp][1] == idx) {
//                                     idx = braces[tmp][0];
//                                     break;
//                                 }
//                                 tmp++;
//                             }

//                             loopHead = Math.max(loopHead, tmp);
//                         }
//                         break;
//                     case ',':
//                         if(inputPointer < SI) memory[pointer] = input[inputPointer++] % 256;
//                         else memory[pointer] = 255;
//                         break;
//                 }

//                 idx++;                
//                 count++;
//             }

//             if(count < 50_000_000) sb.append("Terminates\n");
//             else sb.append("Loops " + braces[loopHead][0] + " " + braces[loopHead][1] + "\n");
//         }

//         System.out.println(sb.toString());
//     }
// }




// import java.io.BufferedReader;
// import java.io.InputStreamReader;
// import java.util.Stack;
// import java.util.StringTokenizer;
// import java.io.IOException;

// public class Main {
//     static int T, sm, sc, si, pointer;
//     static char[] code;
//     static String input;
//     static int[] data;
//     static int[][] braces;
//     public static void main(String[] args) throws IOException {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         StringTokenizer st;

//         T = Integer.parseInt(br.readLine());
//         while(0 < T--) {
//             st = new StringTokenizer(br.readLine());
//             sm = Integer.parseInt(st.nextToken());
//             sc = Integer.parseInt(st.nextToken());
//             si = Integer.parseInt(st.nextToken());
            
//             pointer = 0;
//             data = new int[sm];

//             code = br.readLine().toCharArray();
            
//             Stack<Integer> braceTemp = new Stack<Integer>();
//             braces = new int[sc][2];
//             int top = 0;
//             for(int i = 0; i < sc; i++) {
//                 if(code[i] == '[') {
//                     braceTemp.push(i);
//                 } else if(code[i] == ']') {
//                     braces[top][0] = braceTemp.pop();
//                     braces[top][1] = i;
//                     top++;
//                 }
//             }
            

//             input = br.readLine();

//             int loopIndex = 0;
//             int inputIndex = 0;
//             int clock = 0;
//             for(int i = 0; i < sc; i++) {

//                 if(50_000_000 <= clock) break;

//                 switch(code[i]) {
//                     case '-':
//                         data[pointer] = (data[pointer] - 1) % 256;
//                         break;

//                     case '+':
//                         data[pointer] = (data[pointer] + 1) % 256;
//                         break;
                        
//                     case '<':
//                         if(pointer == 0) pointer = sm - 1;
//                         else pointer--;
                        
//                         break;

//                     case '>':
//                         if(pointer == sm - 1) pointer = 0;
//                         else pointer++;
                        
//                         break;

//                     case '[':
//                         if(data[pointer] == 0) {
//                             int j = 0;
//                             while(true) {
//                                 if(braces[j][0] == i) {
//                                     i = braces[j][1];
//                                     break;
//                                 }

//                                 j++;
//                             }
//                         }

//                         break;

//                     case ']':
//                         if(data[pointer] != 0) {
//                             int j = 0;
//                             while(true) {
//                                 if(braces[j][1] == i) {
//                                     i = braces[j][0];
//                                     break;
//                                 }

//                                 j++;
//                             }

//                             loopIndex = loopIndex < j ? j : loopIndex;
//                         }
                        
//                         break;

//                     case '.':
//                         break;

//                     case ',':
//                         if(inputIndex < si) {
//                             data[pointer] = input.charAt(inputIndex++);
//                         } else {
//                             data[pointer] = 255;
//                         }
//                         break;
//                 }

//                 clock++;
//             }

//             if (50_000_000 <= clock) System.out.println("Loops " + braces[loopIndex][0] + " " + braces[loopIndex][1]);
//             else System.out.println("Terminates");
//         }
//     }
// }