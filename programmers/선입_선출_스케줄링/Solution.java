import java.util.Arrays;

class Solution {
    public int solution(int n, int[] cores) {
        int answer = 0;
        int num = cores.length;
        n -= num; // 0초에는 모든 core 에 작업이 할당된다.

        // 작업이 모두 완료되는 시간을 binary search 를 통해서 탐색한다.
        int left = 1;
        int right = n * Arrays.stream(cores).max().getAsInt() / num;
        while(left <= right) {
            int mid = (left + right) / 2;
            int allocated = allocate(mid, cores);
            if(n <= allocated) right = mid - 1;
            else left = mid + 1;
        }

        int rest = allocate(left, cores) - n; // 마지막 시간에 할당된 작업의 개수를 구한다.
        for(int i = num - 1; -1 < i; i--) {
            if(left % cores[i] == 0) {
                if(rest == 0) {
                    answer = i + 1;
                    break;
                }
                rest--;
            }
        }
        return answer;
    }

    public int allocate(int time, int[] cores) {
        int allocated = 0;
        for(int core : cores) allocated += time / core;
        return allocated;
    }
}