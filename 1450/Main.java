import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int N, C, nums[];
    static ArrayList<Integer>[] list = new ArrayList[2];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) nums[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(nums);

        list[0] = new ArrayList<>();
        list[1] = new ArrayList<>();
        find(0, N/2+1, 0, 0);
        find(1, N, N/2+1, 0);
        Collections.sort(list[0]);
        Collections.sort(list[1]);

        int ans = 0;
        for(int num1 : list[0]) {
            for(int num2 : list[1]) {
                if(num1 + num2 <= C) ans++;
                else break;
            }
        }

        System.out.println(ans);
    }

    public static void find(int part, int limit, int index, int cost) {
        if(index == limit || cost == C) {
            list[part].add(cost);
            return ;
        }

        find(part, limit, index+1, cost);
        if(cost + nums[index] <= C) find(part, limit, index+1, cost + nums[index]);
    }
}