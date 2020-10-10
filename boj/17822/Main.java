import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Num {
    int val;
    boolean isDead = false;

    Num(int val, boolean isDead) {
        this.val = val;
        this.isDead = isDead;
    }

}

public class Main {
    static int N, M, T, X, D, K;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static LinkedList<Num>[] circles;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // input: N, M, T
        N = Integer.parseInt(st.nextToken()); // num of circles
        M = Integer.parseInt(st.nextToken()); // num of numbers on a circle
        T = Integer.parseInt(st.nextToken()); // time to rotate a circle
        circles = new LinkedList[N + 1]; // circle number 1 ~ N

        // input: numbers on the circles
        for(int i = 1; i < N + 1; i++) {
            circles[i] = new LinkedList<Num>();
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                circles[i].offer(new Num(Integer.parseInt(st.nextToken()), false));
            }
        }

        // input & rotate the circles & remove the overlapped numbers
        for(int t = 0; t < T; t++) {
            
            // input: X, D, K
            st = new StringTokenizer(br.readLine());
            X = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            // roatate the circle
            for(int i = 1; i < N + 1; i++) {

                if(i % X != 0) continue;

                if(D == 0) {
                    // clockwise
                    // tail to front
                    for(int k = 0; k < K; k++) {
                        circles[i].offerFirst(circles[i].pollLast());
                    }
    
                } else {
                    // counterclockwise
                    // front to tail
                    for(int k = 0; k < K; k++) {
                        circles[i].offerLast(circles[i].pollFirst());    
                    }
                }
            }

            // remove overlapped numbers
            int x, y, count = 0, sum = 0, isChanged = 0;
            for(int i = 1; i < N + 1; i++) {
                for(int j = 0; j < M; j++) {

                    // continue when the element already dead
                    if(circles[i].get(j).val == 0) continue;

                    sum += circles[i].get(j).val;
                    count++;

                    // check nearby numbers
                    for(int d = 0; d < 4; d++) {

                        x = i + dx[d];
                        y = j + dy[d];

                        if(x < 1) continue;
                        if(N < x) continue;
                        if(y < 0) y = M - 1;
                        if(y == M) y = 0;
                        
                        if(circles[x].get(y).val == circles[i].get(j).val) {
                            circles[x].set(y, new Num(circles[x].get(y).val, true));
                            circles[i].set(j, new Num(circles[i].get(j).val, true));
                            isChanged++;
                        }
                    }
                }
            }

            double avg = (double)sum / (double)count;
            if(isChanged == 0) {

                for(int i = 1; i < N + 1; i++) {
                    for(int j = 0; j < M; j++) {
    
                        // continue when the element already dead
                        if(circles[i].get(j).val == 0) continue;
    
                        if(avg < circles[i].get(j).val) {
                            circles[i].set(j, new Num(circles[i].get(j).val - 1, false));
                        } else if(circles[i].get(j).val < avg) {
                            circles[i].set(j, new Num(circles[i].get(j).val + 1, false));
                        }
                    }
                }
            }

            for(int i = 1; i < N + 1; i++) {
                for(int j = 0; j < M; j++) {
                    if(circles[i].get(j).isDead) {
                        circles[i].set(j, new Num(0, true));
                    }
                }
            }

        }

        int sum = 0;
        for(int i = 1; i < N + 1; i++) {
            for(int j = 0; j < M; j++) {
                if(circles[i].get(j).isDead) continue;
                sum += circles[i].get(j).val;
            }
        }

        System.out.println(sum);
    }
}