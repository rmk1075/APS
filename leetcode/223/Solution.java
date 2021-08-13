class Solution {
    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        int width = getDistance(ax1, ax2, bx1, bx2);
        int height = getDistance(ay1, ay2, by1, by2);
        return (ax2 - ax1) * (ay2 - ay1) + (bx2 - bx1) * (by2 - by1) - width * height;
    }

    public int getDistance(int a1, int a2, int b1, int b2) {
        if(a1 < b1) {
            if(a2 < b1) return 0;
            if(a2 < b2) return a2 - b1;
            return b2 - b1;
        } else if(a1 < b2) {
            if(a2 < b2) return a2 - a1;
            return b2 - a1;
        }

        return 0;
    }
}