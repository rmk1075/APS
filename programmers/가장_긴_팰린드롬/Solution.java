/**
 * overview
 * 
 * 문자열 내에서 가장 긴 팰린드롬을 찾아야한다.
 * 팰린드롬은 중앙지점을 기준으로 좌우가 대칭인 문자열을 의미한다.
 * 문자열의 각 문자를 시작점으로 하여 팰린드롬이 되는 문자열 범위를 찾아서 각 범위를 비교하여 결과를 찾는다.
 * 
 * description
 * 
 * 문자열의 길이가 최대 2500 이기 때문에 완전탐색을 하게 되면 시간 제한에 걸리게 된다.
 * 그렇기 때문에 dynamic programming 을 통해서 연산을 수행한다.
 * dp 의 결과는 isPalindrome 배열에 저장한다.
 * isPalindrome 배열의 요소의 [x][y] 값은 문자열 s의 x번째 문자부터 y번째 문자까지가 팰린드롬인지 여부를 의미한다.
 * 이를 통해서 x번째 문자와 y번째 문자가 같은 경우에 이전에 미리 연산한 [x+1][y-1] 의 값을 확인하여 팰린드롬인지 더 빠르게 찾을 수 있다.
 * 탐색을 통해서 팰린드롬 문자열들의 길이를 비교하여 최대값을 반환한다.
 */
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
