import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int N, M, P, MOD = 1_000_000_007;
    static int[] songs;
    static int[][] playList;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        P = sc.nextInt();
        sc.close();

        songs = new int[N];
        Arrays.fill(songs, -M-1);

        playList = new int[P][N+1];
        for(int i = 0; i < P; i++) Arrays.fill(playList[i], -1);

        System.out.println(cal(0, 0));
    }

    public static int cal(int index, int count) {
        if(index == P) {
            if(count < N) return 0;
            return 1;
        }

        if(playList[index][count] != -1) return playList[index][count];

        int result = 0;
        for(int i = 0; i < N; i++) {
            if(songs[i] + M < index) {
                int temp = songs[i];
                songs[i] = index;

                if(temp < 0) result = (result + cal(index+1, count+1)) % MOD;
                else result = (result + cal(index+1, count)) % MOD;

                songs[i] = temp;
            }
        }

        return playList[index][count] = result;
    }
}