import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    public int solution(int[] arrows) {
        int answer = 0;

        int x = 0;
        int y = 0;
        Map<String, List<String>> map = new HashMap<>();
        map.put("0:0", new ArrayList<>());
        for(int arrow : arrows) {
            int nx = x + dx[arrow];
            int ny = y + dy[arrow];
            
            String src = x + ":" + y;
            String dest = nx + ":" + ny;
            if(map.containsKey(dest)) {
                if(!map.get(dest).contains(src)) {
                    map.get(src).add(dest);
                    map.get(dest).add(src);
                    answer++;
                    if(checkDiagonal(map, x, y, arrow)) answer++;
                }
            } else {
                map.get(src).add(dest);
                map.put(dest, new ArrayList<>(Arrays.asList(src)));
                if(checkDiagonal(map, x, y, arrow)) answer++;
            }

            x = nx;
            y = ny;
        }

        return answer;
    }

    public boolean checkDiagonal(Map<String, List<String>> map, int x, int y, int arrow) {
        int d1 = -1, d2 = -1;
        switch(arrow) {
            case 1:
                d1 = 0;
                d2 = 2;
                break;
            case 3:
                d1 = 2;
                d2 = 4;
                break;
            case 5:
                d1 = 4;
                d2 = 6;
                break;
            case 7:
                d1 = 6;
                d2 = 0;
                break;
            default:
                return false;
        }

        String src = (x + dx[d1]) + ":" + (y + dy[d1]);
        String dest = (x + dx[d2]) + ":" + (y + dy[d2]);
        return map.containsKey(src) && map.get(src).contains(dest);
    }
}