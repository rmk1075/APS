import java.util.Arrays;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;

        Arrays.sort(rocks);
        int left = 0;
        int right = distance;
        while(left <= right) {
            int mid = (left + right) / 2;
            if(valid(distance, rocks, n, mid)) {
                answer = Math.max(answer, mid);
                left = mid + 1;
            } else right = mid - 1;
        }

        return answer;
    }

    public boolean valid(int distance, int[] rocks, int n, int mid) {
        int l = rocks.length;
        int cnt = 0;
        int prev = 0;
        for(int i = 0; i < l; i++) {
            while(i < l && rocks[i] - prev < mid) {
                cnt++;
                i++;
            }

            if(i == l && (distance - prev < mid)) return false;
            if(n < cnt) return false;
            if(i < l) prev = rocks[i];
        }

        return true;
    }
}