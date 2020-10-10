import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int T;
    static char[] N;
    static int[] list = {1, 10, 25};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        while(0 < T--) {
            N = br.readLine().toCharArray();

            int ans = 0;
            for(int i = N.length-1; 0 < i; i -= 2) {
                ans += find((N[i-1] - '0') * 10 + (N[i] - '0'));
            }
            if(N.length % 2 != 0) ans += N[0]-'0';

            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }

    public static int find(int num) {
        if(num < 10) return num;

        int val = Integer.MAX_VALUE;
        for(int i = 2; 0 < i; i--) {
            int temp = Integer.MAX_VALUE;
            for(int j = 1; j <= num / list[i]; j++) temp = Math.min(temp, j + find(num - (list[i] * j)));
            val = Math.min(val, temp);
        }
        val = Math.min(val, num);
        return val;
    }
}