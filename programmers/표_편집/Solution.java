import java.util.Stack;

class Solution {
    public String solution(int n, int k, String[] cmd) {
        String answer = "";

        int[] pre = new int[n];
        int[] post = new int[n];
        for(int i = 0; i < n; i++) {
            pre[i] = i - 1;
            post[i] = i + 1;
        }
        post[n - 1] = -1;

        Stack<int[]> stack = new Stack<>();
        StringBuffer sb = new StringBuffer("O".repeat(n));
        for(String c : cmd) {
            String[] parsed = c.split(" ");
            switch(parsed[0]) {
                case "U":
                    {
                        int temp = Integer.parseInt(parsed[1]);
                        while(0 < temp--) k = pre[k];
                    }
                    break;
                case "D":
                    {
                        int temp = Integer.parseInt(parsed[1]);
                        while(0 < temp--) k = post[k];
                    }
                    break;
                case "C":
                    {
                        stack.push(new int[]{pre[k], k, post[k]});
                        if(pre[k] != -1) post[pre[k]] = post[k];
                        if(post[k] != -1) pre[post[k]] = pre[k];
                        sb.setCharAt(k, 'X');

                        k = post[k] == -1 ? pre[k] : post[k];
                    }
                    break;
                case "Z":
                    {
                        int[] value = stack.pop();
                        if(value[0] != -1) post[value[0]] = value[1];
                        if(value[2] != -1) pre[value[2]] = value[1];
                        sb.setCharAt(value[1], 'O');
                    }
            }
        }

        answer = sb.toString();
        return answer;
    }
}