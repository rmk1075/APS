import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int check[] = new int[9], set[][] = {{0, 0}, {0, 3}, {0, 6}, {3, 0}, {3, 3}, {3, 6}, {6, 0}, {6, 3}, {6, 6}};
    static char[][] sdoku = new char[9][9];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i = 0; i < 9; i++) sdoku[i] = br.readLine().toCharArray();
        for(int i = 0; i < 9; i++) check(i, set[i][0], set[i][1]);
        dfs(0, 0);
    }    

    public static void dfs(int x, int y) {
        if(x == 9) print();
        if(sdoku[x][y] != '0') {
            if(y + 1 == 9) dfs(x + 1, 0);
            else dfs(x, y + 1);
            return ;
        }

        int idx = 0;
        for(; idx < 9; idx++) {
            int difX = x - set[idx][0], difY = y - set[idx][1];
            if(-1 < difX && difX < 3 && -1 < difY && difY < 3) break;
        }

        for(int i = 1; i < 10; i++) {
            if((check[idx] & (1 << i)) != 0 || judge(x, y, i)) continue;
            check[idx] |= (1 << i);
            sdoku[x][y] = (char)(i + '0');
            
            if(y + 1 == 9) dfs(x + 1, 0);
            else dfs(x, y + 1);

            check[idx] &= ~(1 << i);
            sdoku[x][y] = '0';
        }
    }

    public static boolean judge(int x, int y, int k) {
        char val = (char)(k + '0');
        for(int i = 0; i < 9; i++) {
            if(sdoku[x][i] == val || sdoku[i][y] == val) return true;
        }
        return false;
    }

    public static void print() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) sb.append(sdoku[i][j]);
            sb.append("\n");
        }
        System.out.println(sb);
        System.exit(0);
    }

    public static void check(int index, int x, int y) {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(sdoku[x + i][y + j] != '0') {
                    check[index] |= (1 << (sdoku[x + i][y + j] - '0'));
                }
            }
        }
    }
}