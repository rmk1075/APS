import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, X;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        int map1[][] = new int[N][N], map2[][] = new int[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) map1[i][j] = map2[j][i] = Integer.parseInt(st.nextToken());
        }

        int ans = 0;
        for(int i = 0; i < N; i++) {
            ans += (check(map1, i)) ? 1 : 0;
            ans += (check(map2, i)) ? 1 : 0;
        }
        System.out.println(ans);
    }

    public static boolean check(int[][] map, int idx) {
        int cnt = 0, h = map[idx][0];
        for(int i = 0; i < N; i++) {
            if(map[idx][i] == h) {
                cnt++;
                continue;
            }

            if(1 < Math.abs(map[idx][i] - h)) return false;
            if(map[idx][i] == h+1) {
                if(cnt < X) return false;
                h = map[idx][i];
                cnt = 1;
            } else {
                if(N < X + i) return false;
                h--;
                for(int j = 1; j < X; j++) if(map[idx][++i] != h) return false;
                cnt = 0;
            }
        }
        return true;
    }
}

// import java.io.BufferedReader;
// import java.io.InputStreamReader;
// import java.io.IOException;
// import java.util.StringTokenizer;

// public class Main {

//     static int N, L, way;
//     static int[][] map;
//     static int[][][] covered;

//     public static void main(String[] args) throws IOException {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         StringTokenizer st = new StringTokenizer(br.readLine());

//         N = Integer.parseInt(st.nextToken());
//         L = Integer.parseInt(st.nextToken());

//         map = new int[N][N];
//         covered = new int[N][N][2];
//         for(int i = 0; i < N; i++) {
//             st = new StringTokenizer(br.readLine());
//             for(int j = 0; j < N; j++) {
//                 map[i][j] = Integer.parseInt(st.nextToken());
//             }
//         }

//         way = 0;
//         // TODO:
//         for(int i = 0; i < N; i++) {
//             line(i, 0, 0, 1); // row
//             line(0, i, 1, 0); // column
//         }
       
//         System.out.println(way);
//     }

//     public static void line(int x, int y, int dx, int dy) {

//         boolean check = true;
//         int x_ = x;
//         int y_ = y;
//         int[][][] beforeCovered = new int[N][N][2];
        
//         for(int i = 0; i < N - 1; i++) {
//             x_ += dx;
//             y_ += dy;

//             if(map[x_][y_] != map[x_ - dx][y_ - dy]) {

//                 if(Math.abs(map[x_][y_] - map[x_ - dx][y_ - dy]) == 1) {
                    
//                     boolean isBridge;
//                     if(map[x_][y_] < map[x_ - dx][y_ - dy]) {
//                         if(covered[x_][y_][dx] == 1) {
//                             check = false;
//                             break;
//                         }
//                         isBridge = bridge(x_, y_, dx, dy);
//                     } else {
//                         if(covered[x_ - dx][y_ - dy][dx] == 1) {
//                             check = false;
//                             break;
//                         }
//                         isBridge = bridge(x_ - dx, y_ - dy, -dx, -dy);
//                     }

//                     if(!isBridge) {
                       
//                         check = false;
//                         break;
//                     }
                    
//                 } else {
//                     check = false;
//                     break;
//                 }

//             }
//         }

//         if(check) {
           
//             way++;
//         } else {
//             for(int i = 0; i < N; i++) {
//                 for(int j = 0; j < N; j++) {
//                     covered[i][j] = beforeCovered[i][j];
//                 }
//             }
//         }
//     }

//     public static boolean bridge(int x, int y, int dx, int dy) {

//         if(x + dx * (L - 1) < 0 || y + dy * (L -1) < 0 || N <= x + dx * (L - 1) || N <= y + dy * (L - 1)) {
           
//             return false;
//         }

//         int x_ = x;
//         int y_ = y;
//         for(int i = 1; i < L; i++) {
//             x_ += dx;
//             y_ += dy;
//             if(map[x][y] != map[x_][y_]) return false;

//             if(covered[x_][y_][Math.abs(dx)] == 1) return false;
//         }

//         for(int i = 0; i < L; i++) {
//             covered[x + dx * i][y + dy * i][Math.abs(dx)] = 1;
//         }

//         return true;
//     }
// }