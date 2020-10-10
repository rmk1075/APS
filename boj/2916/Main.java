import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[] angles = new int[361];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int angle;
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            angle = Integer.parseInt(st.nextToken());
            angles[angle] = 1;

            for(int j = 0; j < 361; j++) {
                if(angles[j] == 1) {
                    int temp = 1;
                    while(angles[(j + temp*angle) % 360] == 0) {
                        angles[(j+temp*angle) % 360] = 1;
                        temp++;
                    }
                }
            }

            for(int j = 360; -1 < j; j--) {
                if(angles[j] == 1 && -1 < 360 - j) angles[360 - j] = 1;
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < K; i++) {
            if(angles[Integer.parseInt(st.nextToken())] == 0) System.out.println("NO");
            else System.out.println("YES");
        }
    }
}