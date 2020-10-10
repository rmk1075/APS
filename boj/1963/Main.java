import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int T;
    static char[] temp = new char[4];
    static boolean[] nonPM = new boolean[10000], visited = new boolean[10000];
    static Queue<String> queue = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        // prime numbers
        for(int i = 2; i < 501; i++) {
            for(int j = 2; i * j < 10000; j++) nonPM[i*j] = true;
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        while(0 < T--) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
            if(a == b) {
                sb.append("0\n");
                continue;
            }

            // a -> b
            queue.clear();
            queue.offer(String.valueOf(a));
            Arrays.fill(visited, false);
            visited[a] = true;
            
            String ans = String.valueOf(b);
            boolean isMatch = false;
            int size, cnt = 1;
            loop: while(!queue.isEmpty()) {
                size = queue.size();
                while(0 < size--) {
                    String current = queue.poll();
                    temp = current.toCharArray();

                    for(int i = 0; i < 4; i++) {
                        for(char j = (i == 0) ? '1' : '0'; j <= '9'; j++) {
                            if(current.charAt(i) == j) continue;
                            temp[i] = j;
                            
                            String num = String.valueOf(temp);
                            if(ans.equals(num)) {
                                isMatch = true;
                                break loop;
                            }

                            int t = Integer.parseInt(num);
                            if(visited[t] || nonPM[t]) continue;
                            visited[t] = true;

                            queue.offer(num);
                        }
                        temp[i] = current.charAt(i);
                    }
                }
                cnt++;
            }
            sb.append(isMatch ? cnt+"\n" : "Impossible\n");
        }
        System.out.println(sb);
    }
}