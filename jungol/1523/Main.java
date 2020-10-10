import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main {
    static int n, m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        if(n <= 0 || 100 < n) System.out.println("INPUT ERROR!");
        else if(m < 1 || 3 < m) System.out.println("INPUT ERROR!");
        else {
            switch(m) {
                case 1:
                    for(int i = 1; i <= n; i++) {
                        for(int j = 0; j < i; j++) {
                            System.out.print("*");
                        }
                        System.out.println();
                    }
                    break;
                case 2:
                    for(int i = 0; i < n; i++) {
                        for(int j = 0; j < n - i; j++) {
                            System.out.print("*");
                        }
                        System.out.println();
                    }
                    break;
                case 3:
                    for(int i = 0; i < n; i++) {
                        for(int j = 1; j < n - i; j++) {
                            System.out.print(" ");
                        }

                        for(int j = 0; j < 2 * i + 1; j++) {
                            System.out.print("*");
                        }

                        for(int j = 1; j < n - i; j++) {
                            System.out.print(" ");
                        }

                        System.out.println();
                    }
                    break;
            }
        }
    }
}