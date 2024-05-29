package programmers.재밌는_레이싱_경기장_설계하기;

import java.util.Arrays;

public class Main {
  
}

class Solution {
  private int min = Integer.MAX_VALUE;
  private int minSecond = Integer.MAX_VALUE;
  public int solution(int[] heights) {
    Arrays.sort(heights);
    int len = heights.length;
    int mid = len / 2;
    for (int i = 0; i < mid; i++) {
      compareMin(heights[mid + i] - heights[i]);
    }
    if (len % 2 == 1) {
      compareMin(heights[len - 1] - heights[mid]);
      return minSecond;
    }
    return min;
  }

  private void compareMin(int value) {
    if (value < minSecond) {
      if (value < min) {
        minSecond = min;
        min = value;
      } else {
        minSecond = value;
      }
    }
  }
}
