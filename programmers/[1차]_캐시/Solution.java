class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;

        if(cacheSize == 0) return 5 * cities.length;

        String[] cache = new String[cacheSize];
        int[] used = new int[cacheSize];
        int numOfCache = 0, emptyIdx = 0;

        for(String city : cities) {
            city = city.toUpperCase();

            boolean isCache = false;
            for(int i = 0; i < cacheSize; i++) {
                if(cache[i] != null) used[i]++;
            }

            for(int i = 0; i < cacheSize; i++) {
                if(city.equals(cache[i])) {
                    used[i] = 0;
                    isCache = true;
                    break;
                }
            }

            if(!isCache) {
                if(numOfCache < cacheSize) {
                    cache[emptyIdx] = city;
                    used[emptyIdx++] = 0;
                    numOfCache++;
                } else {
                    int maxU = 0, maxIdx = 0;
                    for(int i = 0; i < cacheSize; i++) {
                        if(maxU < used[i]) {
                            maxIdx = i;
                            maxU = used[i];
                        }
                    }

                    cache[maxIdx] = city;
                    used[maxIdx] = 0;
                }

                answer += 5;
            } else answer += 1;
        }

        return answer;
    }
}