import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, K, sensors[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        sensors = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) sensors[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(sensors);
        
        int[] distances = new int[N - 1];
        for(int i = 0; i < N - 1; i++) distances[i] = sensors[i + 1] - sensors[i];
        Arrays.sort(distances);

        int ans = 0;
        for(int i = 0; i < N - K; i++) ans += distances[i];

        System.out.println(ans);
    }
}