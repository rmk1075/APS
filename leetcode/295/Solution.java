class Node {
    int value;
    Node prev;
    Node next;

    public Node(int value, Node prev, Node next) {
        this.value = value;
        this.prev = prev;
        this.next = next;
    }
}

class MedianFinder {

    int length = 0;
    Node head = null;
    Node median = null;

    public MedianFinder() {}
    
    public void addNum(int num) {
        if(this.length == 0) {
            this.head = new Node(num, null, null);
            this.median = head;
        } else {
            if(num < this.median.value) {
                this.insert(num, this.head);
                if(this.length % 2 != 0) this.median = this.median.prev;
            } else {
                this.insert(num, this.median);
                if(this.length % 2 == 0) this.median = this.median.next;
            }
        }
        this.length++;
    }
    
    public double findMedian() {
        return length % 2 == 0 ? (double)(this.median.value + this.median.next.value) / 2 : this.median.value;
    }

    private void insert(int num, Node src) {
        Node prev = src.prev;
        Node current = src;
        while(current != null && current.value <= num) {
            prev = current;
            current = current.next;
        }

        if(prev == null) {
            Node node = new Node(num, null, this.head);
            this.head.prev = node;
            this.head = node;
            return ;
        }

        Node node = new Node(num, prev, prev.next);
        prev.next = node;
        if(current != null) current.prev = node;
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */