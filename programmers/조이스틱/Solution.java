class Solution {
    public int solution(String name) {
        int answer = 0;

        int cnt = 0;
        char[] c = name.toCharArray();
        for(char ch : c) {
            if(ch != 'A') cnt++;
        }
        
        int N = c.length;
        int index = 0;
        while(0 < cnt) {
            int[] targetInfo = findTarget(c, N, index); // new int[2] : {target, distance}
            index = targetInfo[0];
            answer += targetInfo[1];
            answer += Math.min(c[index] - 'A', 'Z' - c[index] + 1);
            c[index] = 'A';
            cnt--;
        }

        return answer;
    }

    // testcase에 문제가 있는 것 같은데, right의 경우 boundary를 넘어가도 좌측으로 이동하지 않아서 생기는 예외인 것 같다.
    public int[] findTarget(char[] c, int N, int index) {
        int target = -1;
        int left = index;
        int right = index;
        int distance = 0;
        while(true) {
            if(c[right] != 'A') {
                target = right;
                break;
            } else right = getIndex(right + 1, N);

            if(c[left] != 'A') {
                target = left;
                break;
            } else left = getIndex(left - 1, N);

            distance++;
        }

        return new int[]{target, distance};
    }

    public int getIndex(int index, int N) {
        if(index == -1) return N - 1;
        if(index == N) return N - 1;
        return index;
    }
}