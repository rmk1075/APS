import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()), arr[] = new int[1000];

        if(n == 1) {
            System.out.println(1);
            return ;
        } else if(n == 2) {
            System.out.println(3);
            return ;
        }

        arr[1] = 1;
        arr[2] = 3;
        for(int i = 3; i < n; i++) arr[i] = (arr[i-1] + 2*arr[i-2]) % 10007;
        System.out.println((arr[n-1] + 2*arr[n-2]) % 10007);
    }
}