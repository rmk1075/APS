import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Location {
    int x, y, index, pieces;

    Location(int x, int y, int index, int pieces) {
        this.x = x;
        this.y = y;
        this.index = index;
        this.pieces = pieces;
    }
}

public class Main {
    static int N;
    static int[] dx1 = {-1, -2, -2, -1, 1, 2, 2, 1}, dy1 = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] dx2 = {-1, -1, 1, 1}, dy2 = {-1, 1, 1, -1};
    static int[] dx3 = {-1, 0, 1, 0}, dy3 = {0, 1, 0, -1};
    static int[][] map;
    static int[][][][] update;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        int srcX = 0, srcY = 0;
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken()) - 1;
                if(map[i][j] == 0) {
                    srcX = i;
                    srcY = j;
                }
            }
        }

        update = new int[N][N][N*N][3];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                for(int k = 0; k < N*N; k++) update[i][j][k][0] = update[i][j][k][1] = update[i][j][k][2] = -1;
            }
        }

        Queue<Location> queue = new LinkedList<>();
        for(int i = 0; i < 3; i++) {
            queue.offer(new Location(srcX, srcY, 0, i));
            update[srcX][srcY][0][i] = 0;
        }

        int ans = Integer.MAX_VALUE;
        while(!queue.isEmpty()) {
            Location current = queue.poll();
            int x = current.x, y = current.y, dest = current.index, pieces = current.pieces, val = update[x][y][dest][pieces];

            // TODO:
            // System.out.println(val + " : " + x + " " + y + " " + dest + " " + pieces);

            if(dest == N*N-1) {
                ans = Math.min(ans, val);
                continue;
            }

            // change pieces
            for(int i = 0; i < 3; i++) {
                if(pieces == i || update[x][y][dest][i] != -1) continue;
                update[x][y][dest][i] = val + 1;
                queue.offer(new Location(x, y, dest, i));
            }

            // move
            switch(pieces) {
                case 0:
                    // knight
                    for(int d = 0; d < 8; d++) {
                        int x_ = x + dx1[d], y_ = y + dy1[d];
                        if(x_ < 0 || y_ < 0 || N <= x_ || N <= y_) continue;
                        
                        int temp = (map[x_][y_] == dest+1) ? dest+1 : dest ;
                        if(update[x_][y_][temp][pieces] == -1) {
                            update[x_][y_][temp][pieces] = val + 1;
                            queue.offer(new Location(x_, y_, temp, pieces));
                        }
                    }
                    break;
                case 1:
                    // bishop
                    for(int d = 0; d < 4; d++) {
                        int x_ = x, y_ = y;
                        while(true) {
                            x_ += dx2[d];
                            y_ += dy2[d];
                            if(x_ < 0 || y_ < 0 || N <= x_ || N <= y_) break;
                        
                            int temp = (map[x_][y_] == dest+1) ? dest+1 : dest ;
                            if(update[x_][y_][temp][pieces] == -1) {
                                update[x_][y_][temp][pieces] = val + 1;
                                queue.offer(new Location(x_, y_, temp, pieces));
                            }
                        }
                    }
                    break;
                case 2:
                    // rook
                    for(int d = 0; d < 4; d++) {
                        int x_ = x, y_ = y;
                        while(true) {
                            x_ += dx3[d];
                            y_ += dy3[d];
                            if(x_ < 0 || y_ < 0 || N <= x_ || N <= y_) break;
                        
                            int temp = (map[x_][y_] == dest+1) ? dest+1 : dest ;
                            if(update[x_][y_][temp][pieces] == -1) {
                                update[x_][y_][temp][pieces] = val + 1;
                                queue.offer(new Location(x_, y_, temp, pieces));
                            }
                        }
                    }
                    break;
            }
        }

        System.out.println(ans);
    }
}