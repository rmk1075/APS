import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
    static int ans = 0, visited[] = new int[6];
    static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
    static boolean isFire = false;
    static char[][] field = new char[12][6];
    static Queue<Location> queue = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i = 0; i < 12; i++) field[i] = br.readLine().toCharArray();

        check();
        while(isFire) {
            ans++;
            for(int i = 10; -1 < i; i--) {
                for(int j = 0; j < 6; j++) {
                    if(field[i][j] == '.') continue;
                    int a = i+1;
                    while(a < 12 && field[a][j] == '.') {
                        field[a][j] = field[a-1][j];
                        field[a-1][j] = '.';
                        a++;
                    }
                }
            }

            isFire = false;
            for(int i = 0; i < 6; i++) visited[i] = 0;
            check();
        }
        System.out.println(ans);
    }

    public static void check() {
        for(int i = 0; i < 12; i++) {
            for(int j = 0; j < 6; j++) {
                if(field[i][j] == '.') continue;
                
                char ch = field[i][j];
                queue.clear();
                queue.offer(new Location(i, j));
                visited[j] |= (1 << i);

                int x, y, cnt = 1;
                while(!queue.isEmpty()) {
                    Location current = queue.poll();
                    for(int d = 0; d < 4; d++) {
                        x = current.x + dx[d];
                        y = current.y + dy[d];
                        if(x < 0 || y < 0 || x == 12 || y == 6 || field[x][y] != ch || (visited[y] & (1 << x)) != 0) continue;
                        visited[y] |= (1 << x);
                        cnt++;
                        queue.offer(new Location(x, y));
                    }
                }

                if(3 < cnt) {
                    isFire = true;
                    for(int a = 0; a < 12; a++) {
                        for(int b = 0; b < 6; b++) {
                            if((visited[b] & (1 << a)) == 0) continue;
                            field[a][b] = '.';
                        }
                    }
                }

                for(int k = 0; k < 6; k++) visited[k] = 0;
            }
        }
    }
}