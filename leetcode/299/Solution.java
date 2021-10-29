import java.util.HashMap;
import java.util.Map;

class Solution {
    public String getHint(String secret, String guess) {
        char[] secretChar = secret.toCharArray();
        char[] guessChar = guess.toCharArray();
        int[] secretArr = new int[10];
        int[] guessArr = new int[10];
        
        int bulls = 0;
        for(int i = 0; i < secretChar.length; i++) {
            if(secretChar[i] == guessChar[i]) bulls++;
            else {
                secretArr[secretChar[i] - '0']++;
                guessArr[guessChar[i] - '0']++;
            }
        }

        int cows = 0;
        for(int i = 0; i < 10; i++) cows += Math.min(secretArr[i], guessArr[i]);
        return bulls + "A" + cows + "B";
    }
}