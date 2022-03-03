class Solution {
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long left = 0L;
        long right = (long)(1e9 * 2L * 1e5 * 2L);
        while(left <= right) {
            long mid = (left + right) / 2;
            if(isValid(mid, a, b, g, s, w, t)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public boolean isValid(long time, int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long gold = 0L;
        long silver = 0L;
        long weightSum = 0L;
        for(int i = 0; i < t.length; i++) {
            long cycle = (long)Math.ceil((double)(time / t[i]) / 2);
            long weight = w[i] * cycle;
            gold += Math.min(g[i], weight);
            silver += Math.min(s[i], weight);
            weightSum += Math.min(g[i] + s[i], weight);
        }
        return a <= gold && b <= silver && a + b <= weightSum;
    }
}