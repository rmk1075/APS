import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] A;
    static boolean[][] visited;

    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1}, dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] diagonal = {1, 3, 5, 7};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        visited = new boolean[N][N];

        int[][] clouds = createClouds(0);
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            moveClouds(clouds, d, s);
            raining(clouds);
            waterCopyBug(clouds);
            clouds = createClouds(1);
        }

        System.out.println(getSum());
    }

    private static int[][] createClouds(int turn) {
        if(turn == 0)
            return new int[][]{{N - 1, 0}, {N - 1, 1}, {N - 2, 0}, {N - 2, 1}};

        List<int[]> clouds = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(visited[i][j] || A[i][j] < 2) continue;
                A[i][j] -= 2;
                clouds.add(new int[]{i, j});
            }
        }
        return clouds.toArray(new int[clouds.size()][2]);
    }

    private static void moveClouds(int[][] clouds, int d, int s) {
        for(int i = 0; i < s; i++) {
            int N = clouds.length;
            for(int j = 0; j < N; j++) {
                clouds[j][0] = getCloudsLocation(clouds[j][0] + dx[d]);
                clouds[j][1] = getCloudsLocation(clouds[j][1] + dy[d]);
            }
        }
    }

    private static int getCloudsLocation(int location) {
        if(location < 0) return N - 1;
        else if(location == N) return 0;
        else return location;
    }

    private static void raining(int[][] clouds) {
        for(int i = 0; i < N; i++) {
            Arrays.fill(visited[i], false);
        }

        for(int[] cloud : clouds) {
            int x = cloud[0], y = cloud[1];
            A[x][y]++;
            visited[x][y] = true;
        }
    }

    private static void waterCopyBug(int[][] clouds) {
        for(int[] cloud : clouds) {
            int count = 0;
            for(int d : diagonal) {
                int x = cloud[0] + dx[d];
                int y = cloud[1] + dy[d];
                if(x < 0 || y < 0 || x == N || y == N || A[x][y] == 0) continue;
                count++;
            }
            A[cloud[0]][cloud[1]] += count;
        }
    }

    private static int getSum() {
        int result = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                result += A[i][j];
            }
        }
        return result;
    }
}