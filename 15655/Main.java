import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] nums, order;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nums = new int[N+1];
        order = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i < N+1; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);
        make(0, 0);
    }
    
    public static void make(int index, int count) {
        if(count == M) {    
            for(int i : order) {
                System.out.print(i + " ");
            }
            System.out.println();
            
            return;
        }
        
        for(int i = index+1; i <= N; i++) {
            order[count] = nums[i];
            make(i, count+1);
        }
    }
}