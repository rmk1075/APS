import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static int T, SM, SC, SI, pointer, MOD = 256;
    static int[] memory;
    static char[] commands, input;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        while(0 < T--) {
            st = new StringTokenizer(br.readLine());
            SM = Integer.parseInt(st.nextToken());
            SC = Integer.parseInt(st.nextToken());
            SI = Integer.parseInt(st.nextToken());

            int inputPointer = 0;
            pointer = 0;
            memory = new int[SM];
            commands = br.readLine().toCharArray();
            input = br.readLine().toCharArray();
            
            // braces
            Stack<Integer> bracesStack = new Stack<>();
            int[][] braces = new int[SC][2];
            int top = 0;
            for(int i = 0; i < SC; i++) {
                if(commands[i] == '[') {
                    bracesStack.push(i);
                } else if(commands[i] == ']') {
                    braces[top][0] = bracesStack.pop();
                    braces[top++][1] = i;
                }
            }
            
            int idx = 0, count = 0, loopHead = 0;
            while(idx < SC) {
                if(50_000_000 <= count) break;

                switch(commands[idx]) {
                    case '-':
                        memory[pointer] = (memory[pointer] - 1) % MOD;
                        break;
                    case '+':
                        memory[pointer] = (memory[pointer] + 1) % MOD;
                        break;
                    case '<':
                        pointer = (pointer - 1 < 0) ? SM-1 : pointer - 1;
                        break;
                    case '>':
                        pointer = (pointer + 1 == SM) ? 0 : pointer + 1;
                        break;
                    case '[':
                        if(memory[pointer] == 0) {
                            int tmp = 0;
                            while(true) {
                                if(braces[tmp][0] == idx) {
                                    idx = braces[tmp][1];
                                    break;
                                }
                                tmp++;
                            }
                        }
                        break;
                    case ']':
                        if(memory[pointer] != 0) {
                            int tmp = 0;
                            while(tmp < SC) {
                                if(braces[tmp][1] == idx) {
                                    idx = braces[tmp][0];
                                    break;
                                }
                                tmp++;
                            }

                            loopHead = Math.max(loopHead, tmp);
                        }
                        break;
                    case ',':
                        if(inputPointer < SI) memory[pointer] = input[inputPointer++] % 256;
                        else memory[pointer] = 255;
                        break;
                }

                idx++;                
                count++;
            }

            if(count < 50_000_000) sb.append("Terminates\n");
            else sb.append("Loops " + braces[loopHead][0] + " " + braces[loopHead][1] + "\n");
        }

        System.out.println(sb.toString());
    }
}




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