import java.util.*;
class Point {
    int row, col;
    public Point(int r, int c) {
        row=r; col=c;
    }
}

class Solution {
    
    static final int START = 0;
    static final int EMPTY=-1;
    static final int D=-2;
    // static final int GOAL = -3;
    /*
    . 빈공간    -1
    D 장애물    -2
    R 처음위치   0
    */
    public int solution(String[] boardStrs) {
        try {
        int[][] board = new int[boardStrs.length][boardStrs[0].length()];
        Point start = null;
        Point goal = null;
        
        for (int i=0; i<boardStrs.length; i++) {
            for(int j=0;j<boardStrs[i].length();j++) {
                char c = boardStrs[i].charAt(j);
                if ('.'==c) {
                    board[i][j] = EMPTY;
                }
                else if ('D'==c) {
                    board[i][j] = D;
                } else if ('R'==c) {
                    board[i][j] = START;
                    start = new Point(i, j);
                } else if ('G'==c) {
                    board[i][j] = EMPTY;
                    goal = new Point(i, j);
                }
                // System.out.printf("%2d ", board[i][j]);
            }
            // System.out.println();
        }
        // System.out.printf("start (%d,%d)\n", start.row, start.col);
        
        Queue<Point> q = new ArrayDeque<>();
        q.offer(start);
        
        while (!q.isEmpty()) {
            Point current = q.poll();
            // System.out.printf("현재 (%d,%d)\n",current.row,current.col);
            
            List<Point> nextPoints = getNextPoints(current, board);
            
            for (Point p : nextPoints) {
                int nextValue = board[current.row][current.col] + 1;
                if (p.row == goal.row && p.col == goal.col) {
                    return nextValue;
                } else {
                    board[p.row][p.col] = nextValue;
                    q.offer(p);
                }
            }
        }
        return -1;
            
        }catch(Exception e) {
            System.out.println("ERROR");
            return 0;
        }
    }
    public List<Point> getNextPoints(Point current, int[][] board) {
        // 0상 1하 2좌 3우
        int[] dRows = {-1, 1,  0, 0};
        int[] dCols = { 0, 0, -1, 1};
        
        List<Point> result = new ArrayList<>();
        
        for (int i=0; i<4; i++) {
            int currentRow = current.row;
            int currentCol = current.col;
            
            int nextRow = currentRow+dRows[i];
            int nextCol = currentCol+dCols[i];
            
            /*
            다음이 D 이거나 보드바깥이면
            currentRow, currentCol을 업데이트하지않고 종료
            그렇지않으면 currentRow, currentCol을 next로 업데이트하고 next 업데이트
            */
            while (true) {
                if (nextRow < 0 || nextCol < 0 || nextRow >= board.length || nextCol >= board[0].length 
                   || board[nextRow][nextCol] == D) {
                    break;
                }
                currentRow = nextRow;
                currentCol = nextCol;
                
                nextRow = currentRow+dRows[i];
                nextCol = currentCol+dCols[i];
            }
            // board[currentRow][currentCol]에 방문한 적이 없으면 result에 추가
            if (board[currentRow][currentCol] == EMPTY) {
                result.add(new Point(currentRow, currentCol));
            }
        }
        return result;
    }
}
