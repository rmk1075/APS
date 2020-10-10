import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Location {
    int x, y;
    double p;

    Location(int x, int y, double p) {
        this.x = x;
        this.y = y;
        this.p = p;
    }
}

public class Main {
    static int N;
    static int[] dx = {0, 0, 1, -1}, dy = {1, -1, 0, 0};
    static double ans = 0.0, p[] = new double[4];
    static boolean[][] visited = new boolean[30][30];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        for(int i = 0; i < 4; i++) p[i] = Double.parseDouble(st.nextToken()) / 100;
        
        visited[14][14] = true;
        System.out.println(1 - find(0, 14, 14, 1.0));
    }

    public static double find(int cnt, int x, int y, double prob) {
        if(cnt == N) return 0.0;
        
        double ans = 0.0;
        int x_, y_;
        for(int d = 0; d < 4; d++) {
            x_ = x + dx[d];
            y_ = y + dy[d];
            if(visited[x_][y_]) {
                ans += prob * p[d];
                continue;
            }

            visited[x_][y_] = true;
            ans += find(cnt+1, x_, y_, prob*p[d]);
            visited[x_][y_] = false;
        }

        return ans;
    }
}