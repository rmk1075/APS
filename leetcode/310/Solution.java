import java.util.LinkedList;
import java.util.List;

class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if(n < 2) {
            List<Integer> result = new LinkedList<Integer>();
            for(int i = 0; i < n; i++) result.add(i);
            return result;
        }

        List<Integer>[] distances = new List[n];
        for(int i = 0; i < n; i++) distances[i] = new LinkedList<>();
        for(int[] edge : edges) {
            distances[edge[0]].add(edge[1]);
            distances[edge[1]].add(edge[0]);
        }

        LinkedList<Integer> queue = new LinkedList<>();
        for(int i = 0; i < n; i++) {
            if(distances[i].size() == 1) queue.offer(i);
        }

        int cnt = n;
        while(2 < cnt) {
            cnt -= queue.size();
            LinkedList<Integer> newQueue = new LinkedList<>();
            for(Integer node : queue) {
                Integer next = distances[node].iterator().next();
                distances[next].remove(node);
                if(distances[next].size() == 1) newQueue.add(next);
            }

            queue = newQueue;
        }

        return queue;
    }
}