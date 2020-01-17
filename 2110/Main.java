import java.util.Scanner;
import java.util.Arrays;

public class Main {
    static int N, C;
    static int[] houses;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        C = sc.nextInt();
        houses = new int[N];
        for(int i = 0; i < N; i++) {
            houses[i] = sc.nextInt();
        }
        sc.close();

        Arrays.sort(houses);
        
        int left = 0, right = houses[N-1]-houses[0], maxVal = 0;
        int interval = (left+right)/(C-1);
        while(left < right) {
            int router = houses[0], numOfRouter = 1;
            boolean isEnd = false;
            
            for(int i = 1; i < N; i++) {
                if(interval <= houses[i]-router) {
                    router = houses[i];
                    numOfRouter++;
                }

                if(C <= numOfRouter) {
                    isEnd = true;
                    break;
                }
            }

            if(isEnd) {
                maxVal = Math.max(maxVal, interval);
                left = interval+1;
            } else {
                right = interval;
            }
            interval = (left+right)/2;
        }

        int minVal = Integer.MAX_VALUE, router = houses[0];
        for(int i = 0; i < N; i++) {
            if(maxVal <= houses[i]-router) {
                minVal = Math.min(minVal, houses[i]-router);
                router = houses[i];
            }
        }

        System.out.println(minVal);
    }
}