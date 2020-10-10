import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] paper = new int[101][101];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        int leftEnd, downEnd, count = 0;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            leftEnd = Integer.parseInt(st.nextToken());
            downEnd = Integer.parseInt(st.nextToken());

            for(int j = leftEnd + 1; j < leftEnd + 11; j++) {
                for(int k = downEnd + 1; k < downEnd + 11; k++) {
                    if(paper[j][k] == 0) {
                        paper[j][k] = 1;
                        count++;
                    }
                }
            }
        }

        System.out.println(count);
    }
}