import java.util.LinkedList;
import java.util.List;

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer>[] courses = new List[numCourses];
        for(int i = 0; i < numCourses; i++) courses[i] = new ArrayList<>();
        for(int[] pre : prerequisites) courses[pre[0]].add(pre[1]);

        List<Integer> result = new LinkedList<>();
        int[] visited = new int[numCourses];
        for(int i = 0; i < numCourses; i++) {
            if(visited[i] == 2) continue;
            if(!dfs(courses, visited, i, result)) break;
        }

        return result.stream().mapToInt(i->i).toArray();
    }

    public boolean dfs(List<Integer>[] courses, int[] visited, int index, List<Integer> result) {
        visited[index] = 1;
        for(int course : courses[index]) {
            if(visited[course] == 1) {
                result.clear();
                return false;
            } else if(visited[course] == 0) {
                if(!dfs(courses, visited, course, result)) {
                    result.clear();
                    return false;
                }
            }
        }

        result.add(index);
        visited[index] = 2;
        return true;
    }
}