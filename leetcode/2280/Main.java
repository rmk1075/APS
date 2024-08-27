import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int minimumLines(int[][] stockPrices) {
        int length = stockPrices.length;
        if (length == 1) {
            return 0;
        } else if (length < 3) {
            return 1;
        }

        Arrays.sort(stockPrices, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });

        Slope slope = new Slope(stockPrices[0], stockPrices[1]);
        int[] prevPoint = stockPrices[1];
        int numOfLines = 1;
        for (int i = 2; i < stockPrices.length; i++) {
            Slope currentSlope = new Slope(prevPoint, stockPrices[i]);
            prevPoint = stockPrices[i];
            if (slope.compareTo(currentSlope) == 0) {
                continue;
            }
            slope = currentSlope;
            numOfLines++;
        }

        return numOfLines;
    }
}

class Slope implements Comparable<Slope> {
    final long x;
    final long y;

    Slope(int[] pointA, int[] pointB) {
        x = (long)pointB[0] - pointA[0];
        y = (long)pointB[1] - pointA[1];
    }

    @Override
    public int compareTo(Slope o) {
        long a = this.x * o.y;
        long b = this.y * o.x;
        return Long.compare(a, b);
    }
}
