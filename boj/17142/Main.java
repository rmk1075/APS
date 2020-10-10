import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Virus {
    int x, y;

    Virus(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static Virus v;
    static int x, y, time, size;
    static int N, M, minTime = Integer.MAX_VALUE;
    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, 1, 0, -1 };
    static int[] activeViruses;
    static int[][] map, tempMap, visited;
    static ArrayList<Virus> viruses = new ArrayList<>();
    static Queue<Virus> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    viruses.add(new Virus(i, j));
                }
            }
        }

        activeViruses = new int[viruses.size()];
        combination(0, 0);

        if (minTime == Integer.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(minTime);
    }

    public static boolean check() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0 && visited[i][j] == 0)
                    return false;
            }
        }

        return true;
    }

    public static void combination(int index, int count) {
        if (count == M) {
            visited = new int[N][N];
            q = new LinkedList<Virus>();
            for (int i = 0; i < activeViruses.length; i++) {
                if (activeViruses[i] == 1) {
                    visited[viruses.get(i).x][viruses.get(i).y] = 1;
                    q.offer(viruses.get(i));
                }
            }

            time = 0;
            while (!q.isEmpty()) {
                if (check()) {
                    minTime = Math.min(minTime, time);
                    break;
                }

                size = q.size();
                for (int s = 0; s < size; s++) {
                    v = q.poll();
                    for (int d = 0; d < 4; d++) {
                        x = v.x + dx[d];
                        y = v.y + dy[d];

                        if (x < 0 || y < 0 || N <= x || N <= y || map[x][y] == 1 || visited[x][y] == 1)
                            continue;

                        visited[x][y] = 1;
                        q.offer(new Virus(x, y));
                    }
                }

                time++;
                if (minTime <= time)
                    return;
            }

            return;
        }

        if (viruses.size() - index < M - count)
            return;

        combination(index + 1, count);
        activeViruses[index] = 1;
        combination(index + 1, count + 1);
        activeViruses[index] = 0;
    }
}