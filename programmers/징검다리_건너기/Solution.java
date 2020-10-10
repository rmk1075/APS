class Solution {
    public int solution(int[] stones, int k) {
        int answer = 0;

        int N = stones.length, left = Integer.MAX_VALUE, right = 0;
        for(int s : stones) {
            left = Math.min(left, s);
            right = Math.max(right, s);
        }

        int mid = (left + right) / 2;
        while(left <= right) {
            mid = (left + right) / 2;

            if(check(mid, k, N, stones)) {
                answer = mid;
                left = mid + 1;
            } else right = mid - 1;
        }

        return answer;
    }

    public boolean check(int mid, int k, int N, int[] stones) {
        for(int i = 0; i < N; i++) {
            if(stones[i] < mid) {
                int temp = 1;
                while(i + temp < N && stones[i + temp] < mid) temp++;

                if(k <= temp) return false;
                i += temp;
            }
        }

        return true;
    }
}