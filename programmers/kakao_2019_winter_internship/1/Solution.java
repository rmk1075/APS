import java.util.Stack;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0, N = board.length;
        Stack<Integer> stack = new Stack<>();
        for(int col : moves) {
            col--;
            for(int i = 0; i < N; i++) {
                if(board[i][col] == 0) continue;
                if(!stack.isEmpty() && stack.peek() == board[i][col]) {
                    stack.pop();
                    answer += 2;
                } else stack.push(board[i][col]);
                board[i][col] = 0;
                break;
            }
        }

        return answer;
    }
}