import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Location {
    int x, y, dust;

    Location(int x, int y, int dust) {
        this.x = x;
        this.y = y;
        this.dust = dust;
    }
}

public class Main {
    static int R, C, T;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[] filter = new int[2];
    static int[][] A;
    static Queue<Location> queue = new LinkedList<Location>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        A = new int[R][C];

        int f = 0;
        for(int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < C; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());

                if(A[i][j] == -1) {
                    filter[f++] = i;
                } else if(A[i][j] != 0) {
                    queue.offer(new Location(i, j, A[i][j]));
                }
            }
        }

        for(int t = 0; t < T; t++) {
            // spread
            while(!queue.isEmpty()) {
                Location current = queue.poll();

                int x, y, numOfSpread = 0;
                for(int d = 0; d < 4; d++) {
                    x = current.x + dx[d];
                    y = current.y + dy[d];

                    if(x < 0 || y < 0 || R <= x || C <= y || A[x][y] == -1) continue;

                    A[x][y] += current.dust / 5;
                    numOfSpread++;
                }

                A[current.x][current.y] = A[current.x][current.y]  - (current.dust / 5 * numOfSpread);
            }

            // filter
            // filter upside
            int x = filter[0] - 1;
            int y = 0;

            while(0 <= x - 1) {
                A[x][y] = A[x-1][y];
                x--;
            }

            while(y + 1 <= C - 1) {
                A[x][y] = A[x][y+1];
                y++;
            }

            while(x + 1 <= filter[0]) {
                A[x][y] = A[x+1][y];
                x++;
            }

            while(A[x][y-1] != -1) {
                A[x][y] = A[x][y-1];
                y--;
            }
            
            A[x][y] = 0;

            // filter downside
            x = filter[1] + 1;
            y = 0;

            while(x + 1 <= R - 1) {
                A[x][y] = A[x+1][y];
                x++;
            }

            while(y + 1 <= C - 1) {
                A[x][y] = A[x][y+1];
                y++;
            }

            while(filter[1] <= x - 1) {
                A[x][y] = A[x-1][y];
                x--;
            }

            while(A[x][y-1] != -1) {
                A[x][y] = A[x][y-1];
                y--;
            }
            
            A[x][y] = 0;

            // TODO: how push the dust to the queue
            for(int i = 0; i < R; i++) {
                for(int j = 0; j < C; j++) {
                    if(A[i][j] != -1 && A[i][j] != 0) {
                        queue.offer(new Location(i, j, A[i][j]));
                    }
                }
            }
        }

        int sum = 0;
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(A[i][j] != -1) sum += A[i][j];
            }
        }

        System.out.println(sum);
    }
}