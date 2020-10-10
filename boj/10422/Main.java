import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
    static int T, L, MOD = 1000000007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        while(0 < T--) {
            L = Integer.parseInt(br.readLine());
            
            if(L%2 == 1) {
                System.out.println(0);
                continue;
            }

            System.out.println(catalan(L, L/2).divide(BigInteger.valueOf(L/2 + 1)).mod(BigInteger.valueOf(MOD)));
        }
    }

    public static BigInteger catalan(int n, int k) {
        BigInteger res = BigInteger.ONE;

        if(n-k < k) k = n-k;

        for(int i = 0; i < k; i++) {
            res = res.multiply(BigInteger.valueOf(n-i));
            res = res.divide(BigInteger.valueOf(i+1));
        }

        return res;
    }
}