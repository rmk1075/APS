import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Num implements Comparable<Num> {
	int val, count;
	
	Num(int val, int count) {
		this.val = val;
		this.count = count;
	}
	
	@Override
	public int compareTo(Num o) {
		if(this.count == o.count) return this.val - o.val;
		else return this.count - o.count;
	}
}

public class Main {
	
	static int[] nums = new int[101];
	static int r, c, k, R = 3, C = 3;
	static int[][] A = new int[101][101];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < C; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int time = 0;
		for(time = 0; time <= 100; time++) {
			
			if(A[r - 1][c - 1] == k) break;
			
			LinkedList<Num> list = new LinkedList<Num>();
			
			int length;
			if(C <= R) {
				length = 0;
				for(int i = 0; i < R; i++) {
					for(int j = 0; j < C; j++) {
						nums[A[i][j]]++;
						A[i][j] = 0; // init
					}
					
					for(int j = 1; j < nums.length; j++) {
						if(nums[j] != 0) {
							list.add(new Num(j, nums[j]));
						}
					}
					
					Collections.sort(list);

					int idx = 0;
					for(Num n : list) {
						A[i][idx++] = n.val;
						A[i][idx++] = n.count;
					}
					
					length = Math.max(length, idx);
					
					list.clear();
					Arrays.fill(nums, 0);
				}
				
				C = length;
				
			} else {
				length = 0;
				for(int i = 0; i < C; i++) {
					for(int j = 0; j < R; j++) {
						nums[A[j][i]]++;
						A[j][i] = 0; // init
					}
					
					for(int j = 1; j < nums.length; j++) {
						if(nums[j] != 0) {
							list.add(new Num(j, nums[j]));
						}
					}
					
					Collections.sort(list);
					
					int idx = 0;
					for(Num n : list) {
						A[idx++][i] = n.val;
						A[idx++][i] = n.count;
					}
					
					length = Math.max(length, idx);
					
					list.clear();
					Arrays.fill(nums, 0);
				}
				
				R = length;
				
			}
			
		}
		
		if(100 < time) System.out.println(-1);
		else System.out.println(time);
	}

}
