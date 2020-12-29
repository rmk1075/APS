class Solution {
    public String longestCommonPrefix(String[] strs) {
        int N = strs.length;
        char[][] chars = new char[strs.length][];

        int minLen = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            chars[i] = strs[i].toCharArray();
            minLen = Math.min(minLen, chars[i].length);
        }

        if (N == 0 || minLen == 0)
            return "";

        StringBuilder sb = new StringBuilder();
        loop: for (int i = 0; i < minLen; i++) {
            char ch = chars[0][i];
            for (int j = 1; j < N; j++) {
                if (ch != chars[j][i])
                    break loop;
            }

            sb.append(ch);
        }

        return sb.toString();
    }
}