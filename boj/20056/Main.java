import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class FireBall {
    int r, c, m, s, d;

    FireBall(int r, int c, int m, int s, int d) {
        this.r = r;
        this.c = c;
        this.m = m;
        this.s = s;
        this.d = d;
    }
}

public class Main {
    static int N, M, K;
    static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 }, dy = { 0, 1, 1, 1, 0, -1, -1, -1 };
    static List<FireBall> fireBalls;
    static List<FireBall>[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        fireBalls = new ArrayList<>();
        map = new List[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                map[i][j] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            map[r][c].add(new FireBall(r, c, m, s, d));
        }

        while (0 < K--) {
            fireBalls.clear();

            order();

            for (FireBall fb : fireBalls)
                map[fb.r][fb.c].add(fb);

            check();
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (FireBall fb : map[i][j]) {
                    answer += fb.m;
                }
            }
        }

        System.out.println(answer);
    }

    public static void order() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j].isEmpty())
                    continue;

                for (FireBall fb : map[i][j]) {
                    if (fb.m == 0)
                        continue;

                    move(fb, i, j);
                }

                map[i][j].clear();
            }
        }
    }

    public static void move(FireBall fb, int x, int y) {
        x += fb.s * dx[fb.d];
        y += fb.s * dy[fb.d];

        while (x < 0) {
            x += N;
        }

        while (y < 0) {
            y += N;
        }

        while (N <= x) {
            x -= N;
        }

        while (N <= y) {
            y -= N;
        }

        fireBalls.add(new FireBall(x, y, fb.m, fb.s, fb.d));
    }

    public static void check() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (1 < map[i][j].size())
                    divide(i, j, map[i][j].size());
            }
        }
    }

    public static void divide(int r, int c, int size) {
        int m = 0, s = 0;
        boolean isEven = true, isOdd = true;
        for (FireBall fb : map[r][c]) {
            m += fb.m;
            s += fb.s;
            if (fb.d % 2 != 0)
                isEven = false;
            else
                isOdd = false;
        }

        map[r][c].clear();
        if (m < 5)
            return;

        m /= 5;
        s /= size;

        if (isEven || isOdd) {
            map[r][c].add(new FireBall(r, c, m, s, 0));
            map[r][c].add(new FireBall(r, c, m, s, 2));
            map[r][c].add(new FireBall(r, c, m, s, 4));
            map[r][c].add(new FireBall(r, c, m, s, 6));
            return;
        }

        map[r][c].add(new FireBall(r, c, m, s, 1));
        map[r][c].add(new FireBall(r, c, m, s, 3));
        map[r][c].add(new FireBall(r, c, m, s, 5));
        map[r][c].add(new FireBall(r, c, m, s, 7));
    }
}