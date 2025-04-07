import java.util.*;

class Position {
    int row, col;
    int order;
    
    public Position(int r, int c, int o) {
        this.row = r;
        this.col = c;
        this.order = o;
    }
    public Position up() {
        if (row == 0) return null;
        return new Position(row-1, col, order+1);
    }
    public Position down(int rowCnt) {
        if (row+1 < rowCnt) { return new Position(row+1, col, order+1); }
        return null;
    }
    public Position left() {
        if (col == 0) return null;
        return new Position(row, col-1, order+1);
    }
    public Position right(int colCnt) {
        if (col+1 < colCnt) return new Position(row, col+1, order+1);
        return null;
    }
}
class Solution {
    public int solution(int[][] maps) {
        try{
            final int ROW_CNT = maps.length;
            final int COL_CNT = maps[0].length;

            /* 몇번째로 방문했는가 (0 : 0번째 방문 or 미방문) */
            int[][] visitOrders = new int[ROW_CNT][COL_CNT];

            Queue<Position> q = new ArrayDeque<>();
            q.offer(new Position(0, 0, 1));

            while (!q.isEmpty()) {
                Position current = q.poll();
                if (current.row == ROW_CNT-1 && current.col == COL_CNT-1) {
                    // 상대진영 목적지 도착
                    return current.order;
                }

                // 상
                Position up = current.up();
                if (up != null && maps[up.row][up.col] == 1 && visitOrders[up.row][up.col] == 0) {
                    q.offer(up);
                    visitOrders[up.row][up.col] = up.order;
                }
                // 하
                Position down = current.down(ROW_CNT);
                if (down != null && maps[down.row][down.col] == 1 && visitOrders[down.row][down.col] == 0) {
                    q.offer(down);
                    visitOrders[down.row][down.col] = down.order;
                }
                // 좌
                Position left = current.left();
                if (left != null && maps[left.row][left.col] == 1 && visitOrders[left.row][left.col] == 0) {
                    q.offer(left);
                    visitOrders[left.row][left.col] = left.order;
                }
                // 우
                Position right = current.right(COL_CNT);
                if (right != null && maps[right.row][right.col] == 1 && visitOrders[right.row][right.col] == 0) {
                    q.offer(right);
                    visitOrders[right.row][right.col] = right.order;
                }
            }
            return -1;
            
        }catch(Exception e) {
            System.out.println("ERROR");
            return 0;
        }
    }
}
