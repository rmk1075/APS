import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * overview
 * 
 * start 부터 end 까지 두 노드 간의 최단 거리를 찾아야 한다.
 * 이 문제에서는 두가지 요소를 중점적으로 확인해야 한다.
 * 첫번째는 간선의 방향을 확정짓는 요소 - 양 끝의 노드와 노드의 상태,
 * 두번째는 해당 노드의 방문 여부와 재방문시 거리의 비교를 위한 현재 그래프 상태의 저장이다.
 * 
 * trap 의 상태에 따라서 전체 그래프의 간선의 방향이 조금씩 달라진다.
 * 간선의 방향은 간선 양 끝의 노드들의 상태에 따라 달라진다.
 * 양 끝의 노드가 트랩인지 아닌지, 트랩이라면 트랩이 발동했는지 아닌지를 구분하여 간선의 방향을 확인해야 한다.
 * 
 * 모든 경우의 수를 확인하기 위해서 각 반복의 경우마다 그래프의 상태 (간선의 방향들) 을 확인하여야 한다.
 * 단순히 해당 노드를 방문했는지가 아니라 현재 그래프의 상태 - 트랩들의 발동 상태인 경우에 현재 노드를 방문했는지 여부를 확인하고 거리를 비교해야 한다.
 * 
 * 
 * description
 * 
 * 그래프의 각 간선을 저장할 때에 정방향뿐만 아니라 역방향의 간선 정보도 저장한다. 이는 트랩의 발동 여부에 따라 간선의 방향이 달라지기 때문이다.
 * 간선을 저장할 때, flag 로 간선의 방향을 정방향 0, 역방향 1로 저장하는데, 이는 이후에 양 끝의 노드를 비교하여 정방향인 경우에는 0인 간선을 반대의 경우 1인 간선으로 비교를 수행하기 위함이다.
 * 
 * start 와 end 두 노드간의 거리를 찾기 위해서는 dijkstra 알고리즘을 기반으로 로직을 구성한다.
 * priority queue 를 사용하여 현재 연결된 서브 그래프에서 갈 수 있는 최단 거리의 노드를 선택하는 방식으로 탐색을 수행한다.
 * 
 * 각 노드들의 거리는 [n + 1][1 << traps.length] 로 구현한다.
 * 이는 각 노드들을 현재 그래프의 상태에 방문했는지, 방문했을 때 거리가 얼마인지를 저장하기 위함이다.
 * traps 의 길이로 열을 구성한 이유는 그래프는 트랩들의 발동 여부에 따라서만 상태가 변경되기 때문이다.
 * bitwise 를 사용하여 트랩들의 상태를 저장하여 현재 그래프의 상태와 거리를 매핑하여 저장한다.
 * 
 * dijkstra 로직을 수행 중에 현재 간선이 유효한 간선인지 비교를 수행한다.
 * 간선의 방향은 다음의 네가지 경우의 수를 따른다.
 *
 * 1. current 가 트랩이 아니거나 트랩이 발동되지 않은 경우 & next 가 트랩이 아니거나 트랩이 발동되지 않은 경우 -> 정방향 (0)
 * 2. current 가 트랩이 아니거나 트랩이 발동되지 않은 경우 & next 가 트랩이고 트랩이 발동된 경우 -> 역방향 (1)
 * 3. current 가 트랩이고 트랩이 발동된 경우 & next 가 트랩이고 트랩이 발동된 경우 -> 정방향 (0)
 * 4. current 가 트랩이고 트랩이 발동되 경우 & next 가 트랩이 아니거나 트랩이 발동되지 않은 경우 -> 역방향 (1)
 * 
 * 위의 4가지 경우에 따라 next 간선이 유효한지 확인하고 XOR (^) 연산을 통해 상태에 기록한다.
 * 
 * 만약 next 가 end 노드에 다다른 경우 현재까지의 distance 를 비교하여 min 값으로 저장한다.
 * 항상 미로를 탈출하는 경우만 주어지기 때문에 다른 예외처리를 할 필요는 없고, 로직이 완료되면 min 값으로 저장된 answer 를 반환하면 된다.
 */
class Solution {
    int[] trapMap;
    int[][] distances;
    List<int[]>[] edges;
    public int solution(int n, int start, int end, int[][] roads, int[] traps) {
        distances = new int[n + 1][1 << traps.length];
        for(int i = 0; i < n + 1; i++) Arrays.fill(distances[i], Integer.MAX_VALUE);

        trapMap = new int[n + 1];
        Arrays.fill(trapMap, -1);
        for(int i = 0; i < traps.length; i++) trapMap[traps[i]] = i;

        edges = new List[n + 1];
        for(int i = 0; i < n + 1; i++) edges[i] = new LinkedList<>(); // {next, weight, flag}
        Arrays.stream(roads).forEach(road -> {
            edges[road[0]].add(new int[]{road[1], road[2], 0}); // forward
            edges[road[1]].add(new int[]{road[0], road[2], 1}); // backward
        });

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[2], o2[2]);
            }
        });
        pq.offer(new int[]{start, 0, 0}); // {next, weight, state}
        distances[start][0] = 0;
        int answer = Integer.MAX_VALUE;
        while(!pq.isEmpty()) {
            int[] node = pq.poll();
            int current = node[0];
            int currentWeight = node[1];
            int currentState = node[2];

            if(distances[current][currentState] < currentWeight) continue;

            int currentFlag = (trapMap[current] != -1 && ((currentState & (1 << trapMap[current])) != 0)) ? 1 : 0;
            for(int[] edge : edges[current]) {
                int next = edge[0];
                int nextWeight = edge[1];
                int nextFlag = edge[2];

                int flag = 0;
                int nextState = currentState;
                if(currentFlag == 0) {
                    if(trapMap[next] == -1) flag = 0;
                    else {
                        flag = (currentState & (1 << trapMap[next])) == 0 ? 0 : 1;
                        nextState = currentState ^ (1 << trapMap[next]);
                    }
                } else {
                    if(trapMap[next] == -1) flag = 1;
                    else {
                        flag = ((currentState & (1 << trapMap[next])) == 0) ? 1 : 0;
                        nextState = currentState ^ (1 << trapMap[next]);
                    }
                }

                if(flag != nextFlag || distances[next][nextState] <= currentWeight + nextWeight) continue;
                distances[next][nextState] = currentWeight + nextWeight;

                if(next == end) answer = Math.min(answer, distances[next][nextState]);
                else pq.offer(new int[]{next, distances[next][nextState], nextState});
            }
        }

        return answer;
    }
}