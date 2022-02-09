import java.util.LinkedList;
import java.util.List;

class Solution {
    int maxSheeps = 1;
    List<Integer>[] graph;
    public int solution(int[] info, int[][] edges) {
        int N = info.length;
        graph = new List[N];
        for(int i = 0; i < N; i++) graph[i] = new LinkedList<>();
        for(int[] edge : edges) graph[edge[0]].add(edge[1]);

        List<Integer> nexts = new LinkedList<>();
        dfs(0, 0, 0, nexts, info);
        return maxSheeps;
    }

    public void dfs(int index, int sheeps, int wolves, List<Integer> nexts, int[] info) {
        if(info[index] == 0) sheeps++;
        else wolves++;
        if(sheeps <= wolves) return ;
        maxSheeps = Math.max(maxSheeps, sheeps);

        List<Integer> candidates = new LinkedList<>();
        candidates.addAll(nexts);
        candidates.addAll(graph[index]);
        candidates.remove(Integer.valueOf(index));
        for(int candidate : candidates) dfs(candidate, sheeps, wolves, candidates, info);
    }
}