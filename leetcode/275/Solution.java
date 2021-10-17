class Solution {
    public int hIndex(int[] citations) {
        int N = citations.length;
        int left = 0;
        int right = N - 1;
        while(left <= right) {
            int mid = (left + right) >> 1;
            if(N - mid == citations[mid]) return N - mid;
            if(N - mid < citations[mid]) right = mid - 1;
            else left = mid + 1;
        }
        return N - left;
    }
}
