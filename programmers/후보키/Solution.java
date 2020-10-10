import java.util.HashSet;
import java.util.Set;

class Solution {
    int row, col, candidate = 0;
    Set<Integer> keys = new HashSet<>();
    Set<String> cmpSet = new HashSet<>();
    public int solution(String[][] relation) {
        int answer = 0;

        row = relation.length;
        col = relation[0].length;
        for(int i = 1; i < col + 1; i++) {
            bfs(0, i, relation);
        }

        answer = keys.size();
        return answer;
    }

    public void bfs(int selected, int limit, String[][] relation) {

        if(selected == limit) {
            judge(relation);
            return ;
        }

        for(int i = 0; i < col; i++) {
            if((candidate & (1 << i)) != 0 || keys.contains(1 << i)) continue;

            candidate |= (1 << i);

            if(already()) {
                candidate &= ~(1 << i);
                continue;
            }

            bfs(selected + 1, limit, relation);
            candidate &= ~(1 << i);
        }
    }

    public boolean already() {
        for(int key : keys) {
            if((key <= candidate) && ((candidate & key) == key)) return true;
        }

        return false;
    }

    public void judge(String[][] relation) {

        cmpSet.clear();
        for(int i = 0; i < row; i++) {
            String key = "";

            for(int j = 0; j < col; j++) {
                if((candidate & (1 << j)) == 0) continue;
                key += "|" + relation[i][j];
            }
            if(cmpSet.contains(key)) return;
            cmpSet.add(key);
        }

        keys.add(candidate);
    }
}