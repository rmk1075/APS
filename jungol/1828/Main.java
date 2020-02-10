import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.io.IOException;
 
class Element {
    int a, b;
 
    Element (int a, int b) {
        this.a = a;
        this.b = b;
    }

    void change(int b_) {
        this.b = b_;
    }
}
 
public class Main {
    static int N;
    static int[] checked;
    static ArrayList<Element> arr = new ArrayList<Element>();
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int a, b;
        N = Integer.parseInt(br.readLine());
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            arr.add(new Element(a, b));
        }
      
        Collections.sort(arr, new Comparator<Element>() {
            @Override
            public int compare(Element o1, Element o2) {
                return o1.a - o2.a;
            }
        });
 
        int count = 1;
        int temp = arr.get(0).b;
        for(int i = 1; i < arr.size(); i++) {
            if(temp < arr.get(i).a) {
                temp = arr.get(i).b;
                count++;
            } else {
                temp = (temp < arr.get(i).b) ? temp : arr.get(i).b;
            }
        }
 
        System.out.println(count);
    }
}