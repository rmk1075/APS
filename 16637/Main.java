import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Deque;

import java.io.IOException;

public class Main {
    static int N, maxVal;
    static String[] exp;
    static int[] mask;
    // static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        exp = br.readLine().split("");

        mask = new int[N / 2];
        // TODO: memory error
        // visited = new boolean[(int)Math.pow(10, mask.length)];
        // visited[0] = true;
        maxVal = returnVal(exp.clone(), mask);

        find(mask.clone(), 0);

        br.close();

        System.out.println(maxVal);
    }
    
    public static void find(int[] bitMask, int count) {
        if(count == bitMask.length) {
            maxVal = Math.max(maxVal, returnVal(exp.clone(), bitMask.clone()));
            return ;
        }

        find(bitMask.clone(), count+1);
        if(count != 0 && bitMask[count-1] == 1) return;

        bitMask[count] = 1;
        find(bitMask.clone(), count+1);
    }

    public static int returnVal(String[] e, int[] check) {
        Deque<String> q = new LinkedList<String>();

        int temp = 0;
        for(int i = 0; i < e.length; i++) {
            if((e[i].equals("+") || e[i].equals("-") || e[i].equals("*")) && check[temp++] == 1) {
                q.offer(cal(q.pollLast(), e[i], e[++i]));
            } else
                q.offer(e[i]);
        }

        String result = q.poll();
        while(!q.isEmpty()) {
            result = cal(result, q.poll(), q.poll());
        }

        return Integer.parseInt(result);
    }

    public static String cal(String num1, String op, String num2) {

        int result = 0;
        switch(op) {
            case "+":
                result = Integer.parseInt(num1) + Integer.parseInt(num2);
                break;
            case "*":
                result = Integer.parseInt(num1) * Integer.parseInt(num2);
                break;
            case "-":
                result = Integer.parseInt(num1) - Integer.parseInt(num2);
                break;
        }

        return String.valueOf(result);
    }
}