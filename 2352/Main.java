import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()), ports[] = new int[n+1], count[] = new int[n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++) ports[i] = Integer.parseInt(st.nextToken());
        
        int len = 1;
        count[1] = ports[1];
        for(int i = 2; i <= n; i++) {
            if(count[len] < ports[i]) count[++len] = ports[i];
            else {
                int j = 1;
                for(; j <= len; j++) if(ports[i] < count[j]) break;
                len = (len < j) ? j : len;
                count[j] = ports[i];
            }
        }

        System.out.println(len);
    }
}