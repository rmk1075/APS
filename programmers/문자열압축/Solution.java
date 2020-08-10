class Solution {
    public int solution(String s) {
        int answer = s.length(), N = answer;
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= N / 2; i++) {
            int cnt = 1;
            String match = s.substring(0, i);
            sb.setLength(0);
            for(int j = i; j <= N; j += i) {
                if(N < j + i) {
                    if (cnt == 1) {
                        sb.append(match);
                    } else {
                        sb.append(cnt).append(match);
                    }
                    sb.append(s.substring(j));
                    cnt = 0;
                    break;
                }

                String cmp = s.substring(j, j + i);
                if(match.equals(cmp)) {
                    cnt++;
                } else {
                    if(cnt == 1) {
                        sb.append(match);
                    } else {
                        sb.append(cnt).append(match);
                    }

                    match = cmp;
                    cnt = 1;
                }

                if(N < j + i) {
                    if (cnt == 1) {
                        sb.append(match);
                    } else {
                        sb.append(cnt).append(match);
                    }
                    cnt = 0;
                    break;
                }
            }

            answer = Math.min(answer, sb.length());
        }
        
        return answer;
    }
}