import java.util.Scanner;

public class Main {
    static int N;
    static int[][] S;
    static int diff = Integer.MAX_VALUE;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        S = new int[N][N];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                S[i][j] = sc.nextInt();
            }
        }
        sc.close();

        int[] members = new int[N];
        team(members, 0, 0);

        System.out.println(diff);
    }

    public static void team(int[] members, int start, int count) {
        if(count == N/2) {
            int val1 = 0;
            int val2 = 0;
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(i != j && i < j) {
                        if(members[i] == 0 && members[j] == 0) {
                            val1 += S[i][j]+S[j][i];
                        } else if(members[i] == 1 && members[j] == 1) {
                            val2 += S[i][j]+S[j][i];
                        }
                    }
                }
            }
            if(Math.abs(val1-val2) < diff) diff = Math.abs(val1-val2);
        } else {
            for(int i = start+1; i < N; i++) {
                if(members[i] != 1) {
                    members[i] = 1;
                    team(members, i, count + 1);
                    members[i] = 0;
                }
            }
        }
    }
}
