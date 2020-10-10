import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Coins {
    int x1, y1, x2, y2;

    Coins(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
}

public class Main {
    static int N, M;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static char[][] map;
    static boolean[][][][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new boolean[N][M][N][M];

        int idx = 0;
        int[][] coin = new int[2][2];
        for(int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
            for(int j = 0; j < M; j++) {
                if(map[i][j] == 'o') {
                    coin[idx][0] = i;
                    coin[idx++][1] = j;
                }
            }
        }

        // drop only one coin
        // bfs
        Queue<Coins> queue = new LinkedList<>();
        queue.add(new Coins(coin[0][0], coin[0][1], coin[1][0], coin[1][1]));
        visited[coin[0][0]][coin[0][1]][coin[1][0]][coin[1][1]] = true;
        
        int count = 1;
        boolean isDrop = false;
        loop:
        while(!queue.isEmpty()) {
            if(10 < count) break;

            int size = queue.size();
            while(0 < size--) {
                Coins current = queue.poll();

                int x1, y1, x2, y2;
                for(int d = 0; d < 4; d++) {
                    x1 = current.x1 + dx[d];
                    y1 = current.y1 + dy[d];
                    x2 = current.x2 + dx[d];
                    y2 = current.y2 + dy[d];
    
                    boolean check1 = x1 < 0 || y1 < 0 || x1 == N || y1 == M;
                    boolean check2 = x2 < 0 || y2 < 0 || x2 == N || y2 == M;
                    if(check1 && check2) continue; // drop both coins
                    else if (check1 || check2) {
                        isDrop = true;
                        break loop; // drop one coin
                    }

                    // wall
                    if(map[x1][y1] == '#') {
                        x1 = current.x1;
                        y1 = current.y1;
                    }

                    if(map[x2][y2] == '#') {
                        x2 = current.x2;
                        y2 = current.y2;
                    }

                    if(visited[x1][y1][x2][y2]) continue;

                    visited[x1][y1][x2][y2] = true;
                    queue.offer(new Coins(x1, y1, x2, y2));
                }
            }
            count++;
        }

        if(isDrop) System.out.println(count);
        else System.out.println(-1);
    }
}