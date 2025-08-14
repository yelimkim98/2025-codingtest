import java.util.*;

class Node {
    int id;
    Map<Integer, Edge> edges = new HashMap<>();

    public Node(int id) {
        this.id=id;
    }
    public boolean isEdgeExist(int targetNodeId) {
        return edges.containsKey(targetNodeId);
    }
    public void addEdge(int otherNodeId, Edge e) {
        edges.put(otherNodeId, e);
    }
    public Edge getEdge(int nodeId) {
        if (!isEdgeExist(nodeId)) {
            throw new IllegalStateException("ERROR");
        }
        return edges.get(nodeId);
    }
    public Collection<Edge> getEdges() {
        return edges.values();
    }
}

class Edge {
    // n1.id < n2.id
    Node n1;
    Node n2;
    int weight;
    public Edge(Node n1, Node n2, int weight) {
        if (n1.id < n2.id) {
            this.n1=n1;
            this.n2=n2;
        } else {
            this.n1=n2;
            this.n2=n1;
        }
        this.weight=weight;
    }
    public Node getOtherNode(int nodeId) {
        if (nodeId == n1.id) return n2;
        if (nodeId == n2.id) return n1;
        else throw new IllegalStateException("ERROR");
    }
    
    @Override
    public String toString() {
        return String.format("%d-%d %d", n1.id, n2.id, weight);
    }
}

class Graph {
    Node[] nodes;
    public Graph(int n, int[][] roads) {
        nodes = new Node[n];
         for (int i=0; i<n; i++) {
            nodes[i] = new Node(i);
        }
        for (int[] road : roads) {
            Node n1 = nodes[road[0]-1];
            Node n2 = nodes[road[1]-1];
            if (n1.isEdgeExist(n2.id)) {
                Edge e = n1.getEdge(n2.id);
                if (road[2] < e.weight) {
                    e.weight=road[2];
                }
                
            } else {
                Edge e = new Edge(n1, n2, road[2]);
                n1.addEdge(n2.id, e);
                n2.addEdge(n1.id, e);
            }
        }
    }
    public Collection<Edge> getEdges(int nodeId) {
        return nodes[nodeId].getEdges();
    }
}

class Solution {
    
    Graph graph;
    int[] distances;
    boolean[] finished;
    int finishedCnt;
    int lastFinishedIdx;
    
    public int solution(int N, int[][] roads, int K) {
        this.graph = new Graph(N, roads);
        
        // 다익스트라
        // 초기화 : 출발지만 거리0, 나머지는 무한
        distances = new int[N];
        finished = new boolean[N];
        
        distances[0] = 0;
        finished[0] = true;
        finishedCnt = 1;
        lastFinishedIdx = 0;
        
        for (int i=1; i<N; i++) {
            distances[i] = Integer.MAX_VALUE;
        }
        /*
        다익스트라 :
        각 턴마다
        거리가 확정된 노드 중에서 거쳐가거나 아무데도 안거쳐가는 경우에 대해 
        가장 가까운 마을 확정
        모두 확정되면 종료
        
        구현 :
        [직전에 새로 확정된 노드]를 거쳐오는게
        그렇지 않은 경우보다 빠른가?를 확인하여 업데이트
        
        확정 안된 노드들 중 최소거리인 노드를 확정
        */
        while (finishedCnt < N) {
            // lastFinishedIdx를 거치는 거리와, 그 전까지의 거리 중 더 작은 경우로 업데이트
            // 다음에 확정할 노드 결정
            
            // lastFinishedIdx와 연결이 안된 노드 i는
            // lastFinistedIdx~i 거리가 무한이므로 비교 불필요
            for (Edge e : graph.getEdges(lastFinishedIdx)) {
                // System.out.println(e);
                Node targetNode = e.getOtherNode(lastFinishedIdx);
                int newD = add(distances[lastFinishedIdx], e.weight);
                distances[targetNode.id] = Math.min(distances[targetNode.id], newD);
            }
            
            int minDistance = Integer.MAX_VALUE;
            int minDistanceIdx = -1;
            
            for (int i=0; i<N; i++) {
                if (!finished[i] && distances[i] < minDistance) {
                    minDistance = distances[i];
                    minDistanceIdx = i;
                }
            }
            finished[minDistanceIdx] = true;
            lastFinishedIdx = minDistanceIdx;
            finishedCnt++;
        }
        int answer = 0;
        
        for (int i=0; i<N; i++) {
            if (distances[i] <= K) {
                answer++;
            }
        }
        return answer;
    }
    
    int add(int a, int b) {
        long l = (long) a + b;
        return (l > Integer.MAX_VALUE) ? Integer.MAX_VALUE : (int) l;
    }
}
