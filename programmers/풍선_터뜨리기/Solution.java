class Solution {
    public int solution(int[] a) {
        int answer = 0;
        int N = a.length;
        int[] leftMins = new int[N];
        int[] rightMins = new int[N];

        leftMins[0] = Integer.MAX_VALUE;
        for(int i = 1; i < N; i++) leftMins[i] = Math.min(leftMins[i - 1], a[i - 1]);
        rightMins[N - 1] = Integer.MAX_VALUE;
        for(int i = N - 2; -1 < i; i--) rightMins[i] = Math.min(rightMins[i + 1], a[i + 1]);

        for(int i = 0; i < N; i++) {
            if(leftMins[i] < a[i] && rightMins[i] < a[i]) continue;
            answer++;
        }
        return answer;
    }
}