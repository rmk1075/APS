class Solution {
    public boolean isPalindrome(int x) {
        if (0 <= x && x < 10)
            return true;
        if (x < 0)
            return false;

        int l = String.valueOf(x).length();

        int[] array = new int[l];

        for (int i = 0; i < l; i++) {
            array[i] = x % 10;
            x /= 10;
        }

        for (int c = 0; c < l; c++) {
            if (array[c] != array[Math.abs(l - c - 1)])
                return false;
        }

        return true;
    }
}