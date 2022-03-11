class Solution {
    public int solution(String s) {
        int answer = 1;
        int N = s.length();
        boolean[][] isPalindrome = new boolean[N][N];
        char[] chs = s.toCharArray();
        for(int i = N - 1; -1 < i; i--) answer = Math.max(answer, find(chs, N, i, isPalindrome));
        return answer;
    }

    public int find(char[] chs, int N, int start, boolean[][] isPalindrome) {
        int result = 0;
        if(start == -1) return 0;
        for(int i = start; i < N; i++) {
            if(chs[start] == chs[i] && (i - start <= 2 || isPalindrome[start + 1][i - 1])) {
                isPalindrome[start][i] = true;
                result = Math.max(result, i - start + 1);
            }
        }
        return result;
    }
}
