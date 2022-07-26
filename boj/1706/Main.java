import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int R, C;
    static char[][] arr;
    static String minStr = "zzzzzzzzzzzzzzzzzzzzz";
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new char[R][C];
        for(int i = 0; i < R; i++) arr[i] = br.readLine().toCharArray();
        for(int i = 0; i < R; i++) {
            StringBuffer sb = new StringBuffer();
            for(int j = 0; j < C; j++) check(sb, arr[i][j]);
            if(1 < sb.length()) {
                String temp = sb.toString();
                if(0 < minStr.compareTo(temp)) minStr = temp;
            }
        }

        for(int i = 0; i < C; i++) {
            StringBuffer sb = new StringBuffer();
            for(int j = 0; j < R; j++) check(sb, arr[j][i]);
            if(1 < sb.length()) {
                String temp = sb.toString();
                if(0 < minStr.compareTo(temp)) minStr = temp;
            }
        }
        System.out.println(minStr);
    }

    private static void check(StringBuffer sb, char ch) {
        if(ch == '#') {
            if(sb.length() == 0) return;
            if(sb.length() == 1) {
                sb.setLength(0);
                return;
            }
            String temp = sb.toString();
            if(0 < minStr.compareTo(temp)) minStr = temp;
            sb.setLength(0);
        } else sb.append(ch);
    }
}
