import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] temp, arr;
    static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        int sum = 0;
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            int tmp = Integer.parseInt(st.nextToken()); 
            list.add(tmp);
            sum += tmp;
        } 
        
        arr = new int[sum+2];
        arr[0] = 1;

        int num;
        for (int i = 0; i < N; i++) {
            temp = new int[sum+2];

            num = list.get(i);
            for (int j = 0; j < sum; j++) {
                if (arr[j] == 1) {
                    temp[j] = 1;
                    temp[j + num] = 1;
                }
            }

            arr = Arrays.copyOf(temp, sum+2);
        }

        for (int i = 1; i < sum+2; i++) {
            if (arr[i] == 0) {
                System.out.println(i);
                break;
            }
        }
    }
}

// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.util.Arrays;
// import java.util.StringTokenizer;

// public class Main {
//     static int N;
//     static int[] temp, arr = new int[2000001];
//     public static void main(String[] args) throws IOException {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         StringTokenizer st;

//         N = Integer.parseInt(br.readLine());
        
//         int num;
//         arr[0] = 1;
//         st = new StringTokenizer(br.readLine());
//         for(int i = 0; i < N; i++) {
//             temp = new int[20000001];
//             num = Integer.parseInt(st.nextToken());
//             for(int j = 0; j < arr.length; j++) {
//                 if(arr[j] == 1 && (num + j < arr.length)) {
//                     temp[j] = 1;
//                     temp[j + num] = 1;
//                 }
//             }

//             arr = Arrays.copyOf(temp, temp.length);
//         }

//         for(int i = 1; i < arr.length; i++) {
//             if(arr[i] == 0) {
//                 System.out.println(i);
//                 break;
//             }
//         }
//     }
// }