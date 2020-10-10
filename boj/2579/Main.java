import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N, stairs[] = new int[301], dp[] = new int[301];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for(int i = 1; i <= N; i++) stairs[i] = Integer.parseInt(br.readLine());

        dp[1] = stairs[1];
        dp[2] = stairs[1] + stairs[2];
        for(int i = 3; i <= N; i++) dp[i] = stairs[i] + Math.max(stairs[i-1] + dp[i-3], dp[i-2]);
        System.out.println(dp[N]);
    }
}

// import java.util.Scanner;

// public class Main {
//     static int[] stairs, points;    
//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         int N = sc.nextInt();
        
//         stairs = new int[N+1];
//         points = new int[N+1];
//         for(int i = 1; i < N+1; i++) {
//             stairs[i] = sc.nextInt();
//         }
//         sc.close();

//         if(N < 3) {
//             int sum = 0;
//             for(int i = 1; i < N+1; i++) {
//                 sum += stairs[i];
//             }
//             System.out.println(sum);
//         } else {
//             points[1] = stairs[1];
//             points[2] = points[1] + stairs[2];
//             for(int i = 3; i < N+1; i++) {
//                 points[i] = stairs[i] + Math.max(stairs[i-1]+points[i-3], points[i-2]);
//             }
//             System.out.println(points[N]);
//         }
//     }
// }