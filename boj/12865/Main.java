import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
    
        int N = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());
        int[] bag = new int[K+1];
        bag[0] = 0;
        
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken()), v = Integer.parseInt(st.nextToken());
            for(int j = 0; j <= K-w; j++) bag[j+w] = Math.max(bag[j+w], bag[j]+v);
        }

        int maxVal = 0;
        for(int i = 0; i <= K; i++) maxVal = Math.max(maxVal, bag[i]);

        System.out.println(maxVal);
    }
}

// import java.util.Scanner;

// public class Main {
//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         int N = sc.nextInt();
//         int K = sc.nextInt();

//         int[] bag = new int[K+1];
//         bag[0] = 0;

//         for(int i = 0; i < N; i++) {
//             int w = sc.nextInt();
//             int v = sc.nextInt();
//             for(int j = K-w; -1 < j; j--) {
//                 if(bag[j] != -1) {
//                     bag[j+w] = Math.max(bag[j+w], bag[j]+v);
//                 }
//             }
//         }
//         sc.close();

//         int max_val = 0;
//         for(int i = 0; i < K+1; i++) {
//             max_val = Math.max(max_val, bag[i]);
//         }
        
//         System.out.println(max_val);
//     }
// }