class Solution {
    int N, min = Integer.MAX_VALUE, next[];

    public int solution(int[] stones, int k) {
        int answer = 0;
        N = stones.length;
        next = new int[N];

        for (int i = 0; i < N; i++) {
            next[i] = i + 1;
            min = Math.min(min, stones[i]);
        }

        answer += min;
        for (int i = 0; i < N; i++)
            stones[i] -= min;

        loop: while (true) {
            for (int i = 0; i < N; i++) {
                if (stones[i] == 0) {
                    int target = find(next[i], stones);
                    if (k <= target - i)
                        break loop;
                    next[i] = target;
                    i = target - 1;
                } else
                    stones[i]--;
            }
            answer++;
        }

        return answer;
    }

    public int find(int idx, int[] stones) {
        if (idx == N)
            return N;
        if (stones[idx] != 0)
            return idx;
        return next[idx] = find(next[idx], stones);
    }
}