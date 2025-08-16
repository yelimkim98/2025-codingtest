import java.util.*;
class Node {
    int id;
    // key : nodeId, value : 가중치
    Map<Integer, Integer> adjs = new HashMap<>();
    public Node(int id) {
        this.id=id;
    }
    public void addAdj(int nodeId, int weight) {
        int beforeWeight = adjs.getOrDefault(nodeId, Integer.MAX_VALUE);
        adjs.put(nodeId, Math.min(beforeWeight, weight));
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id + " adjs [ ");
        for (Map.Entry<Integer, Integer> entry : adjs.entrySet()) {
            int nodeId = entry.getKey();
            int weight = entry.getValue();
            sb.append(nodeId + "(" + weight + ") ");
        }
        sb.append("]");
        return sb.toString();
    }
}
class Graph {
    Node[] nodes;
    public Graph(int n, int[][] edges) {
        this.nodes=new Node[n];
        for (int id=0; id<n; id++) {
            nodes[id] = new Node(id);
        }
        for (int[] edge : edges) {
            Node n1 = nodes[edge[0]-1];
            Node n2 = nodes[edge[1]-1];
            n1.addAdj(n2.id, edge[2]);
            n2.addAdj(n1.id, edge[2]);
        }
    }
    public Map<Integer, Integer> getAdjs(int nodeId) {
        return this.nodes[nodeId].adjs;
    }
    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        for (int i=0; i<nodes.length; i++) {
            sb.append(nodes[i] + "\n");
        }
        return sb.toString();
    }
}
class Solution {
    public int solution(int n, int[][] roads, int k) {
        Graph graph = new Graph(n, roads);
        // System.out.println(graph);
        int[] distances = new int[n];
        for (int i=1; i<n; i++) {
            distances[i] = Integer.MAX_VALUE;
        }
        boolean[] finished = new boolean[n];
        finished[0] = true;
        int lastFinishedNodeId=0;
        int finishedCnt=0;
        
        while (finishedCnt<n) {
            Map<Integer, Integer> adjs = graph.getAdjs(lastFinishedNodeId);
            for (Map.Entry<Integer, Integer> entry : adjs.entrySet()) {
                int adjNodeId = entry.getKey();
                int weight = entry.getValue();
                int newDistance = add(distances[lastFinishedNodeId], weight);
                distances[adjNodeId] = Math.min(distances[adjNodeId], newDistance);
            }
            int minDistanceNodeId=0;
            int minDistance=Integer.MAX_VALUE;
            for (int nodeId=1; nodeId<n; nodeId++) {
                if (!finished[nodeId] && distances[nodeId]< minDistance) {
                    minDistanceNodeId=nodeId;
                    minDistance=distances[nodeId];
                }
            }
            finished[minDistanceNodeId]=true;
            lastFinishedNodeId=minDistanceNodeId;
            finishedCnt++;
        }
        
        int answer = 0;
        for (int i=0; i<n; i++) {
            // System.out.printf("%d(%d) ", i, distances[i]);
            if (distances[i] <= k) {
                answer++;
            }
        }
        return answer;
    }
    int add(int a, int b) {
        long l = (long) a + b;
        return (l >= Integer.MAX_VALUE) ? Integer.MAX_VALUE : (int) l;
    }
}
