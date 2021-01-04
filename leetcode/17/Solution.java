import java.util.ArrayList;
import java.util.List;

class Solution {
    StringBuilder sb = new StringBuilder();
    List<String> result = new ArrayList<>();
    char[][] alphabets = new char[][] { {}, {}, { 'a', 'b', 'c' }, { 'd', 'e', 'f' }, { 'g', 'h', 'i' },
            { 'j', 'k', 'l' }, { 'm', 'n', 'o' }, { 'p', 'q', 'r', 's' }, { 't', 'u', 'v' }, { 'w', 'x', 'y', 'z' } };

    public List<String> letterCombinations(String digits) {
        char[] digitsArr = digits.toCharArray();
        int N = digitsArr.length;

        if (N == 0)
            return result;

        for (char ch : alphabets[digitsArr[0] - '0']) {
            sb.append(ch);
            dfs(digitsArr, N, 1);
            sb.deleteCharAt(0);
        }

        return result;
    }

    public void dfs(char[] digitsArr, int N, int cnt) {
        if (cnt == N) {
            result.add(sb.toString());
            return;
        }

        for (char ch : alphabets[digitsArr[cnt] - '0']) {
            sb.append(ch);
            dfs(digitsArr, N, cnt + 1);
            sb.deleteCharAt(cnt);
        }
    }
}