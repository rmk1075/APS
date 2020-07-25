class Solution {
    public String solution(int[] numbers, String hand) {
        StringBuilder answer = new StringBuilder();
        int[][] loc = {{3, 1}, {0, 0}, {0, 1}, {0, 2}, {1, 0}, {1, 1}, {1, 2}, {2, 0}, {2, 1}, {2, 2}};

        int N = numbers.length;
        boolean isLeft = hand.equals("left");
        int[] left = {3, 0}, right = {3, 2};
        for(int i = 0; i < N; i++) {
            switch (numbers[i]) {
                case 1: case 4: case 7:
                    left = loc[numbers[i]];
                    answer.append("L");       
                    break;
                case 3: case 6: case 9:
                    right = loc[numbers[i]];
                    answer.append("R");
                    break;
                default:
                    int diff = distance(left, loc[numbers[i]]) - distance(right, loc[numbers[i]]);
                    if(0 < diff) {
                        right = loc[numbers[i]];
                        answer.append("R");
                    } else if(diff < 0) {
                        left = loc[numbers[i]];
                        answer.append("L");
                    } else {
                        if(isLeft) {
                            left = loc[numbers[i]];
                            answer.append("L");
                        } else {
                            right = loc[numbers[i]];
                            answer.append("R");
                        }
                    }
                    break;
            }
        }

        return answer.toString();
    }

    public int distance(int[] A, int[] B) {
        return Math.abs(A[0] - B[0]) + Math.abs(A[1] - B[1]);
    }
}