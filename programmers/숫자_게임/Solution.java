import java.util.Arrays;

/**
 * overview
 * 
 * A팀과 B팀의 비교를 수행한다.
 * 이때 비교한 결과에 대해서 B의 값이 더 큰 경우를 최대로 하도록 B의 순서를 정한다.
 * 
 * description
 * 
 * 두 배열의 값을 비교하기에 앞서 정렬시킨다.
 * 정렬을 시킨 배열을 가지고 for 문을 통해 비교를 수행한다.
 * A 행렬의 인덱스를 a, B 행렬의 인덱스를 b 라고 했을 떄, A[a] < B[b] 인 경우에는 B팀이 승점을 가져간 경우이기 때문에 answer 와 a 에 1씩 더해준다.
 * 만약 B[b] 가 A[a] 와 같거다 더 작은 경우에는 A[a] 보다 더 큰 값을 찾기 위해서 b의 값만 1을 더해준다.
 */
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