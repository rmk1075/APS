import java.util.Scanner;

public class Main {

    static int N, M;
    static int[] arr;
    static boolean[] visited3;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        arr = new int[N];

        switch (M) {
        case 1:
            dice1(0);
            break;
        case 2:
            dice2(1, 0);
            break;
        case 3:
            visited3 = new boolean[7];
            dice3(0);
            break;
        }

        sc.close();
    }

    public static void dice1(int count) {
        if (count == N) {
            for (int i = 0; i < N; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
            return;
        }

        for (int i = 1; i < 7; i++) {
            arr[count] = i;
            dice1(count + 1);
        }
    }

    public static void dice2(int idx, int count) {
        if (count == N) {
            for (int i = 0; i < N; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
            return;
        }

        for (int i = 1; i < 7; i++) {
            if (idx <= i) {
                arr[count] = i;
                dice2(i, count + 1);
            }
        }
    }

    public static void dice3(int count) {
        if (count == N) {
            for (int i = 0; i < N; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
            return;
        }

        for (int i = 1; i < 7; i++) {
            if (!visited3[i]) {
                visited3[i] = true;
                arr[count] = i;
                dice3(count + 1);
                visited3[i] = false;
            }
        }
    }
}