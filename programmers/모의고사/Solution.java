import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    int[][] students = {
        {1, 2, 3, 4, 5},
        {2, 1, 2, 3, 2, 4, 2, 5},
        {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}
    };

    public int[] solution(int[] answers) {
        int[] answer = {};

        int[] points = new int[3];
        for(int i = 0; i < answers.length; i++) {
            int a = answers[i];
            if(a == students[0][i % 5]) points[0]++;
            if(a == students[1][i % 8]) points[1]++;
            if(a == students[2][i % 10]) points[2]++;
        }

        int maxP = Math.max(points[0], Math.max(points[1], points[2]));
        List<Integer> result = new ArrayList<>();
        for(int i = 0; i < 3; i++) {
            if(points[i] == maxP) result.add(i);
        }

        // answer = result.stream().mapToInt((i -> i.intValue() + 1)).toArray();
        int N = result.size();
        answer = new int[N];
        for(int i = 0; i < N; i++) answer[i] = result.get(i) + 1;
        return answer;
    }
}

// import java.util.List;
// import java.util.ArrayList;
// import java.util.Arrays;

// class Solution {
//     public int[] solution(int[] answers) {
//         int[] answer = {};

//         int[] points = new int[3];
//         for(int i = 0; i < answers.length; i++) {
//             int a = answers[i];
//             if(a == i % 5 + 1) points[0]++;
//             if(a == getSecond(i)) points[1]++;
//             if(a == getThird(i)) points[2]++;
//         }

//         int maxP = Math.max(points[0], Math.max(points[1], points[2]));
//         List<Integer> result = new ArrayList<>();
//         for(int i = 0; i < 3; i++) {
//             if(points[i] == maxP) result.add(i);
//         }

//         answer = result.stream().mapToInt((i -> i.intValue() + 1)).toArray();
//         // int N = result.size();
//         // answer = new int[N];
//         // for(int i = 0; i < N; i++) answer[i] = result.get(i) + 1;
//         return answer;
//     }

//     public int getSecond(int index) {
//         if(index % 2 == 0) return 2;
//         switch(index % 8) {
//             case 1:
//                 return 1;
//             case 3:
//                 return 3;
//             case 5:
//                 return 4;
//             default:
//                 return 5;
//         }
//     }

//     public int getThird(int index) {
//         switch(index % 10) {
//             case 0:
//             case 1:
//                 return 3;
//             case 2:
//             case 3:
//                 return 1;
//             case 4:
//             case 5:
//                 return 2;
//             case 6:
//             case 7:
//                 return 4;
//             default:
//                 return 5;
//         }
//     }
// }