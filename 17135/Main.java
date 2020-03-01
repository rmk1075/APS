import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Location{
    int x, y;

    Location(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int N, M, D, maxKill = 0;
    static int[] archers = new int[3];
    static int[][] map;
    static List<Location> enemies = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) enemies.add(new Location(i, j));
            }
        }

        // if no enemy
        if(enemies.size() == 0) {
            System.out.println(0);
            System.exit(0);
        }

        find(0, 0);
        System.out.println(maxKill);
    }

    public static void simulation() {
        int kill = 0;
        boolean[][] killed = new boolean[N][M];

        Queue<Location> queue = new LinkedList<>();
        for(Location e : enemies) queue.offer(new Location(e.x, e.y));

        Location[] targets = {new Location(-1, -1), new Location(-1, -1), new Location(-1, -1)};
        while(!queue.isEmpty()) {            
            // shoot arrow
            for(int i = 0; i < 3; i++) {
                targets[i].x = -1;
                targets[i].y = -1;
            }
            int size = queue.size();
            while(0 < size--) {
                Location current = queue.poll();

                // each archer (N, archer[i])
                for(int i = 0; i < 3; i++) {
                    int distance = N - current.x + Math.abs(archers[i] - current.y);
                    if(D < distance) continue;
                    if((N - targets[i].x + Math.abs(archers[i] - targets[i].y)) < distance) continue;

                    if((N - targets[i].x + Math.abs(archers[i] - targets[i].y)) == distance) {
                        if(targets[i].y < current.y) continue;
                    }

                    targets[i].x = current.x;
                    targets[i].y = current.y;
                }

                queue.offer(current);
            }

            // check to the killed
            for(Location target : targets) {
                if(target.x < 0 || target.y < 0) continue;

                killed[target.x][target.y] = true;
            }

            // move enemy - check killed and reset the array, killed
            size = queue.size();
            while(0 < size--) {
                Location current = queue.poll();

                // killed enemy
                if(killed[current.x][current.y]) {
                    kill++;
                    killed[current.x][current.y] = false;
                    continue;
                }

                // move
                current.x++;
                if(current.x == N) continue; // if arrived to castle

                queue.offer(current);
            }
        }
        maxKill = Math.max(maxKill, kill);
        // return kill;
    }

    public static void find(int index, int count) {
        if(count == 3) {
            // simulation
            simulation();
            // maxKill = Math.max(maxKill, simulation());
            return ;
        }

        if(index == M) return ;

        for(int i = index; i < M; i++) {
            archers[count] = i;
            find(i+1, count+1);
        }
    }
}


// import java.io.BufferedReader;
// import java.io.InputStreamReader;
// import java.util.StringTokenizer;
// import java.io.IOException;

// public class Main {
//     static int N, M, D, maxKill;
//     static int[] archers = new int[3];
//     static int[][] map;
//     public static void main(String[] args) throws IOException {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         StringTokenizer st = new StringTokenizer(br.readLine());
//         N = Integer.parseInt(st.nextToken());
//         M = Integer.parseInt(st.nextToken());
//         D = Integer.parseInt(st.nextToken());
//         map = new int[N][M];
//         maxKill = 0;

//         for(int i = 0; i < N; i++) {
//             st = new StringTokenizer(br.readLine());
//             for(int j = 0; j < M; j++) {
//                 map[i][j] = Integer.parseInt(st.nextToken());
//             }
//         }

//         combination(0, 0);
//         System.out.println(maxKill);
//     }

//     public static void combination(int idx, int count) {
//         if(count == 3) {
//             maxKill = Math.max(maxKill, play());
//             return ;
//         }

//         for(int i = idx; i < M; i++) {
//             archers[count] = i;
//             combination(i + 1, count + 1);
//         }
//     }

//     public static int play() {
//         int[][] field = new int[N][M];
//         for(int i = 0; i < N; i++) {
//             for(int j = 0; j < M; j++) {
//                 field[i][j] = map[i][j];
//             }
//         }

//         int result = 0;
//         while(!isClear(field)) {
//             for(int i = 0; i < 3; i++) {
//                 int x = N;
//                 int y = archers[i];

//                 int enemyX = -1;
//                 int enemyY = -1;
//                 int minDistance = D + 1;
//                 for(int r = 0; r < N; r++) {
//                     for(int c = 0; c < M; c++) {
//                         if(field[r][c] != 0 && Math.abs(x - r) + Math.abs(y - c) <= minDistance) {
//                             if(minDistance == Math.abs(x - r) + Math.abs(y - c) && enemyY < c) continue;

//                             enemyX = r;
//                             enemyY = c;
//                             minDistance = Math.abs(x - r) + Math.abs(y - c);
//                         }
//                     }
//                 }
//                 if(enemyX == -1 && enemyY == -1) continue;

//                 if(field[enemyX][enemyY] == 1) {
//                     result++;
//                     field[enemyX][enemyY] = 2;
//                 }
//             }
//             field = change(field);
//         }
//         return result;
//     }

//     public static boolean isClear(int[][] field) {
//         for(int i = 0; i < field.length; i++) {
//             for(int j = 0; j < field[i].length; j++) {
//                 if(field[i][j] == 1) return false;
//             }
//         }
//         return true;
//     }

//     public static int[][] change(int[][] field) {
//         for(int i = field.length - 1; 0 < i; i--) {
//             for(int j = 0; j < field[i].length; j++) {
//                 field[i][j] = field[i-1][j];
//                 if(field[i][j] == 2) field[i][j] = 0;
//             }
//         }

//         for(int i = 0; i < field[0].length; i++) {
//             field[0][i] = 0;
//         }
//         return field;
//     }
// }