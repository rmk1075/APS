import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.io.IOException;

class Tree implements Comparable<Tree> {
    int x, y, age;

    Tree(int x, int y, int age) {
        this.x = x;
        this.y = y;
        this.age = age;
    }

    @Override
    public int compareTo(Tree o) {
        return this.age <= o.age ? -1 : 1;
    }
}

public class Main {
    static int N, M, K;
    static int[] dx = {-1, 0, 1, 0, -1, -1, 1, 1};
    static int[] dy = {0, 1, 0, -1, -1, 1, -1, 1};
    static int[][] A, farm;
    static Deque<Tree> trees = new LinkedList<Tree>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        A = new int[N][N];
        farm = new int[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                farm[i][j] = 5;
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int j = 0; j < M; j++) {
            st = new StringTokenizer(br.readLine());
            trees.offer(new Tree(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken())));
        }

        Deque<Tree> tempTrees = new LinkedList<Tree>();
        Deque<Tree> deadTrees = new LinkedList<Tree>();
        for(int i = 0; i < K; i++) {
            // spring
            while(!trees.isEmpty()) {
                Tree tree = trees.poll();

                // dead tree
                if(farm[tree.x][tree.y] < tree.age) {
                    deadTrees.offer(tree);
                    continue;
                }

                farm[tree.x][tree.y] -= tree.age++;

                // autumn
                if(tree.age % 5 == 0) {
                    int x, y;
                    for (int j = 0; j < 8; j++) {
                        x = tree.x + dx[j];
                        y = tree.y + dy[j];

                        if (x < 0 || y < 0 || N <= x || N <= y)
                            continue;

                        tempTrees.offerFirst(new Tree(x, y, 1));
                    }
                }

                tempTrees.offerLast(tree);
            }

            if(!deadTrees.isEmpty()) {
                // summer
                while(!deadTrees.isEmpty()) {
                    Tree tree = deadTrees.poll();
                    farm[tree.x][tree.y] += tree.age / 2;
                }

                while(!tempTrees.isEmpty()) {
                    trees.offer(tempTrees.poll());
                }

            } else {
                while(!tempTrees.isEmpty()) {
                    trees.offer(tempTrees.poll());
                }
            }

            // winter
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    farm[j][k] += A[j][k];
                }
            }            
        }

        System.out.println(trees.size());
    }
}