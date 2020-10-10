import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();
        sc.close();

        int[] list = new int[M];

        tracking(N, M, list, 0);
    }

    private static void tracking(int N, int M, int[] list, int count) {
        if(count == M) {
            for(int i = 0; i < count; i++) {
                System.out.print(list[i]+" ");
            }
            System.out.println();
            return ;
        }

        for(int i = 1; i < N+1; i++) {
            if(count == 0 || list[count-1] <= i) {
                list[count] = i;
                tracking(N, M, list, count+1);
                list[count] = 0;
            }
        }
    }
}