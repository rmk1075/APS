import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N, nums[] = {2, 3, 5, 7};
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for(int n : nums) find(n, 1);
        System.out.println(sb);
    }

    public static void find(int val, int count) {
        if(count == N) {
            sb.append(val+ "\n");
            return ;
        }

        for(int n = 1; n < 10; n++) {
            int temp = val * 10 + n;
            if(check(temp)) find(temp, count + 1);
        }
    }

    public static boolean check(int val) {
        for(int i = 2; i < val / 2 + 1; i++) {
            if(val % i == 0) return false;
        }
        return true;
    }
}