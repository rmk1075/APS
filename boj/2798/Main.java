import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();
        int[][] cards = new int[N][2];
        
        for(int i = 0; i < N; i++) {
            cards[i][0] = sc.nextInt();
            cards[i][1] = 0;
        }
        sc.close();

        System.out.println(find(N, M, cards, 0, 0));
    }

    public static int find(int N, int M, int[][] cards, int sum, int count) {
        if(count == 3) return sum;

        int val = 0;
        for(int i = 0; i < N; i++) {
            if(cards[i][1] == 0 && cards[i][0] + sum <= M) {
                cards[i][1] = 1;
                int temp = find(N, M, cards, sum+cards[i][0], count+1);
                cards[i][1] = 0;

                if(val < temp) val = temp;
            }
        }

        return val;
    }
}