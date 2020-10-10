import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static long[] arr = new long[31];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < arr.length; i++) arr[i] = -1;

        while(true) {
            N = Integer.parseInt(br.readLine());
            if(N == 0) break;

            sb.append(cal(N) + "\n");
        }

        System.out.println(sb.toString());
    }

    public static long cal(int num) {
        if(num == 0 || num == 1) return 1;
        if(arr[num] != -1) return arr[num];
        
        long result = 0;
        for(int i = 0; i < num; i++) result += cal(i) * cal(num - 1 - i);

        return arr[num] = result;
    }
}