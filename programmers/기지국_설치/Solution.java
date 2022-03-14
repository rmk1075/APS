class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        double ww = 2 * w + 1;
        int lastIndex = 0;
        for(int station : stations) {
            double distance = station - w - lastIndex - 1;
            if(0 < distance) answer += Math.ceil(distance / ww);
            lastIndex = station + w;
        }

        double distance = n - lastIndex;
        if(0 < distance) answer += Math.ceil(distance / ww);
        return answer;
    }
}