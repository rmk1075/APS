import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

class Word {
    int rule;
    int start;
    int end;

    public Word(int rule, int start, int end) {
        this.rule = rule;
        this.start = start;
        this.end = end;
    }
}

class Solution {
    public String solution(String sentence) {
        char[] chs = sentence.toCharArray();
        LinkedHashMap<Character, List<Integer>> map = getChsMap(chs);

        List<Word> words = new ArrayList<>();
        return parse(chs.length, map, words, sentence).trim();
    }

    public LinkedHashMap<Character, List<Integer>> getChsMap(char[] chs) {
        LinkedHashMap<Character, List<Integer>> map = new LinkedHashMap<>();
        for(int i = 0; i < chs.length; i++) {
            char ch = chs[i];
            if(Character.isLowerCase(ch)) {
                if(map.containsKey(ch)) map.get(ch).add(i);
                else map.put(ch, new LinkedList<>(Arrays.asList(i)));
            }
        }
        return map;
    }

    public String parse(int length, LinkedHashMap<Character, List<Integer>> map, List<Word> words, String sentence) {
        StringBuffer sb = new StringBuffer();
        int N = sentence.length();
        int startCh, endCh, size, stringIdx = 0;
        int lastStartCh = -1, lastEndCh = -1;
        int startWord = 0, endWord = 0;
        int lastStartWord = -1, lastEndWord = -1;
        int rule = 0;
        try {
            for(List<Integer> list : map.values()) {
                size = list.size();
                startCh = list.get(0);
                endCh = list.get(size - 1);
                if(size == 1 || 2 < size) {
                    for(int i = 1; i < size; i++) {
                        if(list.get(i) - list.get(i - 1) != 2) return "invalid";
                    }
                    rule = 1;
                } else if(size == 2) {
                    int distance = endCh - startCh;
                    if(distance == 2 && (lastStartCh < startCh && endCh < lastEndCh)) rule = 1;
                    else if(2 <= distance) rule = 2;
                    else return "invalid";
                }

                if(rule == 1) {
                    startWord = startCh - 1;
                    endWord = endCh + 1;
                    if(lastStartWord < startWord && endWord < lastEndWord) {
                        if(startCh - lastStartCh != 2 || lastEndCh - endCh != 2) return "invalid";
                        else continue;
                    }
                } else if(rule == 2) {
                    startWord = startCh;
                    endWord = endCh;
                    if(lastStartWord < startWord && endWord < lastEndWord) return "invalid";
                }
    
                if(startWord <= lastEndWord) return "invalid";
                if(stringIdx < startWord) {
                    sb.append(getWord(sentence, stringIdx, startWord - 1));
                    sb.append(" ");
                }
                sb.append(getWord(sentence, startWord, endWord));
                sb.append(" ");
                lastStartCh = startCh;
                lastEndCh = endCh;
                lastStartWord = startWord;
                lastEndWord = endWord;
                stringIdx = endWord + 1;
            }
            if(stringIdx < N) sb.append(getWord(sentence, stringIdx, N - 1));
        } catch(Exception e) {
            System.out.println(e);
            return "invalid";
        }
        return sb.toString();
    }

    public String getWord(String sentence, int start, int end) {
        return sentence.substring(start, end + 1).replaceAll("[a-z]", "");
    }
}