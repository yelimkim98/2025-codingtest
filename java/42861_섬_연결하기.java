import java.util.*;

class Edge implements Comparable<Edge> {
    int n1, n2;
    int cost;
    public Edge(int n1, int n2, int cost) {
        this.n1 = n1;
        this.n2 = n2;
        this.cost = cost;
    }
    @Override
    public int compareTo(Edge e) {
        return Integer.compare(this.cost, e.cost);
    }
}

class Solution {
    public int solution(int n, int[][] costs) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int i=0; i<costs.length; i++) {
            pq.offer(new Edge(costs[i][0], costs[i][1], costs[i][2]));
        }
        int[] nodeRoots = new int[n];
        
        for (int i=0; i<n; i++) { nodeRoots[i] = i; }
        
        int cost = 0;
        
        while (!pq.isEmpty()) {
            Edge e = pq.poll();
            
            // 이미 연결되어있는 경우 (사이클 형성하는 case)
            if (nodeRoots[e.n1] == nodeRoots[e.n2]) {
                continue;
            }
            cost += e.cost;
            // update nodeRoots
            updateRoots(e, nodeRoots);
            boolean finished = true;
            for (int i=0; i<n; i++) {
                if (nodeRoots[i] != nodeRoots[e.n1]) {
                    finished = false;
                }
            }
            if (finished) break;
        }
        return cost;
    }
    
    void updateRoots(Edge newEdge, int[] roots) {
        /* originalRoot1 != originalRoot2 */
        int originalRoot1 = roots[newEdge.n1];
        int originalRoot2 = roots[newEdge.n2];
        
        /*
         originalRoot1 또는 originalRoot2를 root 노드로 갖는 모든 노드들의 root 정보를
         originalRoot1, originalRoot2, newEdge.n1, newEdge.n2 중 최솟값으로 update
         */
        int min = Math.min(originalRoot1, originalRoot2);
        min = Math.min(min, newEdge.n1);
        min = Math.min(min, newEdge.n2);
        
        for (int i=0; i<roots.length; i++) {
            if (roots[i] == originalRoot1 || roots[i] == originalRoot2) {
                roots[i] = min;
            }
        }
    }
}
/*
     *4
 0--------1
          |
          | *2
          |
          3
 */
