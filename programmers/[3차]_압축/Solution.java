import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Solution {
    public int[] solution(String msg) {
        int[] answer = {};

        List<String> dict = new ArrayList<>();
        for(int i = 0; i < 26; i++) {
            dict.add(String.valueOf((char)('A' + i)));
        }

        String w = "";
        List<Integer> output = new LinkedList<>();
        for(String c : msg.split("")) {
            String temp = w + c;
            if(dict.indexOf(temp) == -1) {
                output.add(dict.indexOf(w));
                dict.add(temp);
                w = c;
            } else {
                w += c;
            }
        }
        output.add(dict.indexOf(w));

        int idx = 0;
        answer = new int[output.size()];
        for(int val : output) {
            answer[idx++] = val + 1;
        }

        return answer;
    }
}