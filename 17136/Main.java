import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    static int N = 10;
    static int minValue = Integer.MAX_VALUE;
    static int[] pieces = {0, 0, 0, 0, 0, 0};
    static int[][] paper = new int[N][N];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        br.close();

        dfs(0, 0);

        if(minValue == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(minValue);
        }
    }

    public static void dfs(int x, int y) {
        if(x == N) {

            if(isClear()) {
                int temp = 0;
                for(int i = 1; i < 6; i++) temp += pieces[i];

                minValue = Math.min(minValue, temp);
            }

            return ;
        }

        if(paper[x][y] == 1) {
            for(int i = 1; i < 6; i++) {
                if(pieces[i] == 5) continue;

                if(cover(x, y, i)) {

                    if(y + 1 < N)
                        dfs(x, y + 1);
                    else
                        dfs(x + 1, 0);

                    uncover(x, y, i);
                }
            }

        } else {
            if(y + 1 < N)
                dfs(x, y + 1);
            else
                dfs(x + 1, 0);
        }
    }

    public static boolean cover(int x, int y, int size) {
        boolean isCovered = true;

        // check the field
        for(int i = x; i < x + size; i++) {
            for(int j = y; j < y + size; j++) {
                if(N - 1 < i || N - 1 < j || paper[i][j] == 0) return false;
            }
        }

        // cover the field
        for(int i = x; i < x + size; i++) {
            for(int j = y; j < y + size; j++) {
                paper[i][j] = 0;
            }
        }

        // change the pieces arr
        pieces[size]++;
        
        return isCovered;
    }

    public static void uncover(int x, int y, int size) {
        // uncover the field
        for(int i = x; i < x + size; i++) {
            for(int j = y; j < y + size; j++) {
                paper[i][j] = 1;
            }
        }

        // change the pieces arr
        pieces[size]--;
    }

    public static boolean isClear() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(paper[i][j] == 1) return false;
            }
        }

        return true;
    }
}