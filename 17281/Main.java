import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, maxPoint = 0;
	static int[] order = { 4, 5, 6, 0, 1, 2, 3, 7, 8 };
	static int[][] hitters;
	static boolean[] visited = new boolean[9];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		hitters = new int[N][9];

		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 9; j++) {
				hitters[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		order = new int[9];
		visited[0] = true;
		dfs(0);
		System.out.println(maxPoint);		
	}

	public static void simulation() {
		int out, point = 0, idx = 0;
		int[] bases = new int[3];
		// innings
		for(int i = 0; i < N; i++) {
			out = 0;
			for(int b = 0; b < 3; b++) bases[b] = 0;

			while(out < 3) {
				// order
				switch(hitters[i][order[idx]]) {
					case 0:
						out++;
						break;
					case 1:
						point += bases[2];
						bases[2] = bases[1];
						bases[1] = bases[0];
						bases[0] = 1;
						break;
					case 2:
						point += bases[2] + bases[1];
						bases[2] = bases[0];
						bases[1] = 1;
						bases[0] = 0;
						break;
					case 3:
						point += bases[2] + bases[1] + bases[0];
						bases[2] = 1;
						bases[1] = bases[0] = 0;
						break;
					case 4:
						point += bases[2] + bases[1] + bases[0] + 1;
						bases[2] = bases[1] = bases[0] = 0;
						break;
				}
	
				idx = (idx+1 < 9) ? idx+1 : 0;
			}
		}
		maxPoint = Math.max(maxPoint, point);
	}

	public static void dfs(int count) {
		if(count == 9) {
			simulation();
			return ;
		}

		if(count == 3) {
			dfs(count+1);
			return;
		}

		for(int i = 1; i < 9; i++) {
			if(visited[i]) continue;

			visited[i] = true;
			order[count] = i;
			dfs(count+1);
			visited[i] = false;
		}
	}
}







// import java.util.Scanner;
// import java.util.ArrayList;

// public class Main {
// 	static int maxVal = 0;
// 	static int N;
// 	static int[][] innings;
// 	public static void main(String args[]) throws Exception {
// 		Scanner sc = new Scanner(System.in);
// 		N = sc.nextInt();
// 		innings = new int[N][9];
// 		for(int i = 0; i < N; i++) {
// 			for(int j = 0; j < 9; j++) {
// 				innings[i][j] = sc.nextInt();
// 			}
// 		}
// 		sc.close();
		
// 		permutation(new ArrayList<Integer>(), 0);
// 		System.out.println(maxVal);
// 	}
	
// 	public static void permutation(ArrayList<Integer> order, int count) {
// 		if(count == 8) {
// 			order.add(3, 0);
// 			int point = 0;
// 			int index = 0;
// 			for(int i = 0; i < innings.length; i++) {
// 				int out = 0;
// 				int[] temp = {0,0,0};
// 				while(out < 3) {
// 					int val = innings[i][order.get(index)];
// 					if(val == 0) {
// 						out++;
// 					} else if(val == 1) {
// 						point += temp[2];
// 						temp[2] = temp[1];
// 						temp[1] = temp[0];
// 						temp[0] = 1;
// 					} else if(val == 2) {
// 						point += temp[2]+temp[1];
// 						temp[2] = temp[0];
// 						temp[1] = 1;
// 						temp[0] = 0;
// 					} else if(val == 3) {
// 						point += temp[0]+temp[1]+temp[2];
// 						temp[2] = 1;
// 						temp[1] = temp[0] = 0;
// 					} else if(val == 4) {
// 						point += temp[0]+temp[1]+temp[2]+1;
// 						temp[2] = temp[1] = temp[0] = 0;
// 					}
					
// 					index++;
// 					if(8 < index) index = 0;
// 				}
// 			}
// 			maxVal = Math.max(maxVal, point);
// //			for(int i = 0; i < 9; i++) {
// //				System.out.print(order.get(i));
// //			}
// //			System.out.println();
// 			order.remove(3);
// 		} else {
// 			for(int i = 1; i < 9; i++) {
// 				if(!order.contains(i)) {
// 					order.add(i);
// 					permutation(order, count+1);
// 					order.remove(order.size()-1);
// 				}
// 			}
// 		}
// 	}
// }
