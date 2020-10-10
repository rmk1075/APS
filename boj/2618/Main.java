import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Location {
    int x, y;

    Location(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int N, W, dp[][][];
    static Location[] list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        W = Integer.parseInt(br.readLine());
        dp = new int[W+1][W+1][3];
        list = new Location[W+1];
        for(int i = 1; i <= W; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            list[i] = new Location(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        dp[1][0][0] = distance(0, 1, 0);
        dp[0][1][0] = distance(0, 1, 1);
        for(int i = 2; i <= W; i++) {
            for(int j = 0; j < i-1; j++) {
                // i th case
                dp[i][j][0] = dp[i-1][j][0] + distance(i-1, i, 0);
                dp[i][j][1] = i-1;
                dp[i][j][2] = j;

                dp[j][i][0] = dp[j][i-1][0] + distance(i-1, i, 1);
                dp[j][i][1] = j;
                dp[j][i][2] = i-1;
            }

            // j == i-1
            int j = i-1;
            dp[i][j][0] = dp[j][i][0] = Integer.MAX_VALUE;
            for(int k = 0; k < i-1; k++) {
                int temp = dp[k][j][0] + distance(k, i, 0);
                if(temp < dp[i][j][0]) {
                    dp[i][j][0] = temp;
                    dp[i][j][1] = k;
                    dp[i][j][2] = j;
                }

                temp = dp[j][k][0] + distance(k, i, 1);
                if(temp < dp[j][i][0]) {
                    dp[j][i][0] = temp;
                    dp[j][i][1] = j;
                    dp[j][i][2] = k;
                }
            }
        }

        int minX = 0, minY = 0, minVal = Integer.MAX_VALUE;
        for(int i = 0; i < W; i++) {
            if(dp[i][W][0] < minVal) {
                minVal = dp[i][W][0];
                minX = i;
                minY = W;
            }
            
            if(dp[W][i][0] < minVal) {
                minVal = dp[W][i][0];
                minX = W;
                minY = i;
            }
        }

        System.out.println(minVal);
        StringBuilder sb = new StringBuilder();
        while(minX != 0 || minY != 0) {
            sb.append("\n" + ((minX < minY) ? 2 : 1));
            int x = dp[minX][minY][1], y = dp[minX][minY][2];
            minX = x;
            minY = y;
        }
        System.out.println(sb.reverse());
    }

    // a: x, b: y, c: row or col
    public static int distance(int a, int b, int c) {
        if(a == 0) {
            if(c == 0) return Math.abs(list[b].x - 1) + Math.abs(list[b].y - 1);
            else return Math.abs(list[b].x - N) + Math.abs(list[b].y - N);
        }

        return Math.abs(list[a].x - list[b].x) + Math.abs(list[a].y - list[b].y);
    }
}