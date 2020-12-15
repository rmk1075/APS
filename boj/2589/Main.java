import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] dx = { -1, 0, 1, 0 }, dy = { 0, -1, 0, 1 };
    static char[][] map;
    static boolean[][] visited;
    static Queue<int[]> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N][M];
        map = new char[N][M];
        for (int i = 0; i < N; i++)
            map[i] = br.readLine().toCharArray();

        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'L')
                    answer = Math.max(answer, find(i, j));
            }
        }

        System.out.println(answer);
    }

    public static int find(int x, int y) {
        for (int i = 0; i < N; i++)
            Arrays.fill(visited[i], false);

        queue.offer(new int[] { x, y });
        visited[x][y] = true;

        int a, b, size, len = 0;
        int[] current = { x, y };
        while (true) {
            size = queue.size();
            while (0 < size--) {
                current = queue.poll();
                for (int d = 0; d < 4; d++) {
                    a = current[0] + dx[d];
                    b = current[1] + dy[d];
                    if (a < 0 || b < 0 || a == N || b == M || map[a][b] == 'W' || visited[a][b])
                        continue;

                    visited[a][b] = true;
                    queue.offer(new int[] { a, b });
                }
            }
            if (queue.isEmpty())
                break;
            len++;
        }

        return len;
    }
}

// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.util.LinkedList;
// import java.util.Queue;

// class Location {
// int x, y;

// Location(int x, int y) {
// this.x = x;
// this.y = y;
// }
// }

// public class Main {
// static int N, M;
// static int[] dx = { -1, 0, 1, 0 };
// static int[] dy = { 0, -1, 0, 1 };
// static int[][] updated;
// static char[][] map;
// static Queue<Location> queue;

// public static void main(String[] args) throws IOException {
// BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

// String temp = br.readLine();
// N = Integer.parseInt(temp.split(" ")[0]);
// M = Integer.parseInt(temp.split(" ")[1]);
// map = new char[N][M];

// for (int i = 0; i < N; i++) {
// map[i] = br.readLine().toCharArray();
// }

// int ans = 0;
// for (int i = 0; i < N; i++) {
// for (int j = 0; j < M; j++) {
// if (map[i][j] == 'L') {
// ans = Math.max(ans, find(i, j));
// }
// }
// }

// System.out.println(ans);
// }

// public static int find(int srcX, int srcY) {
// updated = new int[N][M];
// queue = new LinkedList<Location>();

// queue.offer(new Location(srcX, srcY));
// updated[srcX][srcY] = 1;

// int count = 0, x, y;
// Location current;
// while (!queue.isEmpty()) {
// int size = queue.size();
// for (int s = 0; s < size; s++) {
// current = queue.poll();

// for (int d = 0; d < 4; d++) {
// x = current.x + dx[d];
// y = current.y + dy[d];

// if (x < 0 || y < 0 || N == x || M == y || map[x][y] == 'W' || updated[x][y]
// == 1)
// continue;

// updated[x][y] = 1;
// queue.offer(new Location(x, y));
// }
// }

// count++;
// }

// return count - 1;
// }
// }