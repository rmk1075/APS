import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int[] solution(int N, int[] stages) {
        int[] stage = new int[N + 2];
        for(int stg : stages) {
            stage[stg]++;
        }

        int cnt = stage[N + 1];
        double[][] failure = new double[N][2];
        for(int i = N; 0 < i; i--) {
            cnt += stage[i];
            failure[i - 1][0] = i;
            failure[i - 1][1] = (cnt == 0) ? 0 : (double)stage[i]/(double)cnt;
        }

        Arrays.sort(failure, new Comparator<double[]>(){
            @Override
            public int compare(double[] o1, double[] o2) {
                return (o1[1] == o2[1]) ? Double.compare(o1[0], o2[0]) : Double.compare(o2[1], o1[1]);
            }
        });
        
        int[] answer = new int[N];
        for(int i = 0; i < N; i++) {
            answer[i] = (int)failure[i][0];
        }

        return answer;
    }
}