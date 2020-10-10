import java.util.Scanner;

public class Main {
    static int[][] fibolist = new int[41][2];
    public static void main(String[] args) {
        fibo();

        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        while(0 < T) {
            int N = sc.nextInt();
            System.out.println(Integer.toString(fibolist[N][0])+" "+Integer.toString(fibolist[N][1]));

            T--;
        }
        sc.close();
    }

    public static void fibo() {
        fibolist[0][0] = 1;
        fibolist[1][1] = 1;
        
        for(int i = 2; i < 41; i++) {
            fibolist[i][0] = fibolist[i-1][0]+fibolist[i-2][0];
            fibolist[i][1] = fibolist[i-1][1]+fibolist[i-2][1];
        }
    }
}