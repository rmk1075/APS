import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    static ArrayList<Long> padovan = new ArrayList<Long>(Arrays.asList(1L, 1L, 1L, 2L, 2L, 3L, 4L, 5L, 7L, 9L));
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while(0 < T) {
            int N = sc.nextInt();

            if(N <= padovan.size()) {
                System.out.println(padovan.get(N-1));
            } else {
                for (int i = padovan.size(); i < N; i++) {
                    padovan.add(padovan.get(i-1)+padovan.get(i-5));
                }
                System.out.println(padovan.get(N-1));
            }

            T--;
        }
        sc.close();
    }
}