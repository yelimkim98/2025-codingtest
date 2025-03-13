import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int s : scoville) {
            pq.offer(s);
        }
        int cnt = 0;
        while (!pq.isEmpty()) {
            int small1 = pq.poll();
            
            if (small1 >= K) {
                return cnt;
            }
            if (pq.size() == 0) {
                return -1;
            }
            cnt++;
            int small2 = pq.poll();
            pq.offer(small1 + small2 * 2);
        }
        return -1;
    }
}
