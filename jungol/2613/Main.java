import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Tomato {
    int x, y;

    Tomato(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int N, M;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] tomatoes;
    static Queue<Tomato> queue = new LinkedList<Tomato>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        tomatoes = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                tomatoes[i][j] = Integer.parseInt(st.nextToken());
                if(tomatoes[i][j] == 1) {
                    queue.add(new Tomato(i, j));
                }
            }
        }

        Tomato tomato;
        // boolean check = false;
        int x, y, size, count = 0;
        while(!queue.isEmpty()) {

            // if(check()) {
            //     check = true;
            //     break;
            // }

            size = queue.size();
            for(int s = 0; s < size; s++) {
                tomato = queue.poll();

                for(int d = 0; d < 4; d++) {
                    x = tomato.x + dx[d];
                    y = tomato.y + dy[d];

                    if(x < 0 || y < 0 || N <= x || M <= y || tomatoes[x][y] != 0) continue;

                    tomatoes[x][y] = 1;
                    queue.add(new Tomato(x, y));
                }
            }

            count++;
        }

        if(check()) {
            if(count == 0) System.out.println(0);
            else System.out.println(count - 1);
        }
        else System.out.println(-1);
    }

    public static boolean check() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(tomatoes[i][j] == 0) return false;
            }
        }

        return true;
    }
}