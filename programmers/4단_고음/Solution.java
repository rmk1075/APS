class Solution {
    public int solution(int n) {
        int answer = cal(n - 2, 2);
        return answer;
    }

    public int cal(int n, int k) {
        if(n < Math.pow(3, k / 2)) return 0;
        if(n == 3) {
            if(k == 2) return 1;
            return 0;
        }

        int count = 0;
        if(3 < n) {
            if(n % 3 == 0 && 1 < k) count += cal(n / 3, k - 2);
        }
        count += cal(n - 1, k + 1);

        return count;
    }
}