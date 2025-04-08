class Solution {
    public int solution(int m, int n, int[][] puddles) {
        /*
         물 있는 곳 -1
         그외 최대 최단경로 수
         */
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        
        for (int[] puddle : puddles) {
            dp[puddle[0]-1][puddle[1]-1] = -1;
        }
        for (int row=0; row<m; row++){
            for (int col=0; col<n; col++) {
                if (dp[row][col] == -1) continue;
                
                // 왼쪽에서 come
                if (col-1 >= 0 && dp[row][col-1] != -1) {
                    dp[row][col] = (dp[row][col] + dp[row][col-1]) % 1_000_000_007;
                }
                // 위에서 come
                if (row-1 >= 0 && dp[row-1][col] != -1) {
                    dp[row][col] = (dp[row][col] + dp[row-1][col]) % 1_000_000_007;
                }
                // System.out.printf("%2d ", dp[row][col]);
            }
            // System.out.println();
        }
        return dp[m-1][n-1];
    }
}
/*
    1  1  1
 1 -1  1  2
 1  1  2  4
 */
