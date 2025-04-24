import java.util.*;

class Solution {
    public int solution(int n, int[][] costs) {
        List<Edge> edges = new ArrayList<>();
        for (int[] cost : costs) {
            edges.add(new Edge(cost[0], cost[1], cost[2]));
        }
        Collections.sort(edges);
        
        /*
        arr 초기화 : 아무 간선도 포함시키지 않음 -> 모든 노드의 root가 자기 자신
        
        비용이 낮은 순서대로 간선을 선택하여 모든 간선 탐색
        - 간선의 양 끝 노드가  같은 root 를 갖고있으면  cycle이 형성되어있는 것임 -> 미포함
        - 간선의 양 끝 노드가 아직 같은 root 를 갖지 않으면 해당 간선을 포함해도 cycle이 형성되지 않음 -> 포함
        */
        int[] arr = new int[n];
        
        for (int i=0; i<n; i++) {
            arr[i] = i;
        }
        
        int totalCost = 0;
        
        for (Edge e : edges) {
            int n1Root = find(e.n1, arr);
            int n2Root = find(e.n2, arr);
            
            // System.out.printf("노드 %d root %d, 노드 %d root %d (비용 %d)\n", e.n1, n1Root, e.n2, n2Root, e.cost);
            
            if (n1Root != n2Root) {
                totalCost += e.cost;
                arr[n1Root] = n2Root;
            }
            // for (int i=0; i<n; i++) {
            //     System.out.print(arr[i] + " ");
            // }
            // System.out.println();
        }
        
        return totalCost;
    }
    // arr 을 이용해서 node 의 root를 찾는다.
    public int find(int node, int[] arr) {
        if (arr[node] == node) {  // 본인이 root임
            return node;
        }
        return find(arr[node], arr);
    }
}
class Edge implements Comparable<Edge> {
    int n1;
    int n2;
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
