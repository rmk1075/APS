import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main {
    static int T, sm, sc, si, pointer;
    static char[] code;
    static String input;
    static int[] data;
    static int[][] braces;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        while(0 < T--) {
            st = new StringTokenizer(br.readLine());
            sm = Integer.parseInt(st.nextToken());
            sc = Integer.parseInt(st.nextToken());
            si = Integer.parseInt(st.nextToken());
            
            pointer = 0;
            data = new int[sm];

            code = br.readLine().toCharArray();
            
            Stack<Integer> braceTemp = new Stack<Integer>();
            braces = new int[sc][2];
            int top = 0;
            for(int i = 0; i < sc; i++) {
                if(code[i] == '[') {
                    braceTemp.push(i);
                } else if(code[i] == ']') {
                    braces[top][0] = braceTemp.pop();
                    braces[top][1] = i;
                    top++;
                }
            }
            

            input = br.readLine();

            int loopIndex = 0;
            int inputIndex = 0;
            int clock = 0;
            for(int i = 0; i < sc; i++) {

                if(50_000_000 <= clock) break;

                switch(code[i]) {
                    case '-':
                        data[pointer] = (data[pointer] - 1) % 256;
                        break;

                    case '+':
                        data[pointer] = (data[pointer] + 1) % 256;
                        break;
                        
                    case '<':
                        if(pointer == 0) pointer = sm - 1;
                        else pointer--;
                        
                        break;

                    case '>':
                        if(pointer == sm - 1) pointer = 0;
                        else pointer++;
                        
                        break;

                    case '[':
                        if(data[pointer] == 0) {
                            int j = 0;
                            while(true) {
                                if(braces[j][0] == i) {
                                    i = braces[j][1];
                                    break;
                                }

                                j++;
                            }
                        }

                        break;

                    case ']':
                        if(data[pointer] != 0) {
                            int j = 0;
                            while(true) {
                                if(braces[j][1] == i) {
                                    i = braces[j][0];
                                    break;
                                }

                                j++;
                            }

                            loopIndex = loopIndex < j ? j : loopIndex;
                        }
                        
                        break;

                    case '.':
                        break;

                    case ',':
                        if(inputIndex < si) {
                            data[pointer] = input.charAt(inputIndex++);
                        } else {
                            data[pointer] = 255;
                        }
                        break;
                }

                clock++;
            }

            if (50_000_000 <= clock) System.out.println("Loops " + braces[loopIndex][0] + " " + braces[loopIndex][1]);
            else System.out.println("Terminates");
        }
    }
}