class Solution {
    int[][] board;
    public int[] solution(int rows, int columns, int[][] queries) {
        board = new int[rows][columns];
        int cnt=1;
        for (int i=0; i<rows; i++) {
            for (int j=0; j<columns; j++) {
                board[i][j] = cnt;
                cnt++;
            }
        }
        int[] answer = new int[queries.length];
        for (int i=0; i<queries.length; i++) {
            answer[i]=f(queries[i]);
        }
        return answer;
    }
    
    int f(int[] query) {
        final int rowStart = query[0]-1;
        final int colStart = query[1]-1;
        final int rowEnd = query[2]-1;
        final int colEnd = query[3]-1;
        
        // (rowStart, colStart) colStart -> colEnd (rowStart, colEnd)
        // (rowStart, colEnd) rowStart -> rowEnd (rowEnd, colEnd)
        // (rowEnd, colEnd) colEnd -> colStart (rowEnd, colStart)
        // (rowEnd, colStart) rowEnd -> rowStart (rowStart, colStart)
        
        int beforeRow=rowStart;
        int beforeCol=colStart;
        int beforeVal=board[beforeRow][beforeCol];
        
        int nextRow=beforeRow;
        int nextCol=beforeCol+1;
        int nextVal=board[nextRow][nextCol];
        
        int[] vector = {0, 1}; // 0:row, 1:col
        
        int min = Integer.MAX_VALUE;
        
        while (true) {
            board[nextRow][nextCol]=beforeVal;
            min = Math.min(min, beforeVal);
            
            // before <- next
            beforeRow=nextRow;
            beforeCol=nextCol;
            beforeVal=nextVal;
            
            // before, next 방향 조정을 위한 vector update
            if (beforeRow == rowStart) {
                if (beforeCol == colStart) {  // (rowStart, colStart)
                    return min;
                    
                } else if (beforeCol == colEnd) {  // (rowStart, colEnd)
                    vector[0] = 1;
                    vector[1] = 0;
                }
            } else if (beforeRow == rowEnd) {
                if (beforeCol == colStart) {  // (rowEnd, colStart)
                    vector[0] = -1;
                    vector[1] = 0;
                } else if (beforeCol == colEnd) {  // (rowEnd, colEnd)
                    vector[0] = 0;
                    vector[1] = -1;
                }
            }
            
            // next 새로계산
            nextRow = beforeRow + vector[0];
            nextCol = beforeCol + vector[1];
            nextVal = board[nextRow][nextCol];
        }
    }
}
