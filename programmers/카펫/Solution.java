class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = {};

        for(int r = 3; r < brown / 2; r++) {
            int c = brown / 2 - r + 2;
            if(yellow == r * c - brown) {
                answer = new int[] {c, r};
                break;
            }
        }

        return answer;
    }
}