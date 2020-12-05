class Solution {
    public int maxArea(int[] height) {
        int max = 0;
        int length = height.length;

        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                int h = (height[i] > height[j]) ? height[j] : height[i];
                int w = j - i;
                if (max < h * w)
                    max = h * w;
            }
        }

        return max;
    }
}