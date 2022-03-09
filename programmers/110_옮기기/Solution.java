class Solution {
    public String[] solution(String[] s) {
        int N = s.length;
        String[] answer = new String[N];
        for(int i = 0; i < N; i++) {
            StringBuilder sb = new StringBuilder();
            StringBuilder count = new StringBuilder();
            for(char num : s[i].toCharArray()) {
                if(num == '0') {
                    if(1 < sb.length() && sb.charAt(sb.length() - 2) == '1' && sb.charAt(sb.length() - 1) == '1') {
                        sb.deleteCharAt(sb.length() - 1);
                        sb.deleteCharAt(sb.length() - 1);
                        count.append("110");
                    } else sb.append(num);
                } else sb.append(num);
            }

            int index = sb.lastIndexOf("0");
            if(index == -1) sb.insert(0, count.toString());
            else sb.insert(index + 1, count.toString());
            answer[i] = sb.toString();
        }
        return answer;
    }
}