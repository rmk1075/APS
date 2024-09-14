class Solution {
    public int[] solution(int target) {
        return solve(target); // {던질 다트 수, "싱글" 또는 "불"을 맞춘 횟수(합)}
    }

    private int[] solve(int target) {
        int[][] arr = initArr(target);
        for (int val = target; 0 < val; val--) {
            int count = arr[val][0];
            int bonus = arr[val][1];
            if (count == -1) {
                continue;
            }
            count++;

            // single ~ tripple
            for (int mul = 1; mul < 4; mul++) {
                for (int i = 1; i <= 20; i++) {
                    int num = i * mul;
                    if (val < num) {
                        break;
                    }
                    cal(arr, val - num, count, mul == 1 ? bonus + 1 : bonus);
                }
            }

            // bull
            if (50 <= val) {
                cal(arr, val - 50, count, bonus + 1);
            }
        }
        return arr[0];
    }

    private void cal(int[][] arr, int next, int count, int bonus) {
        int nextCount = arr[next][0];
        if (count == nextCount) {
            if (arr[next][1] < bonus) {
                arr[next][0] = count;
                arr[next][1] = bonus;
            }
        } else if (count < nextCount) {
            arr[next][0] = count;
            arr[next][1] = bonus;
        }
    }

    private int[][] initArr(int target) {
        int[][] arr = new int[target + 1][2]; // { count, bonus }
        for (int i = 0; i < target; i++) {
            arr[i] = new int[] { Integer.MAX_VALUE, -1 };
        }
        arr[target] = new int[] {0, 0};
        return arr;
    }
}
