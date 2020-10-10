import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
    static int[][] map, indexMap;
    static ArrayList<Integer> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        indexMap = new int[N][M];
        for(int i = 0; i < N; i++) Arrays.fill(indexMap[i], -1);

        int index = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] == 0 || indexMap[i][j] != -1) continue;
                indexMap[i][j] = index;
                Queue<int[]> queue = new LinkedList<>();
                queue.offer(new int[] {i, j});

                int count = 1;
                while(!queue.isEmpty()) {
                    int[] current = queue.poll();
                    for(int d = 0; d < 4; d++) {
                        int x = current[0] + dx[d], y = current[1] + dy[d];
                        if(x < 0 || y < 0 || x == N || y == M || map[x][y] == 0 || indexMap[x][y] != -1) continue;
                        indexMap[x][y] = index;
                        count++;
                        queue.offer(new int[] {x, y});
                    }
                }

                list.add(count);
                index++;
            }
        }

        int maxSum = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] == 1) continue;

                Set<Integer> set = new HashSet<>();
                for(int d = 0; d < 4; d++) {
                    int x = i + dx[d], y = j + dy[d];
                    if(x < 0 || y < 0 || x == N || y == M || indexMap[x][y] == -1) continue;
                    set.add(indexMap[x][y]);
                }

                int tempSum = 1;
                for(int s : set) tempSum += list.get(s);
                maxSum = Math.max(maxSum, tempSum);
            }
        }

        System.out.println(maxSum);
    }
}