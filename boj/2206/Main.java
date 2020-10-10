import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.LinkedList;

public class Main {
    static int N, M;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };
    static int[][] map;
    static int[][][] visited;
    static Queue<Point> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        q = new LinkedList<Point>();
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        map = new int[N][M];
        visited = new int[N][M][2];

        for (int i = 0; i < N; i++) {
            str = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(str[j]);
            }
        }

        q.add(new Point(0, 0, 0));
        visited[0][0][0] = 1;

        int time = 1;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int s = 0; s < size; s++) {
                Point current = q.poll();

                if (current.x == N - 1 && current.y == M - 1) {
                    System.out.println(time);
                    System.exit(0);
                }

                for (int i = 0; i < 4; i++) {
                    int x_ = current.x + dx[i];
                    int y_ = current.y + dy[i];

                    if (x_ < 0 || N - 1 < x_ || y_ < 0 || M - 1 < y_ || visited[x_][y_][current.broken] == 1)
                        continue;

                    if (map[x_][y_] == 0) {
                        visited[x_][y_][current.broken] = 1;
                        q.add(new Point(x_, y_, current.broken));
                    } else if (current.broken == 0) {
                        visited[x_][y_][current.broken] = 1;
                        q.add(new Point(x_, y_, 1));
                    }
                }
            }

            time++;
        }

        System.out.println(-1);
    }
}

class Point {
    int broken = 0;
    int x, y;

    Point(int x, int y, int broken) {
        this.x = x;
        this.y = y;
        this.broken = broken;
    }
}

// import java.util.Scanner;
// import java.util.Queue;
// import java.util.LinkedList;

// public class Main {
//     static int N, M;
//     static int[] dx = {-1, 1, 0, 0};
//     static int[] dy = {0, 0, -1, 1};
//     static int[][] map, visited;
//     static Queue<Point> q;
//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);

//         q = new LinkedList<Point>();
//         N = sc.nextInt();
//         M = sc.nextInt();
//         map = new int[N][M];
//         visited = new int[N][M];

//         for(int i = 0; i < N; i++) {
//             String str = sc.next();
//             for(int j = 0; j < M; j++) {
//                 map[i][j] = Integer.parseInt(str.split("")[j]);
//             }
//         }

//         q.add(new Point(0, 0, false));
//         visited[0][0] = 1;

//         int time = 1;
//         while(!q.isEmpty()) {
//             int size = q.size();
//             for(int s = 0; s < size; s++) {
//                 Point current = q.poll();

//                 if(current.x == N-1 && current.y == M-1) {
//                     System.out.println(time);
//                     System.exit(0);
//                 }

//                 for(int i = 0; i < 4; i++) {
//                     int x_ = current.x + dx[i];
//                     int y_ = current.y + dy[i];

//                     if(x_ < 0 || N-1 < x_ || y_ < 0 || M-1 < y_ || visited[x_][y_] == 1) continue;

//                     if(map[x_][y_] == 0) {
//                         visited[x_][y_] = 1;
//                         q.add(new Point(x_, y_, current.broken));
//                     } else if(!current.broken) {
//                         visited[x_][y_] = 1;
//                         q.add(new Point(x_, y_, true));
//                     }
//                 }    
//             }

//             time++;
//         }

//         System.out.println(-1);
//         sc.close();
//     }
// }

// class Point {
//     boolean broken;
//     int x, y;

//     Point(int x, int y, boolean broken) {
//         this.x = x;
//         this.y = y;
//         this.broken = broken;
//     }
// }