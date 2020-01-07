import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[] P = new int[N];
        for(int i = 0; i < N; i++) {
            P[i] = sc.nextInt();
        }
        sc.close();

        Arrays.sort(P);
        int time = 0;
        for(int i = 0; i < N; i++) {
            time += (N-i)*P[i];
        }

        System.out.println(time);
    }
}