import java.util.Scanner;

public class Main {
    static int N;
    static int[] towers, mem;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        towers = new int[N + 1];
        mem = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            towers[i] = sc.nextInt();
        }
        sc.close();

        for (int i = 1; i < N + 1; i++) {
            if (i == 0) {
                mem[i] = 0;
            } else {
                int j = i - 1;
                while (towers[j] < towers[i] && j != 0) {
                    j = mem[j];
                }

                mem[i] = j;
            }
        }

        for (int i = 1; i < N + 1; i++) {
            System.out.print(mem[i] + " ");
        }

    }
}