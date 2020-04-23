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
    static int N, L, R, ans = 0, map[][], visited[][];
    static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
    static Queue<Location> queue = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        visited = new int[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }

        boolean isUpdated;
        while(true) {
            isUpdated = false;
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) visited[i][j] = -1;
            }

            int cnt = 0;
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(visited[i][j] != -1) continue;
                    visited[i][j] = cnt;
                    if(bfs(i, j, cnt++)) isUpdated = true;
                }
            }

            if(!isUpdated) break;
            ans++;
        }

        System.out.println(ans);
    }

    public static boolean bfs(int srcX, int srcY, int cnt) {
        int sum = map[srcX][srcY], count = 1;
        queue.offer(new Location(srcX, srcY));
        while(!queue.isEmpty()) {
            Location current = queue.poll();
            int x, y;
            for(int d = 0; d < 4; d++) {
                x = current.x + dx[d];
                y = current.y + dy[d];
                if(x < 0 || y < 0 || x == N || y == N || visited[x][y] != -1 || Math.abs(map[current.x][current.y] - map[x][y]) < L || R < Math.abs(map[current.x][current.y] - map[x][y])) continue;
                visited[x][y] = cnt;
                count++;
                sum += map[x][y];
                queue.offer(new Location(x, y));
            }
        }

        if(count == 1) return false;
        int val = sum / count;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(visited[i][j] == cnt) map[i][j] = val;
            }
        }
        return true;
    }
}


// import java.io.BufferedReader;
// import java.io.InputStreamReader;
// import java.util.LinkedList;
// import java.util.Queue;
// import java.util.StringTokenizer;
// import java.io.IOException;

// class Location {
//     int x, y;

//     Location(int x, int y) {
//         this.x = x;
//         this.y = y;
//     }
// }

// public class Main {
//     static int N, L, R;
//     static int[] dx = {-1, 0, 1, 0};
//     static int[] dy = {0, 1, 0, -1};
//     static int[][] nations, visited;
//     static Queue<Location> queue;
//     public static void main(String[] args) throws IOException {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         StringTokenizer st = new StringTokenizer(br.readLine());

//         N = Integer.parseInt(st.nextToken());
//         L= Integer.parseInt(st.nextToken());
//         R = Integer.parseInt(st.nextToken());

//         nations = new int[N][N];
//         for(int i = 0; i < N; i++) {
//             st = new StringTokenizer(br.readLine());
//             for(int j = 0; j < N; j++) {
//                 nations[i][j] = Integer.parseInt(st.nextToken());
//             }
//         }

//         int count = 1;
//         while(true) {
//             visited = new int[N][N];
//             boolean isMove = false;

//             for(int i = 0; i < N; i++) {
//                 for(int j = 0; j < N; j++) {
//                     if(visited[i][j] == -1) continue;
//                     if(bfs(i, j, count)) isMove = true;
//                 }
//             }

//             if(!isMove) break;
//             count++;
//         }

//         System.out.println(count - 1);
//     }

//     public static boolean bfs(int x, int y, int count) {
//         int sumOfPopulation = 0, numOfNations = 0;
//         queue = new LinkedList<Location>();

//         queue.offer(new Location(x, y));
//         visited[x][y] = count;
//         while(!queue.isEmpty()) {
//             Location current = queue.poll();
//             sumOfPopulation += nations[current.x][current.y];
//             numOfNations++;

//             int x_, y_;
//             for(int d = 0; d < 4; d++) {
//                 x_ = current.x + dx[d];
//                 y_ = current.y + dy[d];

//                 if(x_ < 0 || y_ < 0 || N <= x_ || N <= y_ || visited[x_][y_] == count || visited[x_][y_] == -1 || Math.abs(nations[current.x][current.y] - nations[x_][y_]) < L || R < Math.abs(nations[current.x][current.y] - nations[x_][y_])) continue;

//                 queue.offer(new Location(x_, y_));
//                 visited[x_][y_] = count;
//             }
//         }

//         if(numOfNations == 1) {
//             visited[x][y] = 0;
//             return false; 
//         }

//         for(int i = 0; i < N; i++) {
//             for(int j = 0; j < N; j++) {
//                 if(visited[i][j] == count) {
//                     visited[i][j] = -1;
//                     nations[i][j] = sumOfPopulation / numOfNations;
//                 }
//             }
//         }

//         return true;
//     }
// }