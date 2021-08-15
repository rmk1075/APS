import java.util.LinkedList;
import java.util.List;

class MyStack {

    private List<Integer> stack;

    /** Initialize your data structure here. */
    public MyStack() {
        this.stack = new LinkedList<>();
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        stack.add(x);
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return stack.remove(stack.size() - 1);
    }
    
    /** Get the top element. */
    public int top() {
        return stack.get(stack.size() - 1);
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return stack.size() == 0;
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */