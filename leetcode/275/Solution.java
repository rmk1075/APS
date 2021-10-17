class Solution {
    public int hIndex(int[] citations) {
        int N = citations.length;
        int[] counts = new int[N + 1];
        for(int citation : citations) {
            if(N < citation) counts[N]++;
            else counts[citation]++;
        }

        int sum = 0;
        for(int i = N; -1 < i; i--) {
            sum += counts[i];
            if(i <= sum) return i;
        }
        return 0;
    }
}
