import java.util.HashMap;
import java.util.Map;

class LRUCache {

    private int[][] cache;

    private int[] count;

    private int cnt;

    private int capacity;

    private int size;

    private Map<Integer, Integer> map;

    public LRUCache(int capacity) {
        this.cache = new int[capacity][2];
        this.count = new int[capacity];
        this.cnt = 0;
        this.capacity = capacity;
        this.size = 0;
        this.map = new HashMap<>();
    }
    
    public int get(int key) {
        if(this.capacity == 0 || this.size == 0 || !this.map.containsKey(key))
            return -1;

        int index = this.map.get(key);
        this.count[index] = this.cnt++;
        return this.cache[index][1];
    }
    
    public void put(int key, int value) {
        Integer index = map.get(key);
        if(index != null) {
            this.map.put(key, index);
            this.cache[index][1] = value;
            this.count[index] = this.cnt++;
            return ;
        }

        if(this.capacity <= this.size) {
            int minCnt = Integer.MAX_VALUE, minIdx = -1;
            for(int i = 0; i < capacity; i++) {
                if(this.count[i] < minCnt) {
                    minCnt = this.count[i];
                    minIdx = i;
                }
            }

            index = minIdx;
            this.map.remove(this.cache[index][0]);
        } else {
            index = this.size;
            this.size++;
        }

        this.map.put(key, index);
        this.cache[index][0] = key;
        this.cache[index][1] = value;
        this.count[index] = this.cnt++;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */