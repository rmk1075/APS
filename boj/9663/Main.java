import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        sc.close();

        int answer = 0;
        int[] list = new int[N];
        for(int i = 0; i < N; i++) {
            list[0] = i;
            answer += tracking(list, N, 1);
        }

        System.out.println(answer);
    }

    private static int tracking(int[] list, int N, int count) {
        if(count == N) return 1;

        int answer = 0;
        for(int i = 0; i < N; i++) {
            if(isPossible(list, i, count)) {
                list[count] = i;
                answer += tracking(list, N, count+1);
            }
        }

        return answer;
    }

    private static boolean isPossible(int[] list, int num, int count) {
        for(int i = 0; i < count; i++) {
            if(num == list[i] || (count-i) == Math.abs(list[i]-num)) {
                return false;
            }
        }

        return true;
    }
}