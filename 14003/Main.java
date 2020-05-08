import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N, A[], prev[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));        
        N = Integer.parseInt(br.readLine());
        A = new int[N+1];
        prev = new int[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i < N+1; i++) A[i] = Integer.parseInt(st.nextToken());

        ArrayList<Integer> list = new ArrayList<>();
        list.add(-1000_000_000);
        for(int i = 1; i < N+1; i++) {
            int num = A[i], left = 1, right = list.size() - 1;

            if(list.get(list.size() - 1) < num) {
                list.add(num);
                prev[i] = list.size() - 1;
            } else {
                while(left < right) {
                    int mid = (left + right) / 2;
                    if(num <= list.get(mid)) right = mid;
                    else left = mid + 1;
                }

                list.set(right, num);
                prev[i] = right;
            }
        }
        System.out.println(list.size() - 1);
        
        StringBuilder sb = new StringBuilder();
        int idx = list.size() - 1;
        for(int i = N; 0 < i; i--) {
            if(prev[i] == idx) {
                idx--;
                sb.insert(0, A[i] + " ");
            }
        }
        System.out.println(sb);
    }
}