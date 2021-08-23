import java.util.Arrays;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;

        Arrays.sort(citations);
        int N = citations.length;
        loop1: for(int i = 0; i < N; i++) {
            if(citations[i] == answer) continue;

            if(citations[i] <= N - i) {
                answer = citations[i];
                continue;
            }

            for(int j = citations[i] - 1; answer < j; j--) {
                if(j <= N - i) {
                    answer = j;
                    break loop1;
                }
            }
        }

        return answer;
    }
}