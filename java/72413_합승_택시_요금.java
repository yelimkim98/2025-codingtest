class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        // 모든 점의 id를 1 줄여서 쓰자.
        int[][] arr = new int[n][n];
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                if (i==j) arr[i][j] = 0;
                else arr[i][j] = Integer.MAX_VALUE;
            }
        }
        for (int i=0; i<fares.length; i++) {
            int p1 = fares[i][0]-1;
            int p2 = fares[i][1]-1;
            int cost = fares[i][2];
            arr[p1][p2] = cost;
            arr[p2][p1] = cost;
        }
        for (int mid=0; mid<n; mid++) {
            for (int start=0; start<n; start++) {
                for (int end=0; end<n; end++) {
                    int originalCost = arr[start][end];
                    int newCost = add(arr[start][mid], arr[mid][end]);
                    arr[start][end] = Math.min(originalCost, newCost);
                }
            }
        }
        // 임의의 노드 i에 대하여 i까지 같이 탈 경우 비용 계산하여 최솟값 마련
        int answer = Integer.MAX_VALUE;
        
        for (int i=0; i<n; i++) {
            int togetherCost = arr[s-1][i];
            int aSoloCost = arr[i][a-1];
            int bSoloCost = arr[i][b-1];
            answer = Math.min(answer, add(togetherCost, aSoloCost, bSoloCost));
        }
        return answer;
    }
    
    int add(int a, int b) {
        if (a==Integer.MAX_VALUE || b==Integer.MAX_VALUE) return Integer.MAX_VALUE;
        return a+b;
    }
    int add(int a, int b, int c) {
        if (a==Integer.MAX_VALUE || b==Integer.MAX_VALUE || c==Integer.MAX_VALUE) return Integer.MAX_VALUE;
        return a+b+c;
    }
}
