import java.util.*;
class Node {
    int id;
    Set<Node> adjacents = new HashSet<>();
    int depth = 0;
    
    public Node(int id) {
        this.id = id;
    }
    void addAdj(Node n) {
        this.adjacents.add(n);
    }
    void updateDepth(int depth) {
        if (this.depth == 0) {
            this.depth = depth;
        }
        else {
            this.depth = Math.min(this.depth, depth);
        }
    }
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Node)) {
            return false;
        }
        Node n = (Node) o;
        return this.id == n.id;
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

class Solution {
    public int solution(int n, int[][] edge) {
        Node[] nodes = new Node[n];
        for (int i=0; i<n; i++) {
            nodes[i] = new Node(i);
        }
        for (int i=0; i<edge.length; i++) {
            int e1 = edge[i][0]-1;
            int e2 = edge[i][1]-1;
            nodes[e1].addAdj(nodes[e2]);
            nodes[e2].addAdj(nodes[e1]);
        }
        Queue<Node> q = new ArrayDeque<>();
        Set<Integer> visitedNodeIds = new HashSet<>();
        q.offer(nodes[0]);
        int maxDepth = 0;
        visitedNodeIds.add(0);
        while (!q.isEmpty()) {
            Node node = q.poll();
            // visitedNodeIds.add(node.id);
            int nextDepth = node.depth+1;
            maxDepth = Math.max(maxDepth, node.depth);
            // System.out.printf("id %d depth %d\n", node.id+1, node.depth);
            
            for (Node adj : node.adjacents) {
                if (visitedNodeIds.contains(adj.id)) {
                    continue;
                }
                // System.out.printf("다음노드 id %d depth %d\n", adj.id +1, nextDepth);
                adj.updateDepth(nextDepth);
                q.offer(adj);
                visitedNodeIds.add(adj.id);
            }
        }
        int cnt = 0;
        for (Node nn : nodes) {
            if (nn.depth == maxDepth) cnt++;
        }
        return cnt;
    }
}
