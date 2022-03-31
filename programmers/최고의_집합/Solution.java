/**
 * overview
 * 
 * 자연수 n 개로 이루어진 중복집합 중에서 다음의 두 조건을 만족하는 집합을 반환한다.
 * 
 * - 각 원소의 합이 S가 되는 수의 집합
 * - 각 원소의 곱이 최대가 되는 집합
 * 
 * description
 * 
 * 각 원소의 곱이 최대가 되기 위해서는 원소들의 표준편차가 최소가 되어야 한다.
 * 이를 맞추기 위해서 s / n 의 값을 모두 배열에 채워준다.
 * 그다음 첫번째 조건을 맞추기 위해서 각 원소의 합이 S가 되도록 해야한다.
 * 합이 S가 되도록 하기 위해서는 s % n 의 값만큼 배열의 원소들에 더해줘야 한다.
 * 이때 앞서 말한 것처럼 표준편차를 최소로 하기 위해 한 원소에 다 더하는 것이 아니라 가능한 모든 원소에 골고루 1씩 더해준다.
 * 이렇게 구성한 배열을 최고의 집합으로 반환한다.
 */
class Solution {
    public int[] solution(int n, int s) {
        int[] answer = new int[n];
        int num = s / n;
        if(num == 0) return new int[]{-1};
        for(int i = 0; i < n; i++) answer[i] = num;
        for(int i = 0; i < s % n; i++) answer[n - i - 1]++;
        return answer;
    }
}