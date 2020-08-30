import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int solution(String[] lines) {
        int answer = 0;

        int idx = 0, n = lines.length * 2;
        double[][] timeLogs = new double[n][2]; // [time][start (0.0) or end (1.0)]
        for(String line : lines) {
            String[] sp = line.split(" ");  // [2016-09-15][hh:mm:ss.sss][t.ttts]
            double s = convert(sp[1]);
            double t = Double.parseDouble(sp[2].split("s")[0]);

            timeLogs[idx][0] = s;
            timeLogs[idx++][1] = 1.0;

            timeLogs[idx][0] = s - t + 0.001;
            timeLogs[idx++][1] = 0.0;
        }

        Arrays.sort(timeLogs, new Comparator<double[]>(){
            @Override
            public int compare(double[] o1, double[] o2) {

                if(Double.compare(o1[0], o2[0]) == 0) {
                    if(o1[1] == 0.0) return -1;
                    else return 1;
                }

                return Double.compare(o1[0], o2[0]);
            }
        });

        int cnt = 0;
        int[] processes = new int[n];
        for(int i = 0; i < n; i++) {
            if(timeLogs[i][1] == 0.0) {
                processes[i] = ++cnt;
            }
            else processes[i] = cnt--;
        }

        for(int i = 0; i < n; i++) {

            double time = timeLogs[i][0];
            int j = i, temp = processes[i];
            while(++j < n) {

                if(1 <= timeLogs[j][0] - time) break;
                if(timeLogs[j][1] == 0.0) temp++;

            }

            answer = Math.max(answer, temp);
        }

        return answer;
    }

    public double convert(String sp) {
        String[] times = sp.split(":");
        return Double.parseDouble(times[0]) * 3600 + Double.parseDouble(times[1]) * 60 + Double.parseDouble(times[2]);
    }
}