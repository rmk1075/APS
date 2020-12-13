import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static int N = 0, maxCnt = 0;
    static double sum = 0;
    static Map<Integer, Integer> map = new HashMap<>();;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());

            sum += num;
            map.put(num, map.containsKey(num) ? map.get(num) + 1 : 1);
            maxCnt = Math.max(maxCnt, map.get(num));
        }

        int idx = 0, len = map.size();
        int[][] arr = new int[len][2];
        for (int key : map.keySet()) {
            arr[idx][0] = key;
            arr[idx++][1] = map.get(key);
        }

        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        long num1, num2, num3, num4;
        num1 = (long) Math.round(sum / N);
        num2 = num3 = 0;
        num4 = arr[len - 1][0] - arr[0][0];

        int cnt = 0, half = N / 2;
        for (int[] a : arr) {
            cnt += a[1];
            if (half < cnt) {
                num2 = a[0];
                break;
            }
        }

        cnt = 0;
        for (int[] a : arr) {
            if (a[1] != maxCnt)
                continue;

            num3 = a[0];
            if (cnt == 1)
                break;
            cnt++;
        }

        System.out.println(num1 + "\n" + num2 + "\n" + num3 + "\n" + num4);
    }
}