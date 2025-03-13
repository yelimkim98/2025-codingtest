import java.util.*;

class Solution {

    public int solution(int[][] jobs) {
        LinkedList<Job> jobList = new LinkedList<>();
        
        for (int i=0; i<jobs.length; i++) {
            jobList.add(new Job(i, jobs[i][1], jobs[i][0]));
        }
        jobList.sort(Job.REQUEST_TIME_COMPARATOR);
        
        Queue<Job> jobQ = jobList;
        
        PriorityQueue<Job> pq = new PriorityQueue<>();
        
        int currentTime = 0;
        int totalReturnTime = 0;
        
        while (!jobQ.isEmpty() || !pq.isEmpty()) {
            // 대기큐의 요청이 모두 처리되어있음 -> 다음 요청의 요청시각으로 jump
            if (pq.isEmpty()) {
                currentTime = Math.max(jobQ.peek().requestTime, currentTime);
            }
            // 현재시각 이전에 요청된 Job들을 대기큐에 넣는다.
            while (!jobQ.isEmpty() && jobQ.peek().requestTime <= currentTime) {
                pq.offer(jobQ.poll());
            }
            // 요청을 꺼내서 작업한다.
            //
            System.out.printf("currentTime %d pqSize %d \n", currentTime, pq.size());
            Job target = pq.poll();
            target.start(currentTime);  // target의 endTime 및 returnTime이 계산됨
            //
            System.out.println(target);
            currentTime = target.endTime;
            totalReturnTime += target.getReturnTime();
        }
        return totalReturnTime / jobs.length;
    }
}

class Job implements Comparable<Job> {
    
    public static Comparator<Job> REQUEST_TIME_COMPARATOR = (j1, j2) 
        -> Integer.compare(j1.requestTime, j2.requestTime);
    
    public int id;  // 작업번호
    public int duration;  // 소요시간
    public int requestTime;  // 요청시각
    public int startTime; // 시작 시각
    public int endTime;   // 종료 시각
    
    public Job(int id, int duration, int requestTime) {
        this.id = id;
        this.duration = duration;
        this.requestTime = requestTime;
    }
    
    public void start(int startTime) {
        this.startTime = startTime;
        this.endTime = startTime + this.duration;
    }
    
    // 반환 시각
    public int getReturnTime() {
        return endTime - requestTime;
    }
    
    @Override
    public int compareTo(Job j) {
        int result1 = Integer.compare(this.duration, j.duration);
        if (result1 != 0) return result1;
        int result2 = Integer.compare(this.requestTime, j.requestTime);
        if (result2 != 0) return result2;
        int result3 = Integer.compare(this.id, j.id);
        return result3;
    }
    
    @Override
    public String toString() {
        return String.format("id=%d duration=%d requestTime=%d startTime=%d endTime=%d returnTime=%d", id, duration, requestTime, startTime, endTime, getReturnTime());
    }
}
