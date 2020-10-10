import java.util.Arrays;

class Solution {
    public boolean solution(int[][] key, int[][] lock) {
        boolean answer = false;
        int N = lock.length, M = key.length;

        boolean isLocked = false;
        loop: for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(lock[i][j] == 0) {
                    isLocked = true;
                    break loop;
                }
            }
        }
        if(!isLocked) return true;

        int[][] board = new int[N + 2 * M][N + 2 * M];
        for(int d = 0; d < 4; d++) {
            // rotate
            key = rotate(M, key);

            for(int i = 0; i < M + N; i++) {
                loop: for(int j = 0; j < M + N; j++) {
                    
                    // reset
                    for(int k = 0; k < N + 2 * M; k++) {
                        Arrays.fill(board[k], 0);
                    }

                    // copy: lock -> board
                    for(int k = 0; k < N; k++) {
                        for(int l = 0; l < N; l++) {
                            board[k + M][l + M] = lock[k][l];
                        }
                    }

                    // key
                    for(int k = 0; k < M; k++) {
                        for(int l = 0; l < M; l++) {
                            board[k + i][l + j] += key[k][l];
                        }
                    }

                    // check
                    for(int k = M; k < M + N; k++) {
                        for(int l = M; l < M + N; l++) {
                            if(board[k][l] != 1) continue loop;
                        }
                    }

                    return true;
                }
            }
            
        }

        return answer;
    }

    public int[][] rotate(int M, int[][] key) {
        int[][] rotatedKey = new int[M][M];

        for(int i = 0; i < M; i++) {
            for(int j = 0; j < M; j++) {
                rotatedKey[i][j] = key[j][M - i - 1];
            }
        }

        return rotatedKey;
    }
}