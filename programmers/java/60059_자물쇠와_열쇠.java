class Solution {
    int[][] key;
    int[][] lock;
    public boolean solution(int[][] key, int[][] lock) {
        this.key=key;
        this.lock=lock;
        
        // 0도
        if (test()) return true;
        
        // 90도
        turnKey();
        if (test()) return true;
        // System.out.println("test " + test());
        
        // 180도
        turnKey();
        if (test()) return true;
        // System.out.println("test " + test());
        
        // 270도
        turnKey();
        if (test()) return true;
        // System.out.println("test " + test());
        
        return false;
    }
    
    void turnKey() {
        int[][] newKey = new int[key.length][key.length];
        
        for (int row=0, newCol=key.length-1; row<key.length; row++, newCol--) {
            for (int col=0, newRow=0; col<key.length; col++, newRow++) {
                newKey[newRow][newCol] = key[row][col];
            }
        }
        key=newKey;
        // for (int i=0; i<key.length; i++) {
        //     for (int j=0; j<key.length; j++) {
        //         System.out.print(key[i][j] + " ");
        //     }
        //     System.out.println();
        // }
    }
    
    // 이동테스트 - 성공하면 boolean
    boolean test() {
        // 좌측 상단 (row, col) 기준
        // ( -(key-1), -(key-1) ) ~ (lock.length-1, lock.length-1)
        
        for (int lockRow=-(key.length-1); lockRow<lock.length; lockRow++) {
            for (int lockCol=-(key.length-1); lockCol<lock.length; lockCol++) {
                if (match(lockRow, lockCol)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    boolean match(int lockStartRow, int lockStartCol) {
        boolean[][] visited = new boolean[lock.length][lock.length];
        
        for (int keyRow=0, lockRow=lockStartRow; keyRow<key.length; keyRow++, lockRow++) {
            for (int keyCol=0, lockCol=lockStartCol; keyCol<key.length; keyCol++, lockCol++) {
                if ((0 <= lockRow && lockRow < lock.length)
                 && (0 <= lockCol && lockCol < lock.length)) {
                    if (key[keyRow][keyCol] == lock[lockRow][lockCol]) {
                        // 홈과 홈 또는 돌기와 돌기가 만남
                        return false;
                    }
                    visited[lockRow][lockCol]=true;
                }
            }
        }
        // 나머지 영역에 홈이 하나라도 있으면 false
        for (int i=0; i<lock.length; i++) {
            for (int j=0; j<lock.length; j++) {
                // 열쇠 꽂은 영역 외에 홈이 존재
                if (!visited[i][j] && lock[i][j] == 0) return false;
            }
        }
        return true;
    }
}
