import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if(numerator == 0) return "0";
        
        long numeratorLong = numerator, denominatorLong = denominator;
        StringBuilder sb = new StringBuilder();
        if(numeratorLong < 0 ^ denominatorLong < 0) {
            sb.append("-");
            if(numeratorLong < 0) numeratorLong *= -1;
            if(denominatorLong < 0) denominatorLong *= -1;
        }
        
        sb.append(numeratorLong / denominatorLong);
        numeratorLong %= denominatorLong;
        if(numeratorLong == 0) return sb.toString();

        int index = 0;
        List<String> list = new LinkedList<>();
        Map<Long, Integer> map = new HashMap<>();
        while(numeratorLong != 0) {
            if(map.containsKey(numeratorLong)) break;

            map.put(numeratorLong, index++);
            numeratorLong *= 10;
            list.add(String.valueOf(numeratorLong / denominatorLong < 0 ? -1 * (numeratorLong / denominatorLong) : numeratorLong / denominatorLong));
            numeratorLong %= denominatorLong;
        }

        if(numeratorLong != 0) {
            list.add(map.get(numeratorLong), "(");
            list.add(")");
        }
        list.add(0, ".");
        for(String str : list) sb.append(str);
        return sb.toString();
    }
}