import java.util.Scanner;

public class Main {
    static int T, M, N, K;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] field, visited;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        T = sc.nextInt();
        while(0 < T--) {
            M = sc.nextInt();
            N = sc.nextInt();
            K = sc.nextInt();
            field = new int[M][N];
            visited = new int[M][N];
            
            for(int i = 0; i < K; i++) {
                field[sc.nextInt()][sc.nextInt()] = 1;
            }

            int count = 0;
            for(int i = 0; i < M; i++) {
                for(int j = 0; j < N; j++) {
                    if(field[i][j] == 1 && visited[i][j] == 0) {
                        count++;
                        dfs(i, j);
                    }
                }
            }

            System.out.println(count);
        }

        sc.close();
    }

    public static void dfs(int x, int y) {
        visited[x][y] = 1;
        for(int i = 0; i < 4; i++) {
            int x_ = x+dx[i];
            int y_ = y+dy[i];
            if(x_ < 0 || M <= x_ || y_ < 0 || N <= y_) continue;
            if(field[x_][y_] == 1 && visited[x_][y_] == 0) {
                dfs(x_, y_);
            }
        }
    }
}