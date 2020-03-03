import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Location {
    int x, y, d, cnt;

    Location(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    Location(int x, int y, int d, int cnt) {
        this.x = x;
        this.y = y;
        this.d = d;
        this.cnt = cnt;
    }
}

class Edge {
    int src, dest, distance;

    Edge(int src, int dest, int distance) {
        this.src = src;
        this.dest = dest;
        this.distance = distance;
    }
}

public class Main {
    static int N, M, numOfIslands, minDistance = Integer.MAX_VALUE;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
    static int[][] map, graph, edges;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // grouping
        numOfIslands = island();
        graph = new int[numOfIslands][numOfIslands];
        for(int i = 0; i < numOfIslands; i++) {
            Arrays.fill(graph[i], Integer.MAX_VALUE);
        }

        // find every bridge
        build();

        // pick bridges
        edges = new int[numOfIslands][numOfIslands];
        dfs(0, 0, 0);

        if(minDistance == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(minDistance);
    }

    public static boolean bfs() {
        boolean[] visited = new boolean[numOfIslands];
        visited[0] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);

        while(!queue.isEmpty()) {
            int current = queue.poll();
            for(int i = 0; i < numOfIslands; i++) {
                if(edges[current][i] == 0 || visited[i]) continue;

                visited[i] = true;
                queue.offer(i);
            }
        }

        for(boolean v : visited) if(!v) return false;
        return true;
    }

    public static void dfs(int x, int y, int sum) {
        if(minDistance < sum) return ;

        if(x == numOfIslands) {
            if(!bfs()) return ;

            minDistance = sum;
            return;
        }

        int x_, y_;
        if(y + 1 == numOfIslands) {
            x_ = x+1;
            y_ = 0;
        } else {
            x_ = x;
            y_ = y+1;
        }

        dfs(x_, y_, sum);

        if(graph[x][y] == Integer.MAX_VALUE) return ;
        edges[x][y] = graph[x][y];
        dfs(x_, y_, sum + graph[x][y]);
        edges[x][y] = 0;
    }

    public static void build() {
        int[][] checked = new int[N][M];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] == 0 || (15 <= checked[i][j])) continue;

                int src = map[i][j];
                Queue<Location> queue = new LinkedList<>();
                for(int d = 0; d < 4; d++) {
                    if((checked[i][j] & (1 << d)) != 0) continue;
                    checked[i][j] |= (1 << d);
                    queue.offer(new Location(i, j, d, 0));
                }

                while(!queue.isEmpty()) {
                    Location current = queue.poll();
                    int x = current.x + dx[current.d], y = current.y + dy[current.d];
                    if(x < 0 || y < 0 || x == N || y == M || ((checked[x][y] & (1 << current.d)) != 0)) continue;

                    if(map[x][y] == 0) {
                        queue.offer(new Location(x, y, current.d, current.cnt + 1));
                    } else if(map[x][y] == src) {
                        checked[x][y] |= (1 << current.d);
                        queue.offer(new Location(x, y, current.d, 0));
                    } else if(1 < current.cnt) {
                        // dest
                        int dest = map[x][y];
                        graph[src-1][dest-1] = graph[dest-1][src-1] = Math.min(graph[src-1][dest-1], current.cnt);
                    }
                }
            }
        }
    }

    public static int island() {
        boolean[][] visited = new boolean[N][M];

        Queue<Location> queue = new LinkedList<>();
        int idx = 1;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] == 0 || visited[i][j]) continue;

                visited[i][j] = true;
                map[i][j] = idx;
                queue.offer(new Location(i, j));
                while(!queue.isEmpty()) {
                    Location current = queue.poll();
                    for(int d = 0; d < 4; d++) {
                        int x = current.x + dx[d];
                        int y = current.y + dy[d];

                        if(x < 0 || y < 0 || x == N || y == M || map[x][y] == 0 || visited[x][y]) continue;
                        visited[x][y] = true;
                        map[x][y] = idx;
                        queue.offer(new Location(x, y));
                    }
                }
                idx++;
            }
        }
        return idx-1;
    }
}


// import java.io.BufferedReader;
// import java.io.InputStreamReader;
// import java.util.LinkedList;
// import java.util.PriorityQueue;
// import java.util.Queue;
// import java.util.StringTokenizer;
// import java.io.IOException;

// class Edge implements Comparable<Edge> {
//     int src, dest, weight;

//     Edge(int src, int dest, int weight) {
//         this.src = src;
//         this.dest = dest;
//         this.weight = weight;
//     }

//     @Override
//     public int compareTo(Edge target) {
//         return target.weight < this.weight ? 1 : -1;
//     }
// }

// class Location {
//     int x, y;

//     Location(int x, int y) {
//         this.x = x;
//         this.y = y;
//     }
// }

// public class Main {
//     static int N, M, numOfIslands;
//     static int[] dx = { -1, 1, 0, 0 };
//     static int[] dy = { 0, 0, -1, 1 };
//     static int[][] map, visited, islands;

//     public static void main(String[] args) throws IOException {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         StringTokenizer st = new StringTokenizer(br.readLine());

//         N = Integer.parseInt(st.nextToken());
//         M = Integer.parseInt(st.nextToken());

//         // map
//         visited = new int[N][M];
//         map = new int[N][M];
//         for (int i = 0; i < N; i++) {
//             st = new StringTokenizer(br.readLine());
//             for (int j = 0; j < M; j++) {
//                 map[i][j] = Integer.parseInt(st.nextToken());
//             }
//         }

//         // numbering
//         numOfIslands = 1;
//         for (int i = 0; i < N; i++) {
//             for (int j = 0; j < M; j++) {
//                 if (map[i][j] == 1 && visited[i][j] == 0) {
//                     numbering(i, j, numOfIslands++);
//                 }
//             }
//         }

//         // graph of islands
//         islands = new int[numOfIslands][numOfIslands];
//         for (int i = 1; i < numOfIslands; i++) {
//             for (int j = 1; j < numOfIslands; j++)
//                 islands[i][j] = Integer.MAX_VALUE;
//         }

//         // measurement
//         for (int i = 0; i < N; i++) {
//             for (int j = 0; j < M; j++) {
//                 if (map[i][j] == 0)
//                     continue;

//                 for (int d = 0; d < 4; d++) {
//                     int x_ = i + dx[d];
//                     int y_ = j + dy[d];

//                     if (x_ < 0 || y_ < 0 || N <= x_ || M <= y_)
//                         continue;

//                     if (map[x_][y_] == 0)
//                         measure(x_, y_, d, map[i][j]);
//                 }
//             }
//         }

//         // find shortest path
//         int ans = mst();

//         System.out.println(ans);
//     }

//     public static int mst() {

//         int[][] tree = new int[numOfIslands][numOfIslands];
//         PriorityQueue<Edge> edges = new PriorityQueue<>();

//         for(int i = 1; i < numOfIslands; i++) {
//             for(int j = 1; j < numOfIslands; j++) {
//                 if(islands[i][j] != Integer.MAX_VALUE) {
//                     edges.offer(new Edge(i, j, islands[i][j]));
//                 }
//             }
//         }

//         // pick the edges
//         int[] group = new int[numOfIslands];
//         int groupIdx = 1;

//         while(!edges.isEmpty()) {
//             Edge edge = edges.poll();

//             int group1 = group[edge.src];
//             int group2 = group[edge.dest];

//             if(group1 == 0 && group2 == 0) {
//                 group[edge.src] = group[edge.dest] = groupIdx++;
//             } else if(group1 != 0 && group2 != 0) {     
                
//                 if(group1 == group2) continue;

//                 if(group1 < group2) {
//                     for(int i = 1; i < numOfIslands; i++) {
//                         if(group[i] == group2) group[i] = group1;
//                     }
//                 } else {
//                     for(int i = 1; i < numOfIslands; i++) {
//                         if(group[i] == group1) group[i] = group2;
//                     }
//                 }

//             } else {

//                 if(group1 == 0) {
//                     group[edge.src] = group2;
//                 } else {
//                     group[edge.dest] = group1;
//                 }

//             }

//             tree[edge.src][edge.dest] = edge.weight;
//         }

//         for(int i = 1; i < numOfIslands; i++) {
//             if(group[i] != group[1] || group[i] == 0) return -1;
//         }


//         int sum = 0;
//         for(int i = 1; i < numOfIslands; i++) {
//             for(int j = 1; j < numOfIslands; j++) {
//                 sum += tree[i][j];
//             }
//         }

//         return sum;
//     }

//     public static void measure(int x, int y, int d, int islandNum) {

//         int count = 0;
//         boolean outOfBoundary = false;
//         while (map[x][y] == 0) {
//             count++;

//             x += dx[d];
//             y += dy[d];

//             if (x < 0 || y < 0 || N <= x || M <= y) {
//                 outOfBoundary = true;
//                 break;
//             }
//         }

//         if (!outOfBoundary && 1 < count && map[x][y] != islandNum)
//             islands[islandNum][map[x][y]] = islands[map[x][y]][islandNum] = Math.min(islands[islandNum][map[x][y]], count);

//     }

//     public static void numbering(int x, int y, int number) {

//         Queue<Location> queue = new LinkedList<Location>();
//         queue.offer(new Location(x, y));

//         while (!queue.isEmpty()) {

//             Location current = queue.poll();
//             map[current.x][current.y] = number;
//             visited[current.x][current.y] = 1;

//             for (int i = 0; i < 4; i++) {
//                 int x_ = current.x + dx[i];
//                 int y_ = current.y + dy[i];

//                 if (x_ < 0 || y_ < 0 || N <= x_ || M <= y_ || visited[x_][y_] == 1 || map[x_][y_] == 0)
//                     continue;

//                 queue.offer(new Location(x_, y_));
//             }
//         }

//     }
// }