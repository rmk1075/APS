class Solution {
    String[] words = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
     "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    String[] tenWords = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    public String numberToWords(int num) {
        if(num == 0) return "Zero";

        StringBuffer result = new StringBuffer();
        int one = num % 1000;

        num /= 1000;
        int thousand = num % 1000;

        num /= 1000;
        int million = num % 1000;

        num /= 1000;
        int billion = num % 1000;

        if(billion != 0) result.append(numToAlpha(billion) + " Billion ");
        if(million != 0) result.append(numToAlpha(million) + " Million ");
        if(thousand != 0) result.append(numToAlpha(thousand) + " Thousand ");
        if(one != 0) result.append(numToAlpha(one));
        return result.charAt(result.length() - 1) == ' ' ? result.substring(0, result.length() - 1) : result.toString();
    }

    public StringBuffer numToAlpha(int num) {
        StringBuffer result = new StringBuffer();
        int ten = num % 100;
        if(ten != 0) {
            int one = ten % 10;
            if(ten < 20) result.append(words[ten]);
            else result.append(tenWords[ten / 10] + (one == 0 ? "" : " " + words[one]));
        }
        
        int hundred = num / 100;
        return hundred == 0 ? result : (result.length() == 0 ? result.append(words[hundred] + " Hundred") : result.insert(0, words[hundred] + " Hundred "));
    }
}