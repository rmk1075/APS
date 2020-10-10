import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
    static int N, M;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
    static int[][] ans;
    static char[][] map;
    static ArrayList<Integer> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ans = new int[N][M];
        map = new char[N][M];

        Queue<Location> walls = new LinkedList<>();
        for(int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
            for(int j = 0; j < M; j++) {
                if(map[i][j] == '1') walls.offer(new Location(i, j));
            }
        }

        int[][] index = new int[N][M];
        for(int i = 0; i < N; i++) Arrays.fill(index[i], -1);

        int idx = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] == '1' || index[i][j] != -1) continue;
                
                index[i][j] = idx;
                int count = 1;
                Queue<Location> queue = new LinkedList<>();
                queue.offer(new Location(i, j));

                while(!queue.isEmpty()) {
                    Location current = queue.poll();

                    for(int d = 0; d < 4; d++) {
                        int x_ = current.x + dx[d], y_ = current.y + dy[d];
                        if(x_ < 0 || y_ < 0 || x_ == N || y_ == M || map[x_][y_] == '1' || index[x_][y_] != -1) continue;

                        count++;
                        index[x_][y_] = idx;
                        queue.offer(new Location(x_, y_));
                    }
                }
                list.add(count);
                idx++;
            }
        }

        while(!walls.isEmpty()) {
            Location src = walls.poll();
            int count = 1;

            ArrayList<Integer> temp = new ArrayList<>();
            for(int d = 0; d < 4; d++) {
                int x_ = src.x + dx[d], y_ = src.y + dy[d];
                if(x_ < 0 || y_ < 0 || x_ == N || y_ == M || map[x_][y_] == '1' || temp.contains(index[x_][y_])) continue;
                temp.add(index[x_][y_]);
                count += list.get(index[x_][y_]);
            }    
            ans[src.x][src.y] = count % 10;
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) System.out.print(ans[i][j]);
            System.out.println();
        }
    }
}