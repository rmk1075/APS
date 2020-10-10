import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N, x, y, d, g;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int[][] map = new int[101][101];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        for(int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());

            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());

            dragon();
        }

        // cal
        int count = 0;
        for(int i = 0; i < 100; i++) {
            for(int j = 0; j < 100; j++) {
                if(map[i][j] == 1 && map[i][j+1] == 1 && map[i+1][j] == 1 && map[i+1][j+1] == 1) count++;
            }
        }
        System.out.println(count);
    }

    public static void dragon() {
        
        ArrayList<Integer> directions = new ArrayList<Integer>();
        directions.add(d);

        for(int i = 1; i <= g; i++) {
            for(int j = directions.size() - 1; 0 <= j; j--) {
                directions.add((directions.get(j) + 1) % 4);
            }
        }

        map[y][x] = 1;
        for(int i = 0; i < directions.size(); i++) {
            x += dx[directions.get(i)];
            y += dy[directions.get(i)];
            map[y][x] = 1;
        }
    }
}