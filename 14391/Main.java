import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, maxVal = 0;
    static int[] dx = {0, 1};
    static int[] dy = {1, 0};
    static int[][] checked, nums, visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nums = new int[N][M];
        checked = new int[N][M];
        visited = new int[N][M];

        for(int i = 0; i < N; i++) {
            int j = 0;
            for(char ch : br.readLine().toCharArray()) {
                nums[i][j++] = ch - '0';
            }
        }

        divide(0, 0);
        System.out.println(maxVal);
    }

    public static void divide(int x, int y) {

        if(N == x) {
            int sum = 0, temp = 0;
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < M; j++) {
                    if(checked[i][j] == 1) {
                        sum += temp;
                        temp = 0;
                    } else {
                        temp = temp*10 + nums[i][j];
                    }
                }
                sum += temp;
                temp = 0;
            }

            for(int i = 0; i < M; i++) {
                for(int j = 0; j < N; j++) {
                    if(checked[j][i] == 0) {
                        sum += temp;
                        temp = 0;
                    } else {
                        temp = temp*10 + nums[j][i];
                    }
                }
                sum += temp;
                temp = 0;
            }

            maxVal = Math.max(maxVal, sum);

            return ;
        }

        if(M == y) {
            divide(x+1, 0);
            return ;
        }

        checked[x][y] = 1;
        divide(x, y+1);

        checked[x][y] = 0;
        divide(x, y+1);
    }
}