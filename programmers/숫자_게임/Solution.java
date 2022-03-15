import java.util.Arrays;

class Solution {
    public int solution(int[] A, int[] B) {
        int N = B.length;
        Arrays.sort(A);
        Arrays.sort(B);

        int answer = 0;
        int a = 0;
        int b = 0;
        while(b < N) {
            if(A[a] < B[b]) {
                a++;
                answer++;
            }
            b++;
        }
        return answer;
    }
}