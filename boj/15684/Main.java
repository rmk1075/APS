import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    static int N, M ,H, minBridge = 4;
    static int[][] ladders;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        ladders = new int[H + 1][N + 1];

        int a, b;
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            ladders[a][b] = 1;
            ladders[a][b + 1] = 2;
        }

        make(0);

        System.out.println(3 < minBridge ? -1 : minBridge);
    }

    public static void make(int count) {
        if(check()) {
            minBridge = Math.min(minBridge, count);
        }

        if(minBridge - 1 <= count) return ;
        
        for(int i = 1; i < H + 1; i++) {
            for(int j = 1; j < N; j++) {
                if(ladders[i][j] == 0 && ladders[i][j + 1] == 0) {
                    ladders[i][j] = 1;
                    ladders[i][j + 1] = 2;
                    make(count + 1);
                    ladders[i][j] = ladders[i][j + 1] = 0;
                }
            }
        }
    }

    public static boolean check() {
        for(int i = 1; i < N + 1; i++) {
            int x = 0;
            int y = i;

            while(x < H + 1) {
                if(ladders[x][y] == 1) {
                    y++;
                } else if(ladders[x][y] == 2) {
                    y--;
                }
                
                x++;
            }

            if(y != i) return false;
        }

        return true;
    }
}