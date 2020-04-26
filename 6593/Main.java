import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Location {
    int l, r, c;

    Location(int l, int r, int c) {
        this.l = l;
        this.r = r;
        this.c = c;
    }
}

public class Main {
    static int L, R, C, srcL, srcR, srcC;
    static int[] dl = {-1, 1, 0, 0, 0, 0}, dr = {0, 0, -1, 1, 0, 0}, dc = {0, 0, 0, 0, -1, 1};
    static char building[][][] = new char[30][30][30];
    static int visited[][] = new int[30][30];
    static Queue<Location> queue = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        while(true) {
            st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            if(L == 0) break;

            for(int l = 0; l < L; l++) {
                for(int r = 0; r < R; r++) {
                    int c = 0;
                    for(char ch : br.readLine().toCharArray()) {
                        building[l][r][c] = ch;
                        if(ch == 'S') {
                            srcL = l;
                            srcR = r;
                            srcC = c;
                        }
                        c++;                    
                    }
                    visited[l][r] = 0;
                }
                br.readLine();
            }

            queue.clear();
            queue.offer(new Location(srcL, srcR, srcC));
            visited[srcL][srcR] |= (1 << srcC);
            boolean isExit = false;
            int l, r, c, size, time = 1;
            loop: while(!queue.isEmpty()) {
                size = queue.size();
                while(0 < size--) {
                    Location current = queue.poll();
                    for(int d = 0; d < 6; d++) {
                        l = current.l + dl[d];
                        r = current.r + dr[d];
                        c = current.c + dc[d];

                        if(l < 0 || r < 0 || c < 0 || l == L || r == R || c == C || ((visited[l][r] & (1 << c)) != 0) || building[l][r][c] == '#') continue;
                        if(building[l][r][c] == 'E') {
                            isExit = true;
                            break loop;
                        }

                        visited[l][r] |= (1 << c);
                        queue.offer(new Location(l, r, c));
                    }
                }
                time++;
            }
            sb.append((isExit) ? "Escaped in " + time + " minute(s).\n" : "Trapped!\n");
        }
        System.out.println(sb);
    }
}

// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.util.LinkedList;
// import java.util.Queue;
// import java.util.StringTokenizer;

// class Location {
//     int l, r, c;

//     Location(int l, int r, int c) {
//         this.l = l;
//         this.r = r;
//         this.c = c;
//     }
// }

// public class Main {
//     static int L, R, C, srcL, srcR, srcC;
//     static int[] dl = {-1, 1, 0, 0, 0, 0}, dr = {0, 0, -1, 1, 0, 0}, dc = {0, 0, 0, 0, -1, 1};
//     static char building[][][] = new char[30][30][30];
//     static boolean visited[][][] = new boolean[30][30][30];
//     static Queue<Location> queue = new LinkedList<>();
//     public static void main(String[] args) throws IOException {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         StringBuilder sb = new StringBuilder();
//         StringTokenizer st;
//         while(true) {
//             st = new StringTokenizer(br.readLine());
//             L = Integer.parseInt(st.nextToken());
//             R = Integer.parseInt(st.nextToken());
//             C = Integer.parseInt(st.nextToken());
//             if(L == 0) break;

//             for(int l = 0; l < L; l++) {
//                 for(int r = 0; r < R; r++) {
//                     int c = 0;
//                     for(char ch : br.readLine().toCharArray()) {
//                         building[l][r][c] = ch;
//                         if(ch == 'S') {
//                             srcL = l;
//                             srcR = r;
//                             srcC = c;
//                         }                        
//                         visited[l][r][c++] = false;
//                     }
//                 }
//                 br.readLine();
//             }

//             queue.clear();
//             queue.offer(new Location(srcL, srcR, srcC));
//             visited[srcL][srcR][srcC] = true;
//             boolean isExit = false;
//             int l, r, c, size, time = 1;
//             loop: while(!queue.isEmpty()) {
//                 size = queue.size();
//                 while(0 < size--) {
//                     Location current = queue.poll();
//                     for(int d = 0; d < 6; d++) {
//                         l = current.l + dl[d];
//                         r = current.r + dr[d];
//                         c = current.c + dc[d];
//                         if(l < 0 || r < 0 || c < 0 || l == L || r == R || c == C || visited[l][r][c] || building[l][r][c] == '#') continue;
//                         if(building[l][r][c] == 'E') {
//                             isExit = true;
//                             break loop;
//                         }

//                         visited[l][r][c] = true;
//                         queue.offer(new Location(l, r, c));
//                     }
//                 }
//                 time++;
//             }

//             sb.append((isExit) ? "Escaped in " + time + " minute(s).\n" : "Trapped!\n");
//         }
//         System.out.println(sb);
//     }
// }