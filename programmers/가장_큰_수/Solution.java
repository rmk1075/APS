import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public String solution(int[] numbers) {
        String answer = "";

        int N = numbers.length;
        String[] strs = new String[N];
        for(int i = 0; i < N; i++) strs[i] = Integer.toString(numbers[i]);

        Arrays.sort(strs, new Comparator<String>(){
            @Override
            public int compare(String o1, String o2) {
                return Long.compare(Long.valueOf(o2 + o1), Long.valueOf(o1 + o2));
            }
        });

        int index = 0;
        while(index < N && strs[index].equals("0")) index++;

        if(index == N) return "0";

        StringBuffer sb = new StringBuffer();
        for(int i = index; i < N; i++) sb.append(strs[i]);
        
        answer = sb.toString();
        return answer;
    }
}