import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N, K, order[], count[][];
    static ArrayList<Integer> tabs = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        order = new int[K];
        
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < K; i++) order[i] = Integer.parseInt(st.nextToken()) - 1;

        count = new int[K][K+1];
        for(int i = 0; i < K; i++) count[i][K] = K;
        for(int i = K-1; -1 < i; i--) {
            for(int j= 0; j < K; j++) count[j][i] = count[j][i+1];
            count[order[i]][i] = i;
        }
        
        int dev, ans = 0;
        for(int i = 0; i < K; i++) {
            dev = order[i];
            if(tabs.contains(dev)) continue;
            if(tabs.size() == N) {
                int lastIdx = -1, lastTime = 0;
                for(int j = 0; j < N; j++) {
                    if(lastTime < count[tabs.get(j)][i]) {
                        lastIdx = j;
                        lastTime = count[tabs.get(j)][i];
                    }
                }
                tabs.set(lastIdx, dev);
                ans++;
            } else tabs.add(dev);
        }

        System.out.println(ans);
    }
}