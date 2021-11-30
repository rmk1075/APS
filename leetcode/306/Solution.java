class Solution {
    public boolean isAdditiveNumber(String num) {
        int N = num.length();
        for(int i = 1; i <= N / 2; i++) {
            for(int j = 1; Math.max(i, j) <= N - i - j; j++) {
                if(valid(i, j, N, num)) return true;
            }
        }
        return false;
    }

    private boolean valid(int left, int right, int N, String num) {
        if((num.charAt(0) == '0' && 1 < left) || (num.charAt(left) == '0' && 1 < right)) return false;
        String sum = "";
        Long a = Long.parseLong(num.substring(0, left));
        Long b = Long.parseLong(num.substring(left, left + right));
        for(int i = left + right; i < N; i += sum.length()) {
            b = a + b;
            a = b - a;
            sum = String.valueOf(b);
            if(!num.startsWith(sum, i)) return false;
        }
        return true;
    }
}