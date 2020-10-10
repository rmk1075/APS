import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    static int N, M, F, srcX, srcY, map[][];
    static int[] dx = {-1, 0, 0, 1}, dy = {0, -1, 1, 0};
    static boolean[][] visited;
    static Location[] guests;
    static List<Location> candidates = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        F = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        visited = new boolean[N][N];
        guests = new Location[M+2];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        srcX = Integer.parseInt(st.nextToken()) - 1;
        srcY = Integer.parseInt(st.nextToken()) - 1;

        for(int i = 2; i < M + 2; i++) {
            st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = i;
            guests[i] = new Location(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
        }

        Queue<Location> queue = new LinkedList<>();
        queue.offer(new Location(srcX, srcY));
        visited[srcX][srcY] = true;

        while(0 < M-- && 0 < F) {
            boolean isFail = true;
            int target = 0, tarX = 0, tarY = 0;
            loop1: while(!queue.isEmpty() && 0 < F) {
                int size = queue.size();
                while(0 < size--) {
                    Location current = queue.poll();
                    if(1 < map[current.x][current.y]) {
                        candidates.add(current);
                        continue;
                    }
                    
                    for(int d = 0; d < 4; d++) {
                        int x = current.x + dx[d], y = current.y + dy[d];
                        if(x < 0 || y < 0 || x == N || y == N || visited[x][y] || map[x][y] == 1) continue;
                        visited[x][y] = true;
                        queue.offer(new Location(x, y));
                    }
                }
                
                if(0 < candidates.size()) {
                    Collections.sort(candidates, new Comparator<Location>() {
                        @Override
                        public int compare(Location o1, Location o2) {
                            if(o1.x == o2.x) return o1.y - o2.y;
                            return o1.x - o2.x;
                        }
                    });

                    Location current = candidates.get(0);
                    candidates.clear();

                    target = map[current.x][current.y];
                    tarX = guests[target].x;
                    tarY = guests[target].y;
                    map[current.x][current.y] = 0;
                    
                    for(int i = 0; i < N; i++) {
                        for(int j = 0; j < N; j++) visited[i][j] = false;
                    }
                    visited[current.x][current.y] = true;
                    
                    queue.clear();
                    queue.offer(current);
                    isFail = false;
                    break loop1;
                }
                F--;
            }

            if(isFail) {
                System.out.println(-1);
                return ;
            }

            isFail = true;
            int fuel = 0;
            loop2: while(!queue.isEmpty() && -1 < F) {
                int size = queue.size();
                while(0 < size--) {
                    Location current = queue.poll();
                    if(current.x == tarX && current.y == tarY) {
                        F += 2 * fuel;
                        target = 0;

                        for(int i = 0; i < N; i++) {
                            for(int j = 0; j < N; j++) visited[i][j] = false;
                        }
                        visited[current.x][current.y] = true;

                        queue.clear();
                        queue.offer(current);
                        isFail = false;
                        break loop2;
                    }

                    for(int d = 0; d < 4; d++) {
                        int x = current.x + dx[d], y = current.y + dy[d];
                        if(x < 0 || y < 0 || x == N || y == N || visited[x][y] || map[x][y] == 1) continue;
                        visited[x][y] = true;
                        queue.offer(new Location(x, y));
                    }
                }
                F--;
                fuel++;
            }

            if(isFail) {
                System.out.println(-1);
                return ;
            }
        }
        System.out.println(F);
    }
}