class Solution {
    public String convert(String s, int numRows) {
        String result = "";
        char[] temp = null;

        if (numRows == 1)
            return s;

        temp = s.toCharArray();

        for (int i = 0; i < numRows; i++) {
            int j = i;
            int clock = 0;

            while (j < s.length()) {
                if (i == 0) {
                    result = result + String.valueOf(temp[j]);
                    j += 2 * numRows - 2;
                } else if (i == numRows - 1) {
                    result = result + String.valueOf(temp[j]);
                    j += 2 * numRows - 2;
                } else {
                    result = result + String.valueOf(temp[j]);

                    if (clock == 0) {
                        j += 2 * (numRows - 1 - i);
                        clock = 1;
                    } else {
                        j += 2 * i;
                        clock = 0;
                    }
                }
            }
        }

        return result;
    }
}