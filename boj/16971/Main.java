import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N, M, sum, result;
    static int[] rows, cols;
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        init();
        findRows();
        findCols();
        System.out.println(result);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] NM = br.readLine().split(" ");
        N = Integer.parseInt(NM[0]);
        M = Integer.parseInt(NM[1]);

        arr = new int[N][M];
        for(int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            for(int j = 0; j < M; j++) arr[i][j] = Integer.parseInt(line[j]);
        }

        calRows();
        calCols();

        sum = 0;
        for(int i = 0; i < N; i++) sum += rows[i];
        result = sum;
    }

    private static void calRows() {
      rows = new int[N];
      rows[0] = arr[0][0] + arr[0][M - 1];
      for(int i = 1; i < M - 1; i++) rows[0] += arr[0][i] * 2;
      for(int i = 1; i < N - 1; i++) {
        rows[i] += arr[i][0] * 2 + arr[i][M - 1] * 2;
        for(int j = 1; j < M - 1; j++) {
          rows[i] += arr[i][j] * 4;
        }
      }
      rows[N - 1] = arr[N - 1][0] + arr[N - 1][M - 1];
      for(int i = 1; i < M - 1; i++) rows[N - 1] += arr[N - 1][i] * 2;
    }

    private static void calCols() {
      cols = new int[M];
      cols[0] = arr[0][0] + arr[N - 1][0];
      for(int i = 1; i < N - 1; i++) cols[0] += arr[i][0] * 2;
      for(int i = 1; i < M - 1; i++) {
        cols[i] += arr[0][i] * 2 + arr[N - 1][i] * 2;
        for(int j = 1; j < N - 1; j++) {
          cols[i] += arr[j][i] * 4;
        }
      }
      cols[M - 1] = arr[0][M - 1] + arr[N - 1][M - 1];
      for(int i = 1; i < N - 1; i++) cols[M - 1] += arr[i][M - 1] * 2;
    }

    private static void findRows() {
      int row0 = rows[0];
      int rowN = rows[N - 1];
      for(int i = 1; i < N - 1; i++) {
        int temp = rows[i] / 2;
        result = Math.max(
          sum - temp + row0,
          Math.max(
            result,
            sum - temp + rowN
          )
        );
      }
    }

    private static void findCols() {
      int col0 = cols[0];
      int colM = cols[M - 1];
      for(int i = 1; i < M - 1; i++) {
        int temp = cols[i] / 2;
        result = Math.max(
          sum - temp + col0,
          Math.max(
            result,
            sum - temp + colM
          )
        );
      }
    }
}