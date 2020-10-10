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
    static int R, C, map[][];
    static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
    static Queue<Location>queue[] = new Queue[2];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        int count = 0;
        for(int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) count++;
            }
        }

        if(count == 0) {
            System.out.println("0\n0");
            return ;
        }

        queue[0] = new LinkedList<>();
        queue[1] = new LinkedList<>();

        queue[0].offer(new Location(0, 0));
        map[0][0] = -1;
        while(!queue[0].isEmpty()) {
            Location current = queue[0].poll();
            int x, y;
            for(int d = 0; d < 4; d++) {
                x = current.x + dx[d];
                y = current.y + dy[d];
                if(x < 0 || y < 0 || x == R || y == C || map[x][y] == -1) continue;
                queue[map[x][y]].offer(new Location(x, y));
                map[x][y] = -1;
            }
        }

        int size, time = 0, lastCount = count;
        while(0 < count) {
            size = queue[1].size();
            lastCount = count;
            count -= size;
            while(0 < size--) dfs(1);
            while(!queue[0].isEmpty()) dfs(0);
            time++;
        }
        System.out.println(time);
        System.out.println(lastCount);
    }

    public static void dfs(int idx) {
        Location current = queue[idx].poll();
        for(int d = 0; d < 4; d++) {
            int x = current.x + dx[d], y = current.y + dy[d];
            if(map[x][y] == -1) continue;
            queue[map[x][y]].offer(new Location(x, y));
            map[x][y] = -1;
        }
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
//     static int R, C, map[][];
//     static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
//     public static void main(String[] args) throws IOException {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         StringTokenizer st = new StringTokenizer(br.readLine());
//         R = Integer.parseInt(st.nextToken());
//         C = Integer.parseInt(st.nextToken());
//         map = new int[R][C];
//         int count = 0;
//         for(int i = 0; i < R; i++) {
//             st = new StringTokenizer(br.readLine());
//             for(int j = 0; j < C; j++) {
//                 map[i][j] = Integer.parseInt(st.nextToken());
//                 if(map[i][j] == 1) count++;
//             }
//         }

//         if(count == 0) {
//             System.out.println("0\n0");
//             return ;
//         }

//         Queue<Location> queue = new LinkedList<>();
//         Queue<Location> list = new LinkedList<>();
//         queue.offer(new Location(0, 0));
//         map[0][0] = -1;
//         while(!queue.isEmpty()) {
//             Location current = queue.poll();
//             int x, y;
//             for(int d = 0; d < 4; d++) {
//                 x = current.x + dx[d];
//                 y = current.y + dy[d];
//                 if(x < 0 || y < 0 || x == R || y == C || map[x][y] == -1) continue;
//                 if(map[x][y] == 1) list.offer(new Location(x, y));
//                 else queue.offer(new Location(x, y));
//                 map[x][y] = -1;
//             }
//         }

//         int size, time = 0, lastCount = count;
//         while(0 < count) {
//             size = list.size();
//             lastCount = count;
//             count -= size;
//             while(0 < size--) {
//                 Location current = list.poll();
//                 for(int d = 0; d < 4; d++) {
//                     int x = current.x + dx[d], y = current.y + dy[d];
//                     if(map[x][y] == -1) continue;
//                     if(map[x][y] == 0) queue.offer(new Location(x, y));
//                     else list.offer(new Location(x, y));
//                     map[x][y] = -1;
//                 }
//             }

//             while(!queue.isEmpty()) {
//                 Location current = queue.poll();
//                 for(int d = 0; d < 4; d++) {
//                     int x = current.x + dx[d], y = current.y + dy[d];
//                     if(map[x][y] == -1) continue;
//                     if(map[x][y] == 0) queue.offer(new Location(x, y));
//                     else list.offer(new Location(x, y));
//                     map[x][y] = -1;
//                 }
//             }
//             time++;
//         }
//         System.out.println(time);
//         System.out.println(lastCount);
//     }
// }