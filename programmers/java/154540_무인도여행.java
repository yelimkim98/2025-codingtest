import java.util.*;

class Solution {
    
    static final int SEA = -1;
    int[][] map;
    boolean[][] visited;
    List<Integer> ansList = new ArrayList<>();
    
    public int[] solution(String[] maps) {
        map = new int[maps.length][maps[0].length()];
        visited = new boolean[maps.length][maps[0].length()];
        
        for (int j=0; j<maps.length; j++) {
            for (int i=0; i<maps[j].length(); i++) {
                char c = maps[j].charAt(i);
                map[j][i] = (c == 'X') ? SEA : (c - '0');
            }
        }
        for (int i=0; i<map.length; i++) {
            for (int j=0; j<map[0].length; j++) {
                if (map[i][j] != SEA && !visited[i][j]) {
                    int result = f(i, j);
                    ansList.add(result);
                }
            }
        }
        if (ansList.isEmpty()) {
            int[] answer = {-1};
            return answer;
        }
        Collections.sort(ansList);
        int[] answer = new int[ansList.size()];
        for (int i=0; i<ansList.size(); i++) {
            answer[i] = ansList.get(i);
        }
        return answer;
    }
    
    private int f(int row, int col) {
        // 현재위치 방문처리
        visited[row][col] = true;
        int sum = map[row][col];
        
        // 종료조건 : 상하좌우로 갈 곳이 더이상 없을 때
        
        // 상
        int nextRow=row-1;  int nextCol=col;
        if (ok(nextRow, nextCol)) {
            sum += f(nextRow, nextCol);
        }
        // 하
        nextRow=row+1;  nextCol=col;
        if (ok(nextRow, nextCol)) {
            sum += f(nextRow, nextCol);
        }
        // 좌
        nextRow=row;  nextCol=col-1;
        if (ok(nextRow, nextCol)) {
            sum += f(nextRow, nextCol);
        }
        // 우
        nextRow=row;  nextCol=col+1;
        if (ok(nextRow, nextCol)) {
            sum += f(nextRow, nextCol);
        }
        return sum;
    }
    private boolean ok(int nextRow, int nextCol) {
        return 0 <= nextRow && nextRow < map.length
            && 0 <= nextCol && nextCol < map[0].length
            && map[nextRow][nextCol] != SEA 
            && !visited[nextRow][nextCol];
    }
}
