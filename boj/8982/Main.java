import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static ArrayList<int[]> columns = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        br.readLine();
        StringTokenizer st;
        for(int i = 0; i < N/2 -1; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken()), y1 = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int x2 = Integer.parseInt(st.nextToken());

            for(int j = x1; j < x2; j++) {
                columns.add(new int[] {0, y1});
            }
        }
        br.readLine();

        K = Integer.parseInt(br.readLine());
        for(int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken()), y1 = Integer.parseInt(st.nextToken());

            // left
            int height = y1;
            for(int i = x1; -1 < i; i--) {
                columns.get(i)[0] = (columns.get(i)[0] < height) ? height : columns.get(i)[0];
                height = (columns.get(i)[1] < height) ? columns.get(i)[1] : height;
            }

            // right
            height = y1;
            for(int i = x1+1; i < columns.size(); i++) {
                columns.get(i)[0] = (columns.get(i)[0] < height) ? height : columns.get(i)[0];
                height = (columns.get(i)[1] < height) ? columns.get(i)[1] : height;
            }
        }

        int sum = 0;
        for(int[] col : columns) sum += (0 < col[1] - col[0]) ? col[1] - col[0] : 0;
        System.out.println(sum);
    }
}