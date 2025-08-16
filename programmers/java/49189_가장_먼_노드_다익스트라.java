import java.util.*;
class Node {
    public int id;
    public List<Node> adjs;
    public Node(int id) {
        this.id=id;
        this.adjs=new ArrayList<>();
    }
    public void addAdj(Node n) {
        adjs.add(n);
    }
    @Override
    public String toString() {
        return id+"";
    }
}
class Graph {
    Node[] nodes;
    public Graph(int n, int[][] edges) {
        this.nodes = new Node[n];
        
        for (int i=0; i<n; i++) {
            nodes[i] = new Node(i);
        }
        for (int[] edge : edges) {
            Node n1 = nodes[edge[0]-1];
            Node n2 = nodes[edge[1]-1];
            n1.addAdj(n2);
            n2.addAdj(n1);
        }
    }
    public List<Node> getAdjs(int nodeId) {
        return nodes[nodeId].adjs;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Node n : nodes) {
            sb.append(n + " adjs " + n.adjs + "\n");
        }
        return sb.toString();
    }
}
class Solution {
    
    public int solution(int n, int[][] edges) {
        Graph graph = new Graph(n, edges);
        
        int[] distances = new int[n];
        
        for (int i=1; i<n; i++) {
            distances[i] = Integer.MAX_VALUE;
        }
        boolean[] finished = new boolean[n];
        finished[0] = true;
        int finishedCnt=1;
        int lastFinishedNodeId = 0;
        
        while (finishedCnt < n) {
            List<Node> adjs = graph.getAdjs(lastFinishedNodeId);
            for (Node node : adjs) {
                int newDistance = add(distances[lastFinishedNodeId], 1);
                distances[node.id] = Math.min(distances[node.id], newDistance);
            }
            int minNodeId = 0;
            int minDistance = Integer.MAX_VALUE;
            
            for (int nodeId=1; nodeId<n; nodeId++) {
                if (!finished[nodeId] && distances[nodeId] < minDistance) {
                    minNodeId = nodeId;
                    minDistance = distances[nodeId];
                }
            }
            finished[minNodeId]=true;
            finishedCnt++;
            lastFinishedNodeId=minNodeId;
        }
        Arrays.sort(distances);
        int cnt=1;
        for (int i=n-2; i>=0 && distances[i] == distances[n-1]; i--) {
            cnt++;
        }
        return cnt;
    }
    
    public int add(int a, int b) {
        long l = (long) a + b;
        return (l > Integer.MAX_VALUE) ? Integer.MAX_VALUE : (int) l;
    }
}
