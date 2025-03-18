import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        int[] pattern1 = {1, 2, 3, 4, 5};
        int[] pattern2 = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] pattern3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        
        int[] cnts = new int[3];
        cnts[0] = cnt(pattern1, answers);
        cnts[1] = cnt(pattern2, answers);
        cnts[2] = cnt(pattern3, answers);
        int max = 0;
        max = Math.max(cnts[0], max);
        max = Math.max(cnts[1], max);
        max = Math.max(cnts[2], max);
        
        List<Integer> results = new ArrayList<>();
        
        for (int i=0; i<3; i++) {
            if (cnts[i] == max) results.add(i+1);
        }
        return results.stream().mapToInt(x -> x).toArray();
    }
    
    int cnt(int[] pattern, int[] answers) {
        int cnt = 0;
        
        for (int i=0; i<answers.length; i++) {
            if (pattern[i % pattern.length] == answers[i]) cnt++;
        }
        return cnt;
    }
}
