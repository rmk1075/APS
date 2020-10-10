import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    int visited = 0, answer = 0;;
    char[] friends = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'}, line = {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'};
    char[][] rules;

    Map<Character, Integer> map = new HashMap<>();

    public int solution(int n, String[] data) {
        
        for(int i = 0; i < 8; i++) {
            map.put(friends[i], i);
        }

        rules = new char[n][];
        for(int i = 0; i < n; i++) {
            rules[i] = data[i].toCharArray();
            rules[i][0] = (char)('0' + map.get(rules[i][0]));
            rules[i][2] = (char)('0' + map.get(rules[i][2]));
        }

        for(int i = 0; i < 8; i++) {
            visited |= (1 << i);
            line[0] = friends[i];
            bfs(1);
            line[0] = 'X';
            visited &= ~(1 << i);
        }

        return answer;
    }

    public void bfs(int index) {
        int result = 0;
        if(check(line)) {
            if(index == 8) {
                answer++;
                return ;
            }

            for(int i = 0; i < 8; i++) {
                if((visited & (1 << i)) == 0) {
                    visited |= (1 << i);
                    line[index] = friends[i];
                    bfs(index + 1);
                    line[index] = 'X';
                    visited &= ~(1 << i);
                }
            }

        }
    }

    public boolean check(char[] line) {
        int[] mem = {-1, -1};
        for(char[] rule : rules) {
            int a = rule[0] - '0', b = rule[2] - '0';
            if((visited & (1 << a)) == 0 || (visited & (1 << b)) == 0) continue;

            int idx = 0;
            for(int i = 0; i < 8; i++) {
                if(line[i] == friends[a] || line[i] == friends[b]) {
                    mem[idx++] = i;
                }

                if(idx == 2) break;
            }
            
            switch (rule[3]) {
                case '=':
                    if(rule[4] - '0' != Math.abs(mem[0] - mem[1]) - 1) return false;
                    break;
                
                case '<':
                    if(rule[4] - '0' <= Math.abs(mem[0] - mem[1]) - 1) return false;
                    break;
            
                default:
                    if(rule[4] - '0' >= Math.abs(mem[0] - mem[1]) - 1) return false;
                    break;
            }
        }

        return true;
    }
}