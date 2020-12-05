class Solution {
    public boolean isSubsequence(String s, String t) {
        char[] chS = s.toCharArray();
        char[] chT = t.toCharArray();

        int index = 0, len = chS.length;
        if (len == 0)
            return true;

        if (chT.length < len)
            return false;

        for (char ch : chT) {
            if (index < len && ch == chS[index]) {
                index++;

                if (index == len)
                    return true;
            }
        }

        return false;
    }
}