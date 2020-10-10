import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
    static int N, M, minCount = Integer.MAX_VALUE;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] map;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        List<Location> bridges = new ArrayList<>();
        bridges.add(new Location(-1, -1));
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(map[i][j] == 0 && check(i, j)) bridges.add(new Location(i, j));
            }
        }

        Queue<Location> queue = new LinkedList<>();
        for(Location bridge : bridges) {
            if(bridge.x != -1) map[bridge.x][bridge.y] = M;

            queue.clear();
            queue.offer(new Location(0, 0));
            visited = new boolean[N][N];
            visited[0][0] = true;

            int size = 0, x = 0, y = 0, count = 0;
            loop:
            while(!queue.isEmpty()) {
                size = queue.size();

                while(0 < size--) {
                    Location current = queue.poll();
                    
                    if(current.x == N-1 && current.y == N-1){
                        minCount = Math.min(minCount, count);
                        break loop;
                    }

                    for(int d = 0; d < 4; d++) {
                        x = current.x + dx[d];
                        y = current.y + dy[d];


                        if(x < 0 || y < 0 || x == N || y == N || map[x][y] == 0 || visited[x][y]) continue;

                        if(map[x][y] == 1) {
                            visited[x][y] = true;
                            queue.offer(new Location(x, y));
                            continue;
                        }

                        if(map[current.x][current.y] != 1) continue;
                        
                        if((count+1) % map[x][y] == 0) {
                            visited[x][y] = true;
                            queue.offer(new Location(x, y));
                        } else queue.offer(current);
                    }
                }
                count++;
            }

            if(bridge.x != -1) map[bridge.x][bridge.y] = 0;
        }

        System.out.println(minCount);
    }

    public static boolean isCliff(int x, int y) {
        if(-1 < x && -1 < y && x < N && y < N && map[x][y] != 1) return true;
        return false;
    }

    public static boolean check(int x, int y) {
        boolean[] cliff = new boolean[4];
        for(int d = 0; d < 4; d++) cliff[d] = isCliff(x + dx[d], y + dy[d]);

        if((cliff[0] && cliff[1]) || (cliff[0] && cliff[3]) || (cliff[2] && cliff[1]) || (cliff[2] && cliff[3])) return false;
        return true;
    }
}