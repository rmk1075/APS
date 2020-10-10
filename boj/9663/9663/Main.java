import java.util.Scanner;

public class Main {

    static int N;
	static int[] map;
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        sc.close();
		map = new int[N];
		dfs(0);
		
		System.out.println(cnt);		
	}

	static int cnt;

	private static void dfs(int level) {
		
		if (level == N) {
			cnt++;
			return;
		}
		
		for (int i = 0; i < N; i++) {
			map[level] = i;
			
			if (isPromising(level)) {
				dfs(level + 1);
			}
			
			map[level] = 0;
		}
	}

	private static boolean isPromising(int level) {
		
		for (int i = 0; i < level; i++) {
			
			if (map[i] == map[level]) {
				return false;
			}
			
			if(Math.abs(level-i) == Math.abs(map[level]-map[i])) {
				return false;
			}
		}
		return true;
	}
}

// import java.util.Scanner;

// public class Main {
//     static int N, count = 0;
//     static int[] col;
//     static int[][] map;
//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         N = sc.nextInt();
//         sc.close();

//         map = new int[N][N];
//         col = new int[N];
        
//         find(0, 0);

//         System.out.println(count);
//     }

//     public static void find(int index, int cnt) {

//         if(cnt == N) {
//             count++;
//             return;
//         }

//         if(cnt < index) return;

//         for(int i = index; i < N; i++) {
//             for(int j = 0; j < N; j++) {
//                 if(col[j] == 1) continue;

//                 if(map[i][j] == 0 && check(i, j)) {
//                     map[i][j] = 1;
//                     col[j] = 1;

//                     find(i+1, cnt+1);

//                     map[i][j] = 0;
//                     col[j] = 0;
//                 }
//             }
//         }
//     }

//     public static boolean check(int x, int y) {

//         int r = x, c = y;
//         while(0 <= r && 0 <= c) {
//             if(map[r][c] == 1) return false;
//             r--;
//             c--;
//         }

//         r = x;
//         c = y;
//         while(0 <= r && c < N) {
//             if(map[r][c] == 1) return false;
//             r--;
//             c++;
//         }

//         return true;
//     }
// }