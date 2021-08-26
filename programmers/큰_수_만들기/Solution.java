class Solution {
    public String solution(String number, int k) {
        String answer = "";

        StringBuffer sb = new StringBuffer(number);
        int start = 0;
        while(0 < k) {
            int size = sb.length();
            int i = start;
            for(; i < size - 1; i++) {
                if(sb.charAt(i) < sb.charAt(i + 1)) {
                    sb.deleteCharAt(i);
                    break;
                }
            }
            start = i == 0 ? 0 : i - 1;
            
            if(size == sb.length()) sb.deleteCharAt(size - 1);
            k--;
        }

        answer = sb.toString();
        return answer;
    }
}