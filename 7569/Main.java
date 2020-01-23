import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.LinkedList;
import java.util.Queue;

class Tomato {
    int x, y, z;

    Tomato(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}

public class Main {
    static int H, M, N;
    static int days = 0;
    static int[] dx = { 1, 0, -1, 0, 0, 0 };
    static int[] dy = { 0, 1, 0, -1, 0, 0 };
    static int[] dz = { 0, 0, 0, 0, 1, -1 };
    static int[][][] tomatoes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        Queue<Tomato> q = new LinkedList<Tomato>();

        tomatoes = new int[H][N][M];
        for(int i = 0; i < H; i++) {
            for(int j = 0; j < N; j++) {
                StringTokenizer st2 = new StringTokenizer(br.readLine());
                for(int k = 0; k < M; k++) {
                    tomatoes[i][j][k] = Integer.parseInt(st2.nextToken());
                    if(tomatoes[i][j][k] == 1) {
                        q.add(new Tomato(i, j, k));
                    }
                }
            }
        }

        while (!q.isEmpty()) {
            int size = q.size();
            for (int one = 0; one < size; one++) {
                Tomato tomato = q.poll();
                for (int i = 0; i < 6; i++) {
                    int x_ = tomato.x + dx[i];
                    int y_ = tomato.y + dy[i];
                    int z_ = tomato.z + dz[i];

                    if (x_ < 0 || H <= x_ || y_ < 0 || N <= y_ || z_ < 0 || M <= z_)
                        continue;

                    if (tomatoes[x_][y_][z_] == 0) {
                        tomatoes[x_][y_][z_] = 1;
                        q.add(new Tomato(x_, y_, z_));
                    }

                }
            }

            days++;
        }

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for(int k = 0; k < M; k++) {
                    if(tomatoes[i][j][k] == 0) {
                        System.out.println(-1);
                        System.exit(0);
                    }
                }
            }
        }

        System.out.println(days - 1);
    }
}