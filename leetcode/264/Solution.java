class Solution {
    public int nthUglyNumber(int n) {
        int[] result = new int[n];
        result[0] = 1;

        int[] index = {0, 0, 0};
        int[] values = {2, 3, 5};
        for(int i = 1; i < n; i++) {
            int value = Math.min(Math.min(values[0], values[1]), values[2]);
            result[i] = value;
            if(values[0] == value) values[0] = 2 * result[++index[0]];
            if(values[1] == value) values[1] = 3 * result[++index[1]];
            if(values[2] == value) values[2] = 5 * result[++index[2]];
        }

        return result[n - 1];
    }
}