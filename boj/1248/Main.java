import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static int[] candidates, nums = new int[21];
    static int[][] numMatrix;
    static char[] stream;
    static char[][] matrix;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i = -10; i < 11; i++) {
            nums[i+10] = i;
        }

        N = Integer.parseInt(br.readLine());
        candidates = new int[N];
        numMatrix = new int[N][N];

        stream = br.readLine().toCharArray();
        matrix = new char[N][N];
        int idx = 0;
        for(int i = 0; i < N; i++) {
            for(int j = i; j < N; j++) {
                matrix[i][j] = stream[idx++];
            }
        }

        // pick N nums from -10 ~ 10 & permutation
        pick(0);
    }

    public static void pick(int count) {
        // permutation & check
        if(count == N) {

            for(int num : candidates) {
                System.out.print(num + " ");
            }
            System.exit(0);

            return ;
        }

        for(int i = 0; i < nums.length; i++) {
            if(check(nums[i]) != matrix[count][count]) continue;
            if(!put(count, nums[i])) continue;

            candidates[count] = nums[i];
            pick(count+1);
        }
    }

    public static boolean put(int index, int val) {
        if(index == 0) {
            numMatrix[0][0] = val;
            return true;
        }

        for(int i = 0; i <= index; i++) {
            numMatrix[i][index] = numMatrix[i][index-1] + val;
            if(check(numMatrix[i][index]) != matrix[i][index]) return false;
        }

        return true;
    }

    public static char check(int val) {
        if(val < 0) return '-';
        else if(val == 0) return '0';
        else return '+';
    }

    public static boolean check() {

        for(int i = 0; i < N; i++) {
            for(int j = i; j < N; j++) {
               if(cal(i, j) != matrix[i][j]) {
                   return false;
               } 
            }
        }

        return true;
    }

    public static char cal(int x, int y) {
        int sum = 0;

        for(int i = x; i <= y; i++) {
            sum += candidates[i];
        }

        if(sum < 0) return '-';
        else if(sum == 0) return '0';
        else return '+';
    }
}