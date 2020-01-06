import java.util.Scanner;

public class Main {
    static int[] stairs, points;    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        
        stairs = new int[N+1];
        points = new int[N+1];
        for(int i = 1; i < N+1; i++) {
            stairs[i] = sc.nextInt();
        }
        sc.close();

        if(N < 3) {
            int sum = 0;
            for(int i = 1; i < N+1; i++) {
                sum += stairs[i];
            }
            System.out.println(sum);
        } else {
            points[1] = stairs[1];
            points[2] = points[1] + stairs[2];
            for(int i = 3; i < N+1; i++) {
                points[i] = stairs[i] + Math.max(stairs[i-1]+points[i-3], points[i-2]);
            }
            System.out.println(points[N]);
        }
    }
}