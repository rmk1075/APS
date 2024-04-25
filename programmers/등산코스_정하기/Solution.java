import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

class Solution {
  private static final int INF = 1_000_000_000;
  private int[] dijkstra;
  private List<int[]>[] graph;
  private Set<Integer> gateSet;
  private Set<Integer> summitSet;
  public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
    init(n, paths, gates, summits);
    for (int gate : gates) {
      findDijkstra(gate);
    }
    return find(summits);
  }

  private void init(int n, int[][] paths, int[] gates, int[] summits) {
    dijkstra = new int[n + 1];
    Arrays.fill(dijkstra, INF);

    gateSet = new HashSet<>();
    for (int gate : gates) {
      gateSet.add(gate);
    }

    summitSet = new HashSet<>();
    for (int summit : summits) {
      summitSet.add(summit);
    }

    graph = new LinkedList[n + 1];
    for (int i = 0; i < n + 1; i++) {
      graph[i] = new LinkedList<int[]>();
    }
    for (int[] path : paths) {
      int i = path[0];
      int j = path[1];
      int w = path[2];
      graph[i].add(new int[]{j, w});
      graph[j].add(new int[]{i, w});
    }
  }

  private void findDijkstra(int src) {
    Queue<int[]> queue = new LinkedList<>();
    queue.add(new int[]{src, 0});

    while (!queue.isEmpty()) {
      int[] current = queue.poll();
      if (summitSet.contains(current[0])) {
        continue;
      }

      int intensity = current[1];
      for (int[] next : graph[current[0]]) {
        if (gateSet.contains(next)) {
          continue;
        }

        int maxIntensity = Math.max(intensity, next[1]);
        if (dijkstra[next[0]] <= maxIntensity) {
          continue;
        } else {
          dijkstra[next[0]] = maxIntensity;
          queue.add(new int[]{next[0], maxIntensity});
        }
      }
    }
  }

  private int[] find(int[] summits) {
    int resultSummit = -1;
    int resultIntensity = INF;
    for (int summit : summits) {
      int intensity = dijkstra[summit];
      if (intensity <= resultIntensity) {
        if (intensity == resultIntensity && resultSummit < summit) {
          continue;
        }
        resultSummit = summit;
        resultIntensity = intensity;
      }
    }

    return new int[]{resultSummit, resultIntensity};
  }
}