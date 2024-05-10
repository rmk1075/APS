import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    Solution solution = new Solution();

    int k = 3;
    int n = 5;
    int[][] reqs = new int[][]{{10, 60, 1}, {15, 100, 3}, {20, 30, 1}, {30, 50, 3}, {50, 40, 1}, {60, 30, 2}, {65, 30, 1}, {70, 100, 2}};
    int result = solution.solution(k, n, reqs);
    System.out.println(result);
  }
}

class Solution {
  public int solution(int k, int n, int[][] reqs) {
    List<int[]>[] classified = classifyReqs(k, reqs);
    int[][] timeTable = makeTimeTable(k, n, classified);
    int[] numOfMentors = assignMentors(k, n, timeTable);

    int answer = 0;
    for (int i = 0; i < numOfMentors.length; i++) {
      answer += timeTable[i][numOfMentors[i]];
    }
    return answer;
  }

  private List<int[]>[] classifyReqs(int k, int[][] reqs) {
    List<int[]>[] list = new List[k];
    for (int i = 0; i < k; i++) {
      list[i] = new LinkedList<>();
    }

    for (int[] req : reqs) {
      list[req[2] - 1].add(new int[]{req[0], req[1]});
    }
    return list;
  }

  private int[][] makeTimeTable(int k, int n, List<int[]>[] classified) {
    int[][] timeTable = new int[k][n + 1];
    int maxMentors = n - k + 1;
    for (int i = 0; i < k; i++) {
      timeTable[i] = calculateTimeTable(maxMentors, classified[i]);
    }
    return timeTable;
  }

  private int[] calculateTimeTable(int n, List<int[]> reqs) {
    int[] times = new int[n + 1];
    for (int i = 1; i < n + 1; i++) {
      times[i] = calculateTime(i, reqs);
    }
    return times;
  }

  private int calculateTime(int n, List<int[]> reqs) {
    int[] mentors = new int[n];
    int waitTime = 0;
    for (int[] req : reqs) {
      int startTime = req[0];
      int mentor = findMentor(startTime, mentors);
      waitTime += Math.max(0, mentors[mentor] - startTime);
      mentors[mentor] = Math.max(mentors[mentor], startTime) + req[1];
    }
    return waitTime;
  }

  private int findMentor(int startTime, int[] mentors) {
    int mentor = -1;
    int time = Integer.MAX_VALUE;
    for (int i = 0; i < mentors.length; i++) {
      if (time <= mentors[i]) {
        continue;
      }

      mentor = i;
      time = mentors[i];
    }
    return mentor;
  }

  private int[] assignMentors(int k, int n, int[][] timeTable) {
    int[] numOfMentors = new int[k];
    Arrays.fill(numOfMentors, 1);
    int maxMentors = n - k;
    while (0 < maxMentors--) {
      int maxReduce = 0;
      int type = -1;
      for (int i = 0; i < k; i++) {
        int numOfMentor = numOfMentors[i];
        int reduce = timeTable[i][numOfMentor] - timeTable[i][numOfMentor + 1];
        if (maxReduce <= reduce) {
          maxReduce = reduce;
          type = i;
        }
      }
      numOfMentors[type]++;
    }
    return numOfMentors;
  }
}