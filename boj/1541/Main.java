import java.util.Scanner;

public class Main {
    static String exp;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        exp = sc.nextLine();
        sc.close();

        int[] ans = new int[exp.split("-").length];
        for(int i = 0; i < exp.split("-").length; i++) {
            for (String val : exp.split("-")[i].split("\\+")) {
                ans[i] += Integer.parseInt(val);
            }
        }

        int val = ans[0];
        for(int i = 1; i < ans.length; i++) {
            val -= ans[i];
        }
        System.out.println(val);
    }
}