import java.util.regex.Pattern;

class Solution {
    public boolean isMatch(String s, String p) {
        return Pattern.matches(p, s);
    }
}