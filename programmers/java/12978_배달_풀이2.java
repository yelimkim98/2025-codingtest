class Solution {
    public int solution(int n, int[][] roads, int k) {
        int[][] graph = new int[n+1][n+1];
        
        for (int i=1; i<=n; i++) {
            for (int j=1; j<=n; j++) {
                graph[i][j] = (i==j) ? 0 : Integer.MAX_VALUE;
            }
        }
        for (int[] road : roads) {
            int n1 = road[0];
            int n2 = road[1];
            int w = road[2];
            graph[n1][n2] = Math.min(graph[n1][n2], w);
            graph[n2][n1] = graph[n1][n2];
        }
        
        // 1번으로부터의 거리
        int[] distances = new int[n+1];
        boolean[] visited = new boolean[n+1];
        for (int i=2;i<=n;i++){
            distances[i] = Integer.MAX_VALUE;
        }
        // 방문 기준 : 방문되지 않은 모든 노드 중 거리가 가장 짧은 노드
        // 방문했을 때 작업 : 방문처리, 방문하지 않은 인접 노드들의 거리 update
        int currentNode = 1;
        
        while (true) {
            visited[currentNode] = true;
            for (int i=1; i<=n; i++) {
                boolean isAdj = graph[currentNode][i] < Integer.MAX_VALUE;
                if (isAdj && !visited[i]) {
                    int originalDistance = distances[i];
                    int newDistance = distances[currentNode] + graph[currentNode][i];
                    distances[i] = Math.min(originalDistance, newDistance);
                }
            }
            // 다음에 방문할 노드 찾기 (없으면 루프 종료)
            int nextNode = 0;
            int minDistance = Integer.MAX_VALUE;
            for (int i=1; i<=n; i++) {
                if (!visited[i] && distances[i] < minDistance) {
                    nextNode = i;
                    minDistance = distances[i];
                }
            }
            if (nextNode == 0) {
                break;
            } else {
                currentNode = nextNode;
            }
        }
        int answer = 0;
        for (int i=1; i<=n; i++) {
            if (distances[i] <= k) {
                answer++;
            }
        }
        return answer;
    }
}
