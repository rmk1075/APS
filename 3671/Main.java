import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {
    static int C, N, temp, visited, nums[];
    static Set<Integer> set = new HashSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        C = Integer.parseInt(br.readLine());
        while(0 < C--) {
            char[] ch = br.readLine().toCharArray();
            nums = new int[ch.length];
            N = nums.length;
            for(int i = 0; i < N; i++) nums[i] = ch[i] - '0';
            Arrays.sort(nums);
            set.clear();
            visited = 0;
            
            visited |= (1 << 0);
            find(1, nums[0]);
            visited &= ~(1 << 0);
            for(int i = 1; i < N; i++) {
                if(nums[i] == nums[i-1]) continue;
                visited |= (1 << i);
                find(1, nums[i]);
                visited &= ~(1 << i);
            }

            if(set.contains(1)) set.remove(1);
            if(set.contains(0)) set.remove(0);

            sb.append(((set.contains(1)) ? set.size()-1 : set.size()) + "\n");
        }
        System.out.println(sb);
    }

    public static void find(int idx, int num) {
        if(check(num)) set.add(num);
        if(idx == N) return ;

        for(int i = 0; i < N; i++) {
            if((visited & (1 << i)) != 0) continue;
            visited |= (1 << i);
            find(idx+1, num * 10 + nums[i]);
            visited &= ~(1 << i);
        }        
    }

    public static boolean check(int num) {
        for(int i = 2; i <= Math.sqrt(num); i++) {
            if(num % i == 0) return false;
        }
        return true;
    }
}