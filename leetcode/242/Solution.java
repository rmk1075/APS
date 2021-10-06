class Solution {
    public boolean isAnagram(String s, String t) {
        int[] alpha = new int[26];
        char[] sCh = s.toCharArray();
        char[] tCh = t.toCharArray();
        if(sCh.length != tCh.length) return false;
        for(int i = 0; i < sCh.length; i++) {
            alpha[sCh[i] - 'a']++;
            alpha[tCh[i] - 'a']--;
        }

        for(int a : alpha) {
            if(a != 0) return false;
        }
        return true;
    }
}