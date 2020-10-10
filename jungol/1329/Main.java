import java.util.Scanner;

public class Main {
    static int n;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        sc.close();

        if(n % 2 == 0 || n < 0 || 100 < n) {
            System.out.println("INPUT ERROR!");
            System.exit(0);
        }

        int count = 0;
        for(int i = 1; i <= n; i++) {
            for(int j = 0; j < count; j++) {
                System.out.print(" ");
            }

            for(int j = 0; j < 2 * count + 1; j++) {
                System.out.print("*");
            }
            System.out.println();

            if(i < n / 2 + 1) count++;
            else count--;
        }
    }
}