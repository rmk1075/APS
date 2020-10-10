// package programmers.기둥과_보_설치;

import java.util.Arrays;
import java.util.Comparator;

class Solution {
    int N, CNT = 0;
    Boolean[][][] board;
    public int[][] solution(int n, int[][] build_frame) {
        N = n;
        board = new Boolean[n + 1][n + 1][2];
        for(int i = 0; i < n + 1; i++) {
            for(int j = 0; j < n + 1; j++) {
                board[i][j][0] = board[i][j][1] = false;
            }
        }
        
        for(int[] build : build_frame) {
            int y = build[0], x = n - build[1], a = build[2], b = build[3]; // a: 0(기둥), 1(보), b: 0(삭제), 1(설치)

            // 삭제
            if(b == 0) {

                if(!board[x][y][a]) continue;
                remove(x, y, a);

                if(a == 0) {
                    if(check()) continue;
                    create(x, y, a);
                } else {
                    if(check()) continue;
                    create(x, y, a);
                }

            // 설치
            } else {

                create(x, y, a);

                if((a == 0 ? checkPillar(x, y) : checkBase(x, y))) continue;
                remove(x, y, a);

            }
            
        }


        // TODO:
        // for(int[] b : board) {
        //     System.out.println(Arrays.toString(b));
        // }

        int idx = 0;
        int[][] answer = new int[CNT][3];
        for(int i = 0; i < n + 1; i++) {
            for(int j = 0; j < n + 1; j++) {
                if(board[i][j][0]) {
                    answer[idx][0] = j;
                    answer[idx][1] = n - i;
                    answer[idx][2] = 0;
                    idx++;
                }

                if(board[i][j][1]) {
                    answer[idx][0] = j;
                    answer[idx][1] = n - i;
                    answer[idx][2] = 1;
                    idx++;
                }
            }
        }
        

        /**
         * return 하는 배열은 x좌표 기준으로 오름차순 정렬하며, x좌표가 같을 경우 y좌표 기준으로 오름차순 정렬해주세요.
         * x, y좌표가 모두 같은 경우 기둥이 보보다 앞에 오면 됩니다.
         */
        Arrays.sort(answer, new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] == o2[0]) {
                    if(o1[1] == o2[1]) {
                        return o1[2] - o2[2];
                    }
                    return o1[1] - o2[1];
                }
                return o1[0] - o2[0];
            }
        });

        return answer;
    }

    /**
     * 기둥은 바닥 위에 있거나 보의 한쪽 끝 부분 위에 있거나, 또는 다른 기둥 위에 있어야 합니다.
     * 보는 한쪽 끝 부분이 기둥 위에 있거나, 또는 양쪽 끝 부분이 다른 보와 동시에 연결되어 있어야 합니다.
     */

    public boolean checkPillar(int x, int y) {
        if(x == 0) return false;
        if(x == N || board[x + 1][y][0] || (y != 0 && board[x][y - 1][1]) || board[x][y][1]) return true;

        return false;
    }

    public boolean checkBase(int x, int y) {
        if(x == N || y == N) return false;

        if(board[x + 1][y][0] || board[x + 1][y + 1][0]) return true;
        if((y != 0 && board[x][y - 1][1]) && board[x][y + 1][1]) return true;
        return false;
    }

    public boolean check() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(board[i][j][0] && !checkPillar(i, j)) return false;
                if(board[i][j][1] && !checkBase(i, j)) return false;
            }
        }

        return true;
    }

    public void create(int x, int y, int a) {
        board[x][y][a] = true;
        CNT++;


        // TODO:
        // System.out.println("[create] " + x + " " + y + " " + a);
    }

    public void remove(int x, int y, int a) {
        board[x][y][a] = false;
        CNT--;

        // TODO:
        // System.out.println("[remove] " + x + " " + y + " " + a);
    }
}