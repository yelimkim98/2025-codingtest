class Solution {
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        
        for (int i=0; i<5; i++) {
            answer[i] = ff(places[i]);
        }
        return answer;
    }
    
    // 대기실별 검사
    int ff(String[] place) {
        boolean[][] visited = new boolean[5][5];
        
        for (int row=0; row<5; row++) {
            for (int col=0; col<5; col++) {
                char c = place[row].charAt(col);
                if (c == 'P' && !visited[row][col]) {
                    boolean result = f(row, col, place, visited);
                    if (result == false) return 0;
                }
            }
        }
        return 1;
    }
    
    // row, col 에 있는 사람이 규정을 잘 지켰는가?
    boolean f(int row, int col, String[] place, boolean[][] visited) {
        visited[row][col] = true;
        
        // 맨해튼거리 2 이하 : 상하좌우 1칸, 2칸, 대각선 1칸
        // 상하좌우 1칸에 사람이 있거나
        // 상하좌우 어딘가에 빈책상이 있고, 빈책상 옆에 사람이 있거나
        int nextRow=row;
        int nextCol=col;
        // 상하좌우 1칸
        // 상
        nextRow = row-1;
        nextCol = col;
        if (!v(nextRow, nextCol, place, visited)) return false;
        
        // 하
        nextRow = row+1;
        nextCol = col;
        if (!v(nextRow, nextCol, place, visited)) return false;
        
        // 좌
        nextRow = row;
        nextCol = col-1;
        if (!v(nextRow, nextCol, place, visited)) return false;
            
        // 우
        nextRow = row;
        nextCol = col+1;
        if (!v(nextRow, nextCol, place, visited)) return false;
        
        return true;
    }
    
    // 인접위치 검사 (true면 검사통과, false면 규칙어김)
    boolean v(int nextRow, int nextCol, String[] place, boolean[][] visited) {
        if (valid(nextRow, nextCol, visited)) {
            // 사람이 있는 경우
            if (place[nextRow].charAt(nextCol) == 'P') {
                return false;
            }
            // 빈책상이 있는 경우
            if (place[nextRow].charAt(nextCol) == 'O') {
                // 빈 책상의 상하좌우에 확인되지 않은 사람이 있는가?
                // 상
                int nextnextRow=nextRow-1;
                int nextnextCol=nextCol;
                if (person(nextnextRow, nextnextCol, place, visited)) return false;
                
                // 하
                nextnextRow=nextRow+1;
                nextnextCol=nextCol;
                if (person(nextnextRow, nextnextCol, place, visited)) return false;
                
                // 좌
                nextnextRow=nextRow;
                nextnextCol=nextCol-1;
                if (person(nextnextRow, nextnextCol, place, visited)) return false;
                
                // 우
                nextnextRow=nextRow;
                nextnextCol=nextCol+1;
                if (person(nextnextRow, nextnextCol, place, visited)) return false;
            }
        }
        return true;
    }
    
    // 해당 위치가 검사 대상인가
    boolean person(int row, int col, String[] place, boolean[][] visited) {
        return valid(row, col, visited)
            && place[row].charAt(col) == 'P';
    }
    
    boolean valid(int row, int col, boolean[][] visited) {
        return 0 <= row && row < 5
            && 0 <= col && col < 5
            && !visited[row][col];
    }
}
