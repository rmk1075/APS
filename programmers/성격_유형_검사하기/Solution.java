import java.util.*;

class Solution {
    public String solution(String[] survey, int[] choices) {
        int n = survey.length;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int choice = choices[i];
            if (choice < 4) {
                char ch = survey[i].toCharArray()[0];
                map.put(ch, map.getOrDefault(ch, 0) + 4 - choice);
            } else if (4 < choice) {
                char ch = survey[i].toCharArray()[1];
                map.put(ch, map.getOrDefault(ch, 0) + choice - 4);
            }
        }

        StringBuffer sb = new StringBuffer();
        sb.append(decideChar(map, 'R', 'T'));
        sb.append(decideChar(map, 'C', 'F'));
        sb.append(decideChar(map, 'J', 'M'));
        sb.append(decideChar(map, 'A', 'N'));
        return sb.toString();
    }

    private char decideChar(Map<Character, Integer> map, char a, char b) {
        int aa = map.getOrDefault(a, 0);
        int bb = map.getOrDefault(b, 0);
        if (aa == bb) {
            return (char) Math.min(a, b);
        }
        return aa < bb ? b : a;
    }
}