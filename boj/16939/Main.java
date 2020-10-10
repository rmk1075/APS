import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] cube = new int[25];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= 24; i++) cube[i] = Integer.parseInt(st.nextToken());

        if(check()) System.out.println(1);
        else System.out.println(0);
    }

    public static boolean check() {
        // U D
        if(cube[1] == cube[2] && cube[2] == cube[3] && cube[3] == cube[4] && cube[9] == cube[10] && cube[10] == cube[11] && cube[11] == cube[12]) {
            if(cube[5] == cube[6] && cube[17] == cube[18] && cube[21] == cube[22] && cube[13] == cube[14] && cube[7] == cube[8] && cube[19] == cube[20] && cube[23] == cube[24] && cube[15] == cube[16]) {
                if(cube[5] == cube[19] && cube[17] == cube[23] && cube[21] == cube[15] && cube[13] == cube[7]) return true;
                if(cube[5] == cube[15] && cube[17] == cube[7] && cube[21] == cube[19] && cube[13] == cube[23]) return true;
            }
        }

        // L R
        if(cube[13] == cube[14] && cube[14] == cube[15] && cube[15] == cube[16] && cube[17] == cube[18] && cube[18] == cube[19] && cube[19] == cube[20]) {
            if(cube[1] == cube[3] && cube[2] == cube[4] && cube[5] == cube[7] && cube[6] == cube[8] && cube[9] == cube[11] && cube[10] == cube[12] && cube[21] == cube[23] && cube[22] == cube[24]) {
                if(cube[1] == cube[21] && cube[22] == cube[10] && cube[11] == cube[6] && cube[5] == cube[2]) return true;
                if(cube[1] == cube[6] && cube[5] == cube[10] && cube[11] == cube[21] && cube[22] == cube[2]) return true;
            }
        }

        // F B
        if(cube[5] == cube[6] && cube[6] == cube[7] && cube[7] == cube[8] && cube[21] == cube[22] && cube[22] == cube[23] && cube[23] == cube[24]) {
            if(cube[1] == cube[2] && cube[18] == cube[20] && cube[11] == cube[12] && cube[14] == cube[16] && cube[3] == cube[4] && cube[17] == cube[19] && cube[9] == cube[10] && cube[13] == cube[15]) {
                if(cube[1] == cube[17] && cube[18] == cube[10] && cube[11] == cube[14] && cube[13] == cube[3]) return true;
                if(cube[1] == cube[14] && cube[18] == cube[3] && cube[11] == cube[17] && cube[13] == cube[9]) return true;
            }
        }

        return false;
    }
}