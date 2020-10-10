import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N, temp, ans = 0, arr[] = new int[101];
    static boolean[] visited = new boolean[101], ansList = new boolean[101];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for(int i = 1; i <= N; i++) arr[i] = Integer.parseInt(br.readLine());
        for(int i = 1; i <= N; i++) {
            visited[i] = true;
            temp = i;
            find(i);
            visited[i] = false;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(ans + "\n");
        for(int i = 1; i <= N; i++) {
            if(ansList[i]) sb.append(i + "\n");
        }
        System.out.println(sb);
    }

    public static void find(int index) {
        if(!visited[arr[index]]) {
            visited[arr[index]] = true;
            find(arr[index]);
            visited[arr[index]] = false;
        }

        if(arr[index] == temp) {
            ans++;
            ansList[temp] = true;
        }
    }
}