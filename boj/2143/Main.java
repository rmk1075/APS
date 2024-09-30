import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int T;
    private static int N;
    private static int M;
    private static int[] A;
    private static int[] B;

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(find());
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        B = new int[M];
        for (int i = 0; i < M; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static long find() {
        List<Integer> listA = makeSubList(N, A);
        List<Integer> listB = makeSubList(M, B);

        long count = 0;
        int left = 0;
        int right = listB.size() - 1;
        while (left < listA.size() && -1 < right) {
            long valA = listA.get(left);
            long valB = listB.get(right);
            long sum = valA + valB;
            if (sum == T) {
                long countA = 0;
                while (left < listA.size() && listA.get(left) == valA) {
                    countA++;
                    left++;
                }

                long countB = 0;
                while (-1 < right && listB.get(right) == valB) {
                    countB++;
                    right--;
                }

                count += countA * countB;
            } else if (sum < T) {
                left++;
            } else {
                right--;
            }
        }
        return count;
    }

    private static List<Integer> makeSubList(int len, int[] arr) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            int temp = 0;
            for (int j = i; j < len; j++) {
                temp += arr[j];
                list.add(temp);
            }
        }
        list.sort(null);
        return list;
    }
}
