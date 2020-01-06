import java.util.Scanner;

public class Main {
    static long[] numbers;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.close();

        if(n == 0 || n == 1) {
            System.out.println(n);
        } else {            
            numbers = new long[n];
            numbers[0] = 0;
            numbers[1] = 1;

            fibonacci(n);
            System.out.println(numbers[n-1]+numbers[n-2]);
        }
    }
    public static void fibonacci(int n) {
        for(int i = 2; i < n; i++) {
            numbers[i] = numbers[i-1]+numbers[i-2];
        }
    }
}