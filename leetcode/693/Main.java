public class Main {
    
}

class Solution {
    public boolean hasAlternatingBits(int n) {
        char prev = '2';
        for (char ch : Integer.toBinaryString(n).toCharArray()) {
            if (prev == ch) {
                return false;
            }
            prev = ch;
        }
        return true;
    }
}
