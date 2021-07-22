import java.util.*;

class Solution {
    public boolean isHappy(int n) {
        List<Integer> list = new ArrayList<>();
        while(n != 1 && !list.contains(n)) {
            list.add(n);
            int sum = 0;
            while(0 < n) {
                sum += Math.pow(n % 10, 2);
                n /= 10;
            }
            n = sum;
        }
        return n == 1;
    }
}