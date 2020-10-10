import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N = 10, maxVal = 0;
    static int[][] board = {{0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40}, {10, 13, 16, 19, 25, 30, 35, 40}, {20, 22, 24, 25, 30, 35, 40}, {30, 28, 27, 26, 25, 30, 35, 40}};
    static int[][] boardHorses = new int[4][];
    static int[][] dices;
    static int[][] horses;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        boardHorses[0] = new int[21];
        boardHorses[1] = new int[8];
        boardHorses[2] = new int[7];
        boardHorses[3] = new int[8];

        horses = new int[4][2]; // [i]{row, col}
        dices = new int[N][2]; // [i]{point, horse}
        for(int i = 0; i < N; i++) dices[i][0] = Integer.parseInt(st.nextToken());

        find(0);

        System.out.println(maxVal);
    }

    public static boolean check(int row, int col) {

        if(boardHorses[row][col] != 0) return false;

        if(row == 0 && col == 20 && (boardHorses[1][7] == 1 || boardHorses[2][6] == 1 || boardHorses[3][7] == 1)) return false;

        if(row != 0) {

            switch(row) {
                case 1:
                    if(3 < col) {
                        if(boardHorses[2][col-1] == 1 || boardHorses[3][col] == 1) return false;
                        if(col == 7 && boardHorses[0][20] == 1) return false;
                    }
                    
                    break;

                case 2:
                    if(2 < col) {
                        if(boardHorses[1][col+1] == 1 || boardHorses[3][col+1] == 1) return false;
                        if(col == 6 && boardHorses[0][20] == 1) return false;
                    }

                    break;

                case 3:
                    if(3 < col) {
                        if(boardHorses[2][col-1] == 1 || boardHorses[1][col] == 1) return false;
                        if(col == 7 && boardHorses[0][20] == 1) return false;
                    }

                    break;
            }

        }

        return true;
    }

    public static void find(int count) {

        if(count == N) {

            for(int i = 0; i < 4; i++) {
                boardHorses[horses[i][0]][horses[i][1]] = 0;
                horses[i][0] = 0;
                horses[i][1] = 0;
            }
            for(int i = 0; i < boardHorses.length; i++) {
                for(int j = 0; j < boardHorses[i].length; j++) {
                    boardHorses[i][j] = 0;
                }
            }

            boolean finish = true;
            int sum = 0;
            for(int i = 0; i < N; i++) {

                int currentDice = dices[i][0];
                int currentHorse = dices[i][1];

                // row == 0 and 10, 20, 30
                if(horses[currentHorse][0] == 0) {
                    switch(horses[currentHorse][1]) {
                        case 5:
                            boardHorses[horses[currentHorse][0]][horses[currentHorse][1]] = 0;
                            horses[currentHorse][0] = 1;
                            horses[currentHorse][1] = 0;
                            break;
                        case 10:
                            boardHorses[horses[currentHorse][0]][horses[currentHorse][1]] = 0;
                            horses[currentHorse][0] = 2;
                            horses[currentHorse][1] = 0;
                            break;
                        case 15:
                            boardHorses[horses[currentHorse][0]][horses[currentHorse][1]] = 0;
                            horses[currentHorse][0] = 3;
                            horses[currentHorse][1] = 0;
                            break;
                    }
                }

                if(horses[currentHorse][0] == 5) continue;

                if(horses[currentHorse][1] + currentDice < board[horses[currentHorse][0]].length) {

                    // check
                    if(check(horses[currentHorse][0], horses[currentHorse][1] + currentDice)) {

                        boardHorses[horses[currentHorse][0]][horses[currentHorse][1]] = 0;
                        horses[currentHorse][1] += currentDice;
                        boardHorses[horses[currentHorse][0]][horses[currentHorse][1]] = 1;
                        sum += board[horses[currentHorse][0]][horses[currentHorse][1]];

                    } else {
                        finish = false;
                        break;
                    }


                } else {
                    boardHorses[horses[currentHorse][0]][horses[currentHorse][1]] = 0;
                    horses[currentHorse][0] = 5;
                    horses[currentHorse][1] = 0;
                }

            }

            // re init
            horses = new int[4][2];

            // if finished
            // TODO:
            if(finish) {
                // System.out.println(sum);
                // for(int i = 0; i < N; i++) {
                //     System.out.print(dices[i][1] + " ");
                // }
                // System.out.println();

                // if(maxVal < sum) {
                //     System.out.println(sum);
                //     for(int i = 0; i < N; i++) {
                //         System.out.print(dices[i][1] + " ");
                //     }
                //     System.out.println();

                //     if(sum == 218) {
                //         for(int i = 0; i < 4; i++) {
                //             System.out.println(Arrays.toString(boardHorses[i]));
                //         }
                //     }
                // }

                maxVal = Math.max(maxVal, sum);
            }

            return ;
        }

        for(int i = 0; i < 4; i++) {
            dices[count][1] = i;
            find(count + 1);
        }

    }
}