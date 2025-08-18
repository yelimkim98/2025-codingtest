import java.util.*;
import java.util.stream.*;

class Node {
    int id;
    List<Node> adjs;
    int distance=Integer.MAX_VALUE;
    
    public Node(int id) {
        this.id=id;
        this.adjs=new ArrayList<>();
    }
    public void addAdj(Node n) {
        adjs.add(n);
    }
}

class Solution {
    
    Node[] nodes;
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        nodes = new Node[n];
        for (int i=0; i<n; i++) {
            nodes[i] = new Node(i);
        }
        for (int[] road : roads) {
            Node n1=nodes[road[0]-1];
            Node n2=nodes[road[1]-1];
            n1.addAdj(n2);
            n2.addAdj(n1);
        }
        for (int i=0; i<sources.length; i++) {
            sources[i]--;
        }
        destination--;
        
        boolean[] visited = new boolean[n];
        visited[destination]=true;
        nodes[destination].distance=0;
        Deque<Node> q = new ArrayDeque<>();
        q.offer(nodes[destination]);
        
        while (!q.isEmpty()) {
            Node currentNode = q.poll();
            
            List<Node> adjs = currentNode.adjs.stream()
                .filter(adj -> !visited[adj.id])
                .collect(Collectors.toList());
            
            for (Node adj : adjs) {
                adj.distance = currentNode.distance+1;
                q.offer(adj);
                visited[adj.id]=true;
            }
        }
        int[] answer = new int[sources.length];
        for (int i=0; i<sources.length; i++) {
            int d = nodes[sources[i]].distance;
            answer[i] = (d < Integer.MAX_VALUE) ? d : -1;
        }
        return answer;
    }
}
