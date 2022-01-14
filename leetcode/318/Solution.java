class Solution {
    public int maxProduct(String[] words) {
        int N = words.length;
        int i = 0;
        int[] bits = new int[N];
        for(String word : words) {
            for(char w : word.toCharArray()) bits[i] |= 1 << (w - 'a');
            i++;
        }

        int result = 0;
        for(i = 0; i < N - 1; i++) {
            for(int j = i + 1; j < N; j++) {
                if((bits[i] & bits[j]) == 0) result = Math.max(result, words[i].length() * words[j].length());
            }
        }
        return result;
    }
}