class Solution {
    public int[] solution(int n, int s) {
        int[] answer = new int[n];
        int num = s / n;
        if(num == 0) return new int[]{-1};
        for(int i = 0; i < n; i++) answer[i] = num;
        for(int i = 0; i < s % n; i++) answer[n - i - 1]++;
        return answer;
    }
}