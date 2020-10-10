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
    static int N, M, P, sum;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
    static int[] S, castle;
    static int[][] map;
    static Queue<Location>[] players;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        S = new int[P];
        castle = new int[P];
        players = new Queue[P];
        for(int i = 0; i < P; i++) players[i] = new LinkedList<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < P; i++) S[i] = Integer.parseInt(st.nextToken());

        // '.': -1, '#': -2
        for(int i = 0; i < N; i++) {
            int j = 0;
            for(char ch : br.readLine().toCharArray()) {
                if(Character.isDigit(ch)) {
                    map[i][j] = ch - '1';
                    castle[map[i][j]]++;
                    sum++;
                    players[map[i][j]].offer(new Location(i, j++));
                } else {
                    if(ch == '.') map[i][j++] = -1;
                    else map[i][j++] = -2;
                }
            }
        }

        int past = 0;
        while(past != sum) {
            past = sum;
            for(int i = 0; i < P; i++) {
                int cnt = 0;
                while(!players[i].isEmpty()) {
                    int size = players[i].size();
                    while(0 < size--) {
                        Location current = players[i].poll();
                        for(int d = 0; d < 4; d++) {
                            int x = current.x + dx[d], y = current.y + dy[d];
                            if(x < 0 || y < 0 || x == N || y == M || map[x][y] != -1) continue;
                            map[x][y] = i;
                            castle[i]++;
                            sum++;
                            players[i].offer(new Location(x, y));
                        }
                    }

                    cnt++;
                    if(cnt == S[i]) break;
                }
            }
        }
        for(int num : castle) System.out.print(num + " ");
    }
}