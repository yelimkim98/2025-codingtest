class Solution {
    static final int MAX = 1_000_000;
    
    public int solution(int n, int[][] results) {
        int[][] arr = new int[n+1][n+1];
        
        // arr 초기화 - 무한대, 자기자신에겐 0, 인접 노드 설정
        for (int i=1; i<=n; i++) {
            for (int j=1; j<=n; j++) {
                if (i==j) {
                    arr[i][j] = 0;
                    continue;
                }
                arr[i][j] = MAX;
            }
        }
        for (int[] result : results) {
            int n1 = result[0];
            int n2 = result[1];
            arr[n1][n2] = 1;
        }
        
        // 플로이드 워셜 실행
        for (int mid=1; mid<=n; mid++) {
            for (int start=1; start<=n; start++) {
                for (int end=1; end<=n; end++) {
                    int originalDistance = arr[start][end];
                    int newDistance = arr[start][mid] + arr[mid][end];
                    arr[start][end] = Math.min(originalDistance, newDistance);
                }
            }
        }
        
        // A->B 또는 B->A가 가능하면 A와 B사이의 순위를 매길 수 있는 것임
        // A가 임의의 노드 B에 대해 A->B 또는 B->A가 가능하면 A는 정확하게 순위를 매길 수 있는 노드
        int answer = 0;
        
        for (int i=1; i<=n; i++) {
            // i번 노드가 모든 노드에 대해 순위를 매길 수 있는가?
            boolean can = true;
            
            for (int j=1; j<=n; j++) {
                if (i==j) {
                    continue;
                }
                if (arr[i][j] >= MAX && arr[j][i] >= MAX) {
                    can = false;
                    break;
                }
            }
            if (can) {
                answer++;
            }
        }
        return answer;
    }
}


/*
모든 선수에 대해 승 또는 패 기록이 간접적으로 만들어져야 함 -> 모든 선수에 대해 그래프로 이동 가능해야 함
플로이드 워셜 : A 노드에서 B 노드로 가는 최단거리, 이동 불가능하면 무한대
플로이드 워셜로 모든 노드 간의 최단거리를 구해서, 이동 가능한 선수의 수 return
*/
