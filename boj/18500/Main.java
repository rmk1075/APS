import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
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
    static int R, C, N;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
    static char[][] map;
    static boolean[][] visited;
    static Queue<Location> queue = new LinkedList<>(), dropQueue = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited = new boolean[R][C];
        for(int i = 0; i < R; i++) map[i] = br.readLine().toCharArray();
        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            if(shot(Integer.parseInt(st.nextToken()), i % 2)) drop();
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) sb.append(map[i][j]);
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static boolean shot(int row, int check) {
        int start = C-1, end = -1, c = -1;
        if(check == 0) {
            start = 0;
            end = C;
            c = 1;
        }

        while(start != end) {
            if(map[R - row][start] == 'x') {
                map[R - row][start] = '.';
                return true;
            }
            start += c;
        }
        return false;
    }

    public static void drop() {
        for(int i = 0; i < R; i++) {
            Arrays.fill(visited[i], false);
        }

        queue.clear();
        for(int i = 0; i < C; i++) {
            if(visited[R-1][i] || map[R-1][i] == '.') continue;
            
            visited[R-1][i] = true;
            queue.offer(new Location(R-1, i));
            while(!queue.isEmpty()) {
                Location current = queue.poll();
                for(int d = 0; d < 4; d++) {
                    int x = current.x + dx[d], y = current.y + dy[d];
                    if(x < 0 || y < 0 || x == R || y == C || visited[x][y] || map[x][y] == '.') continue;
                    visited[x][y] = true;
                    queue.offer(new Location(x, y));
                }
            }
        }

        for(int i = R-1; -1 < i; i--) {
            for(int j = 0; j < C; j++) {
                if(map[i][j] == '.' || visited[i][j]) continue;
                queue.offer(new Location(i, j));
                map[i][j] = '.';
            }
        }

        if(queue.isEmpty()) return ;

        loop: while(true) {
            int size = queue.size();
            while(0 < size--) {
                Location current = queue.poll();
                if(current.x + 1 == R || map[current.x + 1][current.y] == 'x') break loop;
                queue.offer(new Location(current.x + 1, current.y));
            }

            dropQueue.clear();
            dropQueue.addAll(queue);
        }

        while(!dropQueue.isEmpty()) {
            Location current = dropQueue.poll();
            map[current.x][current.y] = 'x';
        }
    }
}