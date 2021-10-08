class Solution {
    public int addDigits(int num) {
        while(9 < num) {
            int temp = 0;
            while(0 < num) {
                temp += num % 10;
                num /= 10;
            }
            num = temp;
        }
        return num;
    }
}