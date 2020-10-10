import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Location {
    int x, y;

    Location(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int R, C, minDays = Integer.MAX_VALUE;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] meltDays;
    static char[][] map;
    static Location src;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];

        Queue<Location> meltLine = new LinkedList<>();
        for(int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
            for(int j = 0; j < C; j++) {
                if(map[i][j] != 'X') {
                    if(map[i][j] == 'L') src = new Location(i, j);
                    meltLine.offer(new Location(i, j));
                }
            }
        }

        meltDays = new int[R][C];
        int size, count = 1;
        while(!meltLine.isEmpty()) {
            size = meltLine.size();
            while(0 < size--) {
                Location current = meltLine.poll();

                int x, y;
                for(int d = 0; d < 4; d++) {
                    x = current.x + dx[d];
                    y = current.y + dy[d];

                    if(x < 0 || y < 0 || x == R || y == C || map[x][y] != 'X') continue;
                    
                    map[x][y] = '.';
                    meltDays[x][y] = count;
                    meltLine.add(new Location(x, y));
                }
            }

            count++;
        }

        search(0, count-1);
        System.out.println(minDays);
    }

    public static void search(int left, int right) { 
        int mid;
        minDays = right;
        while(left <= right) {
            mid = (left + right) / 2;
            if(find(mid)) {
                minDays = Math.min(minDays, mid);
                right = mid - 1;
            }
            else left = mid + 1;
        }
    }

    public static boolean find(int n) {
        Queue<Location> queue = new LinkedList<>();
        boolean[][] visited = new boolean[R][C];

        queue.offer(new Location(src.x, src.y));
        visited[src.x][src.y] = true;

        while(!queue.isEmpty()) {
            Location current = queue.poll();

            int x, y;
            for(int d = 0; d < 4; d++) {
                x = current.x + dx[d];
                y = current.y + dy[d];

                if(x < 0 || y < 0 || x == R || y == C || n < meltDays[x][y] || visited[x][y]) continue;
                if(map[x][y] == 'L') return true;

                visited[x][y] = true;
                queue.offer(new Location(x, y));
            }
        }

        return false;
    }
}