import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
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
    static int N, K, L;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static String[] command = new String[10001];
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N+1][N+1];

        StringTokenizer st;
        K = Integer.parseInt(br.readLine());
        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
        }

        L = Integer.parseInt(br.readLine());
        for(int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            command[Integer.parseInt(st.nextToken())] = st.nextToken();
        }

        int time = 0;
        Deque<Location> snake = new LinkedList<Location>();
        snake.offer(new Location(1, 1));
        
        int[] head = new int[]{1, 1};
        int[] tail = new int[]{1, 1};
        map[1][1] = 2;
        int d = 0;

        while(true) {
            
            time++;

            int x = head[0] + dx[d];
            int y = head[1] + dy[d];

            if(x < 1 || y < 1 || N < x || N < y || map[x][y] == 2) break;

            head[0] = x;
            head[1] = y;

            snake.addFirst(new Location(x, y));

            if(map[x][y] == 0) {
                // default

                snake.pollLast();

                map[tail[0]][tail[1]] = 0;
                tail[0] = snake.peekLast().x;
                tail[1] = snake.peekLast().y;
            }

            map[x][y] = 2;

            // change direction
            if(command[time] == null) continue;
            if(command[time].equals("L")) {
                d--;
                if(d < 0) d = 3;
            } else if(command[time].equals("D")) {
                d++;
                if(3 < d) d = 0;
            }

        }

        System.out.println(time);
    }
}