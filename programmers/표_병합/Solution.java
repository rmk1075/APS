import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class Solution {
  int[][] table;
  String[] values;
  private final String EMPTY = "EMPTY";
  public String[] solution(String[] commands) {
    initialize();
    List<String> answer = new LinkedList<>();
    for(String command : commands) {
      String[] commandArr = command.split(" ");
      switch (commandArr[0]) {
        case "UPDATE":
          update(commandArr);
          break;
        case "MERGE":
          merge(commandArr);
          break;
        case "UNMERGE":
          unmerge(commandArr);
          break;
        case "PRINT":
          print(commandArr, answer);
          break;
        default:
          break;
      }
    }

    return answer.toArray(new String[answer.size()]);
  }

  private void initialize() {
    values = new String[2501];
    Arrays.fill(values, EMPTY);
    
    int index = 1;
    table = new int[51][51];
    for(int i = 1; i < 51; i++) {
      for(int j = 1; j < 51; j++) {
        table[i][j] = index++;
      }
    }
  }

  private void update(String[] commandArr) {
    if(commandArr.length == 3) {
      for(int i = 1; i < 2501; i++) {
        if(values[i].equals(commandArr[1])) values[i] = commandArr[2];
      }
    } else {
      values[table[Integer.parseInt(commandArr[1])][Integer.parseInt(commandArr[2])]] = commandArr[3];
    }
  }

  private void merge(String[] commandArr) {
    int r1 = Integer.parseInt(commandArr[1]);
    int c1 = Integer.parseInt(commandArr[2]);
    int r2 = Integer.parseInt(commandArr[3]);
    int c2 = Integer.parseInt(commandArr[4]);
    int i1 = table[r1][c1];
    int i2 = table[r2][c2];
    if(i1 == i2) return;
    String v1 = values[i1];
    String v2 = values[i2];
    if(v1.equals(EMPTY) && !v2.equals(EMPTY)) {
      values[i1] = v2;
    }
    values[i2] = EMPTY;

    for(int i = 1; i < 51; i++) {
      for(int j = 1; j < 51; j++) {
        if(table[i][j] == i2) {
          table[i][j] = i1;
        }
      }
    }
  }

  private void unmerge(String[] commandArr) {
    int r = Integer.parseInt(commandArr[1]);
    int c = Integer.parseInt(commandArr[2]);
    int idx = table[r][c];
    String v = values[idx];
    values[idx] = EMPTY;
    int cnt = 1;
    for(int i = 1; i < 51; i++) {
      for(int j = 1; j < 51; j++) {
        if(table[i][j] == idx) {
          table[i][j] = cnt;
          values[cnt] = EMPTY;
        }
        cnt++;
      }
    }
    values[table[r][c]] = v;
  }

  private void print(String[] commandArr, List<String> answer) {
    int r = Integer.parseInt(commandArr[1]);
    int c = Integer.parseInt(commandArr[2]);
    answer.add(values[table[r][c]]);
  }
}