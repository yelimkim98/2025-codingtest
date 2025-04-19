// 다익스트라
import java.util.*;

class D implements Comparable<D>{
    int nodeId;
    int distance;
    public D(int n, int d) {
        nodeId=n; distance=d;
    }
    @Override
    public int compareTo(D d) {
        return Integer.compare(this.distance, d.distance);
    }
    @Override
    public String toString() {
        return String.format("(nodeId=%d,distance=%d)", nodeId, distance);
    }
}

class Solution {
    
    int[][] graph;
    
    public int solution(int n, int[][] roads, int k) {
        try {
        graph = new int[n+1][n+1];
        
        for (int i=1; i<=n; i++) {
            for (int j=1; j<=n; j++) {
                if (i!=j) {
                    graph[i][j] = Integer.MAX_VALUE;
                    graph[j][i] = Integer.MAX_VALUE;
                }
            }
        }
        for (int[] road : roads) {
            int n1 = road[0];
            int n2 = road[1];
            int d = road[2];
            graph[n1][n2] = Math.min(graph[n1][n2], d);
            graph[n2][n1] = Math.min(graph[n2][n1], d);
        }
        // for (int i=1; i<=n;i++) {
        //     for (int j=1;j<=n;j++){
        //         System.out.printf("%2d ", (graph[i][j]==Integer.MAX_VALUE?-1:graph[i][j]));
        //     }
        //     System.out.println();
        // }
        // System.out.println();
        int[] distances = new int[n+1];
        for (int i=2; i<=n; i++) {
            distances[i] = Integer.MAX_VALUE;
        }
        boolean[] visited = new boolean[n+1];
        
        PriorityQueue<D> pq = new PriorityQueue<>();
        pq.offer(new D(1, 0));
        while (!pq.isEmpty()) {
            // System.out.println(pq);
            D current = pq.poll();
            // System.out.printf("current node %d, distance %d\n", current.nodeId, current.distance);
            if (visited[current.nodeId]) continue;
            visited[current.nodeId] = true;
            
            for (int i=2; i<=n; i++) {
                // current node와 연결되어있고, 아직 방문하지 않은 노드들(i)에 대하여
                if (!visited[i] && graph[current.nodeId][i] < Integer.MAX_VALUE) {
                    int originalCost = distances[i];
                    int newCost = distances[current.nodeId] + graph[current.nodeId][i];
                    distances[i] = Math.min(originalCost, newCost);
                    pq.offer(new D(i, distances[i]));
                }
            }
        }
        int cnt = 0;
        for (int i=1; i<=n; i++) {
            if (distances[i] <= k) cnt++;
            // System.out.print(distances[i] + " ");
        }
        // System.out.println();
        return cnt;
        } catch(Exception e) {
            System.out.println("ERROR");
            return 0;
        }
    }
}
