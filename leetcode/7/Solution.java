class Solution {
    public int reverse(int x) {

        int m = x;
        int result = 0;

        while (true) {
            result = 10 * result + m % 10;
            m = m / 10;

            if (m == 0)
                break;

            if ((result < (Integer.MIN_VALUE / 10)) || ((result == (Integer.MIN_VALUE / 10)) && ((m % 10) <= -8)))
                return 0;
            if (((Integer.MAX_VALUE / 10) < result) || (((Integer.MAX_VALUE / 10) == result) && 7 <= (m % 10)))
                return 0;
        }

        return result;
    }
}