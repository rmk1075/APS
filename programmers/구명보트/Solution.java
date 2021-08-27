import java.util.Arrays;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;

        Arrays.sort(people);
        int N = people.length;
        int right = N - 1;
        int left = 0;
        while(left <= right) {
            int p = people[right--];
            if(left <= right && p + people[left] <= limit) left++;
            answer++;
        }

        return answer;
    }
}