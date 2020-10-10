import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Line {
    float x1, y1, x2, y2;

    Line(float x1, float y1, float x2, float y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
}

public class Main {
    static int N, group[];
    static Line[] lines;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        
        group = new int[N];
        for(int i = 0; i < N; i++) group[i] = i;

        lines = new Line[N];
        StringTokenizer st;
        for(int i = 0; i < N; i++) {
            st  = new StringTokenizer(br.readLine());
            lines[i] = new Line(Float.parseFloat(st.nextToken()), Float.parseFloat(st.nextToken()), Float.parseFloat(st.nextToken()), Float.parseFloat(st.nextToken()));
        }

        for(int i = 0; i < N-1; i++) {
            for(int j = i + 1; j < N; j++) {
                int a = find(i), b = find(j);
                if(a == b) continue;
                if(check(lines[i], lines[j])) group[a] = b;
            }
        }

        int temp, numOfGroup = 0, maxCount = 0, candidates[] = new int[N];
        for(int i = 0; i < N; i++) {
            temp = find(group[i]);
            if(candidates[temp] == 0) numOfGroup++;
            candidates[temp]++;
            maxCount = Math.max(maxCount, candidates[temp]);
        }

        System.out.println(numOfGroup);
        System.out.println(maxCount);
    }

    public static float ccw(float x1, float y1, float x2, float y2, float x3, float y3) {
        return (x1*y2 + x2*y3 + x3*y1) - (y1*x2 + y2*x3 + y3*x1);
    }

    public static boolean check(Line l1, Line l2) {
        float a = ccw(l1.x1, l1.y1, l1.x2, l1.y2, l2.x1, l2.y1) * ccw(l1.x1, l1.y1, l1.x2, l1.y2, l2.x2, l2.y2);
        float b = ccw(l2.x1, l2.y1, l2.x2, l2.y2, l1.x1, l1.y1) * ccw(l2.x1, l2.y1, l2.x2, l2.y2, l1.x2, l1.y2);

        if(a == 0 && b == 0) {
            if(Math.max(l2.x1, l2.x2) < Math.min(l1.x1, l1.x2) || Math.max(l1.x1, l1.x2) < Math.min(l2.x1, l2.x2) || Math.max(l2.y1, l2.y2) < Math.min(l1.y1, l1.y2) || Math.max(l1.y1, l1.y2) < Math.min(l2.y1, l2.y2)) return false;
            return true;
        }

        return a <= 0 && b <= 0;
    }

    public static int find(int idx) {
        if(group[idx] == idx) return idx;
        return group[idx] = find(group[idx]);
    }
}