import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N, maxVal;
    static String[] exp;
    static boolean[] braces;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        exp = br.readLine().split("");
        maxVal = cal(exp);

        braces = new boolean[N/2];
        find(0);

        System.out.println(maxVal);
    }

    public static void find(int index) {
        if(index == N/2) {
            maxVal = Math.max(maxVal, cal());
            return ;
        }

        find(index+1);

        if(check(index)) {
            braces[index] = true;
            find(index+1);
            braces[index] = false;
        }
    }

    public static boolean check(int index) {

        if(-1 < index-1 && braces[index-1]) return false;
        if(index+1 < N/2 && braces[index+1]) return true;

        return true;
    }

    public static int cal() {
        String[] temp = new String[N];
        for(int i = 0; i < N; i++) {
            if(i%2 == 0 || (i%2 == 1 && !braces[i/2])) {
                temp[i] = String.valueOf(exp[i]);
                continue;
            }

            int tmp = Integer.parseInt(exp[i-1]);
            if(exp[i].equals("+")) {
                tmp += Integer.parseInt(exp[++i]);
            } else if(exp[i].equals("-")) {
                tmp -= Integer.parseInt(exp[++i]);
            } else if(exp[i].equals("*")) {
                tmp *= Integer.parseInt(exp[++i]);
            }

            temp[i-2] = String.valueOf(tmp);
            temp[i-1] = "+";
            temp[i] = "0";
        }

        return cal(temp);
    }

    public static int cal(String[] arr) {
        int result = Integer.parseInt(arr[0]);
        for(int i = 1; i < arr.length; i++) {
            if(arr[i].equals("+")) {
                result += Integer.parseInt(arr[++i]);
            } else if(arr[i].equals("-")) {
                result -= Integer.parseInt(arr[++i]);
            } else if(arr[i].equals("*")) {
                result *= Integer.parseInt(arr[++i]);
            }
        }

        return result;
    }
}


// import java.io.BufferedReader;
// import java.io.InputStreamReader;
// import java.util.LinkedList;
// import java.util.Deque;

// import java.io.IOException;

// public class Main {
//     static int N, maxVal;
//     static String[] exp;
//     static int[] mask;
//     // static boolean[] visited;
//     public static void main(String[] args) throws IOException {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

//         N = Integer.parseInt(br.readLine());

//         exp = br.readLine().split("");

//         mask = new int[N / 2];
//         // TODO: memory error
//         // visited = new boolean[(int)Math.pow(10, mask.length)];
//         // visited[0] = true;
//         maxVal = returnVal(exp.clone(), mask);

//         find(mask.clone(), 0);

//         br.close();

//         System.out.println(maxVal);
//     }
    
//     public static void find(int[] bitMask, int count) {
//         if(count == bitMask.length) {
//             maxVal = Math.max(maxVal, returnVal(exp.clone(), bitMask.clone()));
//             return ;
//         }

//         find(bitMask.clone(), count+1);
//         if(count != 0 && bitMask[count-1] == 1) return;

//         bitMask[count] = 1;
//         find(bitMask.clone(), count+1);
//     }

//     public static int returnVal(String[] e, int[] check) {
//         Deque<String> q = new LinkedList<String>();

//         int temp = 0;
//         for(int i = 0; i < e.length; i++) {
//             if((e[i].equals("+") || e[i].equals("-") || e[i].equals("*")) && check[temp++] == 1) {
//                 q.offer(cal(q.pollLast(), e[i], e[++i]));
//             } else
//                 q.offer(e[i]);
//         }

//         String result = q.poll();
//         while(!q.isEmpty()) {
//             result = cal(result, q.poll(), q.poll());
//         }

//         return Integer.parseInt(result);
//     }

//     public static String cal(String num1, String op, String num2) {

//         int result = 0;
//         switch(op) {
//             case "+":
//                 result = Integer.parseInt(num1) + Integer.parseInt(num2);
//                 break;
//             case "*":
//                 result = Integer.parseInt(num1) * Integer.parseInt(num2);
//                 break;
//             case "-":
//                 result = Integer.parseInt(num1) - Integer.parseInt(num2);
//                 break;
//         }

//         return String.valueOf(result);
//     }
// }