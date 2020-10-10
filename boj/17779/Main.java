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
    static int N, minDiff = Integer.MAX_VALUE;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int[] count;
    static int[][] map, area;
    static Queue<Location> queue = new LinkedList<Location>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N+1][N+1];
        for(int i = 1; i < N+1; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j < N+1; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 1; i < N+1; i++) {
            for(int j = 1; j < N+1; j++) {
                divide(i, j);
            }
        }

        System.out.println(minDiff);
    }

    public static boolean check(int x, int y) {

        int cnt = 0;
        int cnt1 = 0;
        int x_, y_;
        for(int i = 0; i < 4; i++) {
            x_ = x + dx[i];
            y_ = y + dy[i];

            if(x_ < 1 || y_ < 1 || N < x_ || N < y_) {
                cnt++;
                continue;
            }

            if(area[x_][y_] == 1) cnt1++; 
        }

        if(2 < cnt1 || cnt + cnt1 == 4) return true;
        return false;
    }

    public static void divide(int x, int y) {

        for(int d1 = 1; d1 < N; d1++) {

            // check condition
            if(y - d1 < 1) break;

            for(int d2 = 1; d2 < N; d2++) {

                // check condition
                if(N < x + d1 + d2 || N < y + d2) break;

                // divide map
                count = new int[5];
                area = new int[N+1][N+1];
                for(int i = 0; i <= d1; i++) {
                    area[x + i][y - i] = 1;
                    area[x + d2 + i][y + d2 - i] = 1;
                }

                for(int i = 0; i <= d2; i++) {
                    area[x + i][y + i] = 1;
                    area[x + d1 + i][y - d1 + i] = 1;
                }

                queue.offer(new Location(x+1, y));
                area[x+1][y] = 1;
                while(!queue.isEmpty()) {
                    Location current = queue.poll();

                    int x_, y_;
                    for(int i = 0; i < 4; i++) {
                        x_ = current.x + dx[i];
                        y_ = current.y + dy[i];

                        if(x_ < 1 || y_ < 1 || N < x_ || N < y_ || area[x_][y_] == 1) continue;

                        area[x_][y_] = 1;
                        queue.offer(new Location(x_, y_));
                    }
                }

                // count
                for(int i = 1; i < N+1; i++) {
                    for(int j = 1; j < N+1; j++) {
                        if(area[i][j] == 1) {
                            count[4] += map[i][j];
                        } else {

                            if(check(i, j)) {
                                count[4] += map[i][j];
                            } else {

                                if(i < x + d1 && j <= y) {
                                    count[0] += map[i][j];
                                } else if(i <= x + d2 && y < j) {
                                    count[1] += map[i][j];
                                } else if(x + d1 <= i && j < y - d1 + d2) {
                                    count[2] += map[i][j];
                                } else if(x + d2 < i && y - d1 + d2 <= j) {
                                    count[3] += map[i][j];
                                }
                            }
                        }
                    }
                }

                Arrays.sort(count);
                if(count[0] == 0) continue;
                
                // calculate the difference
                minDiff = Math.min(minDiff, count[4] - count[0]);
            }

        }

    }
}