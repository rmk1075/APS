import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, count[][];
    static int[] dx = {-1, 0 , 1, 0}, dy = {0, 1, 0, -1};
    static char map[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        count = new int[N][M];

        for(int i = 0; i < N; i++) map[i] = br.readLine().toCharArray();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) find(i, j);
        }

        int cnt = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(count[i][j] == 1) cnt++;
            }
        }

        System.out.println(cnt);
    }

    public static int find(int x, int y) {
        if(count[x][y] != 0) return count[x][y];

        int x_ = x, y_ = y;
        switch (map[x][y]) {
            case 'U':
                x_ += dx[0];
                y_ += dy[0];
                break;
            case 'R':
                x_ += dx[1];
                y_ += dy[1];
                break;
            case 'D':
                x_ += dx[2];
                y_ += dy[2];
                break;
            case 'L':
                x_ += dx[3];
                y_ += dy[3];
        }

        if(x_ < 0 || y_ < 0 || x_ == N || y_ == M) return count[x][y] = 1;
        if(count[x_][y_] != 0) return count[x][y] = count[x_][y_];
        
        count[x][y] = -1;
        return count[x][y] = find(x_, y_);
    }
}