import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static long[] arr1 = new long[21], arr2 = new long[21];
    static int N, ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int num = Integer.parseInt(st.nextToken());
        arr1[num] = arr2[num] = 1;
        for(int i = 1; i < N-1; i++) {
            num = Integer.parseInt(st.nextToken());

            for(int j = 0; j < 21; j++) {
                if(arr1[j] == 0) continue;

                if(j + num <= 20) {
                    arr2[j+num] += arr1[j];
                }
                
                if(0 <= j - num) {
                    arr2[j-num] += arr1[j];
                }

                arr2[j] -= arr1[j];
            }

            arr1 = Arrays.copyOf(arr2, arr2.length);
        }

        System.out.println(arr2[Integer.parseInt(st.nextToken())]);
    }
}