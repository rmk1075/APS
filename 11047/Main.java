import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int K = sc.nextInt();
        int[] A = new int[N];
        
        for(int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
        }
        sc.close();

        int count = 0;
        for(int i = N-1; -1 < i; i--) {
            if(K == 0) break;
            if(0 < K/A[i]) {
                count += (int)(K/A[i]);
                K = K%A[i];
            }
        }

        System.out.println(count);
    }
}