import java.util.List;
import java.util.PriorityQueue;
import java.util.Collections;

class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> result = new LinkedList<>();
        List<int[]> building = new ArrayList<>();
        for(int[] b : buildings) {
            building.add(new int[]{b[0], -b[2]});
            building.add(new int[]{b[1], b[2]});
        }

        Collections.sort(building, (a, b) -> {
            if(a[0] == b[0]) return a[1] - b[1];
            return a[0] - b[0];            
        });

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));
        pq.offer(0);
        int prev = 0;
        for(int[] h : building) {
            if(h[1] < 0) pq.offer(-h[1]);
            else pq.remove(h[1]);

            int current = pq.peek();
            if(prev != current) {
                result.add(List.of(h[0], current));
                prev = current;
            }
        }

        return result;
    }
}