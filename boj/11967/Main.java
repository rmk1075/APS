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
    static int N, M, numOfLight = 1, count[][];
    static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
    static boolean[][] check;
    static ArrayList<Location>[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        count = new int[N + 1][N + 1];
        check = new boolean[N + 1][N + 1];
        map = new ArrayList[N + 1][N + 1];
        for(int i = 1; i < N + 1; i++) {
            for(int j = 1; j < N + 1; j++) map[i][j] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())].add(new Location(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        Queue<Location> queue = new LinkedList<>();
        queue.offer(new Location(1, 1));
        check[1][1] = true;
        
        for(Location key : map[1][1]) {
            if(!check[key.x][key.y]) {
                check[key.x][key.y] = true;
                numOfLight++;
            }
        }
        count[1][1] = numOfLight;

        while(!queue.isEmpty()) {
            Location current = queue.poll();
            if(count[current.x][current.y] < numOfLight) continue;
            
            int x, y;
            for(int d = 0; d < 4; d++) {
                x = current.x + dx[d];
                y = current.y + dy[d];
                if(x == 0 || y == 0 || N < x || N < y || !check[x][y] || count[x][y] == numOfLight) continue;
                if(count[x][y] == 0) {
                    for(Location key : map[x][y]) {
                        if(!check[key.x][key.y]) {
                            check[key.x][key.y] = true;
                            numOfLight++;
                        }
                    }
                }
                count[x][y] = numOfLight;
                queue.offer(new Location(x, y));
            }
        }
        
        System.out.println(numOfLight);
    }
}