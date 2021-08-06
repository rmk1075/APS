import java.util.ArrayList;
import java.util.List;

class Solution {
    int combination = 0;
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(result, 1, k, n);
        return result;
    }

    public void dfs(List<List<Integer>> result, int num, int k, int n) {
        if(k == 0) {
            if(n == 0) {
                List<Integer> temp = new ArrayList<>();
                for(int i = 1; i < 10; i++) {
                    if((combination & (1 << i)) == 0) continue;
                    temp.add(i);
                }
                result.add(temp);
            }
            return ;
        }

        for(int i = num; i < 10; i++) {
            if(i <= n) {
                combination |= 1 << i;
                dfs(result, i + 1, k - 1, n - i);
                combination &= ~(1 << i);
            }
        }
    }
}