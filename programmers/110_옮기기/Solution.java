/**
 * overview
 * 
 * 문자열 x 에 대해서 110을 추출해서 재배치하여 사전순으로 가장 앞에오는 문자열을 만들어야한다.
 * 우선 x 를 탐색하여 110을 모두 추출한다. 이때 중간의 110을 추출하고 남은 문자열에서도 110이 있는지 확인해야한다.
 * 추출을 반복하여 더이상 110이 존재하지 않을때까지 추출을 반복한다.
 * 110이 모두 추출된 후에 추출된 값들의 위치를 찾아야한다.
 * 사전순으로 나열할 때 가장 앞으로 가기 위해서는 문자열에서 0이 가장 앞으로 가야한다.
 * 그렇기 때문에 문자열에 0이 있는 경우에는 가장 마지막 0의 뒤에 110을 위치시키고, 0이 없는 경우에는 문자열의 가장 앞에 위치시키도록 한다.
 * 
 * description
 * 
 * 문자열을 다룰때는 String 을 그대로 사용하기 보다는 char[] 나 StringBuilder 를 사용하는 것이 더 유리하다.
 * 문자열을 탐색할 때 Stack 구조로 저장을 수행한다. 만약 0인 경우에는 앞에 저장된 문자 2개가 1인 경우 110이기 때문에 문자들을 지우고 count 를 늘려준다.
 * 110을 제거한 문자열에 대해서 0의 위치를 탐색한다. 0이 있는 경우 가장 마지막 0의 뒤에 count 로 추출한 110 들을 위치시킨다.
 * 만약 0이 없는 경우에는 110들을 가장 앞에 삽입하여 이를 반환한다.
 */
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