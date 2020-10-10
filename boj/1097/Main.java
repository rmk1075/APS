import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
    static int N, K, visited = 0, ans = 0;
    static String[] words;
    static Set<String> set = new HashSet<>(), ansSet = new HashSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        words = new String[N];
        for(int i = 0; i < N; i++) words[i] = br.readLine();
        K = Integer.parseInt(br.readLine());
        
        for(int i = 0; i < N; i++) {
            visited |= (1 << i);
            dfs(1, words[i]);
            visited &= ~(1 << i);
        }
        System.out.println(ans);
    }

    public static void dfs(int count, String str) {
        if(count == N) {
            if(set.contains(str)) {
                if(ansSet.contains(str)) ans++;
            } else {
                set.add(str);
                KMP(str + str, str);
            }
            return ;
        }

        for(int i = 0; i < N; i++) {
            if((visited & (1 << i)) == 0) {
                visited |= (1 << i);
                dfs(count+1, str + words[i]);
                visited &= ~(1 << i);
            }
        }
    }

    public static void KMP(String origin, String pattern) {
        int k = 0, j = 0, pi[] = getPi(pattern);
        for(int i = 0; i < origin.length()-1; i++) {
            while (0 < j && origin.charAt(i) != pattern.charAt(j)) j = pi[j - 1];
            if (origin.charAt(i) == pattern.charAt(j)) {
                if (j == pattern.length() - 1) {
                    k++;
                    j = pi[j];
                } else j++;
            }
        }
        if(k == K) {
            ans++;
            ansSet.add(pattern);
        }
    }

    public static int[] getPi(String pattern) {
        int[] pi = new int[pattern.length()];
        int j = 0;
        for(int i = 1; i < pattern.length(); i++) {
            while(0 < j && pattern.charAt(i) != pattern.charAt(j)) j = pi[j-1];
            if(pattern.charAt(i) == pattern.charAt(j)) pi[i] = ++j;
        }
        return pi;
    }
}