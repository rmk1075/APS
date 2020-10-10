import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Shark {
    int r, c, s, d, z;

    Shark(int r, int c, int s, int d, int z) {
        this.r = r;
        this.c = c;
        this.s = s;
        this.d = d;
        this.z = z;
    }
}

public class Main {
    static int R, C, M;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int[][] map, moved;
    static Queue<Shark> sharks = new LinkedList<Shark>();
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[R][C];

        int r, c, s, d, z;
        for(int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken()) - 1;
            c = Integer.parseInt(st.nextToken()) - 1;
            s = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken()) - 1;
            z = Integer.parseInt(st.nextToken());

            map[r][c] = z;
            sharks.offer(new Shark(r, c, s, d, z));
        }

        int sum = 0;
        for(int j = 0; j < C; j++) {
            for(int i = 0; i < R; i++) {
                if(map[i][j] == 0) continue;

                sum += map[i][j];
                map[i][j] = 0;
                break;
            }

            moved = new int[R][C];
            int size = sharks.size();
            for(int i = 0; i < size; i++) {
                Shark shark = sharks.poll();

                if(map[shark.r][shark.c] != shark.z) continue;

                int x = shark.r;
                int y = shark.c;
                int speed = shark.s;
                int direction = shark.d;
                while(0 < speed--) {
                    x += dx[direction];
                    y += dy[direction];

                    if(x < 0 || y < 0 || R <= x || C <= y) {
                        x -= 2 * dx[direction];
                        y -= 2 * dy[direction];

                        switch(direction) {
                            case 0:
                                direction = 1;
                                break;
                            case 1:
                                direction = 0;
                                break;
                            case 2:
                                direction = 3;
                                break;
                            case 3:
                                direction = 2;
                                break;
                        }
                    }
                }

                if(moved[x][y] < shark.z) {
                    moved[x][y] = shark.z;
                    shark.r = x;
                    shark.c = y;
                    shark.d = direction;
                    
                    sharks.offer(shark);
                }
            }

            map = moved;
        }

        System.out.println(sum);
    }
}