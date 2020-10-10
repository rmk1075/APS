import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int numOfOnes, minCount = Integer.MAX_VALUE;
    static int[] pieces = {5, 5, 5, 5, 5};
    static int[][] map = new int[10][10];
    static int[][] covered = new int[10][10];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        numOfOnes = 0;
        for(int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 10; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) numOfOnes++;
            }
        }

        if(numOfOnes == 0) {
            System.out.println(0);
            System.exit(0);
        }

        find(0, 0, 1, 0);
        if(minCount == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(minCount);
    }

    public static boolean cover(int x, int y, int num, int index) {
        int x_, y_;
        boolean isCover = true;
        loop:
        for(int i = 0; i <= num; i++) {
            for(int j = 0; j <= num; j++) {
                x_ = x + i;
                y_ = y + j;
                if(x_ == 10 || y_ == 10 || map[x_][y_] == 0 || covered[x_][y_] != 0) {
                    isCover = false;
                    break loop;
                }

                covered[x_][y_] = index;
            }
        }

        if(!isCover) {
            for (int i = 0; i <= num; i++) {
                for (int j = 0; j <= num; j++) {
                    x_ = x + i;
                    y_ = y + j;
                    if(x_ == 10 || y_ == 10 || map[x_][y_] == 0 || covered[x_][y_] != index) continue;
                    covered[x_][y_] = 0;
                }
            }
        }

        return isCover;
    }

    public static void find(int x, int y, int count, int sum) {
        if(numOfOnes < count) {
            minCount = Math.min(minCount, sum);
            return ;
        }
        
        if(minCount <= sum+1) return ;

        if (map[x][y] == 0 || covered[x][y] != 0) {
            if(y + 1 == 10) {
                find(x+1, 0, count, sum);
            } else {
                find(x, y+1, count, sum);
            }

            return ;
        }

        for (int k = 4; -1 < k; k--) {
            if (pieces[k] == 0 || 10 <= x + k || 10 <= y + k) continue;

            if (!cover(x, y, k, count)) continue;
            pieces[k]--;

            if(y + 1 == 10) find(x+1, 0, count+((k + 1) * (k + 1)), sum+1);
            else find(x, y+1, count+ ((k + 1) * (k + 1)), sum+1);

            // reset
            for(int i = 0; i <= k; i++) {
                for(int j = 0; j <= k; j++) covered[i + x][j + y] = 0;
            }
            pieces[k]++;
        }
    }
}




// import java.io.BufferedReader;
// import java.io.InputStreamReader;
// import java.io.IOException;
// import java.util.StringTokenizer;

// public class Main {
//     static int N = 10;
//     static int minValue = Integer.MAX_VALUE;
//     static int[] pieces = {0, 0, 0, 0, 0, 0};
//     static int[][] paper = new int[N][N];
//     public static void main(String[] args) throws IOException {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         StringTokenizer st;

//         for(int i = 0; i < N; i++) {
//             st = new StringTokenizer(br.readLine());
//             for(int j = 0; j < N; j++) {
//                 paper[i][j] = Integer.parseInt(st.nextToken());
//             }
//         }
//         br.close();

//         dfs(0, 0);

//         if(minValue == Integer.MAX_VALUE) {
//             System.out.println(-1);
//         } else {
//             System.out.println(minValue);
//         }
//     }

//     public static void dfs(int x, int y) {
//         if(x == N) {

//             if(isClear()) {
//                 int temp = 0;
//                 for(int i = 1; i < 6; i++) temp += pieces[i];

//                 minValue = Math.min(minValue, temp);
//             }

//             return ;
//         }

//         if(paper[x][y] == 1) {
//             for(int i = 1; i < 6; i++) {
//                 if(pieces[i] == 5) continue;

//                 if(cover(x, y, i)) {

//                     if(y + 1 < N)
//                         dfs(x, y + 1);
//                     else
//                         dfs(x + 1, 0);

//                     uncover(x, y, i);
//                 }
//             }

//         } else {
//             if(y + 1 < N)
//                 dfs(x, y + 1);
//             else
//                 dfs(x + 1, 0);
//         }
//     }

//     public static boolean cover(int x, int y, int size) {
//         boolean isCovered = true;

//         // check the field
//         for(int i = x; i < x + size; i++) {
//             for(int j = y; j < y + size; j++) {
//                 if(N - 1 < i || N - 1 < j || paper[i][j] == 0) return false;
//             }
//         }

//         // cover the field
//         for(int i = x; i < x + size; i++) {
//             for(int j = y; j < y + size; j++) {
//                 paper[i][j] = 0;
//             }
//         }

//         // change the pieces arr
//         pieces[size]++;
        
//         return isCovered;
//     }

//     public static void uncover(int x, int y, int size) {
//         // uncover the field
//         for(int i = x; i < x + size; i++) {
//             for(int j = y; j < y + size; j++) {
//                 paper[i][j] = 1;
//             }
//         }

//         // change the pieces arr
//         pieces[size]--;
//     }

//     public static boolean isClear() {
//         for(int i = 0; i < N; i++) {
//             for(int j = 0; j < N; j++) {
//                 if(paper[i][j] == 1) return false;
//             }
//         }

//         return true;
//     }
// }