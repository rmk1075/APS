import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Solution {
    int n, m, visited = 0;
    int[] arr;
    Set<Integer> set = new HashSet<>();
    public int solution(String[] user_id, String[] banned_id) {
        int answer = 0;

        n = banned_id.length;
        m = user_id.length;
        arr = new int[n];

        int bdx = 0;
        for(String ban : banned_id) {
            int temp = 0, udx = 0, len = ban.length();
            char[] b = ban.toCharArray();
            for(String user : user_id) {
                if(len != user.length()) {
                    udx++;
                    continue;
                }

                int i = 0;
                char[] u = user.toCharArray();
                for(; i < len; i++) {
                    if(b[i] == '*' || b[i] == u[i]) continue;
                    break;
                }

                if(i == len) temp |= 1 << udx;
                udx++;
            }

            arr[bdx++] = temp;
        }
        
        dfs(0);

        answer = set.size();
        return answer;
    }

    public void dfs(int index) {
        if(index == n) {
            set.add(visited);
            return ;
        }

        int val = arr[index];
        for(int i = 0; i < m; i++) {
            if(((visited & (1 << i)) != 0) || ((val & (1 << i)) == 0)) continue;

            visited |= 1 << i;
            dfs(index + 1);
            visited &= ~(1 << i);
        }
    }
}