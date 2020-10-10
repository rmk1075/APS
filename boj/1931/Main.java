import java.util.Scanner;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[][] meetings = new int[N][2];
        for(int i = 0; i < N; i++) {
            meetings[i][0] = sc.nextInt();
            meetings[i][1] = sc.nextInt();
        }
        sc.close();

        Arrays.sort(meetings, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return Integer.compare(a[0], b[0]);
            }
        });

        int count = 1;
        int end = meetings[0][1];
        for(int i = 1; i < N; i++) {
            if(end <= meetings[i][0]) {
                count++;
                end = meetings[i][1];
            } else if(meetings[i][1] <= end) {
                end = meetings[i][1];
            }
        }

        System.out.println(count);
    }
}