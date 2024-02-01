import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

class Solution {
  private int point = -1;
  private int donut, stick, infinite;
  private Set<Integer> visited = new HashSet<>();
  private Map<Integer, List<Integer>> in = new HashMap<>();
  private Map<Integer, List<Integer>> out = new HashMap<>();
  public int[] solution(int[][] edges) {
    readGraph(edges);
    
    // 2. stick
    findStick();

    // 3. infinite
    findInfinite();

    // 1. donut
    findDonut();


    if (point == -1) {
      findPoint();
    }

    return new int[]{point, donut, stick, infinite};
  }

  private void readGraph(int[][] edges) {
      for(int[] edge: edges) {
        int from = edge[0];
        int to = edge[1];
        out.putIfAbsent(from, new ArrayList<>());
        out.get(from).add(to);
        in.putIfAbsent(to, new ArrayList<>());
        in.get(to).add(from);
      }
      return ;
  }

  private void findStick() {
    for(int key: in.keySet()) {
      if(visited.contains(key)) {
        continue;
      }

      if(!out.containsKey(key)) {
        visitStick(key);
        stick++;
      }
    }
  }

  private void visitStick(int key) {
    visited.add(key);
    Queue<Integer> queue = new LinkedList<>();
    queue.addAll(in.get(key));
    while(!queue.isEmpty()) {
      int node = queue.poll();
      if(visited.contains(node) || out.get(node).size() != 1) {
        continue;
      }
      visited.add(node);

      List<Integer> next = in.get(node);
      if(next != null) {
        queue.addAll(next);
      }
    }
  }

  private void findDonut() {
    for(int key: out.keySet()) {
      if(visited.contains(key)) {
        continue;
      }

      if(
        out.get(key).size() == 1
        && in.containsKey(key)
      ) {
        visitDonut(key);
        donut++;
      }
    }
  }

  private void visitDonut(int key) {
    visited.add(key);
    Queue<Integer> queue = new LinkedList<>();
    queue.addAll(out.get(key));
    while(!queue.isEmpty()) {
      int node = queue.poll();
      if(visited.contains(node)) {
        continue;
      }
      visited.add(node);

      if(out.containsKey(node)) {
        queue.addAll(out.get(node));
      }
    }
  }

  private void findInfinite() {
    for(int key: out.keySet()) {
      if(visited.contains(key)) {
        continue;
      }

      if(in.containsKey(key) && 1 < out.get(key).size()) {
        visitInfinite(key);
        infinite++;
      }
    }
  }

  private void visitInfinite(int key) {
    visited.add(key);
    Queue<Integer> queue = new LinkedList<>();
    queue.addAll(out.get(key));
    while(!queue.isEmpty()) {
      int node = queue.poll();
      if(visited.contains(node) || !in.containsKey(node)) {
        continue;
      }
      visited.add(node);

      if(out.containsKey(node)) {
        queue.addAll(out.get(node));
      }
    }
  }

  private void findPoint() {
    for(int key: out.keySet()) {
      if(visited.contains(key)) {
        continue;
      }
      if (!in.containsKey(key)) {
        point = key;
        break;
      }
    }
  }
}