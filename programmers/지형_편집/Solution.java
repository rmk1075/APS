import java.util.Arrays;

public class Solution {
    private int n;
    private long cost = Long.MAX_VALUE;
    private int[] arr;
    private long sum;
    public long solution(int[][] land, int P, int Q) {
        init(land);
        find(P, Q);
        return cost;
    }

    private void init(int[][] land) {
        n = land.length * land.length;
        arr = new int[n];
        int i = 0;
        for (int[] lan : land) {
            for (int l : lan) {
                arr[i++] = l;
                sum += l;
            }
        }
        Arrays.sort(arr);
    }

    private void find(int p, int q) {
        long height = arr[0];
        int index = findNextIndex(0);
        long left = 0;
        long right = sum - (n * height);
        cost = calculateCost(left, right, p, q);
        while (index < arr.length) {
            long diff = arr[index] - height;
            left += index * diff;

            right -= (n - index) * diff;

            cost = Math.min(cost, calculateCost(left, right, p, q));
            height = arr[index];
            index = findNextIndex(index);
        }
    }

    private int findNextIndex(int start) {
        int value = arr[start];
        int i = start;
        for (; i < arr.length; i++) {
            if (arr[i] != value) {
                break;
            }
        }
        return i;
    }

    private long calculateCost(long left, long right, int p, int q) {
        return (left * p) + (right * q);
    }
}
