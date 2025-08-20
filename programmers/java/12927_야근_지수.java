import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        for (int work : works) {
            pq.offer(work);
        }
        for (int i=0; i<n; i++) {
            int target = pq.poll();
            if (target == 0) break;
            pq.offer(target-1);
        }
        
        long answer = 0;
        while (!pq.isEmpty()) {
            int target = pq.poll();
            answer += (target * target);
        }
        return answer;
    }
}
