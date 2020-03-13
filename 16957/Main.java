import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int R, C, map[][], cnt[][], dest[][][];
    static int[] dx = {-1, 0, 1, 0, -1, -1, 1, 1}, dy = {0, 1, 0, -1, -1, 1, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        cnt = new int[R][C];
        dest = new int[R][C][2];
        for(int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dest[i][j][0] = i;
                dest[i][j][1] = j;
            }
        }

        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                int minX = i, minY = j, minVal = map[i][j];
                for(int d = 0; d < 8; d++) {
                    int x = i + dx[d], y = j + dy[d];
                    if(x < 0 || y < 0 || x == R || y == C || minVal < map[x][y]) continue;
                    minVal = map[x][y];
                    minX = x;
                    minY = y;
                }

                int[] tmp = find(minX, minY);
                dest[i][j][0] = tmp[0];
                dest[i][j][1] = tmp[1];
            }
        }        

        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                int[] tmp = find(i, j);
                cnt[tmp[0]][tmp[1]]++;
            }
        }

        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                sb.append(cnt[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    public static int[] find(int x, int y) {
        if(dest[x][y][0] == x && dest[x][y][1] == y) return dest[x][y];
        return dest[x][y] = find(dest[x][y][0], dest[x][y][1]);
    }
}