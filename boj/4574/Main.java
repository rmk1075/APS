import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static int N, count;
    static int[] dominoes = new int[10], R = new int[9], C = new int[9], M = new int[9];
    static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
    static int[][] map = new int[9][9];
    static Map<Integer, Integer> matrix = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(i < 3) {
                    if(j < 3) matrix.put(i*9 + j, 0);
                    else if(j < 6) matrix.put(i*9 + j, 1);
                    else matrix.put(i*9 + j, 2);
                } else if(i < 6) {
                    if(j < 3) matrix.put(i*9 + j, 3);
                    else if(j < 6) matrix.put(i*9 + j, 4);
                    else matrix.put(i*9 + j, 5);
                } else {
                    if(j < 3) matrix.put(i*9 + j, 6);
                    else if(j < 6) matrix.put(i*9 + j, 7);
                    else matrix.put(i*9 + j, 8);
                }
            }
        }

        int t = 1;
        N = Integer.parseInt(br.readLine());
        while(N != 0) {
            StringTokenizer st;
            count = 81;
            for(int i = 0; i < 9; i++) {
                R[i] = C[i] = M[i] = dominoes[i] = 0;
                for(int j = 0; j < 9; j++) map[i][j] = 0;
            }
            dominoes[9] = 0;
            for(int n = 0; n < N; n++) {
                st = new StringTokenizer(br.readLine());

                int u = Integer.parseInt(st.nextToken());
                char[] lu = st.nextToken().toCharArray();
                int v = Integer.parseInt(st.nextToken());
                char[] lv = st.nextToken().toCharArray();

                dominoes[u] |= (1 << v);
                dominoes[v] |= (1 << u);

                int x = lu[0] - 'A', y = lu[1] - '1';
                map[x][y] = u;
                R[x] |= (1 << u);
                C[y] |= (1 << u);
                M[matrix.get(x*9 + y)] |= (1 << u);

                x = lv[0] - 'A';
                y = lv[1] - '1';
                map[x][y] = v;
                R[x] |= (1 << v);
                C[y] |= (1 << v);
                M[matrix.get(x*9 + y)] |= (1 << v);
                
                count -=2;
            }

            st = new StringTokenizer(br.readLine());
            for(int i = 1; i < 10; i++) {
                char[] loc = st.nextToken().toCharArray();
                int x = loc[0] - 'A', y = loc[1] - '1';
                map[x][y] = i;
                R[x] |= (1 << i);
                C[y] |= (1 << i);
                M[matrix.get(x*9 + y)] |= (1 << i);
            }
            count -= 9;

            dfs(0);
            sb.append("Puzzle " + t++ + "\n");
            for(int i = 0; i < 9; i++) {
                for(int j = 0; j < 9; j++) sb.append(map[i][j]);
                sb.append("\n");
            }
            N = Integer.parseInt(br.readLine());
        }
        System.out.println(sb.toString());
    }

    public static void uncheck(int r, int c, int num) {
        R[r] &= ~(1 << num);
        C[c] &= ~(1 << num);
        M[matrix.get(r*9 + c)] &= ~(1 << num);
        map[r][c] = 0;
    }

    public static boolean check(int r, int c, int num) {
        if((R[r] & (1 << num)) != 0) return false;
        if((C[c] & (1 << num)) != 0) return false;
        if((M[matrix.get(r*9 + c)] & (1 << num)) != 0) return false;

        R[r] |= (1 << num);
        C[c] |= (1 << num);
        M[matrix.get(r*9 + c)] |= (1 << num);
        map[r][c] = num;

        return true;
    }

    public static boolean dfs(int cnt) {
        if(cnt == count) return true;

        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(map[i][j] != 0) continue;
                for(int k = 1; k < 10; k++) {
                    if(!check(i, j, k)) continue;
                    int x, y;
                    for(int d = 0; d < 4; d++) {
                        x = i + dx[d];
                        y = j + dy[d];
                        if (x < 0 || y < 0 || x == 9 || y == 9 || map[x][y] != 0) continue;

                        for(int l = 1; l < 10; l++) {
                            if((dominoes[k] & (1 << l)) != 0) continue;
                            if(!check(x, y, l)) continue;
                            dominoes[k] |= (1 << l);
                            dominoes[l] |= (1 << k);

                            if(dfs(cnt + 2)) return true;

                            dominoes[k] &= ~(1 << l);
                            dominoes[l] &= ~(1 << k);
                            uncheck(x, y, l);
                        }
                    }
                    uncheck(i, j, k);
                }
                return false;
            }
        }
        return false;
    }
}

// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.util.HashMap;
// import java.util.Map;
// import java.util.StringTokenizer;

// public class Main {
//     static int N, count;
//     static int[] dominoes, R, C, M; // row, column, matrix - visited
//     static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
//     static int[][] map;
//     static Map<Integer, Integer> matrix = new HashMap<>();
//     public static void main(String[] args) throws IOException {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         StringBuilder sb = new StringBuilder();
//         for(int i = 0; i < 9; i++) {
//             for(int j = 0; j < 9; j++) {
//                 if(i < 3) {
//                     if(j < 3) matrix.put(i*9 + j, 0);
//                     else if(j < 6) matrix.put(i*9 + j, 1);
//                     else matrix.put(i*9 + j, 2);
//                 } else if(i < 6) {
//                     if(j < 3) matrix.put(i*9 + j, 3);
//                     else if(j < 6) matrix.put(i*9 + j, 4);
//                     else matrix.put(i*9 + j, 5);
//                 } else {
//                     if(j < 3) matrix.put(i*9 + j, 6);
//                     else if(j < 6) matrix.put(i*9 + j, 7);
//                     else matrix.put(i*9 + j, 8);
//                 }
//             }
//         }

//         int t = 1;
//         N = Integer.parseInt(br.readLine());
//         while(N != 0) {
//             StringTokenizer st;
//             count = 81;
//             R = new int[9];
//             C = new int[9];
//             M = new int[9];
//             map = new int[9][9];
//             dominoes = new int[10];
//             for(int n = 0; n < N; n++) {
//                 st = new StringTokenizer(br.readLine());

//                 int u = Integer.parseInt(st.nextToken());
//                 char[] lu = st.nextToken().toCharArray();
//                 int v = Integer.parseInt(st.nextToken());
//                 char[] lv = st.nextToken().toCharArray();

//                 dominoes[u] |= (1 << v);
//                 dominoes[v] |= (1 << u);

//                 int x = lu[0] - 'A', y = lu[1] - '1';
//                 map[x][y] = u;
//                 R[x] |= (1 << u);
//                 C[y] |= (1 << u);
//                 M[matrix.get(x*9 + y)] |= (1 << u);

//                 x = lv[0] - 'A';
//                 y = lv[1] - '1';
//                 map[x][y] = v;
//                 R[x] |= (1 << v);
//                 C[y] |= (1 << v);
//                 M[matrix.get(x*9 + y)] |= (1 << v);
                
//                 count -=2;
//             }

//             st = new StringTokenizer(br.readLine());
//             for(int i = 1; i < 10; i++) {
//                 char[] loc = st.nextToken().toCharArray();
//                 int x = loc[0] - 'A', y = loc[1] - '1';
//                 map[x][y] = i;
//                 R[x] |= (1 << i);
//                 C[y] |= (1 << i);
//                 M[matrix.get(x*9 + y)] |= (1 << i);
//             }
//             count -= 9;

//             dfs(0);

//             sb.append("Puzzle " + t++ + "\n");
//             for(int i = 0; i < 9; i++) {
//                 for(int j = 0; j < 9; j++) sb.append(map[i][j]);
//                 sb.append("\n");
//             }

//             N = Integer.parseInt(br.readLine());
//         }

//         System.out.println(sb.toString());
//     }

//     public static boolean check(int r, int c, int num) {
//         if((R[r] & (1 << num)) != 0) return false;
//         if((C[c] & (1 << num)) != 0) return false;
//         if((M[matrix.get(r*9 + c)] & (1 << num)) != 0) return false;
//         return true;
//     }

//     public static boolean dfs(int cnt) {
//         if(cnt == count) return true;

//         for(int i = 0; i < 9; i++) {
//             for(int j = 0; j < 9; j++) {
//                 if(map[i][j] != 0) continue;

//                 for(int k = 1; k < 10; k++) {
//                     if(!check(i, j, k)) continue;

//                     map[i][j] = k;
//                     R[i] |= (1 << k);
//                     C[j] |= (1 << k);
//                     M[matrix.get(i*9 + j)] |= (1 << k);

//                     int x, y;
//                     for(int d = 0; d < 4; d++) {
//                         x = i + dx[d];
//                         y = j + dy[d];
//                         if (x < 0 || y < 0 || x == 9 || y == 9 || map[x][y] != 0) continue;

//                         for(int l = 1; l < 10; l++) {
//                             if((dominoes[k] & (1 << l)) != 0) continue;
//                             if(!check(x, y, l)) continue;
//                             dominoes[k] |= (1 << l);
//                             dominoes[l] |= (1 << k);

//                             map[x][y] = l;
//                             R[x] |= (1 << l);
//                             C[y] |= (1 << l);
//                             M[matrix.get(x*9 + y)] |= (1 << l);

//                             if(dfs(cnt + 2)) return true;

//                             dominoes[k] &= ~(1 << l);
//                             dominoes[l] &= ~(1 << k);

//                             map[x][y] = 0;
//                             R[x] &= ~(1 << l);
//                             C[y] &= ~(1 << l);
//                             M[matrix.get(x*9 + y)] &= ~(1 << l);
//                         }
//                     }

//                     map[i][j] = 0;
//                     R[i] &= ~(1 << k);
//                     C[j] &= ~(1 << k);
//                     M[matrix.get(i*9 + j)] &= ~(1 << k);
//                 }

//                 return false;
//             }
//         }

//         return false;
//     }
// }