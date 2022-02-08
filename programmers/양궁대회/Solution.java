import java.util.Arrays;

class Solution {
    int[] lion = new int[11];
    int[] answer = new int[11];
    int maxScore = 0;
    public int[] solution(int n, int[] info) {
        dfs(n, 10, 0, info);
        return 0 < maxScore ? answer : new int[]{-1};
    }

    public void dfs(int n, int index, int score, int[] apeach) {
        if(index == -1) {
            if(maxScore <= score) {
                if(maxScore == score && !isLarger()) return ;
                maxScore = score;
                for(int i = 0; i < 11; i++) answer[i] = lion[i];
                answer[10] += n;
            }
            return ;
        }

        for(int i = n; -1 < i; i--) {
            lion[index] = i;
            dfs(n - i, index - 1, score + compare(10 - index, i, apeach[index]), apeach);
        }
    }
    
    public int compare(int score, int l, int a) {
        if(l == 0 && a == 0) return 0;
        return a < l ? score : -score;
    }

    public boolean isLarger() {
        for(int i = 10; -1 < i; i--) {
            if(lion[i] == answer[i]) continue;
            if(answer[i] < lion[i]) return true;
            else return false;
        }
        return false;
    }
}