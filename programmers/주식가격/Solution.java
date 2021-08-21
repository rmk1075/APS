import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
    public int[] solution(int[] prices) {
        int N = prices.length;
        int[] answer = new int[N];

        for(int i = 0; i < N - 1; i++) {
            int price = prices[i];
            for(int j = i + 1; j < N; j++) {
                answer[i]++;
                if(prices[j] < price) break;
            }
        }

        return answer;
    }
}

// import java.util.Comparator;
// import java.util.List;
// import java.util.PriorityQueue;

// class Solution {
//     public int[] solution(int[] prices) {
//         int N = prices.length;
//         int[] answer = new int[N];

//         // int[] {index, value}
//         PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
//             @Override
//             public int compare(int[] o1, int[] o2) {
//                 return o2[1] - o1[1];
//             }
//         });

//         pq.offer(new int[]{0, prices[0]});
//         for(int i = 1; i < N; i++) {
//             int price = prices[i];
//             while(pq.size() != 0 && price < pq.peek()[1]) {
//                 int[] prev = pq.poll();
//                 answer[prev[0]] = i - prev[0];
//             }
//             pq.offer(new int[]{i, price});
//         }

//         while(pq.size() != 0) {
//             int[] prev = pq.poll();
//             answer[prev[0]] = N - 1 - prev[0];
//         }

//         return answer;
//     }
// }