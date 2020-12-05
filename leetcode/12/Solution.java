class Solution {
    public String intToRoman(int num) {
        String result = "";
        int count;

        String Symbol[] = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
        int Value[] = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };

        for (int i = 0; i < Symbol.length; i++) {
            while (Value[i] <= num) {
                num -= Value[i];
                result += Symbol[i];
            }
        }

        return result;
    }
}