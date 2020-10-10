import java.util.Scanner;

public class Main {
    static int[] arr;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.close();

        arr = new int[N+1];
        for(int i = 2; i < N+1; i++) {
            arr[i] = Integer.MAX_VALUE;
        }

        arr[1] = 0;
        for(int i = 1; i < N+1; i++) {
            if(i+1 < N+1) arr[i+1] = Math.min(arr[i]+1, arr[i+1]);
            if(i*2 < N+1) arr[i*2] = Math.min(arr[i]+1, arr[i*2]);
            if(i*3 < N+1) arr[i*3] = Math.min(arr[i]+1, arr[i*3]);
        }

        System.out.println(arr[N]);
    }
}