import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Location {
    int x, y, cnt;

    Location(int x, int y, int cnt) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
    }
}

public class Main {
    static int N, M, K;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static char[][] map;
    static boolean[][][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new boolean[K+1][N][M];

        for(int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        if(N == 1 && M == 1) {
            System.out.println(1);
            System.exit(0);
        }

        visited[0][0][0] = true;
        Queue<Location> queue = new LinkedList<>();
        queue.add(new Location(0, 0, 0));

        int size, count = 1;
        while(!queue.isEmpty()) {
            size = queue.size();
            while(0 < size--) {
                Location current = queue.poll();

                int x, y;
                for(int d = 0; d < 4; d++) {
                    x = current.x + dx[d];
                    y = current.y + dy[d];
    
                    if(x == N-1 && y == M-1) {
                        System.out.println(count+1);
                        System.exit(0);
                    }
    
                    if(x < 0 || y < 0 || N == x || M == y || visited[current.cnt][x][y]) continue;
    
                    if(map[x][y] == '1') {
                        if(current.cnt == K) continue;

                        visited[current.cnt][x][y] = true;
                        queue.add(new Location(x, y, current.cnt+1));
                        continue;
                    }
    
                    visited[current.cnt][x][y] = true;
                    queue.add(new Location(x, y, current.cnt));
                }
            }
            count++;
        }

        System.out.println(-1);
    }
}