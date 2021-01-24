class Solution {
    public int divide(int dividend, int divisor) {
        if (divisor == 1)
            return dividend;

        if (divisor == -1) {
            if (dividend == Integer.MIN_VALUE)
                return Integer.MAX_VALUE;
            return -dividend;
        }

        if (dividend == 0)
            return 0;

        boolean isNegative = (dividend < 0 && 0 <= divisor) || (0 < dividend && divisor < 0);
        dividend = dividend < 0 ? dividend : -dividend;
        divisor = divisor < 0 ? divisor : -divisor;

        int count = 0;
        while (dividend <= divisor) {
            dividend -= divisor;
            count++;
        }

        return isNegative ? -count : count;
    }
}