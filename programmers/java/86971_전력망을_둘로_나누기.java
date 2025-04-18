/*
 모든 간선을 다 끊어보고 비교
 */
import java.util.*;

class Node {
    int id;  // 송전탑 id (0 ~ n-1)
    List<Node> adjacent = new LinkedList<>(); // 양방향 참조
    
    public Node(int id) {
        this.id = id;
    }
    
    void addAdjacent(Node n) {
        this.adjacent.add(n);
    }
    
    void deleteAdjacent(int nodeId) {
        for (int i=0; i<adjacent.size(); i++) {
            if (adjacent.get(i).id == nodeId) {
                adjacent.remove(i);
                return;
            }
        }
    }
}

class Solution {
    public int solution(int n, int[][] wires) {
        Node[] nodes = new Node[n+1];
        
        // 인접노드정보 초기화
        for (int i=1; i<=n; i++) {
            nodes[i] = new Node(i);
        }
        for (int i=0; i<wires.length; i++) {
            int[] wire = wires[i];
            Node n1 = nodes[wire[0]];
            Node n2 = nodes[wire[1]];
            n1.addAdjacent(n2);
            n2.addAdjacent(n1);
        }
        int ans = n;
        // i번 연결이 끊어졌을때 두개의 트리 각각의 size
        for (int i=0; i<wires.length; i++) {
            // i번 연결 끊기
            int[] wire = wires[i];
            
            Node n1 = nodes[wire[0]];
            Node n2 = nodes[wire[1]];
            
            n1.deleteAdjacent(wire[1]);
            n2.deleteAdjacent(wire[0]);
                        
            // 두개 트리 각각의 size 계산
            int n1Size = size(n, n1);
            int n2Size = size(n, n2);
                        
            int diff = Math.abs(n1Size - n2Size);
            
            ans = diff < ans ? diff : ans;
            
            // 끊은 연결 복구
            n1.addAdjacent(n2);
            n2.addAdjacent(n1);
        }
        return ans;
    }
    
    public int size(int nodeCnt, Node start) {
        boolean[] visited = new boolean[nodeCnt+1];
        Deque<Node> stack = new ArrayDeque<>();
        stack.push(start);
        
        int cnt = 0;
        
        while (!stack.isEmpty()) {
            Node top = stack.pop();
            if (visited[top.id]) {
                continue;
            }
            cnt++;
            visited[top.id] = true;
            
            for (Node adj : top.adjacent) {
                stack.push(adj);
            }
        }
        return cnt;
    }
}
