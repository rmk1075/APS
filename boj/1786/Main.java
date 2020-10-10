import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    static char[] T, P;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = br.readLine().toCharArray();
        P = br.readLine().toCharArray();

        int[] pi = new int[P.length];
        int j = 0, ans = 0;
        for(int i = 1; i < P.length; i++) {
            while(0 < j && P[i] != P[j]) j = pi[j-1];
            if(P[i] == P[j]) pi[i] = ++j;
        }

        j = 0;
        for(int i = 0; i < T.length; i++) {
            while(0 < j && T[i] != P[j]) j = pi[j-1];
            if(T[i] == P[j]) {
                if(j == P.length - 1) {
                    sb.append(i - P.length + 2 + " ");
                    ans++;
                    j = pi[j];
                }
                else j++;
            }
        }
        System.out.println(ans);
        System.out.println(sb);
    }
}