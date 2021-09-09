import java.util.Arrays;

class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";

        int playTime = timeToInt(play_time);
        int advTime = timeToInt(adv_time);

        int[] counts = new int[playTime + 1];
        for(String l : logs) {
            String[] ls = l.split("-");
            int start = timeToInt(ls[0]);
            int end = timeToInt(ls[1]);
            while(start < end) counts[start++]++;
        }

        // init area
        int ans = 0;
        long cnt = 0L;
        for(int i = 0; i < advTime; i++) cnt += counts[i];
        
        long maxCnt = cnt;
        for(int i = 1; i < playTime - advTime + 1; i++) {
            cnt = cnt - counts[i - 1] + counts[i - 1 + advTime];
            if(maxCnt < cnt) {
                maxCnt = cnt;
                ans = i;
            }
        }

        answer = intToTime(ans);
        return answer;
    }

    public String intToTime(int value) {
        int hour = value / 3600;
        int min = (value % 3600) / 60;
        int sec = value % 60;
        return String.format("%02d:%02d:%02d", hour, min, sec);
    }

    public int timeToInt(String time) {
        String[] ts = time.split(":");
        return Integer.parseInt(ts[0]) * 3600 + Integer.parseInt(ts[1]) * 60 + Integer.parseInt(ts[2]);
    }
}