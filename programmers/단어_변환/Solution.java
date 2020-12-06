import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        int N = words.length, len = begin.length();

        char[] beginCh = begin.toCharArray();
        char[] targetCh = target.toCharArray();
        char[][] wordsCh = new char[N][];
        for (int i = 0; i < N; i++) {
            wordsCh[i] = words[i].toCharArray();
        }

        Queue<char[]> queue = new LinkedList<>();
        queue.offer(beginCh);

        boolean[] visited = new boolean[N];

        int cnt = 0, cycle = 0;
        char[] src = new char[1];
        loop1: while (!queue.isEmpty()) {
            cycle = queue.size();
            while (0 < cycle--) {
                src = queue.poll();

                if (compare(len, src, targetCh) == 0) {
                    answer = cnt;
                    break loop1;
                }

                for (int i = 0; i < N; i++) {
                    if (visited[i] || compare(len, src, wordsCh[i]) != 1)
                        continue;

                    visited[i] = true;
                    queue.offer(wordsCh[i]);
                }
            }
            cnt++;
        }

        if (compare(len, src, targetCh) != 0)
            return 0;

        return answer;

    }

    public static int compare(int N, char[] src, char[] tar) {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if (src[i] != tar[i])
                cnt++;
        }

        return cnt;
    }
}