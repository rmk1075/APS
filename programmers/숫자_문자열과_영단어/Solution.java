class Solution {
    public int solution(String s) {
        int N = s.length();
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < N; i++) {
            if(Character.isDigit(s.charAt(i))) sb.append(s.charAt(i));
            else {
                switch(s.charAt(i)) {
                    case 'z':
                        sb.append('0');
                        i += 3;
                        break;
                    case 'o':
                        sb.append('1');
                        i += 2;
                        break;
                    case 't':
                        if(s.charAt(++i) == 'w') {
                            sb.append('2');
                            i++;
                        } else {
                            sb.append('3');
                            i += 3;
                        }
                        break;
                    case 'f':
                        if(s.charAt(++i) == 'o') {
                            sb.append('4');
                            i += 2;
                        } else {
                            sb.append('5');
                            i += 2;
                        }
                        break;
                    case 's':
                        if(s.charAt(++i) == 'i') {
                            sb.append('6');
                            i ++;
                        } else {
                            sb.append('7');
                            i += 3;
                        }
                        break;
                    case 'e':
                        sb.append('8');
                        i += 4;
                        break;
                    case 'n':
                        sb.append('9');
                        i += 3;
                }
            }
        }
        return Integer.parseInt(sb.toString());
    }
}