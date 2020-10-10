import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.IOException;

class Location {
    int x, y;

    Location(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int M, N, V, X, Y;
    static int highest, shortestTime;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] map, volcanoes;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // map size
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        // num of volcanoes
        V = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        // person
        X = Integer.parseInt(st.nextToken()) - 1;
        Y = Integer.parseInt(st.nextToken()) - 1;

        map = new int[M][N];
        volcanoes = new int[V][3];

        // map
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // volcanoes
        for(int i = 0; i < V; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 2; j++) {
                volcanoes[i][j] = Integer.parseInt(st.nextToken()) - 1;
            }
            volcanoes[i][2] = Integer.parseInt(st.nextToken());
            map[volcanoes[i][0]][volcanoes[i][1]] = -1;
        }

        // init
        // TODO: remove visited?? -> integrate visited and map??
        highest = map[X][Y];
        shortestTime = 0;
        map[X][Y] = -1;

        Queue<Location> queue = new LinkedList<Location>();
        queue.offer(new Location(X, Y));

        int time = 1;
        while(!queue.isEmpty()) {

            cover(time);


            int size = queue.size();
            for(int s = 0; s < size; s++) {
                Location current = queue.poll();

                for(int d = 0; d < 4; d++) {
                    int x = current.x + dx[d];
                    int y = current.y + dy[d];

                    if(x < 0 || M <= x || y < 0 || N <= y || map[x][y] == -1) continue;

                    queue.offer(new Location(x, y));
                    if(highest < map[x][y]) {
                        highest = map[x][y];
                        shortestTime = time;
                    }

                    map[x][y] = -1;
                }
            }

            time++;            
        }

        System.out.println(highest + " " + shortestTime);
    }

    public static void cover(int time) {
        for(int v = 0; v < V; v++) {
            if(volcanoes[v][2] < time) {
                for(int i = 0; i < M; i++) {
                    for(int j = 0; j < N; j++) {
                        if(map[i][j] == -1) continue;

                        if(Math.abs(volcanoes[v][0] - i) + Math.abs(volcanoes[v][1] - j) <= time - volcanoes[v][2]) map[i][j] = -1;
                    }
                }
            }
        }
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
//     static int M, N, V, X, Y;
//     static int highest, shortestTime;
//     static int[] dx = {-1, 1, 0, 0};
//     static int[] dy = {0, 0, -1, 1};
//     static int[][] map, volcanoes, visited;
//     public static void main(String[] args) throws IOException {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         StringTokenizer st = new StringTokenizer(br.readLine());

//         // map size
//         M = Integer.parseInt(st.nextToken());
//         N = Integer.parseInt(st.nextToken());
//         // num of volcanoes
//         V = Integer.parseInt(st.nextToken());

//         st = new StringTokenizer(br.readLine());
//         // person
//         X = Integer.parseInt(st.nextToken()) - 1;
//         Y = Integer.parseInt(st.nextToken()) - 1;

//         map = new int[M][N];
//         visited = new int[M][N];
//         volcanoes = new int[V][3];

//         // map
//         for(int i = 0; i < M; i++) {
//             st = new StringTokenizer(br.readLine());
//             for(int j = 0; j < N; j++) {
//                 map[i][j] = Integer.parseInt(st.nextToken());
//             }
//         }

//         // volcanoes
//         for(int i = 0; i < V; i++) {
//             st = new StringTokenizer(br.readLine());
//             for(int j = 0; j < 2; j++) {
//                 volcanoes[i][j] = Integer.parseInt(st.nextToken()) - 1;
//             }
//             volcanoes[i][2] = Integer.parseInt(st.nextToken());
//             map[volcanoes[i][0]][volcanoes[i][1]] = -1;
//         }

//         // init
//         // TODO: remove visited?? -> integrate visited and map??
//         highest = map[X][Y];
//         shortestTime = 0;
//         visited[X][Y] = 1;

//         Queue<Location> queue = new LinkedList<Location>();
//         queue.offer(new Location(X, Y));

//         int time = 1;
//         while(!queue.isEmpty()) {

//             cover(time);


//             int size = queue.size();
//             for(int s = 0; s < size; s++) {
//                 Location current = queue.poll();

//                 for(int d = 0; d < 4; d++) {
//                     int x = current.x + dx[d];
//                     int y = current.y + dy[d];

//                     if(x < 0 || M <= x || y < 0 || N <= y || visited[x][y] == 1 || map[x][y] == -1) continue;

//                     queue.offer(new Location(x, y));
//                     visited[x][y] = 1;
//                     if(highest < map[x][y]) {
//                         highest = map[x][y];
//                         shortestTime = time;
//                     }
//                 }
//             }

//             time++;            
//         }

//         System.out.println(highest + " " + shortestTime);
//     }

//     public static void cover(int time) {
//         for(int v = 0; v < V; v++) {
//             if(volcanoes[v][2] < time) {
//                 for(int i = 0; i < M; i++) {
//                     for(int j = 0; j < N; j++) {
//                         if(map[i][j] == -1) continue;

//                         if(Math.abs(volcanoes[v][0] - i) + Math.abs(volcanoes[v][1] - j) <= time - volcanoes[v][2]) map[i][j] = -1;
//                     }
//                 }
//             }
//         }
//     }
// }