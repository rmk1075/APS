import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int N = 0, judge = 0, ans = Integer.MAX_VALUE, orderVisit = 0, order[] = new int[5], field[][] = new int[5][5], pieces[][] = new int[5][2], picked[][] = new int[5][2];
    static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
    static char[][] map = new char[5][5];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i = 0; i < 5; i++) {
            int j = 0;
            for(char ch : br.readLine().toCharArray()) {
                if(ch == '*') {
                    pieces[N][0] = i;
                    pieces[N++][1] = j;
                }
                map[i][j++] = ch;
            }
        }

        for(int i = 0; i < N; i++) judge |= (1 << i);
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                picked[0][0] = i;
                picked[0][1] = j;
                field[i][j] = 1;
                pick(i, j, 1);
                field[i][j] = 0;
            }
        }

        System.out.println(ans);
    }

    public static void cal(int cnt) {
        if(cnt == N) {
            int dist = 0;
            for(int i = 0; i < N; i++) dist += Math.abs(pieces[order[i]][0] - picked[i][0]) + Math.abs(pieces[order[i]][1] - picked[i][1]);
            ans = Math.min(ans, dist);
            return ;
        }

        for(int i = 0; i < N; i++) {
            if((orderVisit & (1 << i)) != 0) continue;
            orderVisit |= (1 << i);
            order[cnt] = i;
            cal(cnt + 1);
            orderVisit &= ~(1 << i);
        }
    }

    public static void pick(int x, int y, int cnt) {
        if(cnt == N) {
            if(check()) {
                for(int i = 0; i < N; i++) {
                    orderVisit |= (1 << i);
                    order[0] = i;
                    cal(1);
                    orderVisit &= ~(1 << i);
                }
            }

            return ;
        }

        for(int i = x; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                if(i * 5 + j <= x * 5 + y) continue;
                picked[cnt][0] = i;
                picked[cnt][1] = j;
                field[i][j] = cnt+1;
                pick(i, j, cnt+1);
                field[i][j] = 0;
            }
        }
    }

    public static boolean check() {
        int checked = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(picked[0]);
        checked |= (1 << 0);

        while(!queue.isEmpty()) {
            int x, y, current[] = queue.poll();
            for(int d = 0; d < 4; d++) {
                x = current[0] + dx[d];
                y = current[1] + dy[d];
                if(x < 0 || y < 0 || x == 5 || y == 5 || field[x][y] == 0 || (checked & (1 << (field[x][y]-1))) != 0) continue;
                checked |= (1 << (field[x][y] - 1));
                queue.offer(picked[field[x][y] - 1]);
            }
        }
        return checked == judge;
    }
}