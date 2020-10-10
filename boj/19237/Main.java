import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Shark {
    int x, y, d; // death = -1
    int[][] ds = new int[4][4];

    public Shark(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int N, M, K, map[][][];
    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
    static Shark[] sharks;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        map = new int[N][N][3]; // [0]: 0 or shark no, [1]: time, [2]: 0 - was empty, 1 - was smelled
        sharks = new Shark[M + 1];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                int no = Integer.parseInt(st.nextToken());
                map[i][j][0] = no;
                if(0 < no) {
                    sharks[no] = new Shark(i, j);
                    map[i][j][1] = K;
                    map[i][j][2] = 1;
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i < M + 1; i++) sharks[i].d = Integer.parseInt(st.nextToken()) - 1;
        for(int i = 1; i < M + 1; i++) {
            for(int j = 0; j < 4; j++) {
                st = new StringTokenizer(br.readLine());
                for(int k = 0; k < 4; k++) {
                    sharks[i].ds[j][k] = Integer.parseInt(st.nextToken()) - 1;
                }
            }
        }

        int cnt = M, time = 0;
        while(1 < cnt && time < 1001) {
            // move sharks
           loop:  for(int i = 1; i < M + 1; i++) {
                int d = sharks[i].d;
                if(d == -1) continue;

                // empty
                for(int j = 0; j < 4; j++) {
                    int x = sharks[i].x + dx[sharks[i].ds[d][j]], y = sharks[i].y + dy[sharks[i].ds[d][j]];
                    if(x < 0 || y < 0 || x == N || y == N || map[x][y][2] == 1) continue;
                    if(map[x][y][0] != 0) {
                        // shark
                        if(map[x][y][1] == K + 1) {
                            // die
                            sharks[i].d = -1;
                            cnt--;
                            continue loop;
                        } else continue;
                    }

                    map[x][y][0] = i;
                    map[x][y][1] = K + 1;
                    
                    sharks[i].x = x;
                    sharks[i].y = y;
                    sharks[i].d = sharks[i].ds[d][j];

                    continue loop;
                }

                // find back
                for(int j = 0; j < 4; j++) {
                    int x = sharks[i].x + dx[sharks[i].ds[d][j]], y = sharks[i].y + dy[sharks[i].ds[d][j]];
                    if(x < 0 || y < 0 || x == N || y == N || map[x][y][0] != i) continue;
                    map[x][y][1] = K + 1;
                    sharks[i].x = x;
                    sharks[i].y = y;
                    sharks[i].d = sharks[i].ds[d][j];

                    continue loop;
                }
            }

            // check smells
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(map[i][j][0] != 0) {
                        map[i][j][1]--;
                        if(map[i][j][1] == 0) {
                            map[i][j][0] = 0;
                            map[i][j][2] = 0;
                        } else map[i][j][2] = 1;
                    }
                }
            }
            time++;
        }
        System.out.println((time == 1001) ? -1 : time);
    }
}