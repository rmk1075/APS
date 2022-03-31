import java.util.LinkedList;
import java.util.List;

/**
 * overview
 * 
 * 간단한 하노이의 탑 문제이다.
 * 3개의 타워가 있을 때, 1 -> 3 으로 n 개의 원판을 옮겨야 한다.
 * 
 * description
 * 
 * 조건을 지키기 위해서는 한번에 1 -> 3 으로 옮길 수 없다.
 * 2를 거쳐서 원판을 크기게 맞게 순서대로 옮겨야 한다.
 * 1 -> 3 으로 n 개를 옮기기 위해서는 먼저 n - 1 개를 2 로 옮기고 남은 한개를 3으로 옮겨야 한다.
 * 그 다음 2의 n - 1 개의 원판을 다시 3까지 옮겨야 한다.
 * 이를 재귀적으로 구현하여 문제를 해결하였다.
 */
class Solution {
    List<int[]> list = new LinkedList<>();
    public int[][] solution(int n) {        
        move(1, 2, 3, n);
        int[][] answer = {};
        answer = list.toArray(new int[list.size()][2]);
        return answer;
    }

    public void move(int from, int mid, int to, int count) {
        if(count == 0) return ;
        move(from, to, mid, count - 1);
        list.add(new int[]{from, to});
        move(mid, from, to, count - 1);
    }
}