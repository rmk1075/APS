import java.util.Scanner;

public class Main {
    static int N, K;
    static char[] S;
    static boolean[][][][] visited;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        sc.close();

        S = new char[N];
        visited = new boolean[N+1][N+1][N+1][N*(N-1)/2 + 1];
        dfs(0, 0, 0, 0);      

        System.out.println(-1);
    }

    public static void dfs(int index, int acnt, int bcnt, int sum) {
        if(index == N) {
            if(sum != K) return ;

            for(char ch : S) System.out.print(ch);
            System.exit(0);
        }

        if(K < sum) return ;

        if(visited[index][acnt][bcnt][sum]) return ;
        visited[index][acnt][bcnt][sum] = true;

        S[index] = 'A';
        dfs(index+1, acnt+1, bcnt, sum);

        S[index] = 'B';
        dfs(index+1, acnt, bcnt+1, sum + acnt);

        S[index] = 'C';
        dfs(index+1, acnt, bcnt, sum + acnt + bcnt);
    }
}