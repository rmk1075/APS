class Solution {
    public int myAtoi(String str) {
        char[] Atoi = str.toCharArray();
        int result = 0;
        int initial = 1;
        boolean starting = true;

        for (int i = 0; i < Atoi.length; i++) {
            if (starting == true) {
                if (Atoi[i] == ' ')
                    continue;

                if (Atoi[i] == '-') {
                    initial = -1;
                    starting = false;
                    continue;
                } else if (Atoi[i] == '+') {
                    initial = 1;
                    starting = false;
                    continue;
                }
            }

            if (Atoi[i] < 48 || 58 < Atoi[i])
                break;

            if (initial * result < Integer.MIN_VALUE / 10 || (initial * result == Integer.MIN_VALUE / 10
                    && initial * (Atoi[i] - 48) < Integer.MIN_VALUE % 10)) {
                return Integer.MIN_VALUE;
            } else if (Integer.MAX_VALUE / 10 < initial * result
                    || (initial * result == Integer.MAX_VALUE / 10 && Integer.MAX_VALUE % 10 < (Atoi[i] - 48))) {
                return Integer.MAX_VALUE;
            } else {
                result = (result * 10) + (Atoi[i] - 48);
            }

            starting = false;
        }

        result = result * initial;

        return result;
    }
}