import java.util.ArrayList;
import java.util.List;

class Solution {
    List[] list;
    public int solution(int N, int number) {
        list = new List[9];
        for(int i = 0; i < 9; i++) list[i] = new ArrayList<>();

        int num = 0;
        for(int i = 1; i < 9; i++) {
            num = num * 10 + N;
            if(valid(N, number, i, num)) return i;
        }
        return -1;
    }

    public boolean valid(int N, int number, int index, int num) {
        List<Integer> l = list[index];
        for(int i = index - 1; index / 2 <= i; i--) {
            List<Integer> a = list[i];
            List<Integer> b = list[index - i];

            for(int aa : a) {
                for(int bb : b) {
                    // +
                    int temp = aa + bb;
                    if(number == temp) return true;
                    if(!l.contains(temp)) l.add(temp);

                    // -
                    temp = aa - bb;
                    if(number == temp) return true;
                    if(!l.contains(temp)) l.add(temp);

                    temp = bb - aa;
                    if(number == temp) return true;
                    if(!l.contains(temp)) l.add(temp);

                    // *
                    temp = aa * bb;
                    if(number == temp) return true;
                    if(!l.contains(temp)) l.add(temp);
                    
                    if(aa != 0 && bb != 0) {
                        // /
                        temp = aa / bb;
                        if(number == temp) return true;
                        if(!l.contains(temp)) l.add(temp);

                        temp = bb / aa;
                        if(number == temp) return true;
                        if(!l.contains(temp)) l.add(temp);
                    }

                }
            }
        }

        if(num == number) return true;
        list[index].add(num);

        return false;
    }
}