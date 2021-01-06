import java.util.ArrayList;
import java.util.List;

class Solution {
    StringBuilder sb, temp;
    List<String> list = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        sb = new StringBuilder();
        temp = new StringBuilder();

        sb.append("(");
        dfs(n, 1, 1, 0);
        return list;
    }

    public void dfs(int n, int cnt, int left, int right) {
        if (cnt == 2 * n) {
            list.add(sb.toString());
            return;
        }

        if (left < n) {
            sb.append("(");
            dfs(n, cnt + 1, left + 1, right);
            sb.deleteCharAt(cnt);

            if (left == right)
                return;

            sb.append(")");
            dfs(n, cnt + 1, left, right + 1);
            sb.deleteCharAt(cnt);
        } else {
            temp.setLength(0);
            while (right++ < n)
                temp.append(")");
            list.add(sb.toString() + temp.toString());
        }
    }
}