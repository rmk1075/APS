import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    public int[] solution(int[] answers) {
        int[] answer = {};

        int[] points = new int[3];
        for(int i = 0; i < answers.length; i++) {
            int a = answers[i];
            if(a == i % 5 + 1) points[0]++;
            if(a == getSecond(i)) points[1]++;
            if(a == getThird(i)) points[2]++;
        }

        int maxP = Math.max(points[0], Math.max(points[1], points[2]));
        List<Integer> result = new ArrayList<>();
        for(int i = 0; i < 3; i++) {
            if(points[i] == maxP) result.add(i);
        }

        int N = result.size();
        answer = new int[N];
        for(int i = 0; i < N; i++) answer[i] = result.get(i) + 1;
        return answer;
    }

    public int getSecond(int index) {
        if(index % 2 == 0) return 2;
        switch(index % 8) {
            case 1:
                return 1;
            case 3:
                return 3;
            case 5:
                return 4;
            default:
                return 5;
        }
    }

    public int getThird(int index) {
        switch(index % 10) {
            case 0:
            case 1:
                return 3;
            case 2:
            case 3:
                return 1;
            case 4:
            case 5:
                return 2;
            case 6:
            case 7:
                return 4;
            default:
                return 5;
        }
    }
}