import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] paper = new int[100][100];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        int x, y;
        for(int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            for(int i = x; i < x + 10; i++) {
                for(int j = y; j < y + 10; j++) {
                    paper[i][j] = 1;
                }
            }
        }

        int count = 0;
        for(int i = 0; i < 100; i++) {
            for(int j = 0; j < 100; j++) {
                
                if(paper[i][j] == 1) {
                    count += check(i, j);
                }
            }
        }

        System.out.println(count);
    }

    public static int check(int x, int y) {
        int x_, y_, count = 0;
        for(int i = 0; i < 4; i++) {
            x_ = x + dx[i];
            y_ = y + dy[i];
            if(x_ < 0 || y_ < 0 || 100 <= x_ || 100 <= y_ || paper[x_][y_] == 0) count++;
        }

        return count;
    }
}