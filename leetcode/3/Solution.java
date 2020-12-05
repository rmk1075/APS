class Solution {
    public int lengthOfLongestSubstring(String s) {

        char[] input = s.toCharArray();

        int max = 0;

        for (int i = 0; i < input.length; i++) {
            Set<Character> temp = new HashSet<>();
            int count = 0;

            for (int j = i; j < input.length; j++) {
                if (temp.contains(input[j])) {
                    break;
                } else {
                    temp.add(input[j]);
                    count++;
                }
            }

            if (max < count)
                max = count;
        }

        return max;
    }
}