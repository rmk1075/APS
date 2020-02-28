import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};
    static int[][] map, visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        visited = new int[M][N];

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }

        ArrayList<Integer> area = new ArrayList<>();
        int count = 1, maxArea = 0;
        for(int i = 0; i < M; i++) {
            for(int j = 0; j < N; j++) {
                if(visited[i][j] != 0) continue;

                Queue<Location> queue = new LinkedList<>();
                queue.offer(new Location(i, j));
                visited[i][j] = count;
                
                int tempArea = 1;
                while(!queue.isEmpty()) {
                    Location current = queue.poll();

                    int x, y;
                    for(int d = 0; d < 4; d++) {
                        if((map[current.x][current.y] & (1 << d)) != 0) continue;

                        x = current.x + dx[d];
                        y = current.y + dy[d];

                        if(x < 0 || y < 0 || x == M || y == N || visited[x][y] != 0) continue;

                        visited[x][y] = count;
                        queue.offer(new Location(x, y));
                        tempArea++;
                    }
                }

                area.add(tempArea);
                maxArea = Math.max(maxArea, tempArea);
                count++;
            }
        }
        count--;

        System.out.println(count);
        System.out.println(maxArea);

        int maxAreaAfterBrokenWall = 0;
        for(int i = 0; i < M; i++) {
            for(int j = 0; j < N; j++) {
                int x, y;
                for(int d = 0; d < 4; d++) {
                    x = i + dx[d];
                    y = j + dy[d];

                    if(x < 0 || y < 0 || x == M || y == N || visited[i][j] == visited[x][y]) continue;
                    maxAreaAfterBrokenWall = Math.max(maxAreaAfterBrokenWall, area.get(visited[i][j]-1) + area.get(visited[x][y]-1));
                }
            }
        }
        System.out.println(maxAreaAfterBrokenWall);
    }
}