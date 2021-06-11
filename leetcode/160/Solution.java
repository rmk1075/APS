import java.util.Stack;

public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Stack<ListNode> stackA = new Stack<>();
        ListNode a = headA;
        while(a.next != null) {
            stackA.push(a);
            a = a.next;
        }

        Stack<ListNode> stackB = new Stack<>();
        ListNode b = headB;
        while(b.next != null) {
            stackB.push(b);
            b = b.next;
        }

        ListNode result = null;
        while(a.equals(b)) {
            result = a;

            if(stackA.isEmpty() || stackB.isEmpty()) break;
            a = stackA.pop();
            b = stackB.pop();
        }

        return result;
    }
}