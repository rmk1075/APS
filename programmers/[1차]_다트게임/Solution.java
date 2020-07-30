class Solution {
    public int solution(String dartResult) {
        int answer = 0;

        char result[] = dartResult.toCharArray();
        int val = 0, idx = 0, arr[] = new int[result.length];
        for (int i = 0; i < result.length; i++) {
            char ch = result[i];
            switch (ch) {
                case '*':
                    if (idx == 1) {
                        int temp1 = arr[--idx];
                        arr[idx++] = temp1 * 2;
                    } else {
                        int temp1 = arr[--idx];
                        int temp2 = arr[--idx];
                        arr[idx++] = temp2 * 2;
                        arr[idx++] = temp1 * 2;
                    }
                    break;
                case '#':
                    int temp = arr[--idx];
                    arr[idx++] = temp * -1;
                    break;
                case 'S':
                    arr[idx++] = val;
                    break;
                case 'D':
                    arr[idx++] = val * val;
                    break;
                case 'T':
                    arr[idx++] = val * val * val;
                    break;
                default:
                    if (ch == '1' && result[i + 1] == '0') {
                        val = 10;
                        i++;
                    } else {
                        val = ch - '0';
                    }
                    break;
            }
        }

        for (int i = 0; i < idx; i++) {
            answer += arr[i];
        }

        return answer;
    }
}

// import java.util.Stack;

// class Solution {
//     public int solution(String dartResult) {
//         int answer = 0;

//         int val = 0;
//         char result[] = dartResult.toCharArray();
//         Stack<Integer> stack = new Stack<>();
//         for (int i = 0; i < result.length; i++) {
//             char ch = result[i];

//             switch (ch) {
//                 case '*':
//                     if (stack.size() == 1) {
//                         int temp1 = stack.pop();
//                         stack.push(temp1 * 2);
//                     } else {
//                         int temp1 = stack.pop();
//                         int temp2 = stack.pop();
//                         stack.push(temp2 * 2);
//                         stack.push(temp1 * 2);
//                     }
//                     break;
//                 case '#':
//                     int temp = stack.pop();
//                     stack.push(temp * -1);
//                     break;
//                 case 'S':
//                     stack.push(val);
//                     break;
//                 case 'D':
//                     stack.push(val * val);
//                     break;
//                 case 'T':
//                     stack.push(val * val * val);
//                     break;
//                 default:
//                     if (ch == '1' && result[i + 1] == '0') {
//                         val = 10;
//                         i++;
//                     } else {
//                         val = ch - '0';
//                     }
//                     break;
//             }
//         }

//         while (!stack.isEmpty()) {
//             int tmp = stack.pop();
//             System.out.println(tmp);
//             answer += tmp;
//         }

//         return answer;
//     }
// }