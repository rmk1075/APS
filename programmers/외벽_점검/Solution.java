import java.util.Arrays;

class Solution {
    int w, d, visited = 0;
    int[] seqD;
    int[][] seqW;
    public int solution(int n, int[] weak, int[] dist) {
        w = weak.length;
        d = dist.length;
        seqW = new int[w][w];
        seqD = new int[d];

        int idx = 0;
        for(int i = 0; i < w; i++) {
            idx = 0;
            for(int j = i; j < w; j++) seqW[i][idx++] = weak[j];
            for(int j = 0; j < i; j++) seqW[i][idx++] = n + weak[j];
        }

        Arrays.sort(dist);

        for(int i = 0; i < d; i++) {
            visited = 0;
            if(dfs(dist, 0, i + 1, d - i - 2)) return i + 1;
        }

        return -1;
    }

    public boolean dfs(int[] dist, int idx, int target, int limit) {
        if(idx == target) {
            for(int i = 0; i < w; i++) {
                int src, len, index = 0;
                for(int j = 0; j < target && index < w; j++) {
                    src = seqW[i][index];
                    len = seqD[j];
                    while(index < w && seqW[i][index] - src <= len) index++;
                }

                if(index == w) return true;
            }

            return false;
        }

        for(int i = d - 1; limit < i; i--) {
            if((visited & (1 << i)) != 0) continue;
            
            visited |= (1 << i);
            seqD[idx] = dist[i];
            if(dfs(dist, idx + 1, target, limit)) return true;
            visited &= ~(1 << i);
        }

        return false;
    }
}