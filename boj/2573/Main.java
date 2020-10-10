import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Location {
    int x, y;

    Location(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int N, M;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] map, group;
    static Queue<Location> queue = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] != 0) queue.offer(new Location(i, j));
            }
        }

        boolean check = false;
        int count = 0;
        while(!queue.isEmpty()) {

            // check is two or over?
            group = new int[N][M];
            Queue<Location> grouping = new LinkedList<>();
            int idx = 0;
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < M; j++) {
                    if(map[i][j] != 0 && group[i][j] == 0) {
                        group[i][j] = ++idx;
                        grouping.offer(new Location(i, j));
                        while(!grouping.isEmpty()) {
                            Location current = grouping.poll();
                            int x, y;
                            for(int d = 0; d < 4; d++) {
                                x = current.x + dx[d];
                                y = current.y + dy[d];

                                if(x < 0 || y < 0 || N == x || M == y || group[x][y] != 0 || map[x][y] == 0) continue;
                                                                
                                group[x][y] = idx;
                                grouping.offer(new Location(x, y));
                            }
                        }
                    }
                }
            }
            if(2 <= idx) {
                check = true;
                break;
            }

            int size = queue.size();
            for(int s = 0; s < size; s++) {
                Location current = queue.poll();

                int x, y, sea = 0;
                for(int d = 0; d < 4; d++) {
                    x = current.x + dx[d];
                    y = current.y + dy[d];

                    if(x < 0 || y < 0 || N == x || M == y || map[x][y] != 0) continue;

                    sea++;
                }

                map[current.x][current.y] -= sea;

                if(map[current.x][current.y] <= 0) {
                    map[current.x][current.y] = -1;
                    continue;
                }

                queue.offer(current);
            }

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < M; j++) {
                    if(map[i][j] < 0) map[i][j] = 0;
                }
            }

            count++;
        }

        if(check) System.out.println(count);
        else System.out.println(0);
    }
}