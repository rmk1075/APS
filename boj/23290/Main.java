import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int M, S, idx = 0;
    static int[] shark = new int[2];
    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] sx = {-1, 0, 1, 0};
    static int[] sy = {0, -1, 0, 1};
    static int[][] smells = new int[4][4];
    static List<Integer>[][] fishMap = new List[4][4];
    static List<Integer>[][] tempMap = new List[4][4];
    static List<Integer>[][] copyMap = new List[4][4];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                fishMap[i][j] = new LinkedList<>();
                tempMap[i][j] = new LinkedList<>();
                copyMap[i][j] = new LinkedList<>();
            }
        }
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            fishMap[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1].add(Integer.parseInt(st.nextToken()) - 1);
        }
        st = new StringTokenizer(br.readLine());
        shark[0] = Integer.parseInt(st.nextToken()) - 1;
        shark[1] = Integer.parseInt(st.nextToken()) - 1;

        while(0 < S--) {
            // 1. copy
            copy();

            // 2. fishes move
            fishesMove();
            
            // 3. shark move
            sharkMove();
            
            // 4. smells disappear
            disappear();

            // 5. paste
            paste();
        }

        int result = 0;
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) result += fishMap[i][j].size();
        }
        System.out.println(result);
    }

    public static void fishesMove() {
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                for(int d : fishMap[i][j]) {
                    int next = findDirection(i, j, d);
                    if(next == -1) tempMap[i][j].add(d);
                    else tempMap[i + dx[next]][j + dy[next]].add(next);
                }
            }
        }

        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                fishMap[i][j].clear();
                fishMap[i][j].addAll(tempMap[i][j]);
                tempMap[i][j].clear();
            }
        }
    }

    public static int findDirection(int x, int y, int fd) {
        int d = fd + 8;
        if(isValid(x, y, d % 8)) return d % 8;
        for(int i = 0; i < 7; i++) {
            d--;
            if(isValid(x, y, d % 8)) return d % 8;
        }
        return -1;
    }

    public static boolean isValid(int fx, int fy, int d) {
        int x = fx + dx[d];
        int y = fy + dy[d];
        return isInBound(x, y) && (shark[0] != x || shark[1] != y) && smells[x][y] == 0;
    }

    public static boolean isInBound(int x, int y) {
        return -1 < x && -1 < y && x < 4 && y < 4;
    }

    public static void sharkMove() {
        int x = shark[0];
        int y = shark[1];
        int[][] maxPath = findPath(x, y);
        exceptFishes(maxPath);

        shark[0] = maxPath[2][0];
        shark[1] = maxPath[2][1];
    }

    public static int[][] findPath(int x, int y) {
        int maxVal = -1;
        int[][] maxPath = new int[3][3];
        for(int i = 0; i < 4; i++) {
            int tempVal = 0;
            int ix = x + sx[i];
            int iy = y + sy[i];
            if(!isInBound(ix, iy)) continue;
            tempVal += fishMap[ix][iy].size();
            for(int j = 0; j < 4; j++) {
                int jx = ix + sx[j];
                int jy = iy + sy[j];
                if(!isInBound(jx, jy)) continue;
                tempVal += fishMap[jx][jy].size();
                for(int k = 0; k < 4; k++) {
                    int kx = jx + sx[k];
                    int ky = jy + sy[k];
                    if(!isInBound(kx, ky)) continue;
                    int ktemp = 0;
                    if(ix != kx || iy != ky) ktemp = fishMap[kx][ky].size();
                    tempVal += ktemp;
                    if(maxVal < tempVal) {
                        maxVal = tempVal;
                        maxPath[0][0] = ix;
                        maxPath[0][1] = iy;
                        maxPath[1][0] = jx;
                        maxPath[1][1] = jy;
                        maxPath[2][0] = kx;
                        maxPath[2][1] = ky;
                    }
                    tempVal -= ktemp;
                }
                tempVal -= fishMap[jx][jy].size();
            }
        }
        return maxPath;
    }

    public static void exceptFishes(int[][] maxPath) {
        int x = maxPath[0][0];
        int y = maxPath[0][1];
        if(fishMap[x][y].size() != 0) {
            fishMap[x][y].clear();
            smells[x][y] = 3;
        }

        x = maxPath[1][0];
        y = maxPath[1][1];
        if(fishMap[x][y].size() != 0) {
            fishMap[x][y].clear();
            smells[x][y] = 3;
        }

        x = maxPath[2][0];
        y = maxPath[2][1];
        if(maxPath[0][0] != x || maxPath[0][1] != y) {
            if(fishMap[x][y].size() != 0) {
                fishMap[x][y].clear();
                smells[x][y] = 3;
            }
        }
    }

    public static void disappear() {
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                if(smells[i][j] == 0) continue;
                smells[i][j]--;
            }
        }
    }

    public static void paste() {
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                fishMap[i][j].addAll(copyMap[i][j]);
                copyMap[i][j].clear();
            }
        }
    }

    public static void copy() {
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                copyMap[i][j].clear();
                copyMap[i][j].addAll(fishMap[i][j]);
            }
        }
    }
}
