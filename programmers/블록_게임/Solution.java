class Block {
    int[] a, b;

    Block(int ax, int ay, int bx, int by) {
        a = new int[] { ax, ay };
        b = new int[] { bx, by };
    }
}

class Solution {
    int N;
    Block[] blocks = new Block[201];

    public int solution(int[][] board) {
        int answer = 0;
        int index;
        N = board.length;

        boolean[] visited = new boolean[201];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                index = board[i][j];
                if (index == 0 || visited[index])
                    continue;

                visited[index] = true;
                find(board, index, i, j);
            }
        }

        while (true) {
            int cnt = 0;

            for (int i = 1; i < 201; i++) {
                if (blocks[i] == null || !drop(board, blocks[i]))
                    continue;

                for (int j = 0; j < N; j++) {
                    for (int k = 0; k < N; k++) {
                        if (board[j][k] == i)
                            board[j][k] = 0;
                    }
                }

                blocks[i] = null;
                cnt++;
            }

            if (cnt == 0)
                break;

            answer += cnt;
        }

        return answer;
    }

    public boolean drop(int[][] board, Block block) {
        int x, y;

        // a
        x = block.a[0];
        y = block.a[1];

        while (-1 < x) {
            if (board[x--][y] != 0)
                return false;
        }

        // b
        x = block.b[0];
        y = block.b[1];

        while (-1 < x) {
            if (board[x--][y] != 0)
                return false;
        }

        return true;
    }

    public void find(int[][] board, int index, int x, int y) {
        if (checkA(board, index, x, y)) {
            blocks[index] = new Block(x, y + 1, x + 1, y + 1);
        } else if (checkB(board, index, x, y)) {
            blocks[index] = new Block(x, y - 1, x - 1, y - 1);
        } else if (checkC(board, index, x, y)) {
            blocks[index] = new Block(x, y + 1, x, y + 2);
        } else if (checkD(board, index, x, y)) {
            blocks[index] = new Block(x, y - 1, x, y - 2);
        } else if (checkE(board, index, x, y)) {
            blocks[index] = new Block(x, y - 1, x, y + 1);
        }
    }

    public boolean checkA(int[][] board, int index, int x, int y) {
        if (N < x + 3 || N < y + 2)
            return false;

        if (board[x + 1][y] != index || board[x + 2][y] != index || board[x + 2][y + 1] != index)
            return false;

        return true;
    }

    public boolean checkB(int[][] board, int index, int x, int y) {
        if (N < x + 3 || y < 1)
            return false;

        if (board[x + 1][y] != index || board[x + 2][y] != index || board[x + 2][y - 1] != index)
            return false;

        return true;
    }

    public boolean checkC(int[][] board, int index, int x, int y) {
        if (N < x + 2 || N < y + 3)
            return false;

        if (board[x + 1][y] != index || board[x + 1][y + 1] != index || board[x + 1][y + 2] != index)
            return false;

        return true;
    }

    public boolean checkD(int[][] board, int index, int x, int y) {
        if (N < x + 2 || y < 2)
            return false;

        if (board[x + 1][y] != index || board[x + 1][y - 1] != index || board[x + 1][y - 2] != index)
            return false;

        return true;
    }

    public boolean checkE(int[][] board, int index, int x, int y) {
        if (N < x + 2 || y < 1 || N < y + 2)
            return false;

        if (board[x + 1][y] != index || board[x + 1][y - 1] != index || board[x + 1][y + 1] != index)
            return false;

        return true;
    }
}