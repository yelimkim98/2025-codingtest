class Solution {
    
    static final char EMPTY = '0';
    
    public int solution(String[] storage, String[] requests) {
        // 빈칸에는 '0' 삽입
        char[][] store = new char[storage.length][storage[0].length()];
        for (int i=0; i<storage.length; i++) {
            for (int j=0; j<storage[i].length(); j++) {
                store[i][j] = storage[i].charAt(j);
            }
        }
        boolean[][] isOnBorder = new boolean[storage.length][storage[0].length()];
        for (int i=0; i<storage.length; i++) {
            isOnBorder[i][0] = true;
            isOnBorder[i][storage[0].length()-1] = true;
        }
        for (int i=0; i<storage[0].length(); i++) {
            isOnBorder[0][i] = true;
            isOnBorder[storage.length-1][i] = true;
        }
        
        for (String request : requests) {
            if (request.length() > 1) {
                pollAll(request.charAt(0), store);
            } else {
                updateBorder(store, isOnBorder);
                for (int i=0; i<isOnBorder.length; i++) {
                    for (int j=0; j<isOnBorder[0].length; j++) {
                        if (isOnBorder[i][j] == true && store[i][j] == request.charAt(0)) {
                            store[i][j] = EMPTY;
                        }
                    }
                }
            }
            // for (int i=0; i<isOnBorder.length; i++) {
            //     for (int j=0; j<isOnBorder[0].length; j++) {
            //         System.out.print((isOnBorder[i][j] ? 1 : 0) + " ");
            //     }
            //     System.out.println();
            // }
            // System.out.println();
        }
        
        
        int answer = 0;
        for (int i=0; i<store.length; i++) {
            for (int j=0; j<store[0].length; j++) {
                if (store[i][j] != '0') answer++;
                // System.out.print(store[i][j] + " ");
            }
            // System.out.println();
        }
        return answer;
    }
    
    void pollAll(char c, char[][] store) {
        for (int i=0; i<store.length; i++) {
            for (int j=0; j<store[0].length; j++) {
                if (store[i][j] == c) {
                    store[i][j] = EMPTY;
                }
            }
        }
    }
    
    void updateBorder(char[][] store, boolean[][] isOnBorder) {
        boolean[][] visited = new boolean[store.length][store[0].length];
        
        for (int i=0; i<store.length; i++) {
            if (store[i][0] == EMPTY) {
                explore(i, 0, visited, store, isOnBorder);
            }
            if (store[i][store[0].length-1] == EMPTY) {
                explore(i, store[0].length-1, visited, store, isOnBorder);
            }
        }
        for (int i=0; i<store[0].length; i++) {
            if (store[0][i] == EMPTY) {
                explore(0, i, visited, store, isOnBorder);
            }
            if (store[store.length-1][i] == EMPTY) {
                explore(store.length-1, i, visited, store, isOnBorder);
            }
        }
    }
    
    void explore(
        int row, int col, boolean[][] visited,
        char[][] store, boolean[][] isOnBorder
    ) {
        if (visited[row][col]) return;
        
        // 방문처리
        visited[row][col] = true;
        
        // 현재위치가 외부라면 isOnBorder 에서는 제외 (테두리가 아니라 아얘 외부가 되었으므로)
        if (store[row][col] == '0') {
            isOnBorder[row][col] = false;
        }
        
        // 상하좌우에 store!='0'인 칸이 있으면 isOnBorder True 처리
        if (row > 0 && store[row-1][col] != '0') {  // 상
            isOnBorder[row-1][col] = true;
        }
        if (row < visited.length-1 && store[row+1][col] != '0') { //하
            isOnBorder[row+1][col] = true;
        }
        if (col > 0 && store[row][col-1] != '0') { // 좌
            isOnBorder[row][col-1] = true;
        }
        if (col < visited[0].length-1 && store[row][col+1] != '0') { // 우
            isOnBorder[row][col+1] = true;
        }
        
        // 상하좌우에 visited=false, store='0'인 칸이 있으면 재귀호출
        if (row > 0 && !visited[row-1][col] && store[row-1][col] == '0') {  // 상
            explore(row-1, col, visited, store, isOnBorder);
        }
        if (row < visited.length-1 && !visited[row+1][col] && store[row+1][col] == '0') {  //하
            explore(row+1, col, visited, store, isOnBorder);
        }
        if (col > 0 && !visited[row][col-1] && store[row][col-1] == '0') {  // 좌
            explore(row, col-1, visited, store, isOnBorder);
        }
        if (col < visited[0].length-1 && !visited[row][col+1] && store[row][col+1] == '0') {  // 우
            explore(row, col+1, visited, store, isOnBorder);
        }
    }
}
