import java.util.*;
class Move {
    int start;
    int end;
    public Move(int s, int e) {
        start = s; end = e;
    }
    @Override
    public String toString() {
        return String.format("[%d,%d]", start, end);
    }
}
class Solution {
    List<Move> moves = new ArrayList<>();
    
    public int[][] solution(int n) {
        recursive(1, 2, 3, n);
        // System.out.println(moves);
        int[][] ans = new int[moves.size()][2];
        for (int i=0; i<moves.size(); i++) {
            Move m = moves.get(i);
            ans[i][0] = m.start;
            ans[i][1] = m.end;
        }
        return ans;
    }
    
    void recursive(int start, int mid, int end, int ringCnt) {
        if (ringCnt == 1) {
            // start에서 end로 바로 옮긴다.
            moves.add(new Move(start, end));
            return;
        }
        // start에서 mid로 옮긴다.
        recursive(start, end, mid, ringCnt-1);
        // start에서 end로 옮긴다.
        moves.add(new Move(start, end));
        // mid에서 end로 옮긴다.
        recursive(mid, start, end, ringCnt-1);
    }
}
