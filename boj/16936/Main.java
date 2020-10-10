import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static long[] ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        ArrayList<Long> list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) list.add(Long.parseLong(st.nextToken()));

        ans = new long[N];
        for(int i = 0; i < N; i++) {
            ans[0] = list.get(i);
            list.remove(i);
            find(ans[0], 1, list);
            list.add(i, ans[0]);
        }
    }

    public static void find(long num, int count, ArrayList<Long> list) {
        if(count == N) {
            for(Long n : ans) System.out.print(n + " ");
            System.exit(0);
        }

        ArrayList<Long> candidates = new ArrayList<>();
        candidates.addAll(list);
        
        // *2
        long temp = num * 2;
        if(candidates.contains(temp)) {
            ans[count] = temp;
            candidates.remove(temp);
            find(temp, count+1, candidates);
            candidates.add(temp);
        }

        // /3
        if(num % 3 != 0) return ;
        temp = num / 3;
        if(candidates.contains(temp)) {
            ans[count] = temp;
            candidates.remove(temp);
            find(temp, count+1, candidates);
            candidates.add(temp);
        }
    }
}