import java.util.LinkedList;
import java.util.Queue;

class Location {
  int x;
  int y;
  StringBuffer sb;

  public Location(int x, int y, StringBuffer sb) {
    this.x = x;
    this.y = y;
    this.sb = sb;
  }
}

class Solution {
  private int[] dx = {1, 0, 0, -1};
  private int[] dy = {0, -1, 1, 0};
  private char[] dc = {'d', 'l', 'r', 'u'};
  public String solution(int n, int m, int x, int y, int r, int c, int k) {
    Queue<Location> queue = new LinkedList<>();
    queue.offer(new Location(x, y, new StringBuffer()));
    while(!queue.isEmpty() && 1 < k--) {
      int size = queue.size();
      while(0 < size--) {
        Location location = queue.poll();
        int cx = location.x;
        int cy = location.y;
        for(int d = 0; d < 4; d++) {
          int nx = cx + dx[d];
          int ny = cy + dy[d];
          if(nx == 0 || ny == 0 || n < nx || m < ny || k < (Math.abs(nx - r) + Math.abs(ny - c))) continue;
          else {
            queue.offer(new Location(nx, ny, new StringBuffer(location.sb.toString() + dc[d])));
            break;
          }
        }
      }
    }

    String answer = null;
    if(k == 0) {
      while(!queue.isEmpty()) {
        Location location = queue.poll();
        int cx = location.x;
        int cy = location.y;
        for(int d = 0; d < 4; d++) {
          int nx = cx + dx[d];
          int ny = cy + dy[d];

          if(nx != r || ny != c) continue;
          if(answer == null) answer = location.sb.toString() + dc[d];
          else {
            String temp = location.sb.toString() + dc[d];
            if(0 < answer.compareTo(temp)) answer = temp;
          }
        }
      }
    }
    return answer != null ? answer : "impossible";
  }
}