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
    static int N, L, R;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] nations, visited;
    static Queue<Location> queue;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L= Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        nations = new int[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                nations[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int count = 1;
        while(true) {
            visited = new int[N][N];
            boolean isMove = false;

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(visited[i][j] == -1) continue;
                    if(bfs(i, j, count)) isMove = true;
                }
            }

            if(!isMove) break;
            count++;
        }

        System.out.println(count - 1);
    }

    public static boolean bfs(int x, int y, int count) {
        int sumOfPopulation = 0, numOfNations = 0;
        queue = new LinkedList<Location>();

        queue.offer(new Location(x, y));
        visited[x][y] = count;
        while(!queue.isEmpty()) {
            Location current = queue.poll();
            sumOfPopulation += nations[current.x][current.y];
            numOfNations++;

            int x_, y_;
            for(int d = 0; d < 4; d++) {
                x_ = current.x + dx[d];
                y_ = current.y + dy[d];

                if(x_ < 0 || y_ < 0 || N <= x_ || N <= y_ || visited[x_][y_] == count || visited[x_][y_] == -1 || Math.abs(nations[current.x][current.y] - nations[x_][y_]) < L || R < Math.abs(nations[current.x][current.y] - nations[x_][y_])) continue;

                queue.offer(new Location(x_, y_));
                visited[x_][y_] = count;
            }
        }

        if(numOfNations == 1) {
            visited[x][y] = 0;
            return false; 
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(visited[i][j] == count) {
                    visited[i][j] = -1;
                    nations[i][j] = sumOfPopulation / numOfNations;
                }
            }
        }

        return true;
    }
}