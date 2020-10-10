import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    static int N, M, V;
    static int[][] map;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        V = sc.nextInt();

        map = new int[N + 1][N + 1];
        for(int i = 0; i < M; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            map[x][y] = 1;
            map[y][x] = 1;
        }
        
        dfs();
        System.out.println();
        bfs();

        sc.close();
    }

    public static void bfs() {

        int[] visited = new int[N + 1];
        Queue<Integer> queue = new LinkedList<Integer>();
        visited[V] = 1;
        queue.offer(V);

        int current;
        while(!queue.isEmpty()) {
            current = queue.poll();
            System.out.print(current + " ");

            for(int i = 1; i < N + 1; i++) {
                if(map[current][i] == 1 && visited[i] == 0) {
                    visited[i] = 1;
                    queue.offer(i);
                }
            }
        }


    }

    public static void dfs() {

        int[] visited = new int[N + 1];
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(V);

        int current;
        while(!stack.isEmpty()) {
            current = stack.pop();
            if(visited[current] == 0){
                visited[current] = 1;
                System.out.print(current + " ");
            }

            for(int i = N; 0 < i; i--) {
                if(map[current][i] == 1 && visited[i] == 0) {
                    stack.push(i);
                }
            }
        }
    }
}