import java.util.*;

class BridgeTruck {
    public int position = 1;
    public int truckWeight;
    
    public BridgeTruck(int truckWeight) {
        this.truckWeight = truckWeight;
    }
    
    public boolean isEnd(int bridge_length) {
        return position > bridge_length;
    }
}

class Solution {
    
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int currentWeight = 0;  // 현재 다리 위 트럭 무게
        int currentTime = 0;
        Queue<BridgeTruck> q = new ArrayDeque<>();    // 다리 위 트럭 정보
        int nextIdx = 0;
        do {
            currentTime++;
            // 다리 위 트럭 이동
            for (BridgeTruck t : q) {
                t.position++;
            }
            // 다 건넌 트럭 발생 시, 다리에서 제거
            if (!q.isEmpty() && q.peek().isEnd(bridge_length)) {
                BridgeTruck b = q.poll();
                currentWeight -= b.truckWeight;
            }
            // 새로운 트럭 올리기
            if (nextIdx == truck_weights.length) {
                continue;
            }
            int nextTruckWeight = truck_weights[nextIdx];
            if (q.size() < bridge_length && currentWeight + nextTruckWeight <= weight) {
                q.offer(new BridgeTruck(nextTruckWeight));
                currentWeight += nextTruckWeight;
                nextIdx++;
            }
            
        } while (!q.isEmpty());
        
        return currentTime;
    }
}
