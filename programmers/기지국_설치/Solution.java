/**
 * overview
 * 
 * 전체 영역을 덮기위해서 필요한 최소의 기지국 개수를 구한다.
 * 현재 설치되어있는 기지국들로 덮지 못하는 영역을 구한뒤 해당 영역들을 덮기 위해 필요한 기지국 개수를 반환한다.
 * 
 * description
 * 
 * stations 를 for 문으로 탐색하면서 각 station 의 위치 정보를 확인한다.
 * 최초 위치를 0부터 시작하여 station 의 왼쪽 마지막 영역과 거리를 비교해서 전파로 덮지 못한 영역의 거리를 구한다.
 * 거리를 기지국으로 덮을 수 있는 범위 (2 * w + 1) 로 나누어서 올림을 하여 설치할 기지국 개수를 구한다.
 * 해당 값을 answer 에 더하여 기지국 개수를 추가한다.
 * 이를 반복하여 추가로 설치할 전체 기지국 개수를 구한다.
 */
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