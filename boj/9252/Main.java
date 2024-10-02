import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static char[] A;
    private static char[] B;
    private static int M;
    private static int N;

    public static void main(String[] args) throws IOException {
        init();
        int[][] mem = find();
        int len = mem[M][N];
        System.out.println(len);
        if (len != 0) {
            System.out.println(findFromMem(mem));
        }
    }

    private static String findFromMem(int[][] mem) {
        StringBuilder sb = new StringBuilder();
        int x = M;
        int y = N;
        while (0 < x && 0 < y) {
            if (A[x - 1] == B[y - 1]) {
                sb.insert(0, A[x - 1]);
                x--;
                y--;
            } else {
                if (mem[x - 1][y] < mem[x][y - 1]) {
                    y--;
                } else {
                    x--;
                }
            }
        }
        return sb.toString();
    }

    private static int[][] find() {
        int[][] mem = new int[M + 1][N + 1];
        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= N; j++) {
                if (A[i - 1] == B[j - 1]) {
                    mem[i][j] = mem[i - 1][j - 1] + 1;
                } else {
                    mem[i][j] = Math.max(mem[i - 1][j], mem[i][j - 1]);
                }
            }
        }
        return mem;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        A = br.readLine().toCharArray();
        M = A.length;

        B = br.readLine().toCharArray();
        N = B.length;
    }
}