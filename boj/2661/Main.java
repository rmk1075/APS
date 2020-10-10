import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N, arr[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        find(-1, 0);
    }

    public static void find(int last, int idx) {
        if(idx == N) {
            StringBuilder sb = new StringBuilder();
            for(int i : arr) sb.append(i);
            System.out.println(sb);
            System.exit(0);
        }

        if(last != 1) {
            arr[idx] = 1;
            if(check(idx)) find(1, idx+1);
        }

        if(last != 2) {
            arr[idx] = 2;
            if(check(idx)) find(2, idx+1);
        }

        if(last != 3) {
            arr[idx] = 3;
            if(check(idx)) find(3, idx+1);
        }
    }

    public static boolean check(int idx) {
        boolean b = true;
        for(int i = idx-1; idx/2 <= i; i--) {
            b = true;
            for(int j = 0; j < idx - i; j++) {
                if(arr[i-j] != arr[idx-j]) {
                    b = false;
                    break;
                }
            }
            if(b) return false;
        }
        return true;
    }
}