import java.util.Arrays;

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