import java.util.ArrayList;
import java.util.List;

class WordDictionary {
    private char value;
    private List<WordDictionary> dictionary;

    /** Initialize your data structure here. */
    public WordDictionary() {
        this('/');
    }

    public WordDictionary(char value) {
        this.value = value;
        this.dictionary = new ArrayList<>();
    }
    
    public void addWord(String word) {
        List<WordDictionary> dict = this.dictionary;
        char[] wordToChar = word.toCharArray();
        for(char ch : wordToChar) {
            boolean isMatch = false;
            for(WordDictionary d : dict) {
                if(d.value == ch) {
                    dict = d.dictionary;
                    isMatch = true;
                    break;
                }
            }

            if(isMatch) continue;
            
            WordDictionary newDict = new WordDictionary(ch);
            dict.add(newDict);
            dict = newDict.dictionary;
        }

        dict.add(new WordDictionary('/'));
    }
    
    public boolean search(String word) {
        return searchByChar(this.dictionary, word.toCharArray(), 0, word.length());
    }

    private boolean searchByChar(List<WordDictionary> dictionary, char[] wordToChar, int index, int len) {
        if(index == len) return searchEnd(dictionary);

        char target = wordToChar[index];
        for(WordDictionary dict : dictionary) {
            if(target == '.' || dict.value == target) {
                if(searchByChar(dict.dictionary, wordToChar, index + 1, len)) return true;
            }
        }

        return false;
    }

    private boolean searchEnd(List<WordDictionary> dictionary) {
        for(WordDictionary dict : dictionary) {
            if(dict.value == '/') return true;
        }

        return false;
    }
}