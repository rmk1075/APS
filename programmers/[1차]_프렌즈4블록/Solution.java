class Solution {
    public int solution(int m, int n, String[] board) {
        int answer = 0;

        char[][] map = new char[m][n];
        for(int i = 0; i < m; i++) map[i] = board[i].toCharArray();

        while(true) {
            int count = clear(m, n, map);
            
            if(count == 0) break;
            else answer += count;
        }

        return answer;
    }

    public int clear(int m, int n, char[][] map) {
        
        // search
        int[] removed = new int[m];
        for(int i = 0; i < m - 1; i++) {
            for(int j = 0; j < n - 1; j++) {
                char ch = map[i][j];
                if(ch == ' ' || map[i + 1][j] != ch || map[i][j + 1] != ch || map[i + 1][j + 1] != ch) continue;
                removed[i] |= (1 << j);
                removed[i] |= (1 << (j + 1));
                removed[i + 1] |= (1 << j);
                removed[i + 1] |= (1 << (j + 1));
            }
        }

        // count
        int count = 0;        
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if((removed[i] & (1 << j)) != 0) {
                    map[i][j] = ' ';
                    count++;
                }
            }
        }

        if(count == 0) return 0;

        // drop
        for(int i = m - 2; -1 < i; i--) {
            for(int j = 0; j < n; j++) {
                if(map[i][j] == ' ') continue;

                int k = i + 1;
                for(; k < m; k++) {
                    if(map[k][j] != ' ') break;
                }

                k--;
                if(i == k) continue;

                map[k][j] = map[i][j];
                map[i][j] = ' ';
            }
        }

        return count;
    }
}

// class Solution {
//     public int solution(int m, int n, String[] board) {
//         int answer = 0;

//         char[][] map = new char[m][n];
//         for(int i = 0; i < m; i++) map[i] = board[i].toCharArray();

//         while(true) {
//             int count = clear(m, n, map);
            
//             if(count == 0) break;
//             else answer += count;
//         }

//         return answer;
//     }

//     public int clear(int m, int n, char[][] map) {
        
//         // search
//         boolean[][] removed = new boolean[m][n];
//         for(int i = 0; i < m - 1; i++) {
//             for(int j = 0; j < n - 1; j++) {
//                 char ch = map[i][j];
//                 if(ch == ' ' || map[i + 1][j] != ch || map[i][j + 1] != ch || map[i + 1][j + 1] != ch) continue;
//                 removed[i][j] = removed[i + 1][j] = removed[i][j + 1] = removed[i + 1][j + 1] = true;
//             }
//         }

//         // count
//         int count = 0;        
//         for(int i = 0; i < m; i++) {
//             for(int j = 0; j < n; j++) {
//                 if(removed[i][j]) {
//                     map[i][j] = ' ';
//                     count++;
//                 }
//             }
//         }

//         if(count == 0) return 0;

//         // drop
//         for(int i = m - 2; -1 < i; i--) {
//             for(int j = 0; j < n; j++) {
//                 if(map[i][j] == ' ') continue;

//                 int k = i + 1;
//                 for(; k < m; k++) {
//                     if(map[k][j] != ' ') break;
//                 }

//                 k--;
//                 if(i == k) continue;

//                 map[k][j] = map[i][j];
//                 map[i][j] = ' ';
//             }
//         }

//         return count;
//     }
// }