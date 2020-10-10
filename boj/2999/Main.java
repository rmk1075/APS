import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    static int N;
    static String[] message;
    static String[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        message = br.readLine().split("");
        N = message.length;

        int R = 0, C = 0;
        for(int i = 1; i < N; i++) {
            if(N % i == 0) {
                if(N / i < i) break;

                R = i;
                C = N / i;
            }
        }

        int idx = 0;
        arr = new String [R][C];
        for(int i = 0; i < C; i++) {
            for(int j = 0; j < R; j++) {
                arr[j][i] = message[idx++];
            }
        }
        
        String s = "";
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                s += arr[i][j];
            }
        }

        System.out.println(s);
    }
}