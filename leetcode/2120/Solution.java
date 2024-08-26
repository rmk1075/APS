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
        for (int i = 0; i < m; i++) {
            answer[i] = find(n, i, chs, startPos);
        }
        return answer;
    }

    private int find(int n, int index, char[] chs, int[] startPos) {
        int x = startPos[0];
        int y = startPos[1];
        int count = 0;
        for (int i = index; i < chs.length; i++) {
            int[] next = nextLocation(x, y, chs[i]);
            x = next[0];
            y = next[1];
            if (isOver(n, x, y)) {
                break;
            }

            count++;
        }
        return count;
    }

    private int[] nextLocation(int x, int y, char instruction) {
        int d = instructionMap.get(instruction);
        return new int[]{x + dx[d], y + dy[d]};
    }

    private boolean isOver(int n, int x, int y) {
        return x < 0 || y < 0 || x == n || y == n;
    }
}