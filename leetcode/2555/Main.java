public class Main {
    public static void main(String[] args) {

    }
}

class Solution {
    public int maximizeWin(int[] prizePositions, int k) {
        int maxCount = 0;
        int n = prizePositions.length;
        int[] dp = new int[n + 1]; // count of largest segment from 0 ~ i
        int j = 0;
        for (int i = 0; i < n; i++) {
            while (prizePositions[j] < prizePositions[i] - k) {
                j++;
            }

            int count = i - j + 1; // count of current segment
            dp[i + 1] = Math.max(dp[i], count);
            maxCount = Math.max(maxCount, dp[j] + count);
        }

        return maxCount;
    }
}
