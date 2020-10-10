import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K, maxCount = 0;
    static char[][] words;
    static boolean[] alphabets;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        words = new char[N][];
        for(int i = 0; i < N; i++) words[i] = br.readLine().toCharArray();

        if(K < 5) {
            System.out.println(0);
            System.exit(0);
        }

        alphabets = new boolean[26];
        alphabets['a'-'a'] = alphabets['c'-'a'] = alphabets['i'-'a'] = alphabets['t'-'a'] = alphabets['n'-'a'] = true;
        K -= 5;

        choice(0, 0);
        
        System.out.println(maxCount);
    }

    public static void choice(int index, int count) {
        if(count == K) {
            int cnt = N;
            for(int i = 0; i < N; i++) {
                for(int j = 4; j < words[i].length-4; j++) {
                    if(!alphabets[words[i][j]-'a']) {
                        cnt--;
                        break;
                    }
                }
            }

            maxCount = Math.max(maxCount, cnt);
            if(maxCount == N) {
                System.out.println(maxCount);
                System.exit(0);
            }
            return ;
        }

        for(int i = index; i < 26; i++) {
            if(alphabets[i]) continue;

            alphabets[i] = true;
            choice(i+1, count+1);
            alphabets[i] = false;
        }
    }
}