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
