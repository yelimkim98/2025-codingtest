class Solution {
    
    public int solution(String arr[]) {
        final int OPERAND_CNT = (arr.length + 1)/2;
        /*
        0 2 4 6 8
        0+1+2-3-4
        dp[a][b] : operands[a] 부터 [b]까지 연산결과의 최댓값
        dp[b][a] : operands[a] 부터 [b]까지 연산결과의 최솟값
        operands[a] == arr[2a]
        operands[b] = arr[2b]
        */
        int[][] dpMax = new int[OPERAND_CNT][OPERAND_CNT];
        int[][] dpMin = new int[OPERAND_CNT][OPERAND_CNT];
        boolean[][] visited = new boolean[OPERAND_CNT][OPERAND_CNT];

        for (int i=0; i<OPERAND_CNT; i++) {
            dpMax[i][i] = Integer.parseInt(arr[2*i]);
            dpMin[i][i] = Integer.parseInt(arr[2*i]);
        }
        /*
         01 12 23 34 45
         02 13 24 35
         03 14 25
         04 15
         05
         */
        int gap = 1;
        while (gap < OPERAND_CNT) {
            for (int start=0, end=start+gap; end<OPERAND_CNT; start++,end++) {
                // arr[start*2]부터 arr[end*2] 까지 연산결과의 최댓값
                for (int middle=start; middle<end; middle++) {
                    String operator = arr[middle*2+1];
                    int result;
                    
                    if ("+".equals(operator)) {
                        result = dpMax[start][middle] + dpMax[middle+1][end];    // 최댓값
                        // System.out.printf("(%d,%d)=%d+%d ",start,end,op1,dpMax[middle+1][end]);
                    } else {    // "-"
                        result = dpMin[start][middle] - dpMin[middle+1][end];    // 최솟값
                        // System.out.printf("(%d,%d)=%d-%d ",start,end,op1,dpMax[middle+1][end]);
                    }                    
                    if (!visited[start][end]) {
                        dpMax[start][end] = result;
                        dpMin[start][end] = result;
                        visited[start][end] = true;
                    } else {
                        dpMax[start][end] = Math.max(dpMax[start][end], result);
                        dpMin[start][end] = Math.min(dpMin[start][end], result);
                    }
                    // System.out.printf("max %d min %d\n", dpMax[start][end], dpMin[start][end]);
                }
            }
            gap++;
        }
        // for (int i=0; i<OPERAND_CNT;i++){
        //     for(int j=0;j<OPERAND_CNT;j++) {
        //         System.out.print(dpMax[i][j] + " ");
        //     }
        //     System.out.println();
        // }
        return dpMax[0][OPERAND_CNT-1];
    }
}
