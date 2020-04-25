class Solution {
    public int[] solution(String s) {
        int[] answer = {}, count = new int[1000001], ans = new int[501];

        s = s.substring(2, s.length()-2);
        for(String str : s.split(",")) {
            if(str.charAt(0) == '{') str = str.substring(1);
            if(str.charAt(str.length()-1) == '}') str = str.substring(0, str.length()-1);
            count[Integer.parseInt(str)]++;
        }

        int len = 0;
        for(int i = 0; i < 1000001; i++) {
            if(count[i] == 0) continue;
            ans[count[i]-1] = i;
            len++;
        }

        answer = new int[len];
        int idx = 0;
        for(int i = 500; -1 < i; i--) {
            if(ans[i] != 0) answer[idx++] = ans[i];
            if(idx == len) break;
        }

        return answer;
    }
}