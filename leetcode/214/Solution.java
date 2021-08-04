class Solution {
    public String shortestPalindrome(String s) {
        int len = s.length();
        int left = 0, right = len - 1;
        char[] sToChar = s.toCharArray();
        for(; -1 < right; right--) {
            if(sToChar[left] == sToChar[right]) left++;
        }
        if(left == len) return s;

        String suffix = s.substring(left);
        return new StringBuilder(suffix).reverse().append(shortestPalindrome(s.substring(0, left))).append(suffix).toString();
    }
}

// class Solution {
//     public String shortestPalindrome(String s) {
//         int len = s.length();
//         char[] sToChar = s.toCharArray();

//         int right = len - 1;
//         for(; 0 < right; right--) {
//             if(sToChar[0] != sToChar[right]) continue;
//             if(isPailndrome(sToChar, right)) break;
//         }

//         if(right < len) {
//             StringBuilder addition = new StringBuilder();
//             addition.append(s.substring(right + 1));
//             s = addition.reverse().toString() + s;
//         }
//         return s;
//     }

//     public boolean isPailndrome(char[] words, int right) {
//         int left = 0;
//         while(left < right) {
//             if(words[left] != words[right]) return false;
//             left++;
//             right--;
//         }
//         return true;
//     }
// }