import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int T, V, E;
    static int[] color;
    static ArrayList<Integer>[] list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        while(0 < T--) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            color = new int[V+1];

            list = new ArrayList[V+1];
            for(int i = 1; i < V+1; i++) {
                list[i] = new ArrayList<Integer>();
            }

            int u, v;
            for(int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                u = Integer.parseInt(st.nextToken());
                v = Integer.parseInt(st.nextToken());

                list[u].add(v);
                list[v].add(u);
            }

            boolean check = true;
            loop:
            for(int i = 1; i < V+1; i++) {
                if(color[i] == 0) {
                    color[i] = 1;
                }

                if(!dfs(i)) {
                    check = false;
                    break loop;
                }
            }

            if(check) sb.append("YES\n");
            else sb.append("NO\n");
        }

        System.out.println(sb.toString());
    }

    public static boolean dfs(int index) {

        for(int i : list[index]) {
            if(color[i] == 0) {
                color[i] = (color[index] == 1) ? 2 : 1;
                dfs(i);
            } else {
                if(color[index] == color[i]) return false;
            }
        }

        return true;
    }
}

// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.LinkedList;
// import java.util.Queue;
// import java.util.StringTokenizer;

// public class Main {
//     static int T, V, E;
//     static int[] color;
//     static ArrayList<Integer>[] list;
//     public static void main(String[] args) throws IOException {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         StringBuilder sb = new StringBuilder();
//         StringTokenizer st;

//         T = Integer.parseInt(br.readLine());
//         while(0 < T--) {
//             st = new StringTokenizer(br.readLine());
//             V = Integer.parseInt(st.nextToken());
//             E = Integer.parseInt(st.nextToken());
//             color = new int[V+1];

//             list = new ArrayList[V+1];
//             for(int i = 1; i < V+1; i++) {
//                 list[i] = new ArrayList<Integer>();
//             }

//             int u, v;
//             for(int i = 0; i < E; i++) {
//                 st = new StringTokenizer(br.readLine());
//                 u = Integer.parseInt(st.nextToken());
//                 v = Integer.parseInt(st.nextToken());

//                 list[u].add(v);
//                 list[v].add(u);
//             }

//             boolean check = true;
//             Queue<Integer> queue = new LinkedList<>();
//             loop:
//             for(int i = 1; i < V+1; i++) {
//                 if(color[i] == 0) {
//                     color[i] = 1;
//                     queue.offer(i);
                    
//                     while(!queue.isEmpty()) {
//                         int current = queue.poll();

//                         for(int j : list[current]) {
//                             if(color[j] == 0) {
//                                 color[j] = (color[current] == 1) ? 2 : 1;
//                                 queue.offer(j);
//                             } else {
//                                 if(color[current] == color[j]) {
//                                     check = false;
//                                     break loop;
//                                 }
//                             }
//                         }
//                     }
//                 }

//                 // TODO:
//                 // System.out.println(Arrays.toString(color));
//             }

//             if(check) sb.append("YES\n");
//             else sb.append("NO\n");
//         }

//         System.out.println(sb.toString());
//     }
// }