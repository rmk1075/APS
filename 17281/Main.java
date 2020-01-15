import java.util.Scanner;
import java.util.ArrayList;

public class Main {
	static int maxVal = 0;
	static int N;
	static int[][] innings;
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		innings = new int[N][9];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < 9; j++) {
				innings[i][j] = sc.nextInt();
			}
		}
		sc.close();
		
		permutation(new ArrayList<Integer>(), 0);
		System.out.println(maxVal);
	}
	
	public static void permutation(ArrayList<Integer> order, int count) {
		if(count == 8) {
			order.add(3, 0);
			int point = 0;
			int index = 0;
			for(int i = 0; i < innings.length; i++) {
				int out = 0;
				int[] temp = {0,0,0};
				while(out < 3) {
					int val = innings[i][order.get(index)];
					if(val == 0) {
						out++;
					} else if(val == 1) {
						point += temp[2];
						temp[2] = temp[1];
						temp[1] = temp[0];
						temp[0] = 1;
					} else if(val == 2) {
						point += temp[2]+temp[1];
						temp[2] = temp[0];
						temp[1] = 1;
						temp[0] = 0;
					} else if(val == 3) {
						point += temp[0]+temp[1]+temp[2];
						temp[2] = 1;
						temp[1] = temp[0] = 0;
					} else if(val == 4) {
						point += temp[0]+temp[1]+temp[2]+1;
						temp[2] = temp[1] = temp[0] = 0;
					}
					
					index++;
					if(8 < index) index = 0;
				}
			}
			maxVal = Math.max(maxVal, point);
//			for(int i = 0; i < 9; i++) {
//				System.out.print(order.get(i));
//			}
//			System.out.println();
			order.remove(3);
		} else {
			for(int i = 1; i < 9; i++) {
				if(!order.contains(i)) {
					order.add(i);
					permutation(order, count+1);
					order.remove(order.size()-1);
				}
			}
		}
	}
}
