public class Main {
    public static void main(String[] args) {
        
    }
}

class Solution {
    public int largestAltitude(int[] gain) {
        int largest = 0;
        int altitude = 0;
        for (int g : gain) {
            altitude += g;
            largest = Math.max(largest, altitude);
        }
        return largest;
    }
}