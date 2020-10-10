import java.util.Scanner;

public class Main {
    static int N, k;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        k = sc.nextInt();
        sc.close();

        int ans = 0;
        int left = 1;
        int right = k;
        while (left <= right) {
            int mid = (left + right) / 2;
            int count = 0;
            for (int i = 1; i <= N; i++) {
                count += Math.min(mid / i, N);
            }

            if (k <= count) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(ans);
    }
}

// import java.util.Scanner;

// public class Main {
//     static long N, k;
//     static long max_k = 1000000000;
//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         N = sc.nextLong();
//         k = sc.nextLong();
//         sc.close();

//         long ans = 0;
//         long left = 1;
//         long right = Math.min(N*N, max_k);
//         while(left <= right) {
//             long mid = (left+right)/2;
//             long count = 0;
//             for(long i = 1; i <= N; i++) {
//                 count += Math.min(mid/i, N);
//             }

//             if(k <= count) {
//                 ans = mid;
//                 right = mid-1;
//             } else {
//                 left = mid+1;
//             }
//         }

//         System.out.println(ans);
//     }
// }