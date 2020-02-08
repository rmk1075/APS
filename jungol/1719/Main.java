import java.util.Scanner;

public class Main {
    static int n, m;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        if(n % 2 == 0 || n < 1 || 100 < n || m < 1 || 4 < m) {
            System.out.println("INPUT ERROR!");
        } else {
            switch(m) {
                case 1:
                    int count = 0;
                    for(int i = 0; i < n; i++) {
                        if(i < n / 2 + 1) count++;
                        else count--;
                        for(int j = 0; j < count; j++) {
                            System.out.print("*");
                        }
                        System.out.println();
                    }

                    break;

                case 2:
                    count = 0;
                    for(int i = 0; i < n; i++) {
                        if(i < n / 2 + 1) count++;
                        else count--;
                        for(int j = 0; j < n / 2 + 1 - count; j++) {
                            System.out.print(" ");
                        }
                        for(int j = 0; j < count; j++) {
                            System.out.print("*");
                        }
                        System.out.println();
                    }

                    break;

                case 3:
                    count = 0;
                    for(int i = 0; i < n; i++) {
                        for(int j = 0; j < count; j++) {
                            System.out.print(" ");
                        }
                        for(int j = 0; j < n - 2 * count; j++) {
                            System.out.print("*");
                        }
                        System.out.println();

                        if(i < n / 2) count++;
                        else count--;
                    }
                    
                    break;

                case 4:
                    for(int i = 1; i <= n; i++) {
                        if(i <= n / 2 + 1) {
                            for(int j = 1; j <= n / 2 + 1; j++) {
                                if(j < i) System.out.print(" ");
                                else System.out.print("*");
                            }
                        } else {
                            for(int j = 1; j <= i; j++) {
                                if(j < n / 2 + 1) System.out.print(" ");
                                else System.out.print("*");
                            }
                        }
                        System.out.println();
                    }

                    break;
            }
        }
    }
}