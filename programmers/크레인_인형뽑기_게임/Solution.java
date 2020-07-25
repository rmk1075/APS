import java.util.Stack;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int N = board.length, answer = 0;

        Stack<Integer> stack = new Stack<>();
        for (int m : moves) {
            m--;
            int val = 0;
            for(int i = 0; i < N; i++) {
                if(board[i][m] == 0) continue;
                val = board[i][m];
                board[i][m] = 0;
                break;
            }
            
            if(val == 0) continue;
            if(!stack.isEmpty() && stack.peek() == val) {
                stack.pop();
                answer += 2;
            } else stack.push(val);
        }

        return answer;
    }
}