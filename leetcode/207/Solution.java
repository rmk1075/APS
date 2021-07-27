import java.util.ArrayList;
import java.util.List;

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] courses = new List[numCourses];
        for(int i = 0; i < numCourses; i++) courses[i] = new ArrayList<>();
        for(int[] pre : prerequisites) courses[pre[1]].add(pre[0]);

        int[] visited = new int[numCourses];
        for(int i = 0; i < numCourses; i++) {
            if(!dfs(courses, visited, i)) return false;
        }
        return true;
    }

    public boolean dfs(List<Integer>[] courses, int[] visited, int index) {
        visited[index] = 1;

        for(int course : courses[index]) {
            if(visited[course] == 1) return false;
            else if(visited[course] == 0) {
                if(!dfs(courses, visited, course)) return false;
            }
        }

        visited[index] = 2;
        return true;
    }
}