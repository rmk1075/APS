import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

class Hanoi {
    String[] towers = {"", "", ""};

    Hanoi(String[] towers) {
        for(int i = 0; i < 3; i++) {
            this.towers[i] = towers[i].toString();
        }
    }

    public String toString() {
        return towers[0] + "|" + towers[1] + "|" + towers[2];
    }
}

public class Main {
    static int[][] move = {{0, 1}, {0, 2}, {1, 0}, {1, 2}, {2, 0}, {2, 1}};
    static String[] hanoi = new String[3];
    static Set<String> visited = new HashSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            if(Integer.parseInt(st.nextToken()) == 0) {
                hanoi[i] = "";
            } else {
                hanoi[i] = st.nextToken();
            }            
        }

        if(check(hanoi)) {
            System.out.println(0);
            System.exit(0);
        }

        visited.add(hanoi[0] + "|" + hanoi[1] + "|" + hanoi[2]);
        Queue<Hanoi> queue = new LinkedList<>();
        queue.offer(new Hanoi(hanoi));

        int size, count = 1;
        while(!queue.isEmpty()) {
            size = queue.size();
            while(0 < size--) {
                Hanoi h = queue.poll();

                // TODO:
                // System.out.println(count);
                // System.out.println(h.toString());

                if(check(h.towers)) {
                    System.out.println((count-1));
                    System.exit(0);
                }

                for(int i = 0; i < 6; i++) {
                    if(h.towers[move[i][0]].length() != 0) {
                        // move
                        h.towers[move[i][1]] += h.towers[move[i][0]].substring(h.towers[move[i][0]].length()-1);
                        h.towers[move[i][0]] = h.towers[move[i][0]].substring(0, h.towers[move[i][0]].length()-1);

                        
                        if(visited.contains(h.toString())) {
                            h.towers[move[i][0]] += h.towers[move[i][1]].substring(h.towers[move[i][1]].length() - 1);
                            h.towers[move[i][1]] = h.towers[move[i][1]].substring(0, h.towers[move[i][1]].length() - 1);
                            continue;
                        }
                        // TODO:
                        // System.out.println(h.toString());

                        visited.add(h.toString());
                        queue.offer(new Hanoi(h.towers));

                        h.towers[move[i][0]] += h.towers[move[i][1]].substring(h.towers[move[i][1]].length()-1);
                        h.towers[move[i][1]] = h.towers[move[i][1]].substring(0, h.towers[move[i][1]].length()-1);
                    }
                }
            }

            count++;
        }
    }

    public static boolean check(String[] h) {
        if(h[0].contains("B") || h[0].contains("C")) return false;
        if(h[1].contains("A") || h[1].contains("C")) return false;
        if(h[2].contains("B") || h[2].contains("A")) return false;

        return true;
    }
}