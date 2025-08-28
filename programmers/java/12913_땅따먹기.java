class Solution {
    int solution(int[][] land) {
        int[][] dp = new int[land.length][4];
        for (int i=0; i<4; i++) {
            dp[0][i]=land[0][i];
        }
        for (int row=0; row<land.length-1; row++) {
            for (int col=0; col<4; col++) {
                // (row, col)
                int nextRow=row+1;
                
                for (int nextCol=0; nextCol<4; nextCol++) {
                    if (nextCol==col) continue;
                    // (row, col)에서 (nextRow, nextCol)로 갔을 때 점수
                    // newScore = dp[row][col] + land[nextRow][nextCol]
                    // dp[nextRow][nextCol]=Math.max(dp[nextRow][nextCol], newScore)
                    int nextScore = dp[row][col] + land[nextRow][nextCol];
                    dp[nextRow][nextCol] = Math.max(dp[nextRow][nextCol], nextScore);
                }
            }
        }
        int answer = dp[land.length-1][0];
        for (int col=1; col<4; col++) {
            answer = Math.max(answer, dp[land.length-1][col]);
        }
        return answer;
    }
}
/*

5 1 4 2
5 1 4 2
5 1 4 2
5 1 4 2
5 1 4 2
*/
