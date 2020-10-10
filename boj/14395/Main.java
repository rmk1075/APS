import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

class Num {
    long val;
    ArrayList<Character> list = new ArrayList<>();

    Num(long val) {
        this.val = val;
    }

    Num(long val, ArrayList<Character> list, char ch) {
        this.val = val;
        this.list.addAll(list);
        this.list.add(ch);
    }
}

public class Main {
    static long S, T;
    static Set<Long> visited = new HashSet<>();
    static Queue<Num> queue = new LinkedList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        S = sc.nextInt();
        T = sc.nextInt();
        sc.close();

        if(S == T) {
            System.out.println(0);
            System.exit(0);
        }

        queue.offer(new Num(S));
        visited.add(S);

        int size;
        while(!queue.isEmpty()) {
            size = queue.size();
            while(0 < size--) {
                Num current = queue.poll();

                if(current.val == T) {
                    current.list.forEach((ch) -> System.out.print(ch));
                    System.exit(0);
                }
                
                // *
                if(!visited.contains(current.val * current.val)) {
                    visited.add(current.val * current.val);
                    queue.offer(new Num(current.val * current.val, current.list, '*'));
                }

                // +
                if(!visited.contains(current.val + current.val)) {
                    visited.add(current.val + current.val);
                    queue.offer(new Num(current.val + current.val, current.list, '+'));
                }
    
                if(current.val == 0L) continue;

                // -
                if(!visited.contains(0L)) {
                    visited.add(0L);
                    queue.offer(new Num(0, current.list, '-'));
                }
    
                // /
                if(current.val != 0 && !visited.contains(1L)) {
                    visited.add(1L);
                    queue.offer(new Num(1, current.list, '/'));
                }
            }
        }

        System.out.println(-1);
    }
}