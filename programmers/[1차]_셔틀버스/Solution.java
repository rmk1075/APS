import java.util.Arrays;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";

        int k = timetable.length;
        int[] table = new int[k];
        String[] temp;
        for(int i = 0; i < k; i++) {
            temp = timetable[i].split(":");
            table[i] = Integer.parseInt(temp[0]) * 60 + Integer.parseInt(temp[1]);
        }
        Arrays.sort(table);

        int time = 9 * 60, waitingStart = 0, waitingEnd = 0, ans = 0;
        for(int i = 0; i < n && time < 24 * 60; i++) {
            while(waitingEnd < k && table[waitingEnd] <= time) waitingEnd++;

            if(waitingEnd - waitingStart < m) {
                ans = time;
                waitingStart = waitingEnd;
            } else {
                ans = table[waitingStart + m - 1] - 1;
                waitingStart += m;
            }

            time += t;
        }

        answer = String.format("%02d", (ans / 60)) + ":" + String.format("%02d", ans % 60);

        return answer;
    }
}