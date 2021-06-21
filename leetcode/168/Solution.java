class Solution {
    public String convertToTitle(int columnNumber) {
        StringBuilder sb = new StringBuilder();
        while(columnNumber > 0) {
            char lastLetter = (char)((columnNumber - 1) % 26 + 'A');
            columnNumber = (columnNumber - 1) / 26;
            
            sb.append(lastLetter);
        }
        return sb.reverse().toString();
    }
}

// class Solution {
//     public String convertToTitle(int columnNumber) {
//         String result = "";
//         while(0 < columnNumber) {
//             int value = columnNumber % 26;
//             if(value == 0) {
//                 result = "Z" + result;
//                 columnNumber = columnNumber / 26 - 1;
//             } else {
//                 result = (char)(value - 1 + 'A') + result;
//                 columnNumber /= 26;
//             }
//         }
//         return result;
//     }
// }