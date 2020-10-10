// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.util.LinkedList;
// import java.util.Queue;
// import java.util.StringTokenizer;

// public class Main {
//     static int N, M, K, INF = Integer.MAX_VALUE;
//     static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
//     static char[][] map;
//     static boolean[][][] visited;
//     public static void main(String[] args) throws IOException {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         StringTokenizer st = new StringTokenizer(br.readLine());
//         N = Integer.parseInt(st.nextToken());
//         M = Integer.parseInt(st.nextToken());
//         K = Integer.parseInt(st.nextToken());

//         if(N == 1 && M == 1) {
//             System.out.println(1);
//             return ;
//         }

//         visited = new boolean[K+1][N][M];
//         map = new char[N][M];
//         for(int i = 0; i < N; i++) map[i] = br.readLine().toCharArray();

//         Queue<int[]> queue = new LinkedList<>();
//         queue.offer(new int[] {0, 0, 0});
//         visited[0][0][0] = true;

//         int size, x, y, ans = 1, status = 0, destX = N-1, destY = M-1;
//         boolean isArrived = false;
//         loop: while(!queue.isEmpty()) {
//             size = queue.size();
//             while(0 < size--) {
//                 int[] current = queue.poll();
//                 boolean temp = false;
//                 for(int d = 0; d < 4; d++) {
//                     x = current[0] + dx[d];
//                     y = current[1] + dy[d];
//                     if(x < 0 || y < 0 || x == N || y == M || visited[current[2]][x][y]) continue;
//                     if(x == destX && y == destY) {
//                         isArrived = true;
//                         ans++;
//                         break loop;
//                     }

//                     if(map[x][y] == '1') {
//                         if(status == 0 && current[2] < K) {
//                             visited[current[2]][x][y] = true;
//                             queue.offer(new int[] {x, y, current[2] + 1});
//                         } else temp = true;
//                     } else {
//                         visited[current[2]][x][y] = true;
//                         queue.offer(new int[] {x, y, current[2]});
//                     }
//                 }
//                 if(status == 1 && current[2] < K && temp) queue.offer(current);
//             }
//             status = (status == 1) ? 0 : 1;
//             ans++;
//         }
//         System.out.println((isArrived) ? ans : -1);
//     }
// }

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Location {
    int x, y, wall;
    
    Location(int x, int y, int wall) {
        this.x = x;
        this.y = y;
        this.wall = wall;
    }
}

public class Main {
    static int N, M, K, INF = Integer.MAX_VALUE;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
    static char[][] map;
    static boolean[][][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if(N == 1 && M == 1) {
            System.out.println(1);
            return ;
        }

        visited = new boolean[K+1][N][M];
        map = new char[N][M];
        for(int i = 0; i < N; i++) map[i] = br.readLine().toCharArray();

        Queue<Location> queue = new LinkedList<>();
        queue.offer(new Location(0, 0, 0));
        visited[0][0][0] = true;

        int size, x, y, ans = 1, status = 0, destX = N-1, destY = M-1;
        boolean isArrived = false;
        loop: while(!queue.isEmpty()) {
            size = queue.size();
            while(0 < size--) {
                Location current = queue.poll();
                boolean temp = false;
                for(int d = 0; d < 4; d++) {
                    x = current.x + dx[d];
                    y = current.y + dy[d];
                    if(x < 0 || y < 0 || x == N || y == M || visited[current.wall][x][y]) continue;
                    if(x == destX && y == destY) {
                        isArrived = true;
                        ans++;
                        break loop;
                    }

                    if(map[x][y] == '1') {
                        if(status == 0 && current.wall < K) {
                            visited[current.wall][x][y] = true;
                            queue.offer(new Location(x, y, current.wall + 1));
                        } else temp = true;
                    } else {
                        visited[current.wall][x][y] = true;
                        queue.offer(new Location(x, y, current.wall));
                    }
                }
                if(status == 1 && current.wall < K && temp) queue.offer(current);
            }
            status = (status == 1) ? 0 : 1;
            ans++;
        }
        System.out.println((isArrived) ? ans : -1);
    }
}