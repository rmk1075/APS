import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N, src = 1, offset = 1;
    static int[] arr = new int[10];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        while(src <= N) {
            while(N % 10 != 9 && src <= N) {
                int x = N;
                while(0 < x) {
                    arr[x % 10] += offset;
                    x /= 10;
                }
                N--;
            }

            if(N < src) break;

            while(src % 10 != 0 && src <= N) {
                int x = src;
                while(0 < x) {
                    arr[x % 10] += offset;
                    x /= 10;
                }
                src++;
            }

            src /= 10;
            N /= 10;

            for(int i = 0; i < 10; i++) arr[i] += (N - src + 1) * offset;
            offset *= 10;
        }
        
        for(int n : arr) System.out.print(n + " ");
    }
}

// import java.util.Scanner;

// public class Main {
//     static int N, src = 1, offset = 1;
//     static int[] arr = new int[10];
//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         N = sc.nextInt();
//         sc.close();

//         while(src <= N) {
//             while(N % 10 != 9 && src <= N) {
//                 cal(N);
//                 N--;
//             }

//             if(N < src) break;

//             while(src % 10 != 0 && src <= N) {
//                 cal(src);
//                 src++;
//             }

//             src /= 10;
//             N /= 10;

//             for(int i = 0; i < 10; i++) arr[i] += (N - src + 1) * offset;
//             offset *= 10;
//         }
        
//         for(int n : arr) System.out.print(n + " ");
//     }

//     public static void cal(int x) {
//         while(0 < x) {
//             arr[x % 10] += offset;
//             x /= 10;
//         }
//     }
// }