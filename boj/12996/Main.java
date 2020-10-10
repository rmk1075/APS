import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int S, MOD = 1_000_000_007;
    static int[] arr = new int[3];
    static int[][][][] songs;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        S = Integer.parseInt(st.nextToken());
        
        arr[0] = Integer.parseInt(st.nextToken());
        arr[1] = Integer.parseInt(st.nextToken());
        arr[2] = Integer.parseInt(st.nextToken());
        
        songs = new int[S][arr[0]+1][arr[1]+1][arr[2]+1];
        for(int i = 0; i < S; i++) {
            for(int j = 0; j < arr[0]+1; j++) {
                for(int k = 0; k < arr[1]+1; k++) {
                    Arrays.fill(songs[i][j][k], -1);
                }
            }
        }

        System.out.println(cal(0, arr[0], arr[1], arr[2]));
    }

    public static int cal(int index, int a, int b, int c) {
        if(index == S) {
            if(a == 0 && b == 0 && c == 0) return 1;
            return 0;
        }

        if(songs[index][a][b][c] != -1) return songs[index][a][b][c];

        int result = 0;
        if(0 < a) result = (result + cal(index+1, a-1, b, c)) % MOD;
        if(0 < b) result = (result + cal(index+1, a, b-1, c)) % MOD;
        if(0 < c) result = (result + cal(index+1, a, b, c-1)) % MOD;

        if(0 < a && 0 < b) result = (result + cal(index+1, a-1, b-1, c)) % MOD;
        if(0 < a && 0 < c) result = (result + cal(index+1, a-1, b, c-1)) % MOD;
        if(0 < b && 0 < c) result = (result + cal(index+1, a, b-1, c-1)) % MOD;

        if(0 < a && 0 < b && 0 < c) result = (result + cal(index+1, a-1, b-1, c-1)) % MOD;

        return songs[index][a][b][c] = result;
    }
}