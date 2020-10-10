import java.util.Scanner;
import java.util.Arrays;

public class Main {
    static int N;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] map, visited;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        map = new int[N][N];
        visited = new int[N][N];

        int total = 0;
        for(int i = 0; i < N; i++) {
            String temp = sc.next();
            
            for(int j = 0; j < N; j++) {
                map[i][j] = temp.charAt(j) - '0';
                
                if(map[i][j] == 1) total++;
            }
        }
        sc.close();

        int[] sections = new int[N*N];
        int index = 0;
        int count = 0;
        loop1:
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(map[i][j] == 1 && visited[i][j] == 0) {
                    sections[index] = dfs(i, j);
                    count += sections[index++];
                }

                if(count == total) break loop1;
            }
        }

        System.out.println(index);
        Arrays.sort(sections);
        for(int i = sections.length-index; i < sections.length; i++) {
            System.out.println(sections[i]);
        }
    }

    public static int dfs(int x, int y) {
        int count = 1;
        visited[x][y] = 1;
        for (int i = 0; i < 4; i++) {
            int a = x+dx[i];
            int b = y+dy[i];
            if (0 <= a && a < N && 0 <= b && b < N && map[a][b] == 1 && visited[a][b] == 0) {
                count += dfs(a, b);
            }
        }

        return count;
    }
}