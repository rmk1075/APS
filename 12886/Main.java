import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

class Stone {
    int[] stone = new int[3];

    Stone(int[] stone) {
        this.stone = Arrays.copyOf(stone, 3);
    }
}

public class Main {
    static int[] stones = new int[3];
    static Set<String> visited = new HashSet<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for(int i = 0; i < 3; i++) stones[i] = sc.nextInt();
        sc.close();
        
        Arrays.sort(stones);
        visited.add(stones[0] + "" + stones[1] + "" + stones[2]);

        Queue<Stone> queue = new LinkedList<>();
        queue.offer(new Stone(stones));

        while(!queue.isEmpty()) {
            Stone current = queue.poll();

            if(current.stone[0] == current.stone[1] && current.stone[1] == current.stone[2]) {
                System.out.println(1);
                System.exit(0);
            }

            int x = 0, y = 0;
            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    if(i == j || current.stone[j] <= current.stone[i]) continue;

                    x = current.stone[i] + current.stone[i];
                    y = current.stone[j] - current.stone[i];

                    if(x == y && y == current.stone[3 - i - j]) {
                        System.out.println(1);
                        System.exit(0);
                    }

                    stones[0] = x;
                    stones[1] = y;
                    stones[2] = current.stone[3 - i - j];

                    Arrays.sort(stones);
                    if(!visited.contains(stones[0] + "" + stones[1] + "" + stones[2])) {
                        visited.add(stones[0] + "" + stones[1] + "" + stones[2]);
                        queue.offer(new Stone(stones));
                    }
                }
            }
        }

        System.out.println(0);
    }
}