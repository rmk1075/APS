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
    static int T, H, W;
    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, 1, 0, -1 };
    static char[][] map;
    static int[][][] visited;
    static Queue<Location>[] queue = new Queue[3];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        while (0 < T--) {
            for(int i = 0; i < 3; i++) queue[i] = new LinkedList<>();

            st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            map = new char[H + 2][W + 2];
            visited = new int[3][H + 2][W + 2];
            for (int i = 0; i < H + 2; i++) {
                Arrays.fill(map[i], '.');
                for (int j = 0; j < 3; j++)
                    Arrays.fill(visited[j][i], Integer.MAX_VALUE);
            }

            int idx = 0;
            for (int i = 1; i <= H; i++) {
                int j = 1;
                for (char ch : br.readLine().toCharArray()) {
                    map[i][j] = ch;
                    if (map[i][j] == '$') {
                        queue[idx].offer(new Location(i, j));
                        visited[idx++][i][j] = 0;
                    }
                    j++;
                }
            }

            queue[2].offer(new Location(0, 0));
            visited[2][0][0] = 0;
            for(int i = 0; i < 3; i++) bfs(i);

            // sum - find minimum cnt
            int temp, ans = Integer.MAX_VALUE;
            for (int i = 0; i < H + 2; i++) {
                for (int j = 0; j < W + 2; j++) {
                    if (map[i][j] == '*' || visited[0][i][j] == Integer.MAX_VALUE || visited[1][i][j] == Integer.MAX_VALUE || visited[2][i][j] == Integer.MAX_VALUE) continue;
                    
                    temp = visited[0][i][j] + visited[1][i][j] + visited[2][i][j];
                    if (map[i][j] == '#') temp -= 2;

                    ans = Math.min(ans, temp);
                }
            }

            // TODO:
            // for(int i = 0; i < 3; i++) {
            //     for(int j = 0; j < visited[i].length; j++) {
            //         for(int k = 0; k < visited[i][j].length; k++) {
            //             System.out.print((visited[i][j][k] == Integer.MAX_VALUE) ? -1 + "\t" : visited[i][j][k] + "\t");
            //         }
            //         System.out.println();
            //     }
            //     System.out.println();
            // }

            System.out.println(ans);
        }
    }

    public static void bfs(int index) {
        int x, y;
        while (!queue[index].isEmpty()) {
            Location current = queue[index].poll();

            for (int d = 0; d < 4; d++) {
                x = current.x + dx[d];
                y = current.y + dy[d];

                // break or wall
                if (x < 0 || y < 0 || x == H + 2 || y == W + 2 || map[x][y] == '*')
                    continue;

                // door
                if (map[x][y] == '#') {
                    if (visited[index][x][y] <= visited[index][current.x][current.y] + 1) continue;

                    visited[index][x][y] = visited[index][current.x][current.y] + 1;
                    queue[index].offer(new Location(x, y));
                    continue;
                }

                // empty road
                if (visited[index][x][y] <= visited[index][current.x][current.y]) continue;

                visited[index][x][y] = visited[index][current.x][current.y];
                queue[index].offer(new Location(x, y));
            }
        }
    }
}

// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.util.Arrays;
// import java.util.LinkedList;
// import java.util.Queue;
// import java.util.StringTokenizer;

// class Location {
//     int x, y, id, cnt = 0;

//     Location(int x, int y, int id) {
//         this.x = x;
//         this.y = y;
//         this.id = id;
//     }

//     Location(int x, int y, int id, int cnt) {
//         this.x = x;
//         this.y = y;
//         this.id = id;
//         this.cnt = cnt;
//     }
// }

// public class Main {
//     static int T, H, W;
//     static int[] dx = {-1, 0, 1, 0};
//     static int[] dy = {0, 1, 0, -1};
//     static char[][] map;
//     static int[][][] visited;
    
//     public static void main(String[] args) throws IOException {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         StringTokenizer st;

//         T = Integer.parseInt(br.readLine());
//         while(0 < T--) {
//             st = new StringTokenizer(br.readLine());
//             H = Integer.parseInt(st.nextToken());
//             W = Integer.parseInt(st.nextToken());
//             map = new char[H+2][W+2];
//             visited = new int[3][H+2][W+2];
//             for(int i = 0; i < H+2; i++) {
//                 Arrays.fill(map[i], '.');
//                 for(int j = 0; j < 3; j++) Arrays.fill(visited[j][i], Integer.MAX_VALUE);
//             }

            
//             Queue<Location> queue = new LinkedList<>();
//             int idx = 0;
//             for(int i = 1; i <= H; i++) {
//                 int j = 1;
//                 for(char ch : br.readLine().toCharArray()) {
//                     map[i][j] = ch;
//                     if(map[i][j] == '$') {
//                         queue.offer(new Location(i, j, idx));
//                         visited[idx++][i][j] = 0;
//                     }
//                     j++;
//                 }
//             }

//             queue.offer(new Location(0, 0, 2));
//             int x, y;
//             while(!queue.isEmpty()) {
//                 Location current = queue.poll();

//                 for(int d = 0; d < 4; d++) {
//                     x = current.x + dx[d];
//                     y = current.y + dy[d];

//                     // break or wall
//                     if(x < 0 || y < 0 || x == H+2 || y == W+2 || map[x][y] == '*') continue;

//                     // door
//                     if(map[x][y] == '#') {
//                         if(visited[current.id][x][y] <= current.cnt + 1) continue;

//                         visited[current.id][x][y] = current.cnt + 1;
//                         queue.offer(new Location(x, y, current.id, current.cnt + 1));
//                         continue;
//                     }

//                     // empty road
//                     if(visited[current.id][x][y] <= current.cnt) continue;
                    
//                     visited[current.id][x][y] = current.cnt;
//                     queue.offer(new Location(x, y, current.id, current.cnt));
//                 }
//             }

//             // sum - find minimum cnt
//             int temp, ans = Integer.MAX_VALUE;
//             for(int i = 0; i < H+2; i++) {
//                 for(int j = 0; j < W+2; j++) {
//                     if(map[i][j] == '*') continue;

//                     temp = visited[0][i][j] + visited[1][i][j] + visited[2][i][j];
//                     if(map[i][j] == '#') temp -= 2;

//                     ans = Math.min(ans, temp);
//                 }
//             }

//             System.out.println(ans);
//         }
//     }
// }