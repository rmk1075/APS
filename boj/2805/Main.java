import java.util.Scanner;

public class Main {
    static int N, M;
    static long[] trees;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        trees = new long[N];
        long maxVal = 0;
        for(int i = 0; i < N; i++) {
            trees[i] = sc.nextInt();
            maxVal = Math.max(maxVal, trees[i]);
        }
        sc.close();

        System.out.println(search(0, maxVal));
    }

    public static long search(long left, long right) {
        long ans = 0;
        long height = 0;
        while (left <= right) {
            height = (left + right) / 2;
            long get = check(height);
            if (get < M) {
                right = height - 1;
            } else {
                if(get == M) return height;
                left = height + 1;
                ans = Math.max(ans, height);
            }
        }

        return ans;     
    }

    public static long check(long height) {
        long m = 0;
        for(int i = 0; i < N; i++) {
            if(height < trees[i])
                m += trees[i]-height;   
        }
        return m;
    }
}