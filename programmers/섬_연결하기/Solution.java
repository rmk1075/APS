import java.util.Arrays;

class Solution {
    public int solution(int n, int[][] costs) {
        int answer = 0;

        Arrays.sort(costs, (o1, o2) -> {return o1[2] - o2[2];});
        int[] islands = new int[n];
        for(int i = 0; i < n; i++) islands[i] = i;
        for(int[] cost : costs) {
            int a = islands[cost[0]];
            int b = islands[cost[1]];
            if(a == b) continue;
            for(int i = 0; i < n; i++) {
                if(islands[i] == a) islands[i] = b;
            }
            answer += cost[2];
        }

        return answer;
    }
}