import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    static int n, m;
    static int[][] cpus;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        cpus = new int[n+1][n+1];
        for(int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            cpus[a][b] = cpus[b][a] = 1;
        }
        sc.close();

        int[] visited = new int[n+1];
        visited[1] = 1;
        
        ArrayList<Integer> stack = new ArrayList<Integer>();
        stack.add(1);        
        
        int count = 0;
        while(!stack.isEmpty()) {
            int num = stack.get(stack.size()-1);
            stack.remove(stack.size()-1);
            for(int i = 1; i < n+1; i++) {
                if(cpus[num][i] == 1 && visited[i] == 0) {
                    stack.add(i);
                    visited[i] = 1;
                    count++;
                }
            }
        }

        System.out.println(count);
    }
}