class Solution {
    public int solution(int[] numbers, int target) {
        int answer = 0;
        answer = dfs(numbers, numbers.length, target, 0);
        return answer;
    }

    public int dfs(int[] numbers, int N, int target, int index) {
        if(index == N) return target == 0 ? 1 : 0;
        int answer = dfs(numbers, N, target - numbers[index], index + 1) + dfs(numbers, N, target + numbers[index], index + 1);
        return answer;
    }
}