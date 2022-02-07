import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

class Solution {
    int defaultTime;
    int defaultFee;
    int overTimeUnit;
    int overTimeFee;
    public int[] solution(int[] fees, String[] records) {
        defaultTime = fees[0];
        defaultFee = fees[1];
        overTimeUnit = fees[2];
        overTimeFee = fees[3];
        
        Map<Integer, Integer> map = new HashMap<>();
        SortedMap<Integer, Integer> sortedMap = new TreeMap<>();
        for(String record : records) {
            String[] infos = record.split(" ");
            String[] times = infos[0].split(":");
            int time = Integer.parseInt(times[0]) * 60 + Integer.parseInt(times[1]);
            int key = Integer.parseInt(infos[1]);
            if(infos[2].equals("IN")) map.put(key, time);
            else {
                if(sortedMap.containsKey(key)) sortedMap.put(key, sortedMap.get(key) + time - map.get(key));
                else sortedMap.put(key, time - map.get(key));
                map.remove(key);
            }
        }

        int lastTime = 23 * 60 + 59;
        for(int key : map.keySet()) {
            if(sortedMap.containsKey(key)) sortedMap.put(key, sortedMap.get(key) + lastTime - map.get(key));
            else sortedMap.put(key, lastTime - map.get(key));
        }
        
        int[] answer = new int[sortedMap.size()];
        int index = 0;
        for(int key : sortedMap.keySet()) answer[index++] = getFee(sortedMap.get(key));
        return answer;
    }

    public int getFee(int time) {
        if(time <= defaultTime) return defaultFee;
        return defaultFee + (int)(Math.ceil((double)(time - defaultTime) / overTimeUnit)) * overTimeFee;
    }
}