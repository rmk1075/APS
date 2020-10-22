import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    int index = 0;
    String lowerWord, headFront = "<head>", headTail = "</head>", bodyFront = "<body>", bodyTail = "</body>", meta = "<meta property=\"og:url\"", https = "https://", ahref = "a href=\"https://", doubleQuotes = "\"";
    List<Integer> headList = new ArrayList<>();
    List<int[]> links = new ArrayList<>();
    Map<String, Integer> map = new HashMap<>();
    Map<Integer, Integer> pointMap = new HashMap<>();   // 기본점수
    public int solution(String word, String[] pages) {
        int answer = 0;
        double maxVal = 0;

        lowerWord = word.toLowerCase();
        for(String page : pages) {
            process(page);
        }

        int[][] linksArr = new int[index][index];
        for(int[] link : links) linksArr[link[0]][link[1]]++;

        int[][] numOfLinks = new int[index][2]; // [0: in, 1: out]
        for(int i = 0; i < index; i++) {
            for(int j = 0; j < index; j++) {
                if(linksArr[i][j] == 0) continue;

                numOfLinks[i][1] += linksArr[i][j];
                numOfLinks[j][0] += linksArr[i][j];
            }
        }

        int idx = 0;
        for(int head : headList) {
            double matchingPoint = pointMap.get(head);

            for(int i = 0; i < index; i++) {
                if(linksArr[i][head] == 0) continue;

                matchingPoint += (double)pointMap.get(i) / (double)numOfLinks[i][1];
            }

            if(maxVal < matchingPoint) {
                maxVal = matchingPoint;
                answer = idx;
            }

            idx++;
        }

        return answer;
    }

    public void process(String page) {
        // divide
        String[] div = page.split(headTail);
        
        // head - find page url
        int idx = head(div[0]);
        headList.add(idx);
        
        // body - find link
        body(idx, div[1]);
        
        // get basic point
        int basicPoint = point(page);
        pointMap.put(idx, basicPoint);
    }

    public int point(String page) {
        int basicPoint = 0;

        for(String word : page.split("[^a-zA-Z]")) {
            if(lowerWord.equals(word.toLowerCase())) basicPoint++;
        }

        return basicPoint;
    }

    public int head(String head) {
        int idx;
        String url = head.split(meta)[1].split(https)[1].split(doubleQuotes)[0];
        
        if(map.containsKey(url)) idx = map.get(url);
        else {
            idx = index++;
            map.put(url, idx);
        }

        return idx;
    }

    public void body(int idx, String body) {
        String[] bodies = body.split(ahref);

        int linkNum;
        for(int i = 1; i < bodies.length; i++) {
            String link = bodies[i].split(doubleQuotes)[0];
            
            if(map.containsKey(link)) linkNum = map.get(link);
            else {
                linkNum = index++;
                map.put(link, linkNum);
            }

            links.add(new int[]{idx, linkNum});
        }
    }
}