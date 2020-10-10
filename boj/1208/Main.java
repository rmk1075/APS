import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static long count = 0;
    static int N, S;
    static int[] nums;
    static List<Long>[] values = new ArrayList[2];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        nums = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) nums[i] = Integer.parseInt(st.nextToken());

        values[0] = new ArrayList<>();
        values[1] = new ArrayList<>();
        find(0, 0, nums.length/2, 0, 0);
        find(1, nums.length/2, nums.length, 0, 0);

        Collections.sort(values[0]);
        Collections.sort(values[1]);

        int left = 0, right = values[1].size()-1;
        while(left < values[0].size() && -1 < right) {
            long lval = values[0].get(left);
            long rval = values[1].get(right);

            if(lval + rval == S) {
                long lcnt = 0;
                while(left < values[0].size() && values[0].get(left) == lval) {
                    lcnt++;
                    left++;
                }

                long rcnt = 0;
                while(-1 < right && values[1].get(right) == rval) {
                    rcnt++;
                    right--;
                }
                count += lcnt * rcnt;
            }

            if(S < lval + rval) right--;
            if(lval + rval < S) left++;
        }

        if(S == 0) count--;
        System.out.println(count);
    }

    public static void find(int index, int left, int right, long sum, int cnt) {

        if(left == right) {
            values[index].add(sum);
            return;
        }

        find(index, left+1, right, sum, cnt);
        find(index, left+1, right, sum + nums[left], cnt+1);
    }
}