import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] students;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        students = new int[N + 1];
        for(int i = 1; i < N + 1; i++) {
            students[i] = i;
        }

        int a, b;
        for(int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());

            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            a = find(a);
            b = find(b);

            if(a == b) continue;
            students[b] = a;
        }

        int count = 0;
        for(int i = 1; i < N + 1; i++) {
            if(students[i] == i) count++;
        }

        System.out.println(count);
    }

    public static int find(int x) {
        if(students[x] == x) return x;

        return students[x] = find(students[x]);
    }
}