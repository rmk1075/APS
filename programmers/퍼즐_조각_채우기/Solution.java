import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * overview
 * 
 * table 의 조각들 중에서 game_board 의 빈칸에 알맞는 조각의 개수를 찾아야한다.
 * game_board 의 비어있는 공간과 table 의 조각들을 각각 읽어온다.
 * 읽어온 공간과 조각을 비교하여 일치하는 조각들과 해당 조각들이 덮는 면적을 구하여 반환한다.
 * 
 * description
 * 
 * 각 조각들을 Puzzle 이라는 객체로 다룬다.
 * Puzzle 타입은 조각을 직사각형 형태로 저장하는데, 전체 직사각형의 면적의 puzzle 과 행, 열의 크기인 r, c, 그리고 실제 조각이 덮는 면적인 size 를 멤버로 가진다.
 * puzzle 행렬에서 실제 조각이 위치하는 요소를 true 로 지정했다.
 * game_board 와 table 을 각각 bfs 로 탐색하여 빈 공간과 퍼즐 조각 리스트를 구한다.
 * 이제 각 리스트의 요소들을 서로 비교하는데, 이때 조각은 회전이 가능하기 때문에 4가지 방향에 대해서 비교를 해야한다.
 * 공간과 조각의 size 가 같은지 먼저 비교하고, r 과 c 를 비교하여 같은 경우에 맞춰서 각 방향에 대해서 요소들의 비교를 수행한다.
 * 비교를 통해서 일치하는 경우에는 size 만큼을 answer 에 더하고 해당 퍼즐의 size 는 -1 로 설정하여 더이상 비교하지 않도록 한다.
 */
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