
/**
 * reference: https://tech.kakao.com/2017/09/13/code-festival-round-2/
 */

import java.util.ArrayList;
import java.util.List;

class Solution {
    public int solution(int n, int m, int[][] timetable) {
        int answer = 0;

        int[] times = new int[1321];
        for (int[] time : timetable) {
            for (int i = time[0]; i <= time[1]; i++)
                times[i]++;
        }

        int maxCnt = 0;
        for (int i = 600; i < 1321; i++)
            maxCnt = Math.max(maxCnt, times[i]);

        answer = find(n, maxCnt);
        return answer;
    }

    public int find(int n, int maxCnt) {
        if (maxCnt == 1)
            return 0;

        List<int[]> list = new ArrayList<>();

        int[] distances = new int[22];
        distances[1] = n * n;
        distances[2] = n * n / 2 + n % 2;
        for (int i = 3; i < 22; i++) {
            for (int j = 0; j < n; j++) {
                list.clear();

                for (int k = 0; k < n; k++) {
                    for (int l = k == 0 ? j : 0; l < n; l++) {
                        boolean flag = true;
                        for (int[] lis : list) {
                            if (cal(k, l, lis[0], lis[1]) < i) {
                                flag = false;
                                break;
                            }
                        }

                        if (flag)
                            list.add(new int[] { k, l });
                    }
                }

                distances[i] = Math.max(distances[i], list.size());
            }
        }

        for (int i = 1; i < 22; i++) {
            if (distances[i] < maxCnt)
                return i - 1;
        }

        return 21;
    }

    public int cal(int a, int b, int c, int d) {
        return Math.abs(a - c) + Math.abs(b - d);
    }
}

// import java.util.LinkedList;
// import java.util.Queue;

// class Solution {
// final int MAXNUM = Integer.MAX_VALUE;
// int maxDistance = 0;
// int[] dx = { -1, 0, 1, 0 }, dy = { 0, -1, 0, 1 };
// boolean[][] visited;
// Queue<int[]> queue = new LinkedList<>();

// public int solution(int n, int m, int[][] timetable) {
// int answer = 0;
// visited = new boolean[n][n];

// int[] times = new int[1321];
// for (int[] time : timetable) {
// for (int i = time[0]; i <= time[1]; i++)
// times[i]++;
// }

// int maxCnt = 0;
// for (int i = 600; i < 1321; i++)
// maxCnt = Math.max(maxCnt, times[i]);

// if (maxCnt != 1)
// dfs(n, new boolean[n][n], maxCnt, 0, MAXNUM);

// answer = maxDistance;
// return answer;
// }

// public void dfs(int n, boolean[][] locker, int maxCnt, int cnt, int
// minDistance) {
// if (cnt == maxCnt) {
// maxDistance = minDistance;
// return;
// }

// for (int i = 0; i < n; i++) {
// for (int j = 0; j < n; j++) {
// if (locker[i][j])
// continue;

// locker[i][j] = true;

// int distance = bfs(n, locker, i, j);
// if (maxDistance < distance)
// dfs(n, locker, maxCnt, cnt + 1, Math.min(minDistance, distance));

// locker[i][j] = false;
// }
// }

// return;
// }

// public int bfs(int n, boolean[][] locker, int srcX, int srcY) {
// int result = 0;

// queue.clear();
// queue.offer(new int[] { srcX, srcY });

// for (int i = 0; i < n; i++) {
// for (int j = 0; j < n; j++)
// visited[i][j] = false;
// }
// visited[srcX][srcY] = true;

// int size, x, y, current[];
// loop: while (!queue.isEmpty()) {
// result++;

// size = queue.size();
// while (0 < size--) {
// current = queue.poll();
// for (int d = 0; d < 4; d++) {
// x = current[0] + dx[d];
// y = current[1] + dy[d];

// if (x < 0 || y < 0 || x == n || y == n || visited[x][y])
// continue;

// if (locker[x][y])
// break loop;

// visited[x][y] = true;
// queue.offer(new int[] { x, y });
// }
// }
// }

// return result;
// }
// }

////////////////////////////////////////////////////////////////////////////////////////

// import java.util.LinkedList;
// import java.util.Queue;

// class Solution {
// final int MAXNUM = Integer.MAX_VALUE;
// int[] dx = { -1, 0, 1, 0 }, dy = { 0, -1, 0, 1 };
// boolean[][] visited;
// Queue<int[]> queue = new LinkedList<>();

// public int solution(int n, int m, int[][] timetable) {
// int answer = 0;
// visited = new boolean[n][n];

// int[] times = new int[1321];
// for (int[] time : timetable) {
// for (int i = time[0]; i <= time[1]; i++)
// times[i]++;
// }

// int maxCnt = 0;
// for (int i = 600; i < 1321; i++)
// maxCnt = Math.max(maxCnt, times[i]);

// answer = maxCnt == 1 ? 0 : dfs(n, new boolean[n][n], maxCnt, 0);
// return answer;
// }

// public int dfs(int n, boolean[][] locker, int maxCnt, int cnt) {
// int result = 0;
// if (cnt == maxCnt) {
// result = MAXNUM;
// for (int i = 0; i < n; i++) {
// for (int j = 0; j < n; j++) {
// if (!locker[i][j])
// continue;

// result = Math.min(result, bfs(n, locker, i, j));
// }
// }

// return result;
// }

// for (int i = 0; i < n; i++) {
// for (int j = 0; j < n; j++) {
// if (locker[i][j])
// continue;

// locker[i][j] = true;
// result = Math.max(result, dfs(n, locker, maxCnt, cnt + 1));
// locker[i][j] = false;
// }
// }

// return result;
// }

// public int bfs(int n, boolean[][] locker, int srcX, int srcY) {
// int result = 0;

// queue.clear();
// queue.offer(new int[] { srcX, srcY });

// for (int i = 0; i < n; i++) {
// for (int j = 0; j < n; j++)
// visited[i][j] = false;
// }
// visited[srcX][srcY] = true;

// int size, x, y, current[];
// loop: while (!queue.isEmpty()) {
// result++;

// size = queue.size();
// while (0 < size--) {
// current = queue.poll();
// for (int d = 0; d < 4; d++) {
// x = current[0] + dx[d];
// y = current[1] + dy[d];

// if (x < 0 || y < 0 || x == n || y == n || visited[x][y])
// continue;

// if (locker[x][y])
// break loop;

// visited[x][y] = true;
// queue.offer(new int[] { x, y });
// }
// }
// }

// return result;
// }
// }