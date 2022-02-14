import java.util.Arrays;

/**
 * overview
 * 
 * 각 시험장들이 이진트리로 구성되어 있을때, 트리의 간선을 끊어서 k 그룹을 나누어야 한다.
 * 이때 각 그룹의 인원들을 비교하여 최대 인원이 최소가 되는 경우의 수를 구해야한다.
 * 완전 탐색을 통해 나눌 수 있는 모든 그룹의 경우의 수를 비교하려면 time complexity 가 너무 커서 효율성에서 걸리게 된다.
 * 이러한 문제를 해결하기 위해서 파라메트릭 탐색을 사용하여 최소가 될 수 있는 인원을 탐색한다.
 * 파라메트릭 탐색을 한 그룹에 들어갈 수 있는 인워의 범위를 정해놓고 범위내에서 원하는 값을 탐색하는 것이다.
 * 탐색을 할 때 탐색 로직 내부에서는 dp 를 통해서 현재 기준의 인원이 유효한지 확인한다.
 * 
 * 
 * description
 * 
 * 우선은 link 를 통해서 각 시험장을 이진트리로 구성한다.
 * 이진트리로 구성한 다음 파라메트릭 탐색을 수행한다.
 * 
 * 1. 파라메트릭 탐색
 * 
 * 파라메트릭 탐색을 수행하기에 앞서 한 그룹에 들어갈 수 있는 인원의 범위를 구한다.
 * 한 그룹에 들어갈 수 있는 인원의 범위는 (전체 인원) / k 보다 크고 전체 인원 이하가 된다.
 * 이 범위를 기준으로 이진 탐색을 수행하여 유효한 값을 찾는다.
 * 
 * 2. dynamic programming
 * 
 * 이진 탐색의 mid 값에 대해서 mid 값이 유효한지 트리를 순회하면서 확인한다.
 * postorder 방식으로 순회하여 현재 노드가 포함된 그룹의 인원의 수를 확인하여 mid 값보다 커지게 되면 간선을 끊어서 그룹을 나눈다.
 * 이때 그룹을 나누는 경우의 수는 현재 노드의 left, right 가 있는지 여부에 따라 달라진다.
 * 1) current + left + right 가 mid 값 보다 같거나 작은 경우는 간선을 나누지 않고 순회를 진행한다.
 * 2) current + left + right 가 mid 값 보다 큰 경우에는 left 와 right 중 더 큰 쪽의 간선을 나누어서 순회를 진행한다.
 * 3) 만약 left 와 right 중 작은 쪽의 그룹과 current 를 더했을 때에도 mid 값보다 큰 경우에는 양쪽의 간선을 모두 나누어 비교를 진행한다.
 * 4) 만약 current 만으로도 mid 값을 넘어가는 경우에는 더 이상 탐색을 하는게 무의미 하기 때문에 최대값을 반환시켜서 mid 가 유효하지 않음을 반환한다.
 * 
 * dp 연산의 값을 저장하는 배열은 new int[N][2] 가 되는데 이는 i 번째 노드까지 탐색을 수행했을 때 {현재까지 나뉘어진 그룹의 개수, i 번째 노드가 포함된 그룹의 인원 수} 를 의미한다.
 * 
 * 파라메트릭 탐색에서는 dp 탐색이 완료된 후에 [head][0] 의 값을 확인하여 head 까지 탐색했을 때 그룹의 수를 k 와 비교하여 이진 탐색을 이어나간다.
 * 이진 탐색의 결과로 찾은 최소 인원 의 수를 결과로 반환한다.
 * 
 * 
 * reference
 * 
 * - https://tech.kakao.com/2021/07/08/2021-카카오-인턴십-for-tech-developers-코딩-테스트-해설/
 */
class Room {
    int id = -1;
    int attendances = 0;
    Room left = null;
    Room right = null;

    public Room(int id, int attendances) {
        this.id = id;
        this.attendances = attendances;
    }
}

class Solution {
    int[][] subArr;
    Room[] rooms;
    public int solution(int k, int[] num, int[][] links) {
        // init rooms
        int N = num.length;
        int sumAttendances = 0;
        rooms = new Room[N];
        for(int i = 0; i < N; i++) {
            sumAttendances += num[i];
            rooms[i] = new Room(i, num[i]);
        }

        boolean[] isChild = new boolean[N];
        for(int i = 0; i < N; i++) {
            int[] link = links[i];
            if(link[0] != -1) {
                rooms[i].left = rooms[link[0]];
                isChild[link[0]] = true;
            }
            if(link[1] != -1) {
                rooms[i].right = rooms[link[1]];
                isChild[link[1]] = true;
            }
        }

        // find head
        Room head = null;
        for(int i = 0; i < N; i++) {
            if(!isChild[i]) {
                head = rooms[i];
                break;
            }
        }

        // parametric search
        int left = sumAttendances / k;
        int right = sumAttendances + 1;
        if(left == right) return right;
        while(left < right) {
            int mid = (left + right) / 2;
            subArr = new int[N][2]; // {number of groups, number attendances of the group}
            find(head, k, mid);
            if(subArr[head.id][0] <= k) right = mid;
            else left = mid + 1;
        }

        return right;
    }

    public void find(Room room, int k, int boundary) {
        int[] left = {1, 0}; // {number of groups, number attendances of the group}
        if(room.left != null) {
            find(room.left, k, boundary);
            left[0] = subArr[room.left.id][0];
            left[1] = subArr[room.left.id][1];
        }

        int[] right = {1, 0}; // {number of groups, number attendances of the group}
        if(room.right != null) {
            find(room.right, k, boundary);
            right[0] = subArr[room.right.id][0];
            right[1] = subArr[room.right.id][1];
        }

        int attendances = room.attendances + left[1] + right[1];
        if(attendances <= boundary) {
            subArr[room.id][0] = left[0] + right[0] - 1;
            subArr[room.id][1] = attendances;
            return ;
        }

        int minAttd = Math.min(left[1], right[1]);
        if(room.attendances + minAttd <= boundary) {
            subArr[room.id][0] = left[0] + right[0];
            subArr[room.id][1] = room.attendances + minAttd;
            return ;
        }

        if(room.attendances <= boundary) {
            subArr[room.id][0] = left[0] + right[0] + 1;
            subArr[room.id][1] = room.attendances;
            return ;
        }

        subArr[room.id][0] = k + 1;
        subArr[room.id][1] = boundary + 1;
    }
}