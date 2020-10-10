// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.util.ArrayList;
// import java.util.StringTokenizer;

// public class Main {
//     static int N, visited = 0, maxCnt = 0, arr[], order[];
//     static ArrayList<Integer> vals = new ArrayList<>();
//     public static void main(String[] args) throws IOException {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         N = Integer.parseInt(br.readLine());
//         arr = new int[N];
//         order = new int[N];
//         StringTokenizer st = new StringTokenizer(br.readLine());
//         for(int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
//         for(int i = 0; i < N; i++) {
//             visited |= 1 << i;
//             order[0] = i;
//             check(1);
//             visited &= ~(1 << i);
//         }
//         System.out.println(maxCnt);
//     }

//     public static void check(int count) {
//         if(count == N) {
//             vals.clear();
//             vals.add(0);
//             int temp = 0;
//             for(int i = 0; i < N-1; i++) {
//                 temp += arr[order[i]];
//                 vals.add(temp);
//             }

//             int cnt = 0;
//             for(int i : vals) {
//                 if(49 < i) break;
//                 if(vals.contains(i + 50)) cnt++;
//             }

//             maxCnt = Math.max(maxCnt, cnt);
//             return ;
//         }

//         for(int i = 0; i < N; i++) {
//             if((visited & (1 << i)) == 0) {
//                 visited |= 1 << i;
//                 order[count] = i;
//                 check(count + 1);
//                 visited &= ~(1 << i);
//             }
//         }
//     }
// }

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, visited = 0, maxCnt = 0, arr[], order[];
    static boolean[] vals = new boolean[100];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        order = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        vals[0] = true;
        for(int i = 0; i < N; i++) {
            visited |= 1 << i;
            order[0] = i;
            check(1);
            visited &= ~(1 << i);
        }

        System.out.println(maxCnt);
    }

    public static void check(int count) {
        if(count == N) {
            int temp = 0;
            for(int i = 1; i < 100; i++) vals[i] = false;
            for(int i = 0; i < N-1; i++) {
                temp += arr[order[i]];
                vals[temp] = true;
            }

            int cnt = 0;
            for(int i = 0; i < 50; i++) {
                if(vals[i] && vals[i + 50]) cnt++;
            }

            maxCnt = Math.max(maxCnt, cnt);
            return ;
        }

        for(int i = 0; i < N; i++) {
            if((visited & (1 << i)) == 0) {
                visited |= 1 << i;
                order[count] = i;
                check(count + 1);
                visited &= ~(1 << i);
            }
        }
    }
}