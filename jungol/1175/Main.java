import java.util.Scanner;

public class Main {

    static int N, M;
    static int[] arr;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        arr = new int[N];

        dice(0, 0);

        sc.close();
    }

    public static void dice(int count, int sum) {
        if (M < sum)
            return;

        if (count == N) {
            if (sum == M) {
                for (int i = 0; i < N; i++) {
                    System.out.print(arr[i] + " ");
                }
                System.out.println();
            }

            return;
        }

        for (int i = 1; i < 7; i++) {
            arr[count] = i;
            dice(count + 1, sum + i);
        }
    }

}