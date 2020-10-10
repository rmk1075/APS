import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    static int N = 10;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int sum = 0;
        for(int i = 0; i < N; i++) {
            int temp = Integer.parseInt(br.readLine());

            if(sum + temp < 100) sum += temp;
            else if(sum + temp == 100) {
                System.out.println(100);
                System.exit(0);
            } else {
                if(100 - sum < sum + temp - 100) {
                    break ;
                } else {
                    sum += temp;
                    break;
                }
            }
        }

        System.out.println(sum);
    }
}