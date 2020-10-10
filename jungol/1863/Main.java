import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] students;
    static ArrayList<Integer>[] relationship;
    static Queue<Integer> queue = new LinkedList<Integer>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        students = new int[N];
        relationship = new ArrayList[N];
        for(int i = 0; i < N; i++) {
            relationship[i] = new ArrayList<Integer>();
        }

        int x, y;
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            x = Integer.parseInt(st.nextToken()) - 1;
            y = Integer.parseInt(st.nextToken()) - 1;

            relationship[x].add(y);
            relationship[y].add(x);
        }

        int num = 1;
        for(int i = 0; i < N; i++) {

            if(students[i] != 0) continue;

            students[i] = num;
            queue.offer(i);
            while(!queue.isEmpty()) {
                int student = queue.poll();

                for(int j = 0; j < relationship[student].size(); j++) {
                    if(students[relationship[student].get(j)] == 0) {
                        queue.offer(relationship[student].get(j));
                        students[relationship[student].get(j)] = num;
                    }
                }
            }

            num++;
        }

        System.out.println(num - 1);
    }
}