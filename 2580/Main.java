import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[][] board = new int[9][9];
        boolean[][] rows = new boolean[9][9];
        boolean[][] cols = new boolean[9][9];
        boolean[][] sections = new boolean[9][9];
        int[][] section_xy = new int[9][9];
        
        ArrayList<Integer[]> zeros = new ArrayList<Integer[]>();
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                section_xy[i][j] = 3*(int)(i/3) + (int)(j/3);
                board[i][j] = sc.nextInt();

                if(board[i][j] == 0) {
                    zeros.add(new Integer[]{i, j});
                } else {
                    rows[i][board[i][j]-1] = cols[board[i][j]-1][j] = sections[section_xy[i][j]][board[i][j]-1] = true;
                }
            }
        }
        sc.close();

        sdoku(board, rows, cols, sections, section_xy, zeros, 0);
    }

    public static void sdoku(int[][] board, boolean[][]rows, boolean[][] cols, boolean[][] sections, int[][] section_xy, ArrayList<Integer[]> zeros, int count) {
        if(zeros.size() == count) {
            for(int i = 0; i < 9; i++) {
                for(int j = 0; j < 9; j++) {
                    System.out.print(board[i][j]);
                    System.out.print(' ');
                }
                System.out.println();
            }
            System.exit(0);
        } else {
            int x = zeros.get(count)[0];
            int y = zeros.get(count)[1];

            for(int i = 1; i < 10; i++) {
                if(!(rows[x][i-1] || cols[i-1][y] || sections[section_xy[x][y]][i-1])) {
                    rows[x][i-1] = cols[i-1][y] = sections[section_xy[x][y]][i-1] = true;
                    board[x][y] = i;
                    sdoku(board, rows, cols, sections, section_xy, zeros, count+1);
                    rows[x][i-1] = cols[i-1][y] = sections[section_xy[x][y]][i-1] = false;
                    board[x][y] = 0;
                }
            }
        }
    }
}