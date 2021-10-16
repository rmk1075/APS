class Solution {
    public int hIndex(int[] citations) {
        int N = citations.length;
        int[] counts = new int[N + 1];
        for(int citation : citations) {
            if(N < citation) counts[N]++;
            else counts[citation]++;
        }

        int sum = 0;
        for(int i = N; -1 < i; i--) {
            sum += counts[i];
            if(i <= sum) return i;
        }
        return 0;
    }
}

// import java.util.Arrays;

// class Solution {
//     public int hIndex(int[] citations) {
//         Arrays.sort(citations);
//         int N = citations.length;
//         int[] counts = new int[citations[N - 1] + 1];

//         int past = citations[N - 1];
//         for(int i = N - 1; -1 < i; i--) {
//             int current = citations[i];
//             while(current < past)  {
//                 if(past <= counts[past]) return past;
//                 past--;
//                 counts[past] = counts[past + 1];
//             }
//             counts[current]++;
//         }

//         int last = counts[citations[0]];
//         for(int i = citations[0]; -1 < i; i--) {
//             if(i <= last) return i;
//         }
//         return 0;
//     }
// }