import java.util.Arrays;

class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int N = board.length;
        int M = board[0].length;

        int[][] points = new int[N][M];
        for(int[] sk : skill) {
            int r1 = sk[1];
            int c1 = sk[2];
            int r2 = sk[3];
            int c2 = sk[4];
            for(int i = r1; i <= r2; i++) points[i][c1] += (sk[0] == 1 ? -sk[5] : sk[5]);

            if(c2 != M - 1) {
                for(int i = r1; i <= r2; i++) points[i][c2 + 1] += (sk[0] == 1 ? sk[5] : -sk[5]);
            }
        }

        int point = 0;
        for(int r = 0; r < N; r++) {
            for(int c = 0; c < M; c++) {
                point += points[r][c];
                if(0 < board[r][c] + point) answer++;
            }
            point = 0;
        }

        return answer;
    }
}