import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// [dice]
//   2
// 4 1 3
//   5
//   6

class Dice {
    int dir = 0; // 0: east, 1: south, 2: west, 3: north | opposite side = (dir + 2) % 4
    int x, y;
    int[] faces = {0, 1, 2, 3, 4, 5, 6}; // [dice]

    Dice(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void reverseDir() {
        this.dir = (this.dir + 2) % 4;
    }

    public void rotate() {
        switch(this.dir) {
            case 0:
                changeFaces(this.faces[4], this.faces[2], this.faces[1], this.faces[6], this.faces[5], this.faces[3]);
                break;
            case 1:
                changeFaces(this.faces[2], this.faces[6], this.faces[3], this.faces[4], this.faces[1], this.faces[5]);
                break;
            case 2:
                changeFaces(this.faces[3], this.faces[2], this.faces[6], this.faces[1], this.faces[5], this.faces[4]);
                break;
            case 3:
                changeFaces(this.faces[5], this.faces[1], this.faces[3], this.faces[4], this.faces[6], this.faces[2]);
        }
    }

    public void changeDir(int b) {
        int a = this.faces[6];
        if(b < a) ++this.dir;
        else if(a < b) this.dir += 3;
        this.dir %= 4;
    }

    public void changeFaces(int a, int b, int c, int d, int e, int f) {
        this.faces[1] = a;
        this.faces[2] = b;
        this.faces[3] = c;
        this.faces[4] = d;
        this.faces[5] = e;
        this.faces[6] = f;
    }
}

public class Main {
    static int N, M, K;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int[][] board;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = 0;
        Dice dice = new Dice(0, 0);
        while(0 < K--) {
            int x = dice.x + dx[dice.dir];
            int y = dice.y + dy[dice.dir];
            if(x < 0 || y < 0 || x == N || y == M) {
                dice.reverseDir();
                x = dice.x + dx[dice.dir];
                y = dice.y + dy[dice.dir];
            }
            dice.x = x;
            dice.y = y;
            dice.rotate();
            result += getPoint(x, y);
            dice.changeDir(board[x][y]);
        }
        System.out.println(result);
    }

    public static int getPoint(int x, int y) {
        boolean[][] visited = new boolean[N][M];
        visited[x][y] = true;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        
        int point = board[x][y];
        int result = board[x][y];
        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            for(int d = 0; d < 4; d++) {
                int nx = current[0] + dx[d];
                int ny = current[1] + dy[d];
                if(nx < 0 || ny < 0 || nx == N || ny == M || visited[nx][ny] || board[nx][ny] != point) continue;
                result += point;
                visited[nx][ny] = true;
                queue.add(new int[]{nx, ny});
            }
        }
        return result;
    }
}
