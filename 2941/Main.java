import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    static String[] words;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        words = br.readLine().split("");
        int count = 0;
        for(int i = 0; i < words.length; i++) {
            count++;
            switch(words[i]) {
                case "c":
                    if(i + 1 < words.length && (words[i + 1].equals("=") || words[i + 1].equals("-"))) {
                            i++;
                    }
                    break;
                    
                case "d":
                    if(i + 1 < words.length && words[i + 1].equals("-")) {
                        i++;
                    } else if(i + 2 < words.length && words[i + 1].equals("z") && words[i + 2].equals("=")) {
                        i += 2;
                    }
                    break;

                case "l":
                    if(i + 1 < words.length && words[i + 1].equals("j")) {
                            i++;
                    }
                    break;

                case "n":
                    if(i + 1 < words.length && words[i + 1].equals("j")) {
                            i++;
                    }
                    break;

                case "s":
                    if(i + 1 < words.length && words[i + 1].equals("=")) {
                            i++;
                    }
                    break;

                case "z":
                    if(i + 1 < words.length && words[i + 1].equals("=")) {
                            i++;
                    }
                    break;
            }
        }

        System.out.println(count);
    }
}