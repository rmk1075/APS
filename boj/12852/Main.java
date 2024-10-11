import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] mem = new int[N + 1][2];
        for (int i = 0; i <= N; i++) {
            mem[i] = new int[] { 0, Integer.MAX_VALUE }; // {prev index, count}
        }
        mem[1][1] = 0;

        for (int i = 1; i <= N; i++) {
            if (mem[i][1] == Integer.MAX_VALUE) {
                continue;
            }

            int count = mem[i][1];

            // 3
            if (i * 3 <= N && count + 1 < mem[i * 3][1]) {
                mem[i * 3][0] = i;
                mem[i * 3][1] = count + 1;
            }

            // 2
            if (i * 2 <= N && count + 1 < mem[i * 2][1]) {
                mem[i * 2][0] = i;
                mem[i * 2][1] = count + 1;
            }

            // 1
            if (i + 1 <= N && count + 1 < mem[i + 1][1]) {
                mem[i + 1][0] = i;
                mem[i + 1][1] = count + 1;
            }
        }

        System.out.println(mem[N][1]);
        int index = N;
        while (true) {
            System.out.print(index + " ");
            index = mem[index][0];
            if (index == 0) {
                break;
            }
        }
    }
}