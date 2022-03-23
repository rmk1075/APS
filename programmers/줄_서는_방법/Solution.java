class Solution {
    public int[] solution(int n, long k) {
        if(n == 1) return new int[]{1};

        int visited = 0;
        int[] answer = new int[n];
        long count = 0L;
        long unit = 1L;
        for(int i = 1; i < n; i++) unit *= i;
        for(int i = 0; i < n - 1; i++) {
            long temp = 0;
            for(int j = 1; j <= n; j++) {
                if((visited & (1 << j)) != 0) continue;
                temp += unit;
                if(k <= count + temp) {
                    count += temp - unit;
                    answer[i] = j;
                    visited |= 1 << j;
                    break;
                }
            }
            unit /= (n - i - 1);
        }
        for(int i = 1; i <= n; i++) {
            if((visited & (1 << i)) == 0) {
                answer[n - 1] = i;
                break;
            }
        }
        return answer;
    }
}