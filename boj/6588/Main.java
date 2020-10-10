import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static int n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		while(n != 0) {
			
			for(int i = 3; i < n; i++) {
				if(cal(i) && cal(n - i)) {
					System.out.println(n + " = " + i + " + " + (n-i));
					break;
				}
			}
			
			n = Integer.parseInt(br.readLine());
		}
	}
	
	public static boolean cal(int n) {
		
		if(n % 2 == 0) return false;
		
		for(int i = 3; i <= Math.sqrt(n); i++) {
			if(n % i == 0) return false;
		}
		
		return true;
	}

}
