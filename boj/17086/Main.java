import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, limit = 0, max = 0, map[][], empty[][];
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1}, dy = {-1, 0, 1, -1, 1, -1, 0, 1};
    static boolean[][] visited;
    static Queue<int[]> queue = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        empty = new int[N * M][2];
        visited = new boolean[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0) {
                    empty[limit][0] = i;
                    empty[limit++][1] = j;
                }
            }
        }

        for(int i = 0; i < limit; i++) {
            int time = 1;
            queue.offer(new int[]{empty[i][0], empty[i][1]});
            visited[empty[i][0]][empty[i][1]] = true;
            loop: while(!queue.isEmpty()) {
                int size = queue.size();
                while(0 < size--) {
                    int x, y, current[] = queue.poll();
                    for(int d = 0; d < 8; d++) {
                        x = current[0] + dx[d];
                        y = current[1] + dy[d];
                        if(x < 0 || y < 0 || x == N || y == M || visited[x][y]) continue;
                        if(map[x][y] == 1) {
                            queue.clear();
                            for(int a = 0; a < N; a++) {
                                for(int b = 0; b < M; b++) visited[a][b] = false;
                            }
                            max = Math.max(max, time);
                            break loop;
                        }
                        visited[x][y] = true;
                        queue.offer(new int[]{x, y});
                    }
                }
                time++;
            }
        }
        System.out.println(max);
    }
}

// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.util.LinkedList;
// import java.util.Queue;
// import java.util.StringTokenizer;

// public class Main {
//     static int N, M, max = 0, map[][];
//     static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1}, dy = {-1, 0, 1, -1, 1, -1, 0, 1};
//     static boolean[][] visited;
//     static Queue<int[]> sharks = new LinkedList<>(), queue = new LinkedList<>();
//     public static void main(String[] args) throws IOException {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         StringTokenizer st = new StringTokenizer(br.readLine());
//         N = Integer.parseInt(st.nextToken());
//         M = Integer.parseInt(st.nextToken());
//         map = new int[N][M];
//         visited = new boolean[N][M];
//         for(int i = 0; i < N; i++) {
//             st = new StringTokenizer(br.readLine());
//             for(int j = 0; j < M; j++) {
//                 map[i][j] = Integer.parseInt(st.nextToken());
//                 if(map[i][j] == 0) sharks.offer(new int[]{i, j});
//             }
//         }

//         while(!sharks.isEmpty()) {
//             int time = 1, src[] = sharks.poll();
//             queue.offer(src);
//             visited[src[0]][src[1]] = true;
//             loop: while(!queue.isEmpty()) {
//                 int size = queue.size();
//                 while(0 < size--) {
//                     int x, y, current[] = queue.poll();
//                     for(int d = 0; d < 8; d++) {
//                         x = current[0] + dx[d];
//                         y = current[1] + dy[d];
//                         if(x < 0 || y < 0 || x == N || y == M || visited[x][y]) continue;
//                         if(map[x][y] == 1) {
//                             queue.clear();
//                             for(int i = 0; i < N; i++) {
//                                 for(int j = 0; j < M; j++) visited[i][j] = false;
//                             }
//                             max = Math.max(max, time);
//                             break loop;
//                         }

//                         visited[x][y] = true;
//                         queue.offer(new int[]{x, y});
//                     }
//                 }
//                 time++;
//             }
//         }
// }
//         System.out.println(max);
//     }