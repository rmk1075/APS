import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * overview
 * 
 * 각 노드의 연결관계를 통해서 트리 구조를 만들고 이를 순회하면서 이익을 구한다.
 * 노드의 이익은 해당 노드의 판매 이익 - 부모로 배분할 이익 + 자녀 노드들로 부터 배분받은 이익을 더해서 구한다.
 * 이때 이익의 계산은 각 판매건마다 계산해야 한다.
 * 10%가 1원 미만인 경우에는 분배하지 않기 때문에 전체 이익을 더한 경우에는 배분값이 다르기 때문에 이를 유의하여 매건 계산해야한다.
 * 
 * description
 * 
 * 각 노드에서 노드 자체의 이익과 배분으로 부모로 보낼 값을 저장한다.
 * 트리 구조에서 dfs로 순회를 하여 자식 노드들의 배분 이익을 부모 노드가 반환받아서 계산한다.
 * 이때 매 이익에 대해서 10%의 배분을 보내야 하기 때문에 전체를 더한 값이 아니라 리스트로 값을 반환하도록 한다.
 */
class Node {
    int value = 0;
    List<Integer> taxList = new LinkedList<>();
}

class Solution {
    static final int PRICE = 100;
    int E, S;
    Map<String, Node> map = new HashMap<>();
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        E = enroll.length;
        S = seller.length;
        for(String e : enroll) map.put(e, new Node());
        for(int i = 0; i < S; i++) {
            int value = amount[i] * PRICE;
            int tax = (int)Math.ceil(value / 10);

            Node node = map.get(seller[i]);
            node.value += value - tax;
            if(tax != 0) node.taxList.add(tax);
        }

        for(int i = 0; i < E; i++) {
            if(referral[i].equals("-")) dfs(enroll[i], enroll, referral, seller, amount);
        }
        
        int[] answer = new int[enroll.length];
        for(int i = 0; i < enroll.length; i++) answer[i] = map.get(enroll[i]).value;
        return answer;
    }

    public List<Integer> dfs(String name, String[] enroll, String[] referral, String[] seller, int[] amounts) {
        Node node = map.get(name);
        for(int i = 0; i < referral.length; i++) {
            if(referral[i].equals(name)) {
                List<Integer> tempTaxList = dfs(enroll[i], enroll, referral, seller, amounts);
                for(int temp : tempTaxList) {
                    int tax = (int)Math.ceil(temp / 10);
                    node.value += temp - tax;
                    if(tax != 0) node.taxList.add(tax);
                }
            }
        }
        return node.taxList;
    }
}

// import java.util.HashMap;
// import java.util.LinkedList;
// import java.util.List;
// import java.util.Map;

// class Solution {
//     static final int PRICE = 100;
//     Map<String, Integer> map = new HashMap<>();
//     public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
//         for(String e : enroll) {
//             map.put(e, 0);
//         }

//         for(int i = 0; i < referral.length; i++) {
//             String parent = referral[i];
//             if(parent.equals("-")) {
//                 dfs(enroll[i], enroll, referral, seller, amount);
//             }
//         }
        
//         int[] answer = new int[enroll.length];
//         for(int i = 0; i < enroll.length; i++) {
//             answer[i] = map.get(enroll[i]);
//         }
//         return answer;
//     }

//     public List<Integer> dfs(String node, String[] enroll, String[] referral, String[] seller, int[] amounts) {
//         int amount = 0;
//         List<Integer> taxList = new LinkedList<>();
//         for(int i = 0; i < referral.length; i++) {
//             if(referral[i].equals(node)) {
//                 List<Integer> tempTaxList = dfs(enroll[i], enroll, referral, seller, amounts);
//                 for(int temp : tempTaxList) {
//                     int tax = (int)Math.ceil(temp / 10);
//                     amount += temp - tax;
//                     if(tax != 0) taxList.add(tax);
//                 }
//             }
//         }

//         for(int i = 0; i < seller.length; i++) {
//             if(seller[i].equals(node)) {
//                 int temp = amounts[i] * PRICE;
//                 int tax = (int)Math.ceil(temp / 10);
//                 amount += temp - tax;
//                 if(tax != 0) taxList.add(tax);
//             }
//         }

//         map.put(node, amount);
//         return taxList;

//     }
// }