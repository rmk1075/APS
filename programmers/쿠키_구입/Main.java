import java.util.LinkedList;
import java.util.Queue;

public class Main {
  public static void main(String[] args) {
    // [1,1,2,3]	3
    // [1,2,4,5]	0
    // int[] cookie = new int[]{1, 1, 2, 3};
    int[] cookie = new int[]{1, 2, 4, 5};

    Solution sol = new Solution();
    int result = sol.solution(cookie);

    System.out.println(result);
  }
}

class Solution {
  private int max = 0;
  private int result = 0;
  private int n;
  public int solution(int[] cookie) {
    init(cookie);
    for (int i = 1; i < cookie.length; i++) {
      find(cookie, cookie[i - 1], cookie[i], i - 1, i);
      if (result == max) {
        break;
      }
    }
    return result;
  }

  private void init(int[] cookie) {
    n = cookie.length;
    for (int c : cookie) {
      max += c;
    }
    max /= 2;
  }

  private void find(int[] cookie, int lv, int rv, int left, int right) {
    while (-1 < left && right < n) {
      if (lv == rv) {
        result = Math.max(result, lv);
        left--;
        if (-1 < left) {
          lv += cookie[left];
        }
      } else if(lv < rv) {
        left--;
        if (-1 < left) {
          lv += cookie[left];
        }
      } else {
        right++;
        if (right < n) {
          rv += cookie[right];
        }
      }
    }
  }
}