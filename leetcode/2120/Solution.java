import java.util.Map;

class Solution {
    private static final int[] dx = { 0, 0, -1, 1 };
    private static final int[] dy = { -1, 1, 0, 0 };
    private static final Map<Character, Integer> instructionMap = Map.of(
            'L', 0, 'R', 1, 'U', 2, 'D', 3);

    public int[] executeInstructions(int n, int[] startPos, String s) {
        int m = s.length();
        int[] answer = new int[m];
        char[] chs = s.toCharArray();
        int[][] locations = initLocations(startPos, m, chs);
        answer[0] = getAnswer(locations, n, m, 0);
        for (int i = 1; i < m; i++) {
            int inst = getInstruction(chs, i);
            int ddx = dx[inst];
            int ddy = dy[inst];
            updateLocations(locations, m, i, ddx, ddy);
            answer[i] = getAnswer(locations, n, m, i);
        }
        return answer;
    }

    private int getInstruction(char[] chs, int index) {
        int prev = instructionMap.get(chs[index - 1]);
        int inst = -1;
        if (prev % 2 == 0) {
            inst = prev + 1;
        } else {
            inst = prev - 1;
        }
        return inst;
    }

    private void updateLocations(int[][] locations, int m, int index, int ddx, int ddy) {
        for (int i = index; i < m; i++) {
            locations[i][0] += ddx;
            locations[i][1] += ddy;
        }
    }

    private int getAnswer(int[][] locations, int n, int m, int index) {
        for (int i = index; i < m; i++) {
            if (isOver(n, locations[i][0], locations[i][1])) {
                return i - index;
            }
        }
        return m - index;
    }

    private int[][] initLocations(int[] startPos, int m, char[] chs) {
        int[][] locations = new int[m][2];
        int x = startPos[0];
        int y = startPos[1];
        for (int i = 0; i < m; i++) {
            char ch = chs[i];
            int[] next = nextLocation(x, y, ch);
            x = next[0];
            y = next[1];
            locations[i][0] = x;
            locations[i][1] = y;
        }

        return locations;
    }

    private int[] nextLocation(int x, int y, char instruction) {
        int d = instructionMap.get(instruction);
        return new int[] { x + dx[d], y + dy[d] };
    }

    private boolean isOver(int n, int x, int y) {
        return x < 0 || y < 0 || x == n || y == n;
    }
}

// import java.util.Map;

// class Solution {
// private static final int[] dx = { 0, 0, -1, 1 };
// private static final int[] dy = { -1, 1, 0, 0 };
// private static final Map<Character, Integer> instructionMap = Map.of(
// 'L', 0, 'R', 1, 'U', 2, 'D', 3);

// public int[] executeInstructions(int n, int[] startPos, String s) {
// int m = s.length();
// int[] answer = new int[m];
// char[] chs = s.toCharArray();
// for (int i = 0; i < m; i++) {
// answer[i] = find(n, i, chs, startPos);
// }
// return answer;
// }

// private int find(int n, int index, char[] chs, int[] startPos) {
// int x = startPos[0];
// int y = startPos[1];
// int count = 0;
// for (int i = index; i < chs.length; i++) {
// int[] next = nextLocation(x, y, chs[i]);
// x = next[0];
// y = next[1];
// if (isOver(n, x, y)) {
// break;
// }

// count++;
// }
// return count;
// }

// private int[] nextLocation(int x, int y, char instruction) {
// int d = instructionMap.get(instruction);
// return new int[]{x + dx[d], y + dy[d]};
// }

// private boolean isOver(int n, int x, int y) {
// return x < 0 || y < 0 || x == n || y == n;
// }
// }