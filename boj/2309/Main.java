import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    static int[] height;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        height = new int[9];
        ArrayList<Integer> ans = new ArrayList<Integer>();

        for(int i = 0; i < 9; i++) {
            height[i] = sc.nextInt();
        }
        sc.close();

        find(0, ans);
    }

    public static void find(int sum, ArrayList<Integer> members) {
        if(members.size() == 7 && sum == 100) {
            int[] ans = new int[7];
            for(int i = 0; i < members.size(); i++) {
                ans[i] = height[members.get(i)];
            }

            Arrays.sort(ans);
            for(int i = 0; i < ans.length; i++) {
                System.out.println(ans[i]);
            }

            System.exit(0);
        }

        for(int i = 0; i < height.length; i++) {
            if(!members.contains(i)) {
                members.add(i);
                if(sum + height[i] <= 100) {
                    find(sum + height[i], members);
                }
                members.remove(members.size()-1);
            }
        }
    }
}