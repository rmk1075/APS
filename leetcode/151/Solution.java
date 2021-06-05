class Solution {
    public String reverseWords(String s) {
        String[] arr = s.split("\\s+");
        StringBuffer sb = new StringBuffer();
        int N = arr.length;
        for(int i = N - 1; -1 < i; i--) {
            sb.append(arr[i]).append(" ");
        }
        return sb.toString().strip();
    }
}