import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
// import java.util.Arrays;
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
    static int W, H, minCount;
    static int[] order;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] distance;
    static char[][] map;
    static boolean[] visited;
    static ArrayList<Location> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        while(W != 0) {
            map = new char[H][W];

            list = new ArrayList<>();
            for(int i = 0; i < H; i++) {
                map[i] = br.readLine().toCharArray();

                for(int j = 0; j < W; j++) {
                    if(map[i][j] == '*') {
                        list.add(new Location(i, j));
                    } else if(map[i][j] == 'o') {
                        list.add(0, new Location(i, j));
                    }
                }
            }

            visited = new boolean[list.size()];
            visited[0] = true;
            order = new int[list.size()];
            order[0] = 0;

            boolean unreachable = false;
            distance = new int[list.size()][list.size()];
            loop:
            for(int i = 0; i < list.size(); i++) {
                for(int j = i+1; j < list.size(); j++) {
                    if(i == j) continue;
                    distance[i][j] = distance[j][i] = bfs(i, j);

                    if(distance[i][j] == -1) {
                        sb.append(-1 + "\n");
                        unreachable = true;
                        break loop;
                    }
                }
            }

            if(!unreachable) {
                minCount = Integer.MAX_VALUE;
                sequence(1);
                sb.append(minCount + "\n");
            }


            st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
        }

        System.out.println(sb.toString());
    }

    public static boolean sequence(int count) {
        if(count == list.size()) {
            int sum = 0;
            for(int i = 0; i < order.length - 1; i++) {
                sum += distance[order[i]][order[i+1]];
            }

            minCount = Math.min(minCount, sum);
            return true;
        }

        for(int i = 1; i < list.size(); i++) {
            if(visited[i]) continue;

            visited[i] = true;
            order[count] = i;
            if(!sequence(count + 1)) return false;
            visited[i] = false;
        }

        return true;
    }

    public static int bfs(int src, int dest) {
        boolean[][] checked = new boolean[H][W];
        Queue<Location> queue = new LinkedList<>();
        queue.add(new Location(list.get(src).x, list.get(src).y));
        checked[list.get(src).x][list.get(src).y] = true;

        int size, count = 1;
        while (!queue.isEmpty()) {
            size = queue.size();
            while (0 < size--) {
                Location current = queue.poll();

                int x, y;
                for (int d = 0; d < 4; d++) {
                    x = current.x + dx[d];
                    y = current.y + dy[d];

                    if (x < 0 || y < 0 || x == H || y == W || checked[x][y] || map[x][y] == 'x')
                        continue;

                    if (x == list.get(dest).x && y == list.get(dest).y) return count;
    
                    checked[x][y] = true;
                    queue.offer(new Location(x, y));
                }
            }
            count++;
        }

        return -1;
    }
}