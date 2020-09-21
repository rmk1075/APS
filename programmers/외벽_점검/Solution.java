import java.util.Arrays;

class Solution {
    public int solution(int n, int[] weak, int[] dist) {
        int answer = 0;

        Arrays.sort(dist);

        int D = dist.length, W = weak.length, visited = 0;
        int[] seqD = new int[D], seqW = new int[W];
        for(int i = 1; i < D + 1; i++) {
            seqD[i - 1] = dist[D - i];

            // start point
            for(int j = 0; j < W; j++) {
                int idx = 0;
                for(int k = j; k < W; k++) seqW[idx++] = weak[k];
                for(int k = 0; k < j; k++) seqW[idx++] = weak[k] + n;

                idx = 0;
                for(int k = 0; k < i && idx < W; k++) {
                    int src = seqW[idx], len = seqD[k];
                    while(idx < W && seqW[idx] - src <= len) idx++;
                }

                if(idx == W) return i;
            }
        }

        return -1;
    }
}