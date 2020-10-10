import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int S = 0, M = Integer.parseInt(br.readLine());
        while(0 < M--) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            switch(st.nextToken()) {
                case "add":
                    S |= (1 << Integer.parseInt(st.nextToken()));
                    break;
                case "remove":
                    S &= ~(1 << Integer.parseInt(st.nextToken()));
                    break;
                case "check":
                    sb.append(((S & (1 << Integer.parseInt(st.nextToken()))) == 0) ? "0\n" : "1\n");
                    break;
                case "toggle":
                    S ^= (1 << Integer.parseInt(st.nextToken()));
                    break;
                case "all":
                    S = (1 << 21) - 1;
                    break;
                case "empty":
                    S = 0;
            }
        }

        System.out.println(sb);
    }
}