import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        int[] arr = new int[N+1];
        arr[1] = 1;
        for(int i = 2; i <= N; i++) {
            arr[i] = i;
            for(int j = (int)Math.floor(Math.sqrt(i)); 0 < j; j--) arr[i] = Math.min(arr[i], 1 + arr[i - (j * j)]);
        }

        System.out.println(arr[N]);
    }
}