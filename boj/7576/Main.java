import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.LinkedList;
import java.util.Queue;

class Tomato {
    int x, y;

    Tomato(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int N, M;
    static int days = 0;
    static int[] dx = { 1, 0, -1, 0 };
    static int[] dy = { 0, 1, 0, -1 };
    static int[][] tomatoes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        Queue<Tomato> q = new LinkedList<Tomato>();

        tomatoes = new int[N][M];
        for (int i = 0; i < N; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                tomatoes[i][j] = Integer.parseInt(st2.nextToken());
                if (tomatoes[i][j] == 1) {
                    q.add(new Tomato(i, j));
                }
            }
        }

        while (!q.isEmpty()) {
            int size = q.size();
            for (int one = 0; one < size; one++) {
                Tomato tomato = q.poll();
                for (int i = 0; i < 4; i++) {
                    int x_ = tomato.x + dx[i];
                    int y_ = tomato.y + dy[i];

                    if (x_ < 0 || N <= x_ || y_ < 0 || M <= y_)
                        continue;

                    if (tomatoes[x_][y_] == 0) {
                        tomatoes[x_][y_] = 1;
                        q.add(new Tomato(x_, y_));
                    }

                }
            }

            days++;
        }

        // check isZero
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (tomatoes[i][j] == 0) {
                    System.out.println(-1);
                    System.exit(0);
                }
            }
        }

        System.out.println(days - 1);
    }
}

// import java.util.Scanner;
// import java.util.LinkedList;
// import java.util.Queue;

// class Tomato {
//     int x, y;

//     Tomato(int x, int y) {
//         this.x = x;
//         this.y = y;
//     }
// }

// public class Main {
//     static int N, M;
//     static int days = 0;
//     static int[] dx = {1, 0, -1, 0};
//     static int[] dy = {0, 1, 0, -1};
//     static int[][] tomatoes;
//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         M = sc.nextInt();
//         N = sc.nextInt();

//         Queue<Tomato> q = new LinkedList<Tomato>();

//         tomatoes = new int[N][M];
//         for(int i = 0; i < N; i++) {
//             for(int j = 0; j < M; j++) {
//                 tomatoes[i][j] = sc.nextInt();
//                 if(tomatoes[i][j] == 1) {
//                     q.add(new Tomato(i, j));
//                 }
//             }
//         }
//         sc.close();
        
//         while(!q.isEmpty()) {
//             int size = q.size();
//             for(int one = 0; one < size; one++) {
//                 Tomato tomato = q.poll();
//                 for(int i = 0; i < 4; i++) {
//                     int x_ = tomato.x + dx[i];
//                     int y_ = tomato.y + dy[i];
                        
//                     if(x_ < 0 || N <= x_ || y_ < 0 || M <= y_) continue;

//                     if(tomatoes[x_][y_] == 0) {
//                         tomatoes[x_][y_] = 1;
//                         q.add(new Tomato(x_, y_));
//                     }
                    
//                 }
//             }

//             days++;
//         }
        
//         // check isZero
//         for(int i = 0; i < N; i++) {
//             for(int j = 0; j < M; j++) {
//                 if(tomatoes[i][j] == 0) {
//                     System.out.println(-1);
//                     System.exit(0);
//                 }
//             }
//         }

//         System.out.println(days-1);
//     }
// }