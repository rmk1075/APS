import java.util.HashMap;
import java.util.Map;

public class Main {
  public static void main(String[] args) {
    Solution sol = new Solution();
  }
}

class Solution {
  Map<Integer, Integer> map = new HashMap<>(); // {temperature, cost}
  Map<Integer, Integer> tempMap = new HashMap<>();
  boolean isCooler = false;
  public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
    init(temperature, t1);
    for (int on : onboard) {
      changeTemperature(temperature, t1, t2, a, b, on);
    }
    return findMinCost();
  }

  private void init(int temperature, int t1) {
    if (temperature < t1) {
      isCooler = true;
    }
    map.put(temperature, 0);
  }

  private void changeTemperature(int temperature, int t1, int t2, int a, int b, int on) {
    if (on == 0) {
      changeAtZero(temperature, t1, t2, a, b);
    } else if (on == 1) {
      changeAtOne(temperature, t1, t2, a, b);   
    }
  }

  private void changeAtZero(int temperature, int t1, int t2, int a, int b) {
    for (int current : map.keySet()) {
      int cost = map.get(current);
      turnOn(current, cost, t1, t2, a, b);
      turnOff(temperature, current, cost, t1, t2, a, b);
    }
    map = tempMap;
    tempMap = new HashMap<>();
  }

  private void turnOn(int current, int cost, int t1, int t2, int a, int b) {
    if (isCooler) {
      if (current < t2) {
        putTemperature(current + 1, cost + a);
      }
    } else {
      if (t1 < current) {
        putTemperature(current - 1, cost + a);
      }
    }
    putTemperature(current, cost + b);
  }

  private void turnOff(int temperature, int current, int cost, int t1, int t2, int a, int b) {
    if (isCooler) {
      if (temperature < current) {
        putTemperature(current - 1, cost);
      } else {
        putTemperature(current, cost);
      }
    } else {
      if (current < temperature) {
        putTemperature(current + 1, cost);
      } else {
        putTemperature(current, cost);
      }
    }
  }

  private void putTemperature(int temp, int cost) {
    if (tempMap.containsKey(temp)) {
      if (cost < tempMap.get(temp)) {
        tempMap.put(temp, cost);
      }
    } else {
      tempMap.put(temp, cost);
    }
  }

  private void changeAtOne(int temperature, int t1, int t2, int a, int b) {
    for (int current : map.keySet()) {
      int cost = map.get(current);
      turnOn(current, cost, t1, t2, a, b);
      turnOff(temperature, current, cost, t1, t2, a, b);
    }
    map.clear();
    for (int temp : tempMap.keySet()) {
      if (isValid(temp, t1, t2)) {
        map.put(temp, tempMap.get(temp));
      }
    }
    tempMap.clear();
  }

  private boolean isValid(int temp, int t1, int t2) {
    return t1 <= temp && temp <= t2;
  }

  private int findMinCost() {
    int minCost = Integer.MAX_VALUE;
    for (int temp : map.keySet()) {
      minCost = Math.min(map.get(temp), minCost);
    }
    return minCost;
  }
}