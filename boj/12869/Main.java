import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] SCV = new int[3];
    static int[][][] update = new int[61][61][61];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) SCV[i] = Integer.parseInt(st.nextToken());

        for(int i = 0; i < 61; i++) {
            for(int j = 0; j < 61; j++) {
                Arrays.fill(update[i][j], -1);
            }
        }
        update[0][0][0] = 0;
        System.out.println(find(SCV[0], SCV[1], SCV[2]));
    }

    public static int find(int a, int b, int c) {
        if(update[a][b][c] != -1) return update[a][b][c];

        update[a][b][c] = Integer.MAX_VALUE;
        update[a][b][c] = Math.min(update[a][b][c], 1 + find((a-9 < 0) ? 0 : a-9, (b-3 < 0) ? 0 : b-3, (c-1 < 0) ? 0 : c-1)); // a, b, c
        update[a][b][c] = Math.min(update[a][b][c], 1 + find((a-9 < 0) ? 0 : a-9, (b-1 < 0) ? 0 : b-1, (c-3 < 0) ? 0 : c-3)); // a, c, b
        update[a][b][c] = Math.min(update[a][b][c], 1 + find((a-3 < 0) ? 0 : a-3, (b-9 < 0) ? 0 : b-9, (c-1 < 0) ? 0 : c-1)); // b, a, c
        update[a][b][c] = Math.min(update[a][b][c], 1 + find((a-1 < 0) ? 0 : a-1, (b-9 < 0) ? 0 : b-9, (c-3 < 0) ? 0 : c-3)); // b, c, a
        update[a][b][c] = Math.min(update[a][b][c], 1 + find((a-3 < 0) ? 0 : a-3, (b-1 < 0) ? 0 : b-1, (c-9 < 0) ? 0 : c-9)); // c, a, b
        update[a][b][c] = Math.min(update[a][b][c], 1 + find((a-1 < 0) ? 0 : a-1, (b-3 < 0) ? 0 : b-3, (c-9 < 0) ? 0 : c-9)); // c, b, a

        return update[a][b][c];
    }
}