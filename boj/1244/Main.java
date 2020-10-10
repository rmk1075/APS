import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] switches;

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        switches = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i < N + 1; i++) {
            switches[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int gender = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());

            if(gender == 1) {
                // man
                int temp = 1;
                while(num * temp < N + 1) {
                    switches[num * temp] = switches[num * temp] == 1 ? 0 : 1;
                    temp++;
                }
            } else {
                // woman
                int index1 = num;
                int index2 = num;
                while(true) {
                    
                    if(index1 < 1 || index2 < 1 || N < index1 || N < index2 || switches[index1] != switches[index2]) break;

                    switches[index1] = switches[index2] = switches[index1] == 1 ? 0 : 1;
                    index1++;
                    index2--;
                }

            }
        }
        
        for(int i = 1; i < N + 1; i++) {
            System.out.print(switches[i]);
            if(i % 20 == 0) System.out.println();
            else System.out.print(" ");
        }
    }
}