import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] ans;
    static boolean[] visited;
    static ArrayList<Integer>[] list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        visited = new boolean[N+1];
        ans = new int[N+1];
        Arrays.fill(ans, -1);
        list = new ArrayList[N+1];
        for(int i = 1; i <= N; i++) list[i] = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());

            list[a].add(b);
            list[b].add(a);
        }

        // find cycle
        find(0, 1);

        for(int i = 1; i <= N; i++) if(ans[i] == -1) dfs(0, i);
        for(int i = 1; i < ans.length; i++) System.out.print(ans[i] + " ");
    }

    public static int dfs(int past, int index) {
        if(ans[index] != -1) return ans[index];

        for(int i = 0; i < list[index].size(); i++) {
            if(list[index].get(i) == past) continue;
            int result = dfs(index, list[index].get(i));
            if(result == -1) continue;
            ans[index] = result + 1;
            return ans[index];
        }

        return -1;
    }

    public static int find(int past, int index) {
        visited[index] = true;

        for(int i = 0; i < list[index].size(); i++) {
            if(list[index].get(i) == past) continue;
            if(visited[list[index].get(i)]) {
                ans[index] = 0;
                return list[index].get(i);
            }

            int result = find(index, list[index].get(i));
            if(result == -1) continue;
            ans[index] = 0;
            if(result == index) return -1;
            else return result;
        }

        return -1;
    }
}