import java.util.*;

class Edge implements Comparable<Edge> {
    int n1, n2;
    int cost;
    public Edge(int n1, int n2, int cost) {
        this.n1=n1;
        this.n2=n2;
        this.cost=cost;
    }
    @Override
    public int compareTo(Edge e) {
        return Integer.compare(this.cost, e.cost);
    }
}

class Solution {
    
    PriorityQueue<Edge> edges = new PriorityQueue<>();
    int[] parents;
    
    public int solution(int n, int[][] costs) {
        for (int[] cost : costs) {
            edges.offer(new Edge(cost[0], cost[1], cost[2]));
        }
        
        parents=new int[n];
        for (int i=0; i<n; i++) {
            parents[i]=i;
        }
        
        int totalCost = 0;
        int edgeCnt = 0;
        
        while (edgeCnt < n-1) {
            Edge next = edges.poll();
            // 이미 두 지점이 연결되어있다면 싸이클
            if (find(next.n1) == find(next.n2)) {
                continue;
            }
            // 두 지점 연결
            totalCost+=next.cost;
            edgeCnt++;
            union(next.n1, next.n2);
        }
        return totalCost;
    }
    
    public void union(int n1, int n2) {
        // n1과 n2의 root를 연결
        int n1Root=find(n1);
        int n2Root=find(n2);
        int minRoot = Math.min(n1Root, n2Root);
        parents[n1Root]=minRoot;
        parents[n2Root]=minRoot;
    }
    
    public int find(int n) {
        if (parents[n]==n) return n;
        int rootNode=find(parents[n]);
        parents[n]=rootNode;
        return rootNode;
    }
}
