import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.close();
        
        long temp = 0;
        long temp1 = 1;
        long temp2 = 2;
        for(int i = 0; i < N-1; i++) {
            temp = temp1;
            temp1 = temp2;
            temp2 = (temp+temp2)%15746;
        }

        System.out.println(temp1);
    }
}