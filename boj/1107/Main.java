import java.util.Scanner;

public class Main {
    static int N, M, minCount, channel = 100;
    static int[] buttons = new int[10];
    static int[] visited;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        visited = new int[N+11];
        for(int i = 0; i < M; i++) {
            buttons[sc.nextInt()] = 1;
        }
        sc.close();

        // init
        minCount = Math.abs(channel - N);

        // make the every combination by the digit buttons ans compare
        // 0 <= N <= 500000
        for(int i = 1; i < 7; i++) {
            if(i < minCount) compareCombi(0, i, 0);
        }
        

        System.out.println(minCount);
    }

    public static void compareCombi(int count, int buttonCnt, int num) {

        if(count == buttonCnt) {

            minCount = Math.min(minCount, buttonCnt + Math.abs(num - N));
            return ;
        }

        for(int i = 0; i < 10; i++) {
            if(buttons[i] == 1) continue;
            
            compareCombi(count+1, buttonCnt, num*10 + i);
        }

    }
}