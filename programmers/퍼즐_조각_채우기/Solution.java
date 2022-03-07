import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Puzzle {
    int size, r, c;
    boolean[][] puzzle;

    public Puzzle(int size, List<int[]> pieces) {
        this.size = size;
        this.puzzle = createPuzzle(pieces);
        this.r = puzzle.length;
        this.c = puzzle[0].length;
    }

    public boolean[][] createPuzzle(List<int[]> pieces) {
        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;
        for(int[] piece : pieces) {
            minX = Math.min(minX, piece[0]);
            maxX = Math.max(maxX, piece[0]);
            minY = Math.min(minY, piece[1]);
            maxY = Math.max(maxY, piece[1]);
        }
        boolean[][] puzzle = new boolean[maxX - minX + 1][maxY - minY + 1];
        for(int[] piece : pieces) {
            puzzle[piece[0] - minX][piece[1] - minY] = true;
        }
        return puzzle;
    }

    @Override
    public boolean equals(Object obj) {
        Puzzle target = (Puzzle)obj;
        if(this.size != target.size) return false;
        if(this.r == target.r && this.c == target.c) {
            boolean flag = true;
            loop1: for(int i = 0; i < this.r; i++) {
                for(int j = 0; j < this.c; j++) {
                    if(this.puzzle[i][j] != target.puzzle[i][j]) {
                        flag = false;
                        break loop1;
                    }
                }
            }
            if(flag) return true;
            flag = true;
            loop2: for(int i = 0; i < this.r; i++) {
                for(int j = 0; j < this.c; j++) {
                    if(this.puzzle[this.r - i - 1][this.c - j - 1] != target.puzzle[i][j]) {
                        flag = false;
                        break loop2;
                    }
                }
            }
            if(flag) return true;
        }

        if(this.r == target.c && this.c == target.r) {
            boolean flag = true;
            loop1: for(int i = 0; i < this.r; i++) {
                for(int j = 0; j < this.c; j++) {
                    if(this.puzzle[i][j] != target.puzzle[j][target.c - i - 1]) {
                        flag = false;
                        break loop1;
                    }
                }
            }
            if(flag) return true;
            flag = true;
            loop2: for(int i = 0; i < this.r; i++) {
                for(int j = 0; j < this.c; j++) {
                    if(this.puzzle[i][j] != target.puzzle[target.r - j - 1][i]) {
                        flag = false;
                        break loop2;
                    }
                }
            }
            if(flag) return true;
        }
        return false;
    }
}

class Solution {
    static int N;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static Queue<int[]> queue = new LinkedList<>();
    public int solution(int[][] game_board, int[][] table) {
        N = game_board.length;
        List<Puzzle> emptyList = new LinkedList<>();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(game_board[i][j] == 0) {
                    Puzzle empty = bfs(i, j, game_board, 0);
                    emptyList.add(empty);
                }
            }
        }

        List<Puzzle> puzzleList = new LinkedList<>();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(table[i][j] == 1) {
                    Puzzle puzzle = bfs(i, j, table, 1);
                    puzzleList.add(puzzle);
                }
            }
        }

        int answer = 0;
        for(Puzzle puzzle : puzzleList) {
            for(Puzzle empty : emptyList) {
                if(puzzle.equals(empty)) {
                    emptyList.remove(empty);
                    answer += puzzle.size;
                    break;
                }
            }
        }
        return answer;
    }

    public Puzzle bfs(int i, int j, int[][] game_board, int target) {
        int temp = target == 0 ? 1 : 0;
        int size = 0;
        List<int[]> pieces = new LinkedList<>();
        queue.offer(new int[]{i, j});
        game_board[i][j] = temp;
        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            size++;
            pieces.add(new int[]{current[0], current[1]});
            for(int d = 0; d < 4; d++) {
                int x = current[0] + dx[d];
                int y = current[1] + dy[d];
                if(x < 0 || y < 0 || x == N || y == N || game_board[x][y] == temp) continue;
                game_board[x][y] = temp;
                queue.offer(new int[]{x, y});
            }
        }
        return new Puzzle(size, pieces);
    }
}