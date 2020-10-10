import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static char[] S, P;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine().toCharArray();
        P = br.readLine().toCharArray();

        int[] pi = new int[P.length];
        int j = 0;
        for(int i = 1; i < P.length; i++) {
            while(0 < j && P[i] != P[j]) j = pi[j-1];
            if(P[i] == P[j]) pi[i] = ++j;
        }

        j = 0;
        for(int i = 0; i < S.length; i++) {
            while(0 < j && S[i] != P[j]) j = pi[j-1];
            if(S[i] == P[j]) {
                if(j == P.length - 1) {
                    System.out.println(1);
                    return ;
                } else j++;
            }
        }
        System.out.println(0);
    }
}