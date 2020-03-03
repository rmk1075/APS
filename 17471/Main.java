import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, group, totalPopulation, minDiff = Integer.MAX_VALUE;
    static int[] populations, graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        populations = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            populations[i] = Integer.parseInt(st.nextToken());
            totalPopulation += populations[i];
        }

        graph = new int[N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            for(int j = 0; j < c; j++) {
                graph[i] |= (1 << (Integer.parseInt(st.nextToken()) - 1));
            }
        }

        dfs(0, 0, 0);
        if(minDiff == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(minDiff);
    }

    public static void dfs(int index, int count, int sum) {

        if(index == N) {
            if(count == 0 || count == N) return ;
            if(bfs()) minDiff = Math.min(minDiff, Math.abs(2*sum - totalPopulation));
            return ;
        }

        dfs(index+1, count, sum);

        group |= (1 << index);
        dfs(index+1, count+1, sum + populations[index]);
        group &= ~(1 << index);
    }

    public static boolean bfs() {
        int check = 0;
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < N; i++) {
            if((group & (1 << i)) != 0) {
                queue.add(i);
                check |= (1 << i);
                break;
            }
        }

        while(!queue.isEmpty()) {
            int current = queue.poll();
            for(int i = 0; i < N; i++) {
                if(((graph[current] & (1 << i)) != 0) && ((group & (1 << i)) != 0) && ((check & (1 << i)) == 0)) {
                    check |= (1 << i);
                    queue.offer(i);
                }
            }
        }

        if(group != check) return false;

        check = 0;
        for(int i = 0; i < N; i++) {
            if((group & (1 << i)) == 0) {
                queue.add(i);
                check |= (1 << i);
                break;
            }
        }

        while(!queue.isEmpty()) {
            int current = queue.poll();
            for(int i = 0; i < N; i++) {
                if(((graph[current] & (1 << i)) != 0) && ((group & (1 << i)) == 0) && ((check & (1 << i)) == 0)) {
                    check |= (1 << i);
                    queue.offer(i);
                }
            }
        }

        for(int i = 0; i < N; i++) {
            if((check & (1 << i)) != 0) {
                check &= ~(1 << i);
            } else {
                check |= (1 << i);
            }
        }
        if(group != check) return false;
        return true;
    }
}

// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.util.LinkedList;
// import java.util.Queue;
// import java.util.StringTokenizer;

// public class Main {
//     static int N, totalPopulation, minDiff = Integer.MAX_VALUE;
//     static int[] populations, graph;
//     static boolean[] group;
//     public static void main(String[] args) throws IOException {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         StringTokenizer st;

//         N = Integer.parseInt(br.readLine());
//         populations = new int[N];
//         group = new boolean[N];

//         st = new StringTokenizer(br.readLine());
//         for(int i = 0; i < N; i++) {
//             populations[i] = Integer.parseInt(st.nextToken());
//             totalPopulation += populations[i];
//         }

//         graph = new int[N];
//         for(int i = 0; i < N; i++) {
//             st = new StringTokenizer(br.readLine());
//             int c = Integer.parseInt(st.nextToken());
//             for(int j = 0; j < c; j++) {
//                 graph[i] |= (1 << (Integer.parseInt(st.nextToken()) - 1));
//             }
//         }

//         dfs(0, 0, 0);
//         if(minDiff == Integer.MAX_VALUE) System.out.println(-1);
//         else System.out.println(minDiff);
//     }

//     public static void dfs(int index, int count, int sum) {

//         if(index == N) {
//             if(count == 0 || count == N) return ;
//             if(bfs(true) && bfs(false)) minDiff = Math.min(minDiff, Math.abs(2*sum - totalPopulation));
//             return ;
//         }

//         dfs(index+1, count, sum);

//         group[index] = true;
//         dfs(index+1, count+1, sum + populations[index]);
//         group[index] = false;
//     }

//     public static boolean bfs(boolean b) {
//         int check = 0;
//         Queue<Integer> queue = new LinkedList<>();
//         for(int i = 0; i < N; i++) {
//             if(group[i] == b) {
//                 queue.add(i);
//                 check |= (1 << i);
//                 break;
//             }
//         }

//         while(!queue.isEmpty()) {
//             int current = queue.poll();
//             for(int i = 0; i < N; i++) {
//                 if(((graph[current] & (1 << i)) != 0) && (group[i] == b) && ((check & (1 << i)) == 0)) {
//                     check |= (1 << i);
//                     queue.offer(i);
//                 }
//             }
//         }

//         int temp = 0;
//         for(int i = 0; i < N; i++) {
//             if(group[i] == b) temp |= (1 << i);
//         }

//         if((temp & check) != temp) return false;
//         return true;
//     }
// }




// import java.io.BufferedReader;
// import java.io.InputStreamReader;
// import java.io.IOException;
// import java.util.LinkedList;
// import java.util.Queue;
// import java.util.StringTokenizer;

// public class Main {

//     static int N, totalPopulation, minDiff;
//     static int[] populations, visited, sumVisited;
//     static int[][] sections;

//     public static void main(String[] args) throws IOException {
            
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         StringTokenizer st;

//         sumVisited = new int[1001];

//         minDiff = Integer.MAX_VALUE;
//         N = Integer.parseInt(br.readLine());
//         visited = new int[N+1];
//         populations = new int[N+1];
//         sections = new int[N+1][N+1];

//         st = new StringTokenizer(br.readLine());
//         totalPopulation = 0;
//         for(int i = 1; i < N + 1; i++) {
//             populations[i] = Integer.parseInt(st.nextToken());
//             totalPopulation += populations[i];
//         }

//         for(int i = 1; i < N + 1; i++) {
//             st = new StringTokenizer(br.readLine());
            
//             int temp = Integer.parseInt(st.nextToken());
//             for(int j = 0; j < temp; j++) {
//                 sections[i][Integer.parseInt(st.nextToken())] = 1;
//             }
//         }            
        
//         sumVisited[0] = 1;
//         divide(0, 0);

//         if(minDiff != Integer.MAX_VALUE)
//             System.out.println(minDiff);
//         else
//             System.out.println(-1);
//     }

//     public static void divide(int count, int sum) {

//         // cal
//         if(sumVisited[sum] == 0) {

//             int[] group = new int[N + 1];
//             for(int i = 1; i < N + 1; i++) {
//                 group[i] = visited[i];
//             }

//             // check group A and group B
//             boolean check = true;
//             for(int g = 0; g < 2; g++) {
//                 Queue<Integer> q = new LinkedList<Integer>();

//                 for (int i = 1; i < N + 1; i++) {
//                     if (group[i] == g) {
//                         q.offer(i);
//                         break;
//                     }
//                 }

//                 while (!q.isEmpty()) {
//                     int current = q.poll();
//                     group[current] = 2;

//                     for (int i = 1; i < N + 1; i++) {
//                         if (sections[current][i] == 1 && group[i] == g) {
//                             q.offer(i);
//                         }
//                     }
//                 }

//                 for (int i = 1; i < N + 1; i++) {
//                     if (group[i] == g) {
//                         check = false;
//                         break;
//                     }
//                 }

//                 if(!check) break;
//             }

//             if(check) {
//                 sumVisited[sum] = 1;
//                 minDiff = Math.min(minDiff, Math.abs(totalPopulation - sum * 2));
//             }
//         }

//         // return: not check the combination which already checked
//         if(count == (N / 2) + 1) return ;

//         for(int i = 1; i < N + 1; i++) {
//             if(visited[i] == 0) {
//                 visited[i] = 1;
//                 divide(count + 1, sum + populations[i]);
//                 visited[i] = 0;
//             }
//         }
//     }
// }