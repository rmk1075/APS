import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Horse {
    int x, y, d;

    Horse(int x, int y, int d) {
        this.x = x;
        this.y = y;
        this.d = d;
    }
}

public class Main {
    static int N, K, turn = 1;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int[][] map;
    static ArrayList<Integer>[][] horsesOnMap;
    static Horse[] horses;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        // input: map
        horsesOnMap = new ArrayList[N][N];
        map = new int[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                horsesOnMap[i][j] = new ArrayList<Integer>();
            }
        }
        
        // input: horse
        horses = new Horse[K];
        for(int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            horses[k] = new Horse(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1);
            horsesOnMap[horses[k].x][horses[k].y].add(k);
        }

        // play
        loop:
        while(turn < 1001) {
            // each horse
            for(int h = 0; h < K; h++) {
                if(move(h)) break loop;
            }

            turn++;
        }

        if(1000 < turn) System.out.println(-1);
        else System.out.println(turn);
    }

    public static boolean move(int index) {
        int d = horses[index].d;
        int x = horses[index].x + dx[d];
        int y = horses[index].y + dy[d];

        // white 0, red 1, blue 2
        // blue of out of map
        if(x < 0 || y < 0 || N <= x || N <= y || map[x][y] == 2) {

            if(d == 0) d = 1;
            else if(d == 1) d = 0;
            else if(d == 2) d = 3;
            else if(d == 3) d = 2;
            horses[index].d = d;

            x = horses[index].x + dx[d];
            y = horses[index].y + dy[d];            

            if(x < 0 || y < 0 || N <= x || N <= y || map[x][y] == 2) return false;

            move(index);

        } else {
            // white or red
            int horseX = horses[index].x;
            int horseY = horses[index].y;
            
            LinkedList<Integer> temp = new LinkedList<Integer>();
            int idx = horsesOnMap[horseX][horseY].indexOf(index);

            while(idx < horsesOnMap[horseX][horseY].size()) {
                temp.offer(horsesOnMap[horseX][horseY].remove(idx));
            }

            while(!temp.isEmpty()) {
                int current;
                if(map[x][y] == 0) current = temp.pollFirst(); // if white
                else current = temp.pollLast(); // if red

                horsesOnMap[x][y].add(current);
                horses[current].x = x;
                horses[current].y = y;
            }
            
            if(4 <= horsesOnMap[x][y].size()) {
                System.out.println(turn);
                System.exit(0);
            }
        }
        
        return false;
    }
}