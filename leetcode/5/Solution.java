class Solution {
    public String longestPalindrome(String s) {
        char[] input = s.toCharArray();

        int temp = 0;
        int max = 0;
        String result = null;

        if (s.length() == 0 || s.length() == 1)
            return s;

        for (int i = 0; i < s.length(); i++) {
            for (int j = s.length() - 1; i <= j; j--) {
                if (compare(input, i, j) == 1) {
                    temp = j - i + 1;

                    if (max < temp) {
                        max = temp;
                        result = String.copyValueOf(input, i, temp);
                    }

                    temp = 0;
                }
            }
        }

        return result;
    }

    int compare(char[] arr, int front, int end) {
        if (end <= front)
            return 1;

        if (arr[front++] == arr[end--]) {
            return compare(arr, front, end);
        } else
            return 0;
    }
}