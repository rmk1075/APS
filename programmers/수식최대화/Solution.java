import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import javax.xml.stream.events.Characters;

class Solution {
    int N, visited = 0;
    long answer = 0;
    char[] op;

    public long solution(String expression) {

        Set<Character> opSet = new HashSet<>();
        Queue<Long> operands = new LinkedList<>();
        Queue<Character> operators = new LinkedList<>();
        String operand = "";
        for(char ch : expression.toCharArray()) {
            switch (ch) {
                case '+':
                case '-':
                case '*':
                    opSet.add(ch);
                    operators.offer(ch);
                    operands.offer(Long.parseLong(operand));
                    operand = "";
                    break;

                default:
                    operand += ch;
                    break;
            }
        }
        operands.offer(Long.parseLong(operand));

        op = new char[opSet.size()];
        int idx = 0;
        for(char o : opSet) {
            op[idx++] = o;
        }

        N = op.length;
        for(int i = 0; i < N; i++) {
            visited |= (1 << i);
            bfs(op[i], operands, operators);
            visited &= ~(1 << i);
        }

        return answer;
    }

    public void bfs(char target, Queue<Long> operands, Queue<Character> operators) {

        Queue<Long> operands_ = new LinkedList<>();
        Queue<Character> operators_ = new LinkedList<>();

        int size = operators.size();
        long tempNum = operands.poll();
        operands.offer(tempNum);
        while(0 < size--) {

            long num = operands.poll();
            char op = operators.poll();

            operands.offer(num);
            operators.offer(op);

            if(op == target) {
                switch (op) {
                    case '+':
                        tempNum += num;
                        break;
                    
                    case '*':
                        tempNum *= num;
                        break;

                    default:
                        tempNum -= num;
                        break;
                }
            } else {
                operands_.offer(tempNum);
                operators_.offer(op);
                tempNum = num;
            }
        }
        operands_.offer(tempNum);

        if(operators_.isEmpty()) {
            answer = Math.max(answer, Math.abs(operands_.poll()));
            return ;
        }

        for(int i = 0; i < N; i++) {
            if((visited & (1 << i)) != 0) continue;

            visited |= (1 << i);
            bfs(op[i], operands_, operators_);
            visited &= ~(1 << i);
        }
        
    }
}