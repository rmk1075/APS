class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int N = queue1.length;
        int[] arr = new int[2 * N];
        long sum1 = 0L;
        for (int i = 0; i < N; i++) {
            arr[i] = queue1[i];
            sum1 += queue1[i];
        }

        long sum2 = 0L;
        for (int i = N; i < 2 * N; i++) {
            arr[i] = queue2[i - N];
            sum2 += queue2[i - N];
        }

        long total = sum1 + sum2;
        if (total % 2 != 0) {
            return -1;
        }

        int count = 0;
        int head = 0;
        int tail = N - 1;
        long mid = total / 2;
        while (head < tail) {
            if (sum1 == mid) {
                break;
            }

            if (sum1 < mid) {
                if (tail == 2 * N - 1) {
                    break;
                }

                sum1 += arr[++tail];
            } else {
                sum1 -= arr[head++];
            }
            count++;
        }
        return sum1 == mid ? count : -1;
    }
}