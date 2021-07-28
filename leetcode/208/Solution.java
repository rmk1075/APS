import java.util.ArrayList;
import java.util.List;

class Trie {
    private String word;

    private List<Trie> trie;

    /** Initialize your data structure here. */
    public Trie() {
        this.trie = new ArrayList<>();
    }

    public Trie(String word) {
        this.trie = new ArrayList<>();
        this.word = word;
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        int len = word.length();
        for(Trie t : trie) {
            if(t.word.length() < len) continue;
            if(t.word.equals(word)) return ;
            if(word.startsWith(t.word)) {
                t.insert(word);
                return ;
            }
        }
        trie.add(new Trie(word));
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        int len = word.length();
        for(Trie t : trie) {
            if(t.word.length() < len) continue;
            if(t.word.equals(word)) return true;
            if(word.startsWith(t.word)) {
                return t.search(word);
            }
        }
        return false;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        int len = prefix.length();
        for(Trie t : trie) {
            if(t.word.length() < len) continue;
            if(t.word.startsWith(prefix) || t.word.equals(word)) return true;
        }
        return false;
    }
}