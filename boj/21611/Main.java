import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] marbles = new int[4];
    static int[] arr;
    static int[][][] board;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N * N];
        board = new int[N][N][2];   // {marble, index}
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) board[i][j][0] = Integer.parseInt(st.nextToken());
        }

        // set arr
        set();

        // blizard
        while(0 < M--) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            blizard(d, s);
            move();
            while(bomb()) move();
            change();
        }

        System.out.println(marbles[1] + (2 * marbles[2]) + (3 * marbles[3]));
    }

    private static void change() {
        int[] arr_new = new int[N * N];
        int j = 1;
        int limit = N * N, val = arr[1], count = 1;
        for(int i = 2; i < limit && j < limit; i++) {
            if(arr[i] == val) count++;
            else {
                arr_new[j++] = count;
                if(j == limit) break;
                arr_new[j++] = val;

                if(arr[i] == 0) break;
                val = arr[i];
                count = 1;
            }
        }

        arr = arr_new;
    }

    private static boolean bomb() {
        boolean flag = false;
        int val = arr[1], count = 1;
        for(int i = 2; i < N * N; i++) {
            if(arr[i] == val) count++;
            else {
                if(3 < count) {
                    if(val != 0) flag = true;
                    marbles[val] += count;

                    int j = i - 1;
                    while(0 < count--) {
                        arr[j--] = 0;
                    }
                }

                val = arr[i];
                count = 1;
            }
        }

        if(3 < count) {
            if(val != 0) flag = true;
            marbles[val] += count;
            int j = N * N - 1;
            while(0 < count--) {
                arr[j--] = 0;
            }
        }

        return flag;
    }

    private static void move() {
        for(int i = 2; i < N * N; i++) {
            if(arr[i] == 0 || arr[i - 1] != 0) continue;
            int j = i;
            while(1 < j && arr[j - 1] == 0) j--;

            arr[j] = arr[i];
            arr[i] = 0;
        }
    }

    private static void blizard(int d, int s) {
        int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
        int x = N / 2, y = N / 2;
        while(0 < s--) {
            x += dx[d];
            y += dy[d];
            if(x < 0 || y < 0 || x == N || y == N) break;
            int index = board[x][y][1];
            arr[index] = 0;
        }
    }

    private static void set() {
        int x = N / 2, y = N / 2, count = 1, d = 0, index = 1;
        int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
        boolean once = true;
        while(true) {
            for(int j = 0; j < count; j++) {
                x += dx[d];
                y += dy[d];
                if(x < 0 || y < 0 || x == N || y == N) return ;
                arr[index] = board[x][y][0];
                board[x][y][1] = index;
                index++;
            }

            if(once) {
                once = false;
            } else {
                count++;
                once = true;
            }

            d = d + 1 < 4 ? d + 1 : 0;
        }
    }
}