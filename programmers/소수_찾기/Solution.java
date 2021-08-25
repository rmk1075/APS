import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

class Solution {
    Set<Integer> set = new HashSet<>();
    int picked = 0;
    int limit = 0;
    public int solution(String numbers) {
        int answer = 0;
        char[] c = numbers.toCharArray();
        Arrays.sort(c);

        int size = 0;
        int N = numbers.length();
        int[] arr = new int[N];
        for(int i = 0; i < N; i++) {
            int num = c[N - 1 - i] - '0';
            arr[i] = num;
            size = size * 10 + num;
        }

        boolean[] primes = new boolean[size + 1];
        getPrimes(primes, size);

        for(int i = 0; i < N; i++) limit |= 1 << i;

        int visited = 0;
        for(int i = 0; i < N; i++) {
            if((visited & (1 << arr[i])) != 0) continue;
            visited |= 1 << arr[i];
            picked |= 1 << i;
            dfs(primes, arr, N, arr[i]);
            picked &= ~(1 << i);
        }

        answer = set.size();
        return answer;
    }

    public void dfs(boolean[] primes, int[] arr, int N, int value) {
        if(!primes[value]) set.add(value);
        if(picked == limit) return ;

        int visited = 0;
        for(int i = 0; i < N; i++) {
            if((picked & (1 << i)) != 0 || (visited & (1 << arr[i])) != 0) continue;
            visited |= 1 << arr[i];
            picked |= 1 << i;
            dfs(primes, arr, N, value * 10 + arr[i]);
            picked &= ~(1 << i);
        }
    }

    public void getPrimes(boolean[] primes, int size) {
        int len = (int)Math.sqrt(size) + 1;
        primes[0] = primes[1] = true;
        for(int i = 2; i < len; i++) {
            if(primes[i]) continue;
            int j = i + i;
            while(j < primes.length) {
                primes[j] = true;
                j += i;
            }
        }
    }
}