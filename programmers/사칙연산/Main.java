package programmers.사칙연산;

import java.util.Arrays;

public class Main {

}

class Solution {
    private static final String PLUS = "+";
    private static final String MINUS = "-";
    private int n; // 숫자의 개수 (각 숫자의 index)
    private int[][] maxDp; // i ~ j 까지의 max 값
    private int[][] minDp; // i ~ j 까지의 min 값
    public int solution(String[] arr) {
        init(arr);
        for (int interval = 1; interval < n; interval++) {
            for (int i = 0; i < n - interval; i++) {
                int j = i + interval;
                for (int k = i; k < j; k++) {
                    String op = arr[2 * k + 1]; // k 번째 숫자 다음의 값 (operator)
                    if (op.equals(PLUS)) {
                        // max = max + max
                        maxDp[i][j] = Math.max(
                            maxDp[i][j],
                            maxDp[i][k] + maxDp[k + 1][j]
                        );
                        // min = min + min
                        minDp[i][j] = Math.min(
                            minDp[i][j],
                            minDp[i][k] + minDp[k + 1][j]
                        );
                    } else if (op.equals(MINUS)) {
                        // max = max - min
                        maxDp[i][j] = Math.max(
                            maxDp[i][j],
                            maxDp[i][k] - minDp[k + 1][j]
                        );
                        // min = min - max
                        minDp[i][j] = Math.min(
                            minDp[i][j],
                            minDp[i][k] - maxDp[k + 1][j]
                        );
                    }
                }
            }
        }
        return maxDp[0][n - 1];
    }

    private void init(String[] arr) {
        n = (arr.length - 1) / 2 + 1;
        maxDp = new int[n][n];
        minDp = new int[n][n];
        for (int i = 0; i < n; i++) {
                Arrays.fill(maxDp[i], Integer.MIN_VALUE);        
                Arrays.fill(minDp[i], Integer.MAX_VALUE);
        }

        for (int i = 0; i < n; i++) {
            int j = 2 * i; // 숫자의 index 를 arr 에서의 index 로 변환
            maxDp[i][i] = minDp[i][i] = Integer.parseInt(arr[j]);
        }
    }
}