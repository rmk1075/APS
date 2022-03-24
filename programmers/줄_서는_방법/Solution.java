/**
 * overview
 * 
 * n 명의 사람들을 사전 순으로 나열할 때, k 번째 순서를 반환한다.
 * 
 * description
 * 
 * n 은 20 이하의 자연수로 n 으로 줄 세울 수 있는 방법은 최대 20! 이다.
 * 이를 모두 계산하면 시간복잡도가 매우 커지기 때문에 모든 방법을 탐색할 수 없다.
 * 대신 특정 숫자가 자리에 위치했을 때 그보다 작은 숫자들, 사전 순으로 앞에 올 수 있는 방법들을 계산하여 탐색을 수행할 수 있다.
 * 
 * n 자리의 배열에 대해서 첫번째 자리부터 자리에 올 수 있는 숫자와 해당 숫자를 위치했을 때 만들 수 있는 방법의 순서 범위를 구한다.
 * 첫번째 자리에 1을 두었을 때 만들 수 있는 방법은 사전순으로 1 ~ (n - 1)! 만큼이다.
 * 반면 2가 왔을 때는 (n - 1)! ~ 2 * (n - 1)! 까지의 방법을 만들 수 있다.
 * 이러한 방법을 통해서 특정 숫자 m 이 해당 자리에 왔을 때 만들 수 있는 순서의 범위가 k 이상인 경우 해당 위치에 m 을 넣고 그 다음 위치에 들어갈 수 있는 숫자를 구한다.
 * 이러한 방식으로 n 개의 숫자를 모두 배치하여 결과를 반환한다.
 */
class Solution {
    public int[] solution(int n, long k) {
        if(n == 1) return new int[]{1};

        int visited = 0;
        int[] answer = new int[n];
        long count = 0L;
        long unit = 1L;
        for(int i = 1; i < n; i++) unit *= i;
        for(int i = 0; i < n - 1; i++) {
            long temp = 0;
            for(int j = 1; j <= n; j++) {
                if((visited & (1 << j)) != 0) continue;
                temp += unit;
                if(k <= count + temp) {
                    count += temp - unit;
                    answer[i] = j;
                    visited |= 1 << j;
                    break;
                }
            }
            unit /= (n - i - 1);
        }
        for(int i = 1; i <= n; i++) {
            if((visited & (1 << i)) == 0) {
                answer[n - 1] = i;
                break;
            }
        }
        return answer;
    }
}