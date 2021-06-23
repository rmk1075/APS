class Solution {
    public int titleToNumber(String columnTitle) {
        char[] columns = columnTitle.toCharArray();
        int N = columns.length;
        int result = 0;
        int value = 1;
        for(int i = N - 1; -1 < i; i--) {
            result += (columns[i] - 'A' + 1) * value;
            value *= 26;
        }

        return result;
    }
}