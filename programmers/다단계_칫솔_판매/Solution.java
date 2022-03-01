import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class Solution {
    static final int PRICE = 100;
    Map<String, Integer> map = new HashMap<>();
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        for(String e : enroll) {
            map.put(e, 0);
        }

        for(int i = 0; i < referral.length; i++) {
            String parent = referral[i];
            if(parent.equals("-")) {
                dfs(enroll[i], enroll, referral, seller, amount);
            }
        }
        
        int[] answer = new int[enroll.length];
        for(int i = 0; i < enroll.length; i++) {
            answer[i] = map.get(enroll[i]);
        }
        return answer;
    }

    public List<Integer> dfs(String node, String[] enroll, String[] referral, String[] seller, int[] amounts) {
        int amount = 0;
        List<Integer> taxList = new LinkedList<>();
        for(int i = 0; i < referral.length; i++) {
            if(referral[i].equals(node)) {
                List<Integer> tempTaxList = dfs(enroll[i], enroll, referral, seller, amounts);
                for(int temp : tempTaxList) {
                    int tax = (int)Math.ceil(temp / 10);
                    amount += temp - tax;
                    if(tax != 0) taxList.add(tax);
                }
            }
        }

        for(int i = 0; i < seller.length; i++) {
            if(seller[i].equals(node)) {
                int temp = amounts[i] * PRICE;
                int tax = (int)Math.ceil(temp / 10);
                amount += temp - tax;
                if(tax != 0) taxList.add(tax);
            }
        }

        map.put(node, amount);
        return taxList;

    }
}