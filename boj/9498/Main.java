import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int score = sc.nextInt();
        if(89 < score) {
            System.out.println('A');
        } else if(79 < score) {
            System.out.println('B');
        } else if(69 < score) {
            System.out.println('C');
        } else if(59 < score) {
            System.out.println('D');
        } else {
            System.out.println('F');
        }

        sc.close();
    }
}