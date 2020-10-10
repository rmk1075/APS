import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Location {
    int x, y;

    Location(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int N, M, v, space, minTime = Integer.MAX_VALUE;
    static int[] order;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] map, viruses;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        order = new int[M];
        map = new int[N][N];
        viruses = new int[10][2];


        v = 0;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] == 2) {
                    viruses[v][0] = i;
                    viruses[v++][1] = j;
                } else if(map[i][j] == 0) space++;
            }
        }
        space += v;

        pick(0, 0);

        if(minTime == Integer.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(minTime);
    }

    public static void pick(int index, int count) {
        if(count == M) {
            spread();
            return ;
        }

        for(int i = index; i < v; i++) {
            order[count] = i;
            pick(i + 1, count + 1);
        }
    }

    public static void spread() {

        // init tempMap
        int[][] tempMap = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tempMap[i][j] = map[i][j];
            }
        }

        Queue<Location> queue = new LinkedList<Location>();
        for (int i = 0; i < M; i++) {
            queue.offer(new Location(viruses[order[i]][0], viruses[order[i]][1]));
            tempMap[viruses[order[i]][0]][viruses[order[i]][1]] = 3;
        }
        
        int time = 0;
        int spreadedSpace = 0;
        while (!queue.isEmpty()) {

            if (spreadedSpace == space - M) {
                minTime = Math.min(minTime, time);
                return;
            }

            if(minTime < time) return ;

            int size = queue.size();
            for (int s = 0; s < size; s++) {
                Location current = queue.poll();

                for (int i = 0; i < 4; i++) {
                    int x = current.x + dx[i];
                    int y = current.y + dy[i];

                    if (x < 0 || y < 0 || N <= x || N <= y || tempMap[x][y] == 1 || tempMap[x][y] == 3) continue;

                    spreadedSpace++;
                    tempMap[x][y] = 3;
                    queue.offer(new Location(x, y));
                }
            }

            time++;
        }

        if(spreadedSpace == space - M) 
            minTime = Math.min(minTime, time);

    }
}