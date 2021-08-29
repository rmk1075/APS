class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;

        int H = triangle.length;
        for(int i = 1; i < H; i++) {
            int N = triangle[i].length;

            // 0
            triangle[i][0] += triangle[i - 1][0];

            // N - 1
            triangle[i][N - 1] += triangle[i - 1][N - 2];

            for(int j = 1; j < N - 1; j++) {
                triangle[i][j] += Math.max(triangle[i - 1][j - 1], triangle[i - 1][j]);
            }
        }

        for(int i = 0; i < triangle[H - 1].length; i++) answer = Math.max(answer, triangle[H - 1][i]);
        return answer;
    }
}