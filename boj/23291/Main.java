import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int[][] fishes;
    static int[][] temp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        fishes = new int[N][N];
        temp = new int[N][N];
        for(int i = 0; i < N; i++) Arrays.fill(fishes[i], -1);

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) fishes[N - 1][i] = Integer.parseInt(st.nextToken());

        int cnt = 0;
        while(!isOK()) {
            add();
            
            // 1 :: length = 1 ; length *= 2
            arrange1();
            
            // 2 :: length = N // 2 ; length //= 2
            arrange2();

            cnt++;
        }
        System.out.println(cnt);
    }

    public static boolean isOK() {
        int maxVal = -1;
        int minVal = Integer.MAX_VALUE;
        for(int i = 0; i < N; i++) {
            maxVal = Math.max(maxVal, fishes[N - 1][i]);
            minVal = Math.min(minVal, fishes[N - 1][i]);
        }
        return maxVal - minVal <= K;
    }

    public static void add() {
        int minVal = Integer.MAX_VALUE;
        List<int[]> list = new LinkedList<>();
        for(int i = 0; i < N; i++) {
            if(fishes[N - 1][i] < minVal) {
                minVal = fishes[N - 1][i];
                list.clear();
                list.add(new int[]{N - 1, i});
            } else if(fishes[N - 1][i] == minVal) list.add(new int[]{N - 1, i});
        }

        for(int[] l : list) fishes[l[0]][l[1]]++;
    }

    public static void arrange1() {
        move1();
        spread();
        reposition();
    }

    public static void move1() {
        int i = 0, l = 1, r = 1, c = 1;
        while(r <= N - l) {
            int ax = N - 1, ay = l - 1;
            int bx = N - 2, by = l;
            for(int rr = 0; rr < r; rr++) {
                for(int cc = 0; cc < c; cc++) {
                    int cx = ax - rr;
                    int cy = ay - cc;
                    int nx = bx - cc;
                    int ny = by + rr;
                    fishes[nx][ny] = fishes[cx][cy];
                    fishes[cx][cy] = -1;
                }
            }

            if(i % 2 == 0) r++;
            else c++;
            l += c;
            i++;
        }
    }

    public static void spread() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(fishes[i][j] == -1) continue;
                for(int d = 0; d < 4; d++) {
                    if(available(i, j, d)) {
                        int x = i + dx[d];
                        int y = j + dy[d];
                        int dd = (fishes[i][j] - fishes[x][y]) / 5;
                        temp[i][j] -= dd;
                        temp[x][y] += dd;
                    }
                }
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(temp[i][j] != 0) {
                    fishes[i][j] += temp[i][j];
                    temp[i][j] = 0;
                }
            }
        }
    }

    public static boolean available(int cx, int cy, int d) {
        int x = cx + dx[d];
        int y = cy + dy[d];
        if(x < 0 || y < 0 || x == N || y == N || fishes[x][y] == -1 || fishes[x][y] <= fishes[cx][cy]) return false;
        return true;
    }

    public static void reposition() {
        int cnt = N - 1;
        loop: for(int i = N - 1; -1 < i; i--) {
            for(int j = 0; j < N; j++) {
                if(fishes[j][i] == -1) continue;
                temp[N - 1][cnt--] = fishes[j][i];
                if(cnt == -1) break loop;
            }
        }

        for(int i = 0; i < N - 1; i++) Arrays.fill(fishes[i], -1);
        for(int i = 0; i < N; i++) {
            fishes[N - 1][i] = temp[N - 1][i];
            temp[N - 1][i] = 0;
        }
    }

    public static void arrange2() {
        move2();
        spread();
        reposition();
    }

    public static void move2() {
        int l = N / 2;
        for(int i = 0; i < l; i++) {
            fishes[N - 2][N - 1 - i] = fishes[N - 1][i];
            fishes[N - 1][i] = -1;
        }

        int ll = l / 2;
        for(int i = 0; i < ll; i++) {
            fishes[N - 3][N - 1 - i] = fishes[N - 2][l + i];
            fishes[N - 2][l + i] = -1;
            fishes[N - 4][N - 1 - i] = fishes[N - 1][l + i];
            fishes[N - 1][l + i] = -1;
        }
    }
}