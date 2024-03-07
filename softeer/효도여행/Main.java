import java.io.*;
import java.util.*;

public class Main {
    private static int N, M, maxHappy = 0;
    private static char[] S;
    private static int[][] board;
    private static char[][] tree;
    private static boolean[] visited;
    public static void main(String[] args) throws IOException {
        init();
        visited[0] = true;
        find(0, 0, ' ');
        System.out.println(maxHappy);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        S = br.readLine().toCharArray();
        tree = new char[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tree[i][j] = ' ';
            }
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            char c = st.nextToken().charAt(0);
            tree[u][v] = c;
            tree[v][u] = c;
        }

        board = new int[5001][S.length + 1];
        visited = new boolean[N];
    }

    private static void find(int level, int index, char ch) {
        if (0 < level) {
          compare(level, ch);
        }

        for (int i = 0; i < N; i++) {
            if (tree[index][i] == ' ' || visited[i]) {
                continue;
            }

            visited[i] = true;
            find(level + 1, i, tree[index][i]);
        }
    }

    private static void compare(int i, char ch) {        
        for (int j = 1; j < S.length + 1; j++) {
            if (ch == S[j - 1]) {
                board[i][j] = board[i - 1][j - 1] + 1;
            } else {
                board[i][j] = Math.max(board[i][j - 1], board[i - 1][j]);
            }
            maxHappy = Math.max(maxHappy, board[i][j]);
        }
    }
}
