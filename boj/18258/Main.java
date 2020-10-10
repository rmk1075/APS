import java.io.*;

public class Main {
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int front = 0;
        int tail = 0;
        int N = Integer.parseInt(br.readLine());
        int[] queue = new int[N];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            switch (input.split(" ")[0]) {
                case "push":
                    queue[tail++] = Integer.parseInt(input.split(" ")[1]);
                    break;

                case "pop":
                    if (tail == front) {
                        bw.write(String.valueOf(-1)+"\n");
                    } else {
                        bw.write(String.valueOf(queue[front++])+"\n");
                    }
                    break;

                case "size":
                    bw.write(String.valueOf(tail-front)+"\n");
                    break;

                case "empty":
                    if (tail == front) {
                        bw.write(String.valueOf(1)+"\n");
                    } else {
                        bw.write(String.valueOf(0)+"\n");
                    }
                    break;

                case "front":
                    if (tail == front) {
                        bw.write(String.valueOf(-1)+"\n");
                    } else {
                        bw.write(String.valueOf(queue[front])+"\n");
                    }
                    break;

                case "back":
                    if (tail == front) {
                        bw.write(String.valueOf(-1)+"\n");
                    } else {
                        bw.write(String.valueOf(queue[tail-1])+"\n");
                    }
                    break;
            }
            bw.flush();
        }
    }
}