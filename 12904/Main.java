import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static String S;
    static StringBuilder T = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        S = br.readLine();
        T.append(br.readLine());

        if(T.toString().equals(S)) {
            System.out.println(1);
            System.exit(0);
        }
        
        while(!T.toString().equals(S) && S.length() < T.length()) {
            if(T.charAt(T.length()-1) == 'A') T.deleteCharAt(T.length()-1);
            else T.deleteCharAt(T.length()-1).reverse();

            if(T.toString().equals(S)) {
                System.out.println(1);
                System.exit(0);
            }
        }
        
        System.out.println(0);
    }
}