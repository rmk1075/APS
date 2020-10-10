import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
    static int N, M, H, W, SR, SC, FR, FC;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        SR = Integer.parseInt(st.nextToken())-1;
        SC = Integer.parseInt(st.nextToken())-1;
        FR = Integer.parseInt(st.nextToken())-1;
        FC = Integer.parseInt(st.nextToken())-1;

        if(SR == FR && SC == FC) {
            System.out.println(0);
            System.exit(0);
        }

        boolean[][] visited = new boolean[N][M];
        visited[SR][SC] = true;

        Queue<Location> queue = new LinkedList<>();
        queue.offer(new Location(SR, SC));

        int size, count = 1;
        while(!queue.isEmpty()) {
            size = queue.size();
            while(0 < size--) {
                Location current = queue.poll();

                for(int d = 0; d < 4; d++) {
                    int x = current.x + dx[d], y = current.y + dy[d];
                    if(x < 0 || y < 0 || N < x + H || M < y + W || visited[x][y] || !check(current.x, current.y, d)) continue;
                    if(x == FR && y == FC) {
                        System.out.println(count);
                        System.exit(0);
                    }

                    visited[x][y] = true;
                    queue.offer(new Location(x, y));
                }
            }
            count++;
        }
        System.out.println(-1);
    }

    public static boolean check(int x, int y, int d) {
        switch(d) {
            case 0:
                int x_ = x + dx[d];
                for(int i = y; i < y + W; i++) {
                    if(map[x_][i] == 1) return false;
                }
                break;
            case 1:
                int y_ = y + W;
                for(int i = x; i < x + H; i++) {
                    if(map[i][y_] == 1) return false;
                }
                break;
            case 2:
                x_ = x + H;
                for(int i = y; i < y + W; i++) {
                    if(map[x_][i] == 1) return false;
                }
                break;
            case 3:
                y_ = y + dy[d];
                for(int i = x; i < x + H; i++) {
                    if(map[i][y_] == 1) return false;
                }
                break;
        }
        return true;
    }
}

// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.util.LinkedList;
// import java.util.Queue;
// import java.util.StringTokenizer;

// class Location {
//     int x, y;

//     Location(int x, int y) {
//         this.x = x;
//         this.y = y;
//     }
// }

// public class Main {
//     static int N, M, H, W, SR, SC, FR, FC;
//     static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
//     static int[][] map;
//     public static void main(String[] args) throws IOException {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         StringTokenizer st = new StringTokenizer(br.readLine());
//         N = Integer.parseInt(st.nextToken());
//         M = Integer.parseInt(st.nextToken());
//         map = new int[N][M];

//         for(int i = 0; i < N; i++) {
//             st = new StringTokenizer(br.readLine());
//             for(int j = 0; j < M; j++) map[i][j] = Integer.parseInt(st.nextToken());
//         }

//         st = new StringTokenizer(br.readLine());
//         H = Integer.parseInt(st.nextToken());
//         W = Integer.parseInt(st.nextToken());
//         SR = Integer.parseInt(st.nextToken())-1;
//         SC = Integer.parseInt(st.nextToken())-1;
//         FR = Integer.parseInt(st.nextToken())-1;
//         FC = Integer.parseInt(st.nextToken())-1;

//         if(SR == FR && SC == FC) {
//             System.out.println(0);
//             System.exit(0);
//         }

//         boolean[][] visited = new boolean[N][M];
//         visited[SR][SC] = true;

//         Queue<Location> queue = new LinkedList<>();
//         queue.offer(new Location(SR, SC));

//         int size, count = 1;
//         while(!queue.isEmpty()) {
//             size = queue.size();
//             while(0 < size--) {
//                 Location current = queue.poll();

//                 for(int d = 0; d < 4; d++) {
//                     int x = current.x + dx[d], y = current.y + dy[d];
//                     if(x < 0 || y < 0 || N < x + H || M < y + W || visited[x][y] || !check(x, y)) continue;
//                     if(x == FR && y == FC) {
//                         System.out.println(count);
//                         System.exit(0);
//                     }

//                     visited[x][y] = true;
//                     queue.offer(new Location(x, y));
//                 }
//             }
//             count++;
//         }
//         System.out.println(-1);
//     }

//     public static boolean check(int x, int y) {
//         for(int i = x; i < x + H; i++) {
//             for(int j = y; j < y + W; j++) {
//                 if(map[i][j] == 1) return false;
//             }
//         }

//         return true;
//     }
// }