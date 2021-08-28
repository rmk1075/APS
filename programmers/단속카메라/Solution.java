import java.util.Arrays;

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;

        Arrays.sort(routes, (o1, o2) -> {return o1[1] == o2[1] ? o1[0] - o2[0] : o1[1] - o2[1];});

        int limit = -30001;
        for(int[] route : routes) {
            if(route[0] <= limit) continue;
            limit = route[1];
            answer++;
        }


        return answer;
    }
}