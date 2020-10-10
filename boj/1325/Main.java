import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static boolean[] visited;
    static ArrayList<Integer>[] cpu;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        cpu = new ArrayList[N];
        for (int i = 0; i < N; i++) cpu[i] = new ArrayList<>();

        int a, b;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            a = Integer.parseInt(st.nextToken()) - 1;
            b = Integer.parseInt(st.nextToken()) - 1;

            cpu[b].add(a);
        }

        ArrayList<Integer> result = new ArrayList<>();
        int hacked, maxCount = 0;
        for (int i = 0; i < N; i++) {
            hacked = find(i);

            if (maxCount == hacked) {
                result.add(i);
            } else if (maxCount < hacked) {
                maxCount = hacked;
                result.clear();
                result.add(i);
            }
        }

        Collections.sort(result);
        result.forEach((c) -> sb.append((c + 1) + " "));

        System.out.println(sb.toString());
    }

    public static int find(int index) {
        visited = new boolean[N];
        visited[index] = true;

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(index);

        int count = 1;
        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int idx : cpu[current]) {
                if (visited[idx]) continue;

                visited[idx] = true;
                count++;
                queue.add(idx);
            }
        }

        return count;
    }
}

// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.util.ArrayList;
// import java.util.Collections;
// import java.util.StringTokenizer;

// public class Main {
//     static int N, M;
//     static int[] hacked;
//     static boolean[] visited;
//     static ArrayList<Integer>[] cpu;
//     public static void main(String[] args) throws IOException {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         StringBuilder sb = new StringBuilder();
//         StringTokenizer st = new StringTokenizer(br.readLine());

//         N = Integer.parseInt(st.nextToken());
//         M = Integer.parseInt(st.nextToken());
//         hacked = new int[N];
//         cpu = new ArrayList[N];
//         for(int i = 0; i < N; i++) cpu[i] = new ArrayList<>();
        
//         int a, b;
//         for(int i = 0; i < M; i++) {
//             st = new StringTokenizer(br.readLine());

//             a = Integer.parseInt(st.nextToken()) - 1;
//             b = Integer.parseInt(st.nextToken()) - 1;

//             cpu[a].add(b);
//         }

//         for(int i = 0; i < N; i++) {
//             visited = new boolean[N];
//             visited[i] = true;
//             hacked[i]++;
//             find(i);
//         }

//         // find maxVal and indecies from array hacked
//         // print the indecies
//         ArrayList<Integer> list = new ArrayList<>();
//         int maxCount = 0;
//         for(int i = 0; i < hacked.length; i++) {
//             if(hacked[i] == maxCount) list.add(i);
//             else if(maxCount < hacked[i]) {
//                 maxCount = hacked[i];
//                 list.clear();
//                 list.add(i);
//             }
//         }

//         Collections.sort(list);
//         list.forEach((c) -> sb.append((c+1) + " "));

//         System.out.println(sb.toString());
//     }
    
//     public static void find(int index) {
//         for(int idx : cpu[index]) {
//             if(visited[idx]) continue;

//             visited[idx] = true;
//             hacked[idx]++;
//             find(idx);
//         }
//     }
// }