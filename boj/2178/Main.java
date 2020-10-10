import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    static int N, M, minVal;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] maze, visited;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        maze = new int[N][M];
        visited = new int[N][M];

        for(int i = 0; i < N; i++) {
            String line = sc.next();
            for(int j = 0; j < M; j++) {
                maze[i][j] = line.charAt(j) - '0';
            }
        }

        minVal = Integer.MAX_VALUE;
        visited[0][0] = 1;

        ArrayList<Integer> qx = new ArrayList<Integer>();
        ArrayList<Integer> qy = new ArrayList<Integer>();
        ArrayList<Integer> qcount = new ArrayList<Integer>();
        qx.add(0);
        qy.add(0);
        qcount.add(1);

        while(!qx.isEmpty()) {
            if(qx.get(0) == N-1 && qy.get(0) == M-1) {
                minVal = Math.min(minVal, qcount.get(0));

                qx.remove(0);
                qy.remove(0);
                qcount.remove(0);

                continue ;
            }

            for(int i = 0; i < 4; i++) {
                int x_ = qx.get(0) + dx[i];
                int y_ = qy.get(0) + dy[i];

                if(x_ < 0 || N <= x_ || y_ < 0 || M <= y_) continue;

                if(maze[x_][y_] == 1 && visited[x_][y_] == 0) {
                    qx.add(x_);
                    qy.add(y_);
                    qcount.add(qcount.get(0)+1);
                    visited[x_][y_] = 1;
                }
            }
            
            qx.remove(0);
            qy.remove(0);
            qcount.remove(0);
        }

        sc.close();

        System.out.println(minVal);
    }
}