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
    static int N, minDistance = Integer.MAX_VALUE;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] map, group, visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        group = new int[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int count = 1;
        Queue<Location> queue = new LinkedList<>();
        Queue<Location> candidates = new LinkedList<>();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(map[i][j] == 1 && group[i][j] == 0) {
                    group[i][j] = count;
                    queue.offer(new Location(i, j));

                    boolean isEdge = false;
                    while(!queue.isEmpty()) {
                        Location current = queue.poll();

                        int x, y;
                        for(int d = 0; d < 4; d++) {
                            x = current.x + dx[d];
                            y = current.y + dy[d];

                            if(x < 0 || y < 0 || N == x || N == y || group[x][y] != 0) continue;
                            if(map[x][y] == 0) {                                
                                isEdge = true;
                                continue;
                            }

                            group[x][y] = count;
                            queue.offer(new Location(x,y));
                        }
                        if(isEdge) candidates.offer(current);
                    }
                    count++;
                }
            }
        }

        while(!candidates.isEmpty()) {
            Location candidate = candidates.poll();

            visited = new int[N][N];
            visited[candidate.x][candidate.y] = 1;
            queue = new LinkedList<>();
            queue.offer(new Location(candidate.x, candidate.y));
            count = 0;
    
            loop: while(!queue.isEmpty()) {
                int size = queue.size();
                for(int s = 0; s < size; s++) {
                    Location current = queue.poll();
    
                    int x, y;
                    for(int d = 0; d < 4; d++) {
                        x = current.x + dx[d];
                        y = current.y + dy[d];
    
                        if(x < 0 || y < 0 || N == x || N == y || group[x][y] == group[candidate.x][candidate.y] || visited[x][y] == 1) continue;
                        
                        if(map[x][y] == 1) {
                            // minDistance = Math.min(minDistance, count);
                            minDistance = count;
                            break loop;
                        }
    
                        visited[x][y] = 1;
                        queue.offer(new Location(x, y));
                    }
                }
    
                count++;
                if (minDistance <= count) break;
            }
        }
        System.out.println(minDistance);
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
//     static int N, minDistance = Integer.MAX_VALUE;
//     static int[] dx = {-1, 0, 1, 0};
//     static int[] dy = {0, 1, 0, -1};
//     static int[][] map, group, visited;
//     public static void main(String[] args) throws IOException {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         StringTokenizer st;

//         N = Integer.parseInt(br.readLine());
//         map = new int[N][N];
//         group = new int[N][N];
//         for(int i = 0; i < N; i++) {
//             st = new StringTokenizer(br.readLine());

//             for(int j = 0; j < N; j++) {
//                 map[i][j] = Integer.parseInt(st.nextToken());
//             }
//         }

//         // grouping
//         int count = 1;
//         Queue<Location> queue = new LinkedList<>();
//         for(int i = 0; i < N; i++) {
//             for(int j = 0; j < N; j++) {
//                 if(map[i][j] == 1 && group[i][j] == 0) {
//                     group[i][j] = count;
//                     queue.offer(new Location(i, j));

//                     while(!queue.isEmpty()) {
//                         Location current = queue.poll();

//                         int x, y;
//                         for(int d = 0; d < 4; d++) {
//                             x = current.x + dx[d];
//                             y = current.y + dy[d];

//                             if(x < 0 || y < 0 || N == x || N == y || map[x][y] == 0 || group[x][y] != 0) continue;

//                             group[x][y] = count;
//                             queue.offer(new Location(x,y));
//                         }
//                     }

//                     count++;
//                 }
//             }
//         }

//         for(int i = 0; i < N; i++) {
//             for(int j = 0; j < N; j++) {
//                 if(map[i][j] == 1) {
//                     visited = new int[N][N];
//                     visited[i][j] = 1;
//                     queue = new LinkedList<>();
//                     queue.offer(new Location(i, j));
//                     count = 0;

//                     loop:
//                     while(!queue.isEmpty()) {

//                         if(minDistance <= count) break;

//                         int size = queue.size();
//                         for(int s = 0; s < size; s++) {
//                             Location current = queue.poll();

//                             int x, y;
//                             for(int d = 0; d < 4; d++) {
//                                 x = current.x + dx[d];
//                                 y = current.y + dy[d];

//                                 if(x < 0 || y < 0 || N == x || N == y || group[x][y] == group[i][j] || visited[x][y] == 1) continue;
                                
//                                 if(map[x][y] == 1) {
//                                     minDistance = Math.min(minDistance, count);
//                                     break loop;
//                                 }

//                                 visited[x][y] = 1;
//                                 queue.offer(new Location(x, y));
//                             }
//                         }

//                         count++;
//                     }
//                 }
//             }
//         }

//         System.out.println(minDistance);
//     }
// }