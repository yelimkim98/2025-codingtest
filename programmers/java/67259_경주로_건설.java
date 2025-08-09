class Solution {
    
    static final int EMPTY = 0;
    static final int WALL = 1;
    int N;
    int[][] board;
    boolean[][] visited;
    int[][] minCost;
    P minP = null;
    
    public int solution(int[][] board) {
        this.board = board;
        N=board.length;
        this.visited = new boolean[board.length][board[0].length];
        this.minCost = new int[N][N];
        
        f(new P(0, 0));
        
        return minP.cost;
    }
    
    void f(P p) {   // p에서 도착지로 이동하기 (도착 시 기록)
        if (p.row == N-1 && p.col == N-1) {
            if (minP == null || p.cost < minP.cost) {
                minP = p;
            }
            return;
        }
        if (minCost[p.row][p.col] != 0 && minCost[p.row][p.col] < p.cost-500) {
            // 이미 해당 점을 지난 더 좋은 케이스가 존재함
            return;
        }
        
        visited[p.row][p.col] = true;
        minCost[p.row][p.col] = p.cost;
        
        for (Direction d : Direction.values()) {
            if (canMove(p, d)) {
                P nextP = p.move(d);
                if (minP == null || nextP.cost < minP.cost) f(nextP);
            }
        }
        visited[p.row][p.col] = false;
    }
        
    boolean canMove(P p, Direction d) {
        int row;
        int col;
        if (d == Direction.UP) {
            row=p.row-1;
            col=p.col;
        } else if (d == Direction.DOWN) {
            row=p.row+1;
            col=p.col;
        } else if (d == Direction.LEFT) {
            row=p.row;
            col=p.col-1;
        } else if (d == Direction.RIGHT) {
            row=p.row;
            col=p.col+1;
        } else {
            throw new IllegalStateException();
        }
        return valid(row, col);
    }
    
    boolean valid(int r, int c) {
        return 0<=r && r<N && 0<=c && c<N
            && board[r][c] == EMPTY && !visited[r][c];
    }
    
    static class P {
        final int row;
        final int col;
        final int cost;
        final Direction lastDirection;
        
        public P(int r, int c) {
            this(r, c, 0, null);
        }
        public P(int r, int c, int cost, Direction d) {
            row=r;
            col=c;
            this.cost=cost;
            lastDirection=d;
        }
        
        P move(Direction d) {
            int newRow;
            int newCol;
            
            if (d == Direction.UP) {
                newRow=row-1;
                newCol=col;
            } else if (d == Direction.DOWN) {
                newRow=row+1;
                newCol=col;
            } else if (d == Direction.LEFT) {
                newRow=row;
                newCol=col-1;
            } else if (d == Direction.RIGHT) {
                newRow=row;
                newCol=col+1;
            } else {
                throw new IllegalStateException();
            }
            int nextCost = cost;
            if (lastDirection == null || d == lastDirection) {
                nextCost += 100;
            } else {
                nextCost += 600;
            }
            return new P(newRow, newCol, nextCost, d);
        }
    }
    enum Direction {
        DOWN, RIGHT, UP, LEFT;
    }
}
