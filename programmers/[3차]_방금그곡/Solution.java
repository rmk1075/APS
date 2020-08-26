/**
 * C# -> H
 * D# -> I
 * F# -> J
 * G# -> K
 * A# -> L
 */

class Solution {
    public String solution(String m, String[] musicinfos) {
        String answer = "(None)";
        
        StringBuilder sb = new StringBuilder();
        for(char ch : m.toCharArray()) {
            if(ch == '#') {
                char lastCh = sb.charAt(sb.length() - 1);
                ch = convert(lastCh);
                sb.deleteCharAt(sb.length() - 1);
            }
            
            sb.append(ch);
        }
        String M = sb.toString();

        int musicLen = 0;
        for (String info : musicinfos) {
            String[] infoArr = info.split(",");

            String[] startTime = infoArr[0].split(":");
            String[] endTime = infoArr[1].split(":");
            char[] notes = infoArr[3].toCharArray();
            int noteLen = notes.length;

            int start = Integer.parseInt(startTime[0]) * 60 + Integer.parseInt(startTime[1]);
            int end = Integer.parseInt(endTime[0]) * 60 + Integer.parseInt(endTime[1]);

            sb.setLength(0);
            int i = 0;
            while (start < end) {
                char note = notes[i++ % noteLen];
                
                if (note == '#') {
                    note = convert(sb.charAt(sb.length() - 1));
                    sb.deleteCharAt(sb.length() - 1);
                } else start++;

                sb.append(note);
            }

            if(notes[i % noteLen] == '#') {
                char note = convert(sb.charAt(sb.length() - 1));
                sb.deleteCharAt(sb.length() - 1);
                sb.append(note);
            }

            if (sb.toString().contains(M) && musicLen < sb.length()) {
                answer = infoArr[2];
                musicLen = sb.length();
            }
        }

        return answer;
    }

    public char convert(char lastCh) {
        switch (lastCh) {
            case 'C':
                return 'H';

            case 'D':
                return 'I';

            case 'F':
                return 'J';

            case 'G':
                return 'K';

            default:
                return 'L';
        }
    }
}