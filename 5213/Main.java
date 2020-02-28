import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Location {
    int x, y, distance;
    ArrayList<Integer> list = new ArrayList<>();

    Location(int x, int y, int distance, ArrayList<Integer> list, int a) {
        this.x = x;
        this.y = y;
        this.distance = distance;
        this.list.addAll(list);
        this.list.add(a);
    }
}

class Tile {
    int index;
    int[] val = new int[2];

    Tile(int index, int a, int b) {
        this.index = index;
        this.val[0] = a;
        this.val[1] = b;
    }
}

public class Main {
    static int N;
    static boolean[][] visited;
    static Tile[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        visited = new boolean[N][N];
        map = new Tile[N][N];

        int idx = 1;
        for(int i = 0; i < N; i++) {
            int boundary = (i%2 == 0) ? N : N-1;
            for (int j = 0; j < boundary; j++) {
                st = new StringTokenizer(br.readLine());
                map[i][j] = new Tile(idx++, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }
        }

        idx--;

        Queue<Location> queue = new LinkedList<>();
        queue.offer(new Location(0, 0, 1, new ArrayList<>(), 1));

        int maxIndex = 0;
        int minDistance = Integer.MAX_VALUE;
        ArrayList<Integer> tempList = new ArrayList<>();
        while(!queue.isEmpty()) {
            Location current = queue.poll();
            int x, y, val0 = map[current.x][current.y].val[0], val1 = map[current.x][current.y].val[1];

            if(maxIndex < map[current.x][current.y].index) {
                maxIndex = map[current.x][current.y].index;
                minDistance = current.distance;
                tempList = current.list;
            }

            if(maxIndex == idx) break;

            // 1
            x = current.x;
            y = current.y - 1;
            if(-1 < y && val0 == map[x][y].val[1] && !visited[x][y]) {
                visited[x][y] = true;
                queue.offer(new Location(x, y, current.distance+1, current.list, map[x][y].index));
            }
            
            // 4
            x = current.x;
            y = current.y + 1;
            if(y < N && map[x][y] != null && val1 == map[x][y].val[0] && !visited[x][y]) {
                visited[x][y] = true;
                queue.offer(new Location(x, y, current.distance+1, current.list, map[x][y].index));
            }

            // even
            if(current.x % 2 == 0) {
                // 2
                x = current.x + 1;
                y = current.y - 1;
                if(-1 < y && x < N && val0 == map[x][y].val[1] && !visited[x][y]) {
                    visited[x][y] = true;
                    queue.offer(new Location(x, y, current.distance+1, current.list, map[x][y].index));
                }
    
                // 3
                x = current.x + 1;
                y = current.y;
                if(x < N && map[x][y] != null && val1 == map[x][y].val[0] && !visited[x][y]) {
                    visited[x][y] = true;
                    queue.offer(new Location(x, y, current.distance+1, current.list, map[x][y].index));
                }

                // 5
                x = current.x - 1;
                y = current.y - 1;
                if(-1 < x && -1 < y && val0 == map[x][y].val[1] && !visited[x][y]) {
                    visited[x][y] = true;
                    queue.offer(new Location(x, y, current.distance+1, current.list, map[x][y].index));
                }

                // 6
                x = current.x - 1;
                y = current.y;
                if(-1 < x && map[x][y] != null && val1 == map[x][y].val[0] && !visited[x][y]) {
                    visited[x][y] = true;
                    queue.offer(new Location(x, y, current.distance+1, current.list, map[x][y].index));
                }

            // odd
            } else {
                // 2
                x = current.x + 1;
                y = current.y;
                if(x < N && map[x][y] != null && val0 == map[x][y].val[1] && !visited[x][y]) {
                    visited[x][y] = true;
                    queue.offer(new Location(x, y, current.distance+1, current.list, map[x][y].index));
                }
    
                // 3
                x = current.x + 1;
                y = current.y + 1;
                if(x < N && y < N && map[x][y] != null && val1 == map[x][y].val[0] && !visited[x][y]) {
                    visited[x][y] = true;
                    queue.offer(new Location(x, y, current.distance+1, current.list, map[x][y].index));
                }

                // 5
                x = current.x - 1;
                y = current.y;
                if(-1 < x && val0 == map[x][y].val[1] && !visited[x][y]) {
                    visited[x][y] = true;
                    queue.offer(new Location(x, y, current.distance+1, current.list, map[x][y].index));
                }

                // 6
                x = current.x - 1;
                y = current.y + 1;
                if(-1 < x && y < N && map[x][y] != null && val1 == map[x][y].val[0] && !visited[x][y]) {
                    visited[x][y] = true;
                    queue.offer(new Location(x, y, current.distance+1, current.list, map[x][y].index));
                }
            }
        }

        System.out.println(minDistance);
        tempList.forEach((n) -> System.out.print(n + " "));
    }
}