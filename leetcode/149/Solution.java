class Solution {
    public int maxPoints(int[][] points) {
        int N = points.length, result = 0;
        if(N < 3) return N;

        for(int i = 0; i < N; i++) {
            int[] p1 = points[i];
            for(int j = i + 1; j < N; j++) {
                int[] p2 = points[j];

                int x1, y1, x2, y2;
                if(p1[0] < p2[0]) {
                    x1 = p1[0];
                    y1 = p1[1];
                    x2 = p2[0];
                    y2 = p2[1];
                } else {
                    x1 = p2[0];
                    y1 = p2[1];
                    x2 = p1[0];
                    y2 = p1[1];
                }

                int x = x2 - x1;
                int y = y2 - y1;

                int count = 2;
                if(x == 0) {
                    int val = p1[0];
                    for(int k = j + 1; k < N; k++) {
                        if(points[k][0] == val) count++;
                    }
                    result = Math.max(result, count);
                    continue;
                } else if(y == 0) {
                    int val = p1[1];
                    for(int k = j + 1; k < N; k++) {
                        if(points[k][1] == val) count++;
                    }
                    result = Math.max(result, count);
                    continue;
                }

                for(int k = j + 1; k < N; k++) {
                    int[] point = points[k];
                    int[] gradient = getGradient(x1, y1, point[0], point[1]);
                    if(x * gradient[1] == y * gradient[0]) count++;
                }
                result = Math.max(result, count);
            }
        }
        return result;
    }

    public int[] getGradient(int x1, int y1, int x2, int y2) {
        if(x1 < x2) {
            return new int[]{x2 - x1, y2 - y1};
        } else {
            return new int[]{x1 - x2, y1 - y2};
        }
    }
}