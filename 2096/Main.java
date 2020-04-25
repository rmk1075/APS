import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, map[][], max[][], min[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N+1][3];
        max = new int[N+1][3];
        min = new int[N+1][3];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < 3; j++) max[i][j] = min[i][j] = -1;
        }

        StringTokenizer st;
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < 3; i++) max[N][i] = min[N][i] = map[N][i];

        find(0, 1);
        System.out.println(max[0][1] + " " + min[0][1]);
    }

    public static void find(int row, int col) {
        if(max[row][col] == -1) {
            switch(col) {
                case 0:
                    find(row + 1, 0);
                    find(row + 1, 1);
                    max[row][col] = map[row][col] + Math.max(max[row + 1][0], max[row + 1][1]);
                    min[row][col] = map[row][col] + Math.min(min[row + 1][0], min[row + 1][1]);
                    break;
                case 1:
                    find(row + 1, 0);
                    find(row + 1, 1);
                    find(row + 1, 2);
                    max[row][col] = Math.max(max[row + 1][0], max[row + 1][1]);
                    max[row][col] = Math.max(max[row][col], max[row + 1][2]) + map[row][col];
                    min[row][col] = Math.min(min[row + 1][0], min[row + 1][1]);
                    min[row][col] = Math.min(min[row][col], min[row + 1][2]) + map[row][col];
                    break;
                case 2:
                    find(row + 1, 1);
                    find(row + 1, 2);
                    max[row][col] = Math.max(max[row + 1][1], max[row + 1][2]) + map[row][col];
                    min[row][col] = Math.min(min[row + 1][1], min[row + 1][2]) + map[row][col];
                    break;
            }
        }
    }
}