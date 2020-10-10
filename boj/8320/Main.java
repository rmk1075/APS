import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int ans = 0;
        for(int i = 1; i < N + 1; i++) {
            for(int j = i; i * j <= N; j++) {
                ans++;
            }
        }

        System.out.println(ans);
    }
}