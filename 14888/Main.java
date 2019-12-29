import java.util.Scanner;

public class Main {
    public static int maxNum = -1000000000;
    public static int minNum = 1000000000;
    public static int N = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        int[] nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = sc.nextInt();
        }

        int[] ops = new int[4];
        for (int i = 0; i < 4; i++) {
            ops[i] = sc.nextInt();
        }
        sc.close();

        cal(ops, nums, nums[0], 1);

        System.out.println(maxNum);
        System.out.println(minNum);
    }

    public static void cal(int[] ops, int[] nums, int val, int count) {
        if (count == N) {
            maxNum = Math.max(maxNum, val);
            minNum = Math.min(minNum, val);
        } else {
            for (int i = 0; i < 4; i++) {
                if (0 < ops[i]) {
                    ops[i]--;
                    switch(i) {
                        case 0:
                            cal(ops, nums, val+nums[count], count+1);
                            break;
                        case 1:
                            cal(ops, nums, val-nums[count], count+1);
                            break;
                        case 2:
                            cal(ops, nums, val*nums[count], count+1);
                            break;
                        case 3:
                            cal(ops, nums, (int)(val/nums[count]), count+1);
                            break;
                    }
                    ops[i]++;
                }
            }
        }
    }
}

// import java.util.Scanner;

// public class Main {
//     static int maxNum = -1000000001;
//     static int minNum = 1000000001;
//     public static int N = 0;
//     public static int[] nums, ops;

//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);

//         N = sc.nextInt();
//         nums = new int[N];
//         for (int i = 0; i < N; i++) {
//             nums[i] = sc.nextInt();
//         }

//         ops = new int[4];
//         for (int i = 0; i < 4; i++) {
//             ops[i] = sc.nextInt();
//         }
//         sc.close();

//         cal(ops[0], ops[1], ops[2], ops[3], nums[0], 1);

//         System.out.println(maxNum);
//         System.out.println(minNum);
//     }

//     public static void cal(int add, int sub, int mul, int div, int val, int count) {
//         if (count == N) {
//             maxNum = Math.max(maxNum, val);
//             minNum = Math.min(minNum, val);
//         } else {
//             if (add != 0) {
//                 cal(add - 1, sub, mul, div, val + nums[count], count + 1);
//             }
//             if (sub != 0) {
//                 cal(add, sub - 1, mul, div, val - nums[count], count + 1);
//             }
//             if (mul != 0) {
//                 cal(add, sub, mul - 1, div, val * nums[count], count + 1);
//             }
//             if (div != 0) {
//                 cal(add, sub, mul, div - 1, val / nums[count], count + 1);
//             }
//         }
//     }
// }