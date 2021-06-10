class MinStack {

    class Node {
        int val;
        Node past;

        public Node(int val, Node past) {
            this.val = val;
            this.past = past;
        }
    }

    Node top;

    int N;

    /** initialize your data structure here. */
    public MinStack() {
        N = 0;
    }
    
    public void push(int val) {
        if(N == 0) {
            top = new Node(val, null);
        } else {
            Node newNode = new Node(val, top);
            top = newNode;
        }
        N++;
    }
    
    public void pop() {
        top = top.past;
        N--;
    }
    
    public int top() {
        return top.val;
    }
    
    public int getMin() {
        Node node = top;
        int minVal = top.val;
        while(node.past != null) {
            node = node.past;
            minVal = Math.min(minVal, node.val);
        }
        return minVal;
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