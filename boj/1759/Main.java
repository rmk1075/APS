import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int L, C;
    static char[] alphabets, ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        ans = new char[L];
        alphabets = new char[C];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < C; i++) alphabets[i] = st.nextToken().toCharArray()[0];
        Arrays.sort(alphabets);

        find(0, 0, 0, 0);
    }

    public static void find(int num1, int num2, int index, int count) {
        
        if(count == L) {
            if(num1 < 1 || num2 < 2) return ;
            
            for(char c : ans) System.out.print(c);
            System.out.println();
            return ;
        }
        
        if(index == C) return;

        // pick this char
        switch(alphabets[index]) {
            case 'a': case 'e': case 'i': case 'o': case 'u':
                ans[count] = alphabets[index];
                find(num1+1, num2, index+1, count+1);
                break;
            default:
                ans[count] = alphabets[index];
                find(num1, num2+1, index+1, count+1);
                break;
        }

        // not pick this char
        find(num1, num2, index+1, count);
    }
}