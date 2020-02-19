import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] visited;
    static ArrayList<Integer>[] nums;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new int[N];
        nums = new ArrayList[N];
        for(int i = 0; i < N; i++) nums[i] = new ArrayList<Integer>();

        int a, b;
        for(int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());

            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            nums[a].add(b);
            nums[b].add(a);
        }

        for(int i = 0; i < N; i++) {
            visited[i] = 1;
            dfs(i, 0);
            visited[i] = 0;
        }

        System.out.println(0);
    }

    public static void dfs(int index, int count) {
        if(count == 4) {
            System.out.println(1);
            System.exit(0);
        }

        for(int i = 0; i < nums[index].size(); i++) {
            if(visited[nums[index].get(i)] == 0) {
                visited[nums[index].get(i)] = 1;
                dfs(nums[index].get(i), count+1);
                visited[nums[index].get(i)] = 0;
            }
        }
    }
}