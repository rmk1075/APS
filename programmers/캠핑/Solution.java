import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Solution {
    public int solution(int n, int[][] data) {
        int answer = 0;

        Set<Integer> xSet = new HashSet<>();
        Set<Integer> ySet = new HashSet<>();

        for (int[] d : data) {
            xSet.add(d[0]);
            ySet.add(d[1]);
        }

        Integer[] xArr = new Integer[xSet.size()];
        Integer[] yArr = new Integer[ySet.size()];

        xSet.toArray(xArr);
        ySet.toArray(yArr);

        Arrays.sort(xArr);
        Arrays.sort(yArr);

        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            int x = data[i][0], y = data[i][1];

            int index = 0;
            for (int x_ : xArr) {
                if (x_ == x) {
                    data[i][0] = index;
                    break;
                }
                index++;
            }

            index = 0;
            for (int y_ : yArr) {
                if (y_ == y) {
                    data[i][1] = index;
                    break;
                }
                index++;
            }

            map[data[i][0]][data[i][1]] = 1;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] += (0 < i ? map[i - 1][j] : 0) + (0 < j ? map[i][j - 1] : 0)
                        - (0 < i && 0 < j ? map[i - 1][j - 1] : 0);
            }
        }

        for (int i = 0; i < n; i++) {
            int x = data[i][0], y = data[i][1];
            for (int j = i + 1; j < n; j++) {
                if (x == data[j][0] || y == data[j][1])
                    continue;

                int x1 = Math.min(x, data[j][0]);
                int y1 = Math.min(y, data[j][1]);
                int x2 = Math.max(x, data[j][0]);
                int y2 = Math.max(y, data[j][1]);

                if ((x2 - 1 < x1 + 1 || y2 - 1 < y1 + 1)
                        || (map[x2 - 1][y2 - 1] - map[x1][y2 - 1] - map[x2 - 1][y1] + map[x1][y1] == 0))
                    answer++;
            }
        }

        return answer;
    }
}