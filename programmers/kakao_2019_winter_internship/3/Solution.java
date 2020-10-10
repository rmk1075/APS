import java.util.HashSet;
import java.util.Set;

class Solution {
    int visited = 0, N, M;
    Set<Integer> set = new HashSet<>();
    public int solution(String[] user_id, String[] banned_id) {
        N = banned_id.length;
        M = user_id.length;
        
        for(int i = 0; i < N; i++) {
            banned_id[i] = banned_id[i].replace("*", ".");
        }

        ban(0, user_id, banned_id);
        return set.size();
    }

    public void ban(int idx, String[] user_id, String[] banned_id) {
        if(idx == N) {
            set.add(visited);
            return ;
        }

        for(int i = 0; i < M; i++) {
            if((visited & (1 << i)) != 0 || !user_id[i].matches(banned_id[idx]))continue;
            visited |= (1 << i);
            ban(idx+1, user_id, banned_id);
            visited &= ~(1 << i);
        }
    }
}