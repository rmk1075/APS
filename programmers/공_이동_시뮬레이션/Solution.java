import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

class Solution {
    public long solution(int n, int m, int x, int y, int[][] queries) {
        long x1 = x;
        long y1 = y;
        long x2 = x;
        long y2 = y;
        for(int q = queries.length - 1; -1 < q; q--) {
            int[] query = queries[q];
            int d = query[0];
            int dist = query[1];
            if(d == 0) {
                if(y1 != 0) y1 += dist;
                y2 = Math.min(m - 1, y2 + dist);
            } else if(d == 1) {
                y1 = Math.max(0, y1 - dist);
                if(y2 != m - 1) y2 -= dist;
            } else if(d == 2) {
                if(x1 != 0) x1 += dist;
                x2 = Math.min(n - 1, x2 + dist);
            } else if(d == 3) {
                x1 = Math.max(0, x1 - dist);
                if(x2 != n - 1) x2 -= dist;
            }

            if(n <= x1 || x2 < 0 || m <= y1 || y2 < 0) return 0;
        }

        long answer = (x2 - x1 + 1) * (y2 - y1 + 1);
        return answer;
    }
}