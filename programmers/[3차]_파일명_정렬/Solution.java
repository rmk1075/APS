import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public String[] solution(String[] files) {
        Arrays.sort(files, new Comparator<String>(){
            @Override
            public int compare(String o1, String o2) {
                String[] file1 = split(o1), file2 = split(o2);

                return file1[0].equals(file2[0]) ? Integer.parseInt(file1[1]) - Integer.parseInt(file2[1]) : file1[0].compareTo(file2[0]);
            }
        });

        return files;
    }

    public String[] split(String f) {
        String[] file = new String[2];
        
        // head
        file[0] = f.split("[0-9]")[0].toUpperCase();

        // number
        String number = "";
        for(char ch : f.substring(file[0].length()).toCharArray()) {
            if(Character.isDigit(ch) && number.length() < 5) number += ch;
            else break;
        }
        file[1] = number;
        
        return file;
    }
}