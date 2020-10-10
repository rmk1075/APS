import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();    
        sol.solution(new int[] {2, 4, 5, 3, 2, 1, 4, 2, 5, 1}, 3);
    }
}

class Solution {
    int N, answer, min = Integer.MAX_VALUE, next[];
    public int solution(int[] stones, int k) {
        N = stones.length;
        next = new int[N];

        for(int i = 0; i < N; i++) min = Math.min(min, stones[i]);

        answer = min;
        loop: while(true) {
            // TODO:
            System.out.println(answer);
            System.out.println(Arrays.toString(stones));
            System.out.println(Arrays.toString(next));

            for(int i = 0; i < N; i++) {
                if(stones[i] < answer) {
                    if(k < next[i]) break loop;
                    i += next[i];
                } else if(stones[i] == answer) {
                    next[i] = 2;

                    // right
                    if(i != N-1) {
                        if(stones[i+1] < answer) next[i] = next[i+1] + 1;
                    }

                    // left
                    if(i != 0) {
                        int j = i-1;
                        while(-1 < j && stones[j] < answer) {
                            next[j] = next[j+1] + 1;
                            j--;
                        }
                    }
                }
            }
            answer++;
        }

        System.out.println(answer-1);
        return --answer;
    }
}