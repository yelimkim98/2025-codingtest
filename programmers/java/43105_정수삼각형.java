class Solution {
    
    public int solution(int[][] triangle) {
        try {
        int[][] dp = new int[triangle.length][];
        for (int i=0; i<triangle.length; i++) {
            dp[i] = new int[i+1];
        }
        dp[0][0] = triangle[0][0];
        
        for (int currentRow=0; currentRow<triangle.length-1; currentRow++) {
            int nextRow = currentRow+1;
            
            for (int currentCol=0; currentCol<=currentRow; currentCol++) {
                // System.out.printf("(%d,%d)\n", currentRow, currentCol);
                int currentValue = dp[currentRow][currentCol];
                int nextCol1 = currentCol;
                int nextCol2 = currentCol+1;
                int nextCol1Value = currentValue + triangle[nextRow][nextCol1];
                int nextCol2Value = currentValue + triangle[nextRow][nextCol2];
                dp[nextRow][nextCol1] = Math.max(dp[nextRow][nextCol1], nextCol1Value);
                dp[nextRow][nextCol2] = Math.max(dp[nextRow][nextCol2], nextCol2Value);
            }
        }
        // System.out.println("HI");
        int answer = 0;
        for (int i=0; i<dp[dp.length-1].length; i++) {
            if (dp[dp.length-1][i] > answer) {
                answer = dp[dp.length-1][i];
            }
        }
        return answer;
        }catch(Exception e) {System.out.println("ERROR"); return 0;}
    }
}
