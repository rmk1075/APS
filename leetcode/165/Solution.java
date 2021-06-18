import java.util.Arrays;

class Solution {
    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\."), v2 = version2.split("\\.");
        int len = Math.min(v1.length, v2.length);
        for(int i = 0; i < len; i++) {
            int comp = Integer.compare(Integer.parseInt(v1[i]), Integer.parseInt(v2[i]));
            if(comp == 0) continue;
            else return comp;
        }

        if(v1.length < v2.length) {
            for(int i = len; i < v2.length; i++) {
                if(Integer.parseInt(v2[i]) != 0) return -1;
            }
        } else if(v2.length < v1.length) {
            for(int i = len; i < v1.length; i++) {
                if(Integer.parseInt(v1[i]) != 0) return 1;
            }
        }

        return 0;
    }
}