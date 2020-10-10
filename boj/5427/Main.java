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
    static int T, W, H;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
    static char[][] map = new char[1000][1000];
    static Queue<Location> fire = new LinkedList<>(), sg = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        while(0 < T--) {
            st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            fire.clear();
            sg.clear();
            for(int i = 0; i < H; i++) {
                char[] line = br.readLine().toCharArray();
                for(int j = 0; j < W; j++) {
                    map[i][j] = line[j];
                    if(map[i][j] == '@') sg.offer(new Location(i, j));
                    else if(map[i][j] == '*') fire.offer(new Location(i, j));
                }
            }

            boolean isEscape = false;
            int size, x, y, time = 1;
            loop: while(!sg.isEmpty()) {
                size = fire.size();
                while(0 < size--) {
                    Location current = fire.poll();
                    for(int d = 0; d < 4; d++) {
                        x = current.x + dx[d];
                        y = current.y + dy[d];
                        if(x < 0 || y < 0 || x == H || y == W || map[x][y] == '*' || map[x][y] == '#') continue;
                        map[x][y] = '*';
                        fire.offer(new Location(x, y));
                    }
                }

                size = sg.size();
                while(0 < size--) {
                    Location current = sg.poll();
                    for(int d = 0; d < 4; d++) {
                        x = current.x + dx[d];
                        y = current.y + dy[d];
                        if(x < 0 || y < 0 || x == H || y == W) {
                            isEscape = true;
                            break loop;
                        }
                        if(map[x][y] != '.') continue;
                        map[x][y] = '@';
                        sg.offer(new Location(x, y));
                    }
                }

                time++;
            }

            sb.append(isEscape ? time + "\n" : "IMPOSSIBLE\n");
        }
        System.out.println(sb);
    }
}