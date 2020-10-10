import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Cal {
    int display, clipboard;

    Cal(int display, int clipboard) {
        this.display = display;
        this.clipboard = clipboard;
    }
}

public class Main {
    static int S;
    static int[][] visited = new int[2002][2002];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        S = sc.nextInt();
        sc.close();

        Queue<Cal> queue = new LinkedList<>();
        queue.offer(new Cal(1, 0));
        visited[1][0] = 0;

        int size, count = 1;
        while(!queue.isEmpty()) {
            size = queue.size();

            while(0 < size--) {
                Cal current = queue.poll();

                // delete
                if(0 < current.display && visited[current.display-1][current.clipboard] == 0) {
                    if(current.display-1 == S) {
                        System.out.println(count);
                        System.exit(0);
                    }

                    visited[current.display-1][current.clipboard] = 1;
                    queue.offer(new Cal(current.display-1, current.clipboard));
                }
                
                if(S < current.display) continue;
    
                // copy
                if(visited[current.display][current.display] == 0) {
                    visited[current.display][current.display] = 1;
                    queue.offer(new Cal(current.display, current.display));
                }
    
                // paste
                if(current.clipboard != 0 && current.display + current.clipboard <= 1000 && visited[current.display + current.clipboard][current.clipboard] == 0) {
                    if(current.display + current.clipboard == S) {
                        System.out.println(count);
                        System.exit(0);
                    }

                    visited[current.display + current.clipboard][current.clipboard] = 1;
                    queue.offer(new Cal(current.display + current.clipboard, current.clipboard));
                }

            }
            count++;
        }
        
        System.out.println(count);
    }
}