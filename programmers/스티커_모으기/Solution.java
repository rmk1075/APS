/**
 * overview
 * 
 * 원형으로 이루어진 스티커 모음에서 규칙에 따라 스티커를 뜯어 최대 점수를 획득해야 한다.
 * 스티커를 뜯으면 양옆의 스티커는 더이상 사용할 수 없는 규칙이 있기 때문에 이를 유의해서 로직을 구성해야 한다.
 * 또한 원형이기 때문에 첫번째 스티커와 마지막 스티커가 서로 연관이 있음을 인지해야한다.
 * 
 * description
 * 
 * i 번째 스티커를 뜯을 때 i - 1 과 i + 1 번째 스티커는 뜯을 수 없게 된다.
 * 그렇기 때문에 점수를 계산할 때 i 번째에서는 (i - 1 번째 까지 얻은 최대 점수)와 (i - 2 번째 까지 얻은 최대 점수 + i 번째 스티커 점수) 를 비교해야한다.
 * 이를 dynamic programming 으로 계산하는데, 이 때 0번째와 N - 1 번째가 연결되어 있기 때문에 0번째 스티커를 포함한 경우와 포함하지 않은 경우 2가지로 나누어야 한다.
 * 두경우를 각각 계산하여 0 ~ N - 1 / 1 ~ N 으로 계산한 결과를 비교하여 더 큰 값을 가지는 쪽을 결과로 반환한다.
 */
class Solution {
    public int solution(int sticker[]) {
        int N = sticker.length;
        if(N == 1) return sticker[0];

        int[] dp0 = new int[N];
        dp0[0] = sticker[0];
        dp0[1] = sticker[0];
        for(int i = 2; i < N - 1; i++) dp0[i] = Math.max(dp0[i - 1], dp0[i - 2] + sticker[i]);

        int[] dp1 = new int[N];
        dp1[1] = sticker[1];
        for(int i = 2; i < N; i++) dp1[i] = Math.max(dp1[i - 1], dp1[i - 2] + sticker[i]);

        int answer = Math.max(dp0[N - 2], dp1[N - 1]);
        return answer;
    }
}