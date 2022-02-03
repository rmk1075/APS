class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        String str = convertToK(n, k);
        String[] nums = str.split("0");
        for(String num : nums) {
            if(num.length() != 0 && isPrimeNumber(Long.parseLong(num))) answer++;
        }
        return answer;
    }

    public String convertToK(int n, int k) {
        StringBuffer sb = new StringBuffer();
        while(0 < n) {
            sb.insert(0, n % k);
            n /= k;
        }
        return sb.toString();
    }

    public boolean isPrimeNumber(long num) {
        if(num <= 1) return false;
        if(num == 2) return true;
        for(int i = 2; i <= Math.sqrt(num); i++) {
            if(num % i == 0) return false;
        }
        return true;
    }
}