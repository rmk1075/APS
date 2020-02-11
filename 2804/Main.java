import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main {
    static String[] A, B;
    static String[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = st.nextToken().split("");
        B = st.nextToken().split("");
        arr = new String[B.length][A.length];

        int x = 0, y = 0;
        loop1:
        for(int i = 0; i < A.length; i++) {
            for(int j = 0; j < B.length; j++) {
                if(A[i].equals(B[j])) {
                    x = i;
                    y = j;
                    break loop1;
                }
            }
        }
        
        for(int i = 0; i < A.length; i++) {
            arr[y][i] = A[i];
        }

        for(int i = 0; i < B.length; i++) {
            arr[i][x] = B[i];
        }

        for(int i = 0; i < B.length; i++) {
            for(int j = 0; j < A.length; j++) {
                if(arr[i][j] == null) System.out.print('.');
                else System.out.print(arr[i][j]);
            }
            System.out.println();
        }
    }
}