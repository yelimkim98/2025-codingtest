import java.util.*;

class Node {
    String word;
    int idx;
    Set<Node> adjacents = new HashSet<>();
    int time = 0;
    
    public Node(int idx, String word) { 
        this.idx = idx;
        this.word = word; 
    }
    
    public void addAdj(Node n) {
        this.adjacents.add(n);
    }
    public boolean isAdj(Node n) {
        int cnt = 0;
        for (int i=0; i<this.word.length(); i++) {
            if (this.word.charAt(i) != n.word.charAt(i)) {
                cnt++;
            }
        }
        return cnt == 1;
    }
    public void updateTime(int newTime) {
        if (time == 0) {
            this.time = newTime;
        }
        else {
            this.time = Math.min(time, newTime);
        }
        
    }
    
    @Override
    public String toString() {
        return "(" + this.idx + " " + this.word + ")";
    }
}

class Solution {
    
    public int solution(String begin, String target, String[] words) {
        Node[] nodes = new Node[words.length+1];
        nodes[0] = new Node(0, begin);
        int targetIdx = -1;
        
        for (int i=1; i<=words.length; i++) {
            nodes[i] = new Node(i, words[i-1]);
            if (words[i-1].equals(target)) {
                targetIdx = i;
            }
        }
        if (targetIdx == -1) {
            return 0;
        }
        for (int i=0; i<nodes.length; i++) {
            for (int j=0; j<nodes.length; j++) {
                // nodes[i]와 nodes[j]가 서로 인접노드이면 양쪽에 인접노드 정보 추가
                if (nodes[i].isAdj(nodes[j])) {
                    nodes[i].addAdj(nodes[j]);
                    nodes[j].addAdj(nodes[i]);
                }
            }
        }
        // for (Node n : nodes) {
        //     for (Node adj : n.adjacents) {
        //         System.out.printf("(%d %s) ", adj.idx, adj.word);
        //     }
        //     System.out.println("");
        // }
        // 0번노드에서 targetIdx번 노드로 가려면?
        boolean[] visit = new boolean[nodes.length];
        Queue<Node> q = new ArrayDeque<>();
        q.offer(nodes[0]);
        
        while (!q.isEmpty()) {
            Node n = q.poll();
            if (n.idx == targetIdx) {
                return n.time;
            }
            visit[n.idx] = true;
            // System.out.printf("Node %d %s adj ", n.idx, n.word);
            
            for (Node adj : n.adjacents) {
                if (visit[adj.idx]) {
                    continue;
                }
                adj.updateTime(n.time + 1);
                q.offer(adj);
                // System.out.printf("(%d %s %d) ", adj.idx, adj.word, adj.time);
            }
            // System.out.println();
        }
        return 0;
    }
}
