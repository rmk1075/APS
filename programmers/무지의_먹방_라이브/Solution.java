import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    public int solution(int[] food_times, long k) {
        int len = food_times.length;

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            public int compare(int[] o1, int[] o2) {
                return o1[1] == o2[1] ? o1[0] - o2[0] : o1[1] - o2[1];
            }
        });
        for(int i = 0; i < len; i++) pq.offer(new int[]{i, food_times[i]});
        
        int prev = 0;
        while(!pq.isEmpty()) {
            int[] current = pq.peek();
            long time = ((long)current[1] - (long)prev) * pq.size();
            if(k < time) {
                int[][] arr = new int[pq.size()][2];
                pq.toArray(arr);
                Arrays.sort(arr, new Comparator<int[]>() {
                    public int compare(int[] o1, int[] o2) {
                        return o1[0] - o2[0];
                    }
                });
                k %= arr.length;
                return arr[(int)k][0] + 1;
            }
            prev = current[1];
            k -= time;
            pq.poll();
        }

        return -1;
    }
}

// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.Collections;
// import java.util.Comparator;
// import java.util.List;

// class Solution {
//     public int solution(int[] food_times, long k) {
//         int answer = 0;

//         int idx = 0, len = food_times.length;
//         if (k < len) {
//             answer = 1 + (int) k;
//             return answer;
//         }

//         int[][] foods = new int[len][2];
//         for (int food : food_times) {
//             foods[idx][1] = food;
//             foods[idx][0] = ++idx;
//         }

//         Arrays.sort(foods, new Comparator<int[]>() {
//             @Override
//             public int compare(int[] o1, int[] o2) {
//                 if (o1[1] == o2[1])
//                     return o1[0] - o2[0];

//                 return o1[1] - o2[1];
//             }
//         });

//         idx = 0;
//         int cnt = 0;
//         long count = 0L;
//         while (idx < len) {
//             if (k < count + len - idx)
//                 break;

//             count += len - idx;
//             cnt++;
//             while (idx < len && foods[idx][1] == cnt)
//                 idx++;
//         }

//         if (idx == len) {
//             answer = -1;
//             return answer;
//         }

//         List<int[]> list = new ArrayList<>();
//         for (int i = idx; i < len; i++)
//             list.add(foods[i]);

//         Collections.sort(list, new Comparator<int[]>() {
//             @Override
//             public int compare(int[] o1, int[] o2) {
//                 return o1[0] - o2[0];
//             }
//         });

//         answer = list.get((int) (k - count))[0];
//         return answer;
//     }
// }