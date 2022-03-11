/**
 * overview
 * 
 * 목적지 (x, y) 에 도달할 수 있는 모든 출발지를 찾아야한다.
 * queries 에 저장된 query 들을 통해서 출발지를 찾아야 하는데,
 * 이는 query 를 역순으로 하여 목적지에서 출발지들을 찾을 수 있다.
 * 이때 단순히 query 를 역순으로 조회하는 것 뿐 아니라 query 의 방향도 역순으로 해야한다.
 * 또한 출발지 대상에 대해서 하나하나의 지점들을 찾게되면 시간, 메모리 모두가 오버하게된다.
 * 그렇기 때문에 하나의 지점이 아닌 전체 대상 후보 영역으로 탐색을 수행해야 한다.
 * 
 * description
 * 
 * 출발지 대상의 영역을 목적지로 초기화하여 탐색을 수행한다.
 * x1, y1, x2, y2 는 각각 최소 x, 최소 y, 최대 x, 최대 y 의 좌표를 나타낸다.
 * 4점을 연결한 사각형이 출발지 후보 대상 영역이다.
 * 탐색을 수행하면서 각각의 좌표에 대한 예외처리를 해야한다.
 * 각각 최소 좌표가 행, 열의 최대 크기보다 큰 경우, 최대 좌표가 0보다 작은 경우에는 목적지에 도달할 수 없는 경우로 0을 반환한다.
 * 반대로 최소 좌표가 0보다 작거나, 최대 좌표가 행, 열의 최대 크기보다 큰 경우에는 이동시에 벽에 부딛혀서 도달하는 경우기 때문에 각각 0 과 최대 크기를 값으로 변환해준다.
 * 탐색이 완료되면 최종으로 탐색된 영역의 넓이를 구하여 반환한다.
 */
class Solution {
    public long solution(int n, int m, int x, int y, int[][] queries) {
        long x1 = x;
        long y1 = y;
        long x2 = x;
        long y2 = y;
        for(int q = queries.length - 1; -1 < q; q--) {
            int[] query = queries[q];
            int d = query[0];
            int dist = query[1];
            if(d == 0) {
                if(y1 != 0) y1 += dist;
                y2 = Math.min(m - 1, y2 + dist);
            } else if(d == 1) {
                y1 = Math.max(0, y1 - dist);
                if(y2 != m - 1) y2 -= dist;
            } else if(d == 2) {
                if(x1 != 0) x1 += dist;
                x2 = Math.min(n - 1, x2 + dist);
            } else if(d == 3) {
                x1 = Math.max(0, x1 - dist);
                if(x2 != n - 1) x2 -= dist;
            }

            if(n <= x1 || x2 < 0 || m <= y1 || y2 < 0) return 0;
        }

        long answer = (x2 - x1 + 1) * (y2 - y1 + 1);
        return answer;
    }
}