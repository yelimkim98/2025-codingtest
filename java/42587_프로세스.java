import java.util.*;

class Task {
    public int location;
    public int priority;
    
    public Task(int location, int priority) {
        this.location = location;
        this.priority = priority;
    }
}

class Solution {
    public int solution(int[] priorities, int location) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        Queue<Task> q = new ArrayDeque<>();
        
        for (int i=0; i<priorities.length; i++) {
            q.offer(new Task(i, priorities[i]));
            pq.offer(priorities[i]);
        }
        int answer = 0;
        
        while (true) {
            Task t = q.poll();
            int largestPriority = pq.peek();
            
            if (t.priority == largestPriority) {
                pq.poll();
                answer++;
                if (t.location == location) {
                    return answer;
                }
            }
            else {
                q.offer(t);
            }
        }
    }
}
