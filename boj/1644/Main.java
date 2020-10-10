import java.util.Scanner;

public class Main {
    static int N, front, tail, count = 0;
    static boolean[] arr;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        sc.close();
        arr = new boolean[N+1];

        for(int i = 2; i <= (int)Math.sqrt(N); i++) {
            if(arr[i]) continue;

            for(int j = i*2; j <= N; j += i) {
                arr[j] = true;
            }
        }

        int sum = 0;
        front = tail = 2;
        while(tail < N+1) {
            if(sum < N) {
                sum += tail++;

            } else {
                if(sum == N) count++;
                sum -= front++;
            }

            while(tail < N+1 && arr[tail]) tail++;
            while(arr[front]) front++;
        }

        while(N < sum) {
            sum -= front++;

            while(front <= tail && arr[front]) front++;
        }

        if(sum == N) count++;

        System.out.println(count);
    }
}