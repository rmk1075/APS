import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static int N, numOfAlphabet, maxVal = Integer.MIN_VALUE;
    static int[] values;
    static int[] nums = new int[10];
    static char[] alphabets;
    static char[][] words;
    static Map<Character, Integer> alphabet;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        words = new char[N][];
        for(int i = 0; i < N; i++) {
            words[i] = br.readLine().toCharArray();
        }

        alphabet = new HashMap<>();
        numOfAlphabet = 0;
        for(int i = 0; i < N; i++) {
            for(char a : words[i]) {
                if(alphabet.get(a) != null) continue;
                alphabet.put(a, numOfAlphabet++);    
            }
        }

        values = new int[numOfAlphabet];

        find(0);

        System.out.println(maxVal);
    }

    public static void find(int count) {
        if(count == numOfAlphabet) {
            // cal
            int sum = 0, temp = 0;
            for(int i = 0; i < N; i++) {
                temp = 0;
                for(char a : words[i]) {
                    temp = temp*10 + values[alphabet.get(a)];
                }

                sum += temp;
            }

            maxVal = Math.max(maxVal, sum);

            return ;
        }

        for(int i = 0; i < numOfAlphabet; i++) {
            if(nums[9-i] == 0) {
                nums[9-i] = 1;
                values[count] = 9-i;
                find(count+1);
                nums[9-i] = 0;
            }
        }
    }
}