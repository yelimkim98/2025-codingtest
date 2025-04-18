import java.util.*;

class Solution {
    
    public int solution(int[][] land) {
        int[][] landIds = new int[land.length][land[0].length];
        Map<Integer, Integer> sizesByLandId = new HashMap<>();
        
        int id = 1;
        for (int i=0; i<land.length; i++) {
            for (int j=0; j<land[0].length; j++) {
                if (land[i][j] == 1 && landIds[i][j] == 0) {
                    int size = dfs(i, j, landIds, land, id);
                    sizesByLandId.put(id, size);
                    id++;
                }
            }
        }
        int answer = 0;
        
        for (int i=0; i<land[0].length; i++) {
            Set<Integer> ids = new HashSet<>();
            for (int j=0; j<land.length; j++) {
                if (landIds[j][i] > 0) {
                    ids.add(landIds[j][i]);
                }
            }
            int result = 0;
            for (int landId : ids) {
                result += sizesByLandId.get(landId);
            }
            answer = Math.max(answer, result);
        }
        
        return answer;
    }
    
    int dfs(int startRow, int startCol, int[][] landIds, int[][] land, int id) {
        Deque<Point> stack = new ArrayDeque<>();
        stack.push(new Point(startRow, startCol));
        landIds[startRow][startCol] = id;
        int result = 0;
        
        while (!stack.isEmpty()) {
            Point p = stack.pop();
            result++;
            int row = p.row;
            int col = p.col;
            
            
            // 상
            if (row > 0 && landIds[row-1][col]==0 && land[row-1][col] == 1) {
                landIds[row-1][col] = id;
                stack.push(new Point(row-1, col));
            }
            // 하
            if (row < land.length-1 && landIds[row+1][col]==0 && land[row+1][col] == 1) {
                landIds[row+1][col] = id;
                stack.push(new Point(row+1, col));
            }
            // 좌
            if (col > 0 && landIds[row][col-1]==0 && land[row][col-1]==1) {
                landIds[row][col-1] = id;
                stack.push(new Point(row, col-1));
            }
            // 우
            if (col < land[0].length-1 && landIds[row][col+1]==0 && land[row][col+1]==1) {
                landIds[row][col+1] = id;
                stack.push(new Point(row, col+1));
            }
        }
        return result;
    }
}

class Point {
    int row;
    int col;
    public Point(int r, int c) {
        row = r;
        col = c;
    }
}
