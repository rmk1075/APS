import java.util.HashMap;
import java.util.Map;

class Trie {
    int cnt;
    Map<Character, Trie> trie;

    Trie() {
        cnt = 0;
        trie = new HashMap<>();
    }
}

class Solution {
    Map<Character, Trie> root = new HashMap<>();

    public int solution(String[] words) {
        int answer = 0;

        for (String word : words) {
            Trie current = new Trie();
            current.trie = root;

            for (char ch : word.toCharArray()) {
                if (!current.trie.containsKey(ch))
                    current.trie.put(ch, new Trie());

                current = current.trie.get(ch);
                current.cnt++;
            }
        }

        for (String word : words) {
            Trie current = new Trie();
            current.trie = root;

            for (char ch : word.toCharArray()) {
                current = current.trie.get(ch);
                answer++;
                if (current.cnt == 1)
                    break;
            }
        }

        return answer;
    }
}