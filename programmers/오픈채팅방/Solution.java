import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public String[] solution(String[] record) {
        String[] answer = {};

        int n = record.length;
        Map<String, Integer> idMap = new HashMap<>();       // ID : idx
        Map<Integer, String> nickMap = new HashMap<>();     // idx : nickname
        int[][] actionList = new int[n][2];                 // idx : action (0: enter, 1: leave)

        int index = 0, actionIdx = 0;
        for(String r : record) {
            String[] line = r.split(" ");
            String id = line[1];
            String nick = "";
            if(line.length == 3) nick = line[2];

            switch (line[0]) {
                case "Enter":
                    if(idMap.containsKey(id)) {
                        int temp = idMap.get(id);
                        nickMap.put(temp, nick);
                        actionList[actionIdx][0] = temp;
                        actionList[actionIdx++][1] = 0;
                    } else {
                        idMap.put(id, index);
                        nickMap.put(index, nick);
                        actionList[actionIdx][0] = index++;
                        actionList[actionIdx++][1] = 0;
                    }
                    break;

                case "Leave":
                    actionList[actionIdx][0] = idMap.get(id);
                    actionList[actionIdx++][1] = 1;
                    break;
            
                default:
                    nickMap.put(idMap.get(id), nick);
                    break;
            }
        }

        answer = new String[actionIdx];
        for(int i = 0; i < actionIdx; i++) {
            answer[i] = nickMap.get(actionList[i][0]) + (actionList[i][1] == 0 ? "님이 들어왔습니다." : "님이 나갔습니다.");
        }

        return answer;
    }
}