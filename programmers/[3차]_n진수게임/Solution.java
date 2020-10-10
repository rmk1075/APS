import java.util.Stack;

/**
 * 진법 n, 미리 구할 숫자의 갯수 t, 게임에 참가하는 인원 m, 튜브의 순서 p
 */

class Solution {
    Stack<String> stack = new Stack<>();
    public String solution(int n, int t, int m, int p) {
        StringBuilder sb = new StringBuilder();
        int count = 0, number = 0, index = 1;
        while(true) {

            if(stack.isEmpty()) {
                convert(n, number++);
            }

            if(index == p) {
                sb.append(stack.pop());

                if(++count == t) break;
            } else stack.pop();

            if(m < ++index) index = 1;
        }

        return sb.toString();
    }

    public void convert(int n, int number) {

        if(number == 0) {
            stack.add("0");
            return ;
        }

        while(0 < number) {

            int temp = number % n;
            stack.add((temp < 10) ? String.valueOf(temp) : String.valueOf((char)('A' + temp - 10)));
            number /= n;

        }
    }
}