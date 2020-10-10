import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Location {
    int x, y;

    Location(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int N, M, minDistance = Integer.MAX_VALUE;
    static ArrayList<Location> chickens = new ArrayList<Location>();
    static ArrayList<Location> houses = new ArrayList<Location>();
    static int[][] distances;
    static int[] open;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        open = new int[M];
        map = new int[N+1][N+1];

        for(int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j < N + 1; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) houses.add(new Location(i, j));
                if(map[i][j] == 2) chickens.add(new Location(i, j));
            }
        }

        calDistances();

        choice(0, 0);

        System.out.println(minDistance);
    }

    public static void calDistances() {

        distances = new int[houses.size()][chickens.size()];
        for(int i = 0; i < houses.size(); i++) {
            for(int j = 0; j < chickens.size(); j++) {
                distances[i][j] = Math.abs(houses.get(i).x - chickens.get(j).x) + Math.abs(houses.get(i).y - chickens.get(j).y);               
            }
        }

    }

    public static void choice(int num, int count) {

        if(count == M) {
            // cal distance
            distance();

            return ;
        }

        for(int i = num; i < chickens.size(); i++) {
            open[count] = i;
            choice(i, count + 1);
        }

    }

    public static void distance() {

        int distanceSum = 0;
        for(int i = 0; i < houses.size(); i++) {
            int eachDistance = Integer.MAX_VALUE;
            for(int j = 0; j < open.length; j++) {
                eachDistance = Math.min(eachDistance, distances[i][open[j]]);
            }

            distanceSum += eachDistance;

            if(minDistance <= distanceSum) return ;
        }

        minDistance = Math.min(minDistance, distanceSum);
    }
}