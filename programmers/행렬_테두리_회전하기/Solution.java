import java.util.LinkedList;
import java.util.List;

class Solution {
    int row, column;
    int[][] board;
    public int[] solution(int rows, int columns, int[][] queries) {
        board = createBoard(rows, columns);
        List<Integer> result = new LinkedList<>();
        for(int[] query : queries) {
            result.add(rotate(query[0] - 1, query[1] - 1, query[2] - 1, query[3] - 1));
        }
        int[] answer = result.stream().mapToInt(Integer::intValue).toArray();
        return answer;
    }

    private int[][] createBoard(int rows, int columns) {
        row = rows;
        column = columns;
        int[][] result = new int[rows][columns];
        int index = 1;
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                result[i][j] = index++;
            }
        }
        return result;
    }

    private int rotate(int x1, int y1, int x2, int y2) {
        int result = Integer.MAX_VALUE;
        int x = x1;
        int y = y1;
        int temp = board[x][y];
        for(x = x1 + 1; x <= x2; x++) {
            board[x - 1][y] = board[x][y];
            result = Math.min(result, board[x][y]);
        }
        x = x2;
        for(y = y1 + 1; y <= y2; y++) {
            board[x][y - 1] = board[x][y];
            result = Math.min(result, board[x][y]);
        }
        y = y2;
        for(x = x2 - 1; x1 <= x; x--) {
            board[x + 1][y] = board[x][y];
            result = Math.min(result, board[x][y]);
        }
        x = x1;
        for(y = y2 - 1; y1 <= y; y--) {
            board[x][y + 1] = board[x][y];
            result = Math.min(result, board[x][y]);
        }
        y = y1;
        if(y + 1 < column) {
            board[x][y + 1] = temp;
            result = Math.min(result, temp);
        }

        return result;
    }
}