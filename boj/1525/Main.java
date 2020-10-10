import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

class Matrix {
    int x, y;
    int[][] matrix = new int[3][3];

    Matrix(int[][] matrix, int x, int y) {
        this.x = x;
        this.y = y;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) this.matrix[i][j] = matrix[i][j];
        }
    }

    void swap(int a, int b) {
        int temp = matrix[a][b];
        matrix[a][b] = matrix[this.x][this.y];
        matrix[this.x][this.y] = temp;
    }

    int val() {
        int value = 0;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) value = value * 10 + this.matrix[i][j];
        }

        return value;
    }
}

public class Main {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static Map<Integer, Boolean> visited = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int x = 0, y = 0;
        int[][] src = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        int[][] map = new int[3][3];
        for(int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0) {
                    x = i;
                    y = j;
                }
            }
        }

        if(isSame(src, map)) {
            System.out.println(0);
            System.exit(0);
        }

        Queue<Matrix> queue = new LinkedList<>();
        queue.add(new Matrix(src, 2, 2));
        visited.put(123456780, true);

        int count = 1;
        boolean isMatch = false;
        loop:
        while(!queue.isEmpty()) {
            int size = queue.size();

            // TODO:
            // System.out.println(count);

            while(0 < size--) {
                Matrix current = queue.poll();

                // TODO:
                // if(count < 4) {
                //         for(int i = 0; i < 3; i++)
                //         System.out.println(Arrays.toString(current.matrix[i]));
                //     System.out.println();
                // }

                for(int d = 0; d < 4; d++) {
                    x = current.x + dx[d];
                    y = current.y + dy[d];

                    if(x < 0 || y < 0 || x == 3 || y == 3) continue;

                    Matrix temp = new Matrix(current.matrix, current.x, current.y);
                    temp.swap(x, y);

                    if(visited.containsKey(temp.val())) continue;
                    if(isSame(temp.matrix, map)) {
                        isMatch = true;
                        break loop;
                    }
                    
                    visited.put(temp.val(), true);
                    temp.x = x;
                    temp.y = y;
                    queue.add(temp);
                }

            }

            count++;
        }

        if(isMatch) System.out.println(count);
        else System.out.println(-1);
    }

    public static boolean isSame(int[][] A, int[][] B) {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(A[i][j] != B[i][j]) return false;
            }
        }

        return true;
    }
}