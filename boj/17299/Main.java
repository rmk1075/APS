import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, arr[], F[] = new int[1000001], NGF[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        NGF = new int[N][2];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            F[arr[i]]++;
        }

        NGF[N-1][0] = -1;
        NGF[N-1][1] = N;
        for(int i = N-2; -1 < i; i--) {
            if(F[arr[i]] < F[arr[i+1]]) {
                NGF[i][0] = arr[i+1];
                NGF[i][1] = i+1;
                continue;
            }

            int j = i+1;
            while(NGF[j][0] != -1 && F[NGF[j][0]] <= F[arr[i]]) j = NGF[j][1];
            NGF[i][0] = NGF[j][0];
            NGF[i][1] = NGF[j][1];
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) sb.append(NGF[i][0] + " ");
        System.out.println(sb);
    }
}

// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.util.HashMap;
// import java.util.Map;
// import java.util.StringTokenizer;

// public class Main {
//     static int N, arr[], NGF[][];
//     static Map<Integer, Integer> F = new HashMap<>(); 
//     public static void main(String[] args) throws IOException {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         N = Integer.parseInt(br.readLine());
//         arr = new int[N];
//         NGF = new int[N][2];
//         StringTokenizer st = new StringTokenizer(br.readLine());
//         for(int i = 0; i < N; i++) {
//             arr[i] = Integer.parseInt(st.nextToken());
//             if(F.get(arr[i]) != null) F.put(arr[i], F.get(arr[i]) + 1);
//             else F.put(arr[i], 1);
//         }

//         NGF[N-1][0] = -1;
//         NGF[N-1][1] = N;
//         for(int i = N-2; -1 < i; i--) {
//             if(F.get(arr[i]) < F.get(arr[i+1])) {
//                 NGF[i][0] = arr[i+1];
//                 NGF[i][1] = i+1;
//                 continue;
//             }

//             int j = i+1;
//             while(NGF[j][0] != -1 && F.get(NGF[j][0]) <= F.get(arr[i])) j = NGF[j][1];
//             NGF[i][0] = NGF[j][0];
//             NGF[i][1] = NGF[j][1];
//         }

//         StringBuilder sb = new StringBuilder();
//         for(int i = 0; i < N; i++) sb.append(NGF[i][0] + " ");
//         System.out.println(sb);
//     }
// }