/**
 * overview
 * 
 * n만큼의 시간동안 각 도시에서 운반할 수 있는 금과 은의 무게를 구해서 이를 a, b 와 비교한다.
 * 시간의 범위가 int 를 벗어나기 때문에 이진 탐색의 방법으로 시간을 구한다.
 * 
 * description
 * 
 * 0 ~ 1e9 * 2L * 1e5 * 2L 의 범위를 기준으로 이진탐색을 시작한다.
 * 매 시간에 각 도시에서 옮길 수 있는 금의 최대 무게, 은의 최대 무게, 그리고 전체 옮길 수 있는 무게를 구하고 이를 다 더한다.
 * 더한 값을 a, b, a + b 와 각각 비교하여 옮길 수 있는 무게들이 같거나 더 큰 경우에는 n시간 동안 목표를 만족한다는 의미이다.
 * 이러한 비교를 반복하여 목표를 만족할 수 있는 최소 시간을 구한다. 
 */
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