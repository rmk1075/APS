import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
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
    static int N;
    static ArrayList<Integer> ans = new ArrayList<Integer>();
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static String[][] map;
    static int[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new String[N][N];
        visited = new int[N][N];
        for(int i = 0; i < N; i++) {
            map[i] = br.readLine().split("");
        }

        Queue<Location> q = new LinkedList<Location>();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(map[i][j].equals("1") && visited[i][j] == 0) {
                    q.offer(new Location(i, j));
                    visited[i][j] = 1;

                    int count = 0;
                    while(!q.isEmpty()) {
                        Location current = q.poll();
                        count++;

                        int x_, y_;
                        for(int d = 0; d < 4; d++) {
                            x_ = current.x + dx[d];
                            y_ = current.y + dy[d];

                            if(x_ < 0 || y_ < 0 || N <= x_ || N <= y_ || visited[x_][y_] == 1 || map[x_][y_].equals("0")) continue;

                            q.offer(new Location(x_, y_));
                            visited[x_][y_] = 1;
                        }
                    }

                    ans.add(count);
                }
            }
        }

        ans.sort(null);
        System.out.println(ans.size());
        for(int a : ans) {
            System.out.println(a);
        }
    }

}
