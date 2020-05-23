import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Location {
    int x, y;
    Location(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int srcX, srcY, ans = 0, dx[] = {-1, 0, 1, 0}, dy[] = {0, -1, 0 ,1};
    static char[][] map = new char[5][5];
    static boolean[][] picked = new boolean[5][5], visited = new boolean[5][5];
    static Queue<Location> queue = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i = 0; i < 5; i++) map[i] = br.readLine().toCharArray();
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                picked[i][j] = true;
                srcX = i;
                srcY = j;
                dfs(map[i][j] == 'S' ? 1 : 0, 1, i*5 + j);
                picked[i][j] = false;
            }
        }
        System.out.println(ans);
    }

    public static void dfs(int som, int count, int start) {
        if(count == 7) {
            if(3 < som && check()) ans++;
            return ;
        }

        for(int i = srcX; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                if(i*5 + j <= start || picked[i][j]) continue;
                picked[i][j] = true;
                dfs(som + (map[i][j] == 'S' ? 1 : 0), count + 1, i*5 + j);
                picked[i][j] = false;
            }
        }
    }

    public static boolean check() {
        queue.offer(new Location(srcX, srcY));

        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) visited[i][j] = false;
        }
        visited[srcX][srcY] = true;

        int cnt = 1;
        while(!queue.isEmpty()) {
            Location current = queue.poll();
            for(int d = 0; d < 4; d++) {
                int x = current.x + dx[d], y = current.y + dy[d];
                if(x < 0 || y < 0 || x == 5 || y == 5 || !picked[x][y] || visited[x][y]) continue;
                visited[x][y] = true;
                cnt++;
                queue.offer(new Location(x, y));
            }
        }
        return cnt == 7;
    }
}