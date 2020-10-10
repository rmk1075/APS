import java.util.Scanner;

public class Main {
    static int R, C;
    static String word = "", ans;
    static String[][] puzzles;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        R = sc.nextInt();
        C = sc.nextInt();
        sc.nextLine();
        puzzles = new String[R][C];
        for(int i = 0; i < R; i++) {
            puzzles[i] = sc.nextLine().split("");
        }
        sc.close();

        // row
        for(int i = 0; i < R; i++) {
            String tempWord = "";
            for(int j = 0; j < C; j++) {
                if(puzzles[i][j].equals("#")) {
                    if(tempWord == "") continue;

                    check(tempWord);
                    tempWord = "";
                } else tempWord += puzzles[i][j];
            }

            if(!tempWord.equals("")) {
                check(tempWord);
                tempWord = "";
            }
        }

        // col
        for(int i = 0; i < C; i++) {
            String tempWord = "";
            for(int j = 0; j < R; j++) {
                if(puzzles[j][i].equals("#")) {
                    if(tempWord == "") continue;

                    check(tempWord);
                    tempWord = "";
                } else tempWord += puzzles[j][i];
            }

            if(!tempWord.equals("")) {
                check(tempWord);    
                tempWord = "";
            }
        }

        System.out.println(ans);
    }

    public static void check(String tempWord) {
        if(ans == null) ans = tempWord;
        else {
            // compare
            compare(tempWord);
        }
    }

    public static void compare(String newWord) {
        
        if(newWord.length() < 2) return ;

        for(int i = 0; i < (ans.length() < newWord.length() ? ans.length() : newWord.length()); i++) {
            if(ans.charAt(i) == newWord.charAt(i)) continue;
            
            if(newWord.charAt(i) < ans.charAt(i)) {
                ans = newWord;
            } else return ;
        }
        
        if(newWord.length() < ans.length()) ans = newWord;
    }
}