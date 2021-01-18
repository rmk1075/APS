class Solution {
    public int strStr(String haystack, String needle) {
        if (needle.length() == 0)
            return 0;

        char[] chHayStack = haystack.toCharArray();
        char[] chNeedle = needle.toCharArray();
        for (int i = 0; i < chHayStack.length - chNeedle.length + 1; i++) {
            if (chHayStack[i] == chNeedle[0]) {
                boolean flag = true;
                for (int j = 1; j < chNeedle.length; j++) {
                    if (chHayStack[i + j] != chNeedle[j]) {
                        flag = false;
                        break;
                    }
                }

                if (flag)
                    return i;
            }
        }

        return -1;
    }
}