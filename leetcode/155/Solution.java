class MinStack {

    class Node {
        int val;
        int minVal;
        Node past;

        public Node(int val, int minVal, Node past) {
            this.val = val;
            this.minVal = minVal;
            this.past = past;
        }
    }

    Node top;

    /** initialize your data structure here. */
    public MinStack() {

    }
    
    public void push(int val) {
        if(top == null) {
            top = new Node(val, val, null);
        } else {
            Node newNode = new Node(val, Math.min(top.minVal, val), top);
            top = newNode;
        }
    }
    
    public void pop() {
        top = top.past;
    }
    
    public int top() {
        return top.val;
    }
    
    public int getMin() {
        return top.minVal;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */