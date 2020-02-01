import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, totalPopulation, minDiff;
    static int[] populations, visited, sumVisited;
    static int[][] sections;

    public static void main(String[] args) throws IOException {
            
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        sumVisited = new int[1001];

        minDiff = Integer.MAX_VALUE;
        N = Integer.parseInt(br.readLine());
        visited = new int[N+1];
        populations = new int[N+1];
        sections = new int[N+1][N+1];

        st = new StringTokenizer(br.readLine());
        totalPopulation = 0;
        for(int i = 1; i < N + 1; i++) {
            populations[i] = Integer.parseInt(st.nextToken());
            totalPopulation += populations[i];
        }

        for(int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            
            int temp = Integer.parseInt(st.nextToken());
            for(int j = 0; j < temp; j++) {
                sections[i][Integer.parseInt(st.nextToken())] = 1;
            }
        }            
        
        sumVisited[0] = 1;
        divide(0, 0);

        if(minDiff != Integer.MAX_VALUE)
            System.out.println(minDiff);
        else
            System.out.println(-1);
    }

    public static void divide(int count, int sum) {

        // cal
        if(sumVisited[sum] == 0) {

            int[] group = new int[N + 1];
            for(int i = 1; i < N + 1; i++) {
                group[i] = visited[i];
            }

            // check group A and group B
            boolean check = true;
            for(int g = 0; g < 2; g++) {
                Queue<Integer> q = new LinkedList<Integer>();

                for (int i = 1; i < N + 1; i++) {
                    if (group[i] == g) {
                        q.offer(i);
                        break;
                    }
                }

                while (!q.isEmpty()) {
                    int current = q.poll();
                    group[current] = 2;

                    for (int i = 1; i < N + 1; i++) {
                        if (sections[current][i] == 1 && group[i] == g) {
                            q.offer(i);
                        }
                    }
                }

                for (int i = 1; i < N + 1; i++) {
                    if (group[i] == g) {
                        check = false;
                        break;
                    }
                }

                if(!check) break;
            }

            if(check) {
                sumVisited[sum] = 1;
                minDiff = Math.min(minDiff, Math.abs(totalPopulation - sum * 2));
            }
        }

        // return: not check the combination which already checked
        if(count == (N / 2) + 1) return ;

        for(int i = 1; i < N + 1; i++) {
            if(visited[i] == 0) {
                visited[i] = 1;
                divide(count + 1, sum + populations[i]);
                visited[i] = 0;
            }
        }
    }
}