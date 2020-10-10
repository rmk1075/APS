class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            int val = arr1[i] | arr2[i];
            for(int j = 0; j < n; j++) {
                sb.append(((1 << j) & (val)) == 0 ? " " : "#");
            }
            answer[i] = sb.reverse().toString();
            sb.setLength(0);
        }

        return answer;
    }
}