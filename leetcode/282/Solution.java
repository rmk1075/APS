import java.util.LinkedList;
import java.util.List;

class Solution {
    public List<String> addOperators(String num, int target) {
        List<String> result = new LinkedList<>();
        int N = num.length();
        for(int i = 0; i < N; i++) {
            if(i != 0 && num.charAt(0) == '0') break;
            long left = Long.parseLong(num.substring(0, i + 1));
            dfs(result, String.valueOf(left), num, target, N, i + 1, left, left);
        }
        return result;
    }

    public void dfs(List<String> result, String temp, String num, int target, int N, int index, long val, long prev) {
        if(index == N) {
            if(target == val) result.add(temp);
            return ;
        }

        for(int i = index; i < N; i++) {
            if(i != index && num.charAt(index) == '0') break;
            long left = Long.parseLong(num.substring(index, i + 1));
            dfs(result, temp + "+" + left, num, target, N, i + 1, val + left, left);
            dfs(result, temp + "-" + left, num, target, N, i + 1, val - left, -left);
            dfs(result, temp + "*" + left, num, target, N, i + 1, val - prev + prev * left, prev * left);
        }
    }
}