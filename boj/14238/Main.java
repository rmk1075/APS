import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static char[] S;
    static int[] arr = new int[3];
    static boolean[][][][][] dp = new boolean[51][51][51][3][3];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        S = br.readLine().toCharArray();
        for(char ch : S) {
            arr[ch - 'A']++;
        }

        dfs(0, 2, 2);
        System.out.println(-1);
    }

    public static void dfs(int count, int bLast, int cLast) {
        if(count == S.length) {
            for(char ch : S) System.out.print(ch);
            System.exit(0);
        }

        if(dp[arr[0]][arr[1]][arr[2]][bLast][cLast]) return ;
        dp[arr[0]][arr[1]][arr[2]][bLast][cLast] = true;

        if(arr[2] != 0 && cLast == 2) {
            arr[2]--;
            S[count] = 'C';
            dfs(count+1, (bLast + 1 == 3) ? 2 : bLast+1, 0);
            arr[2]++;
        }
        
        if(arr[1] != 0 && 1 <= bLast) {
            arr[1]--;
            S[count] = 'B';
            dfs(count+1, 0, (cLast + 1 == 3) ? 2 : cLast+1);
            arr[1]++;
        }
        
        if(arr[0] != 0) {
            arr[0]--;
            S[count] = 'A';
            dfs(count+1, (bLast+1 == 3) ? 2 : bLast+1, (cLast+1 == 3) ? 2 : cLast+1);
            arr[0]++;
        }
    }
}