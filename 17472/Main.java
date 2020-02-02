import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.IOException;

class Edge implements Comparable<Edge> {
    int src, dest, weight;

    Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge target) {
        return target.weight < this.weight ? 1 : -1;
    }
}

class Location {
    int x, y;

    Location(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int N, M, numOfIslands;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };
    static int[][] map, visited, islands;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // map
        visited = new int[N][M];
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // numbering
        numOfIslands = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1 && visited[i][j] == 0) {
                    numbering(i, j, numOfIslands++);
                }
            }
        }

        // graph of islands
        islands = new int[numOfIslands][numOfIslands];
        for (int i = 1; i < numOfIslands; i++) {
            for (int j = 1; j < numOfIslands; j++)
                islands[i][j] = Integer.MAX_VALUE;
        }

        // measurement
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0)
                    continue;

                for (int d = 0; d < 4; d++) {
                    int x_ = i + dx[d];
                    int y_ = j + dy[d];

                    if (x_ < 0 || y_ < 0 || N <= x_ || M <= y_)
                        continue;

                    if (map[x_][y_] == 0)
                        measure(x_, y_, d, map[i][j]);
                }
            }
        }

        // find shortest path
        int ans = mst();

        System.out.println(ans);
    }

    public static int mst() {

        int[][] tree = new int[numOfIslands][numOfIslands];
        PriorityQueue<Edge> edges = new PriorityQueue<>();

        for(int i = 1; i < numOfIslands; i++) {
            for(int j = 1; j < numOfIslands; j++) {
                if(islands[i][j] != Integer.MAX_VALUE) {
                    edges.offer(new Edge(i, j, islands[i][j]));
                }
            }
        }

        // pick the edges
        int[] group = new int[numOfIslands];
        int groupIdx = 1;

        while(!edges.isEmpty()) {
            Edge edge = edges.poll();

            int group1 = group[edge.src];
            int group2 = group[edge.dest];

            if(group1 == 0 && group2 == 0) {
                group[edge.src] = group[edge.dest] = groupIdx++;
            } else if(group1 != 0 && group2 != 0) {     
                
                if(group1 == group2) continue;

                if(group1 < group2) {
                    for(int i = 1; i < numOfIslands; i++) {
                        if(group[i] == group2) group[i] = group1;
                    }
                } else {
                    for(int i = 1; i < numOfIslands; i++) {
                        if(group[i] == group1) group[i] = group2;
                    }
                }

            } else {

                if(group1 == 0) {
                    group[edge.src] = group2;
                } else {
                    group[edge.dest] = group1;
                }

            }

            tree[edge.src][edge.dest] = edge.weight;
        }

        for(int i = 1; i < numOfIslands; i++) {
            if(group[i] != group[1] || group[i] == 0) return -1;
        }


        int sum = 0;
        for(int i = 1; i < numOfIslands; i++) {
            for(int j = 1; j < numOfIslands; j++) {
                sum += tree[i][j];
            }
        }

        return sum;
    }

    public static void measure(int x, int y, int d, int islandNum) {

        int count = 0;
        boolean outOfBoundary = false;
        while (map[x][y] == 0) {
            count++;

            x += dx[d];
            y += dy[d];

            if (x < 0 || y < 0 || N <= x || M <= y) {
                outOfBoundary = true;
                break;
            }
        }

        if (!outOfBoundary && 1 < count && map[x][y] != islandNum)
            islands[islandNum][map[x][y]] = islands[map[x][y]][islandNum] = Math.min(islands[islandNum][map[x][y]], count);

    }

    public static void numbering(int x, int y, int number) {

        Queue<Location> queue = new LinkedList<Location>();
        queue.offer(new Location(x, y));

        while (!queue.isEmpty()) {

            Location current = queue.poll();
            map[current.x][current.y] = number;
            visited[current.x][current.y] = 1;

            for (int i = 0; i < 4; i++) {
                int x_ = current.x + dx[i];
                int y_ = current.y + dy[i];

                if (x_ < 0 || y_ < 0 || N <= x_ || M <= y_ || visited[x_][y_] == 1 || map[x_][y_] == 0)
                    continue;

                queue.offer(new Location(x_, y_));
            }
        }

    }
}