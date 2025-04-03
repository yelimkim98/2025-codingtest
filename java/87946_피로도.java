class Solution {
    
    int maxCnt = 0;
        
    public int solution(int k, int[][] dungeons) {
        boolean[] visit = new boolean[dungeons.length];
        
        for (int i=0; i<dungeons.length; i++) {
            explore(k, i, 0, visit, dungeons);
        }
        return maxCnt;
    }
    
    public void explore(int p, int visitIdx, int beforeVisitCnt, boolean[] visit, int[][] dungeons) {
        // visitIdx번 던전 방문
        if (beforeVisitCnt == dungeons.length) {
            maxCnt = beforeVisitCnt;
            return;
        }
        if (visit[visitIdx]) {
            return;
        }
        if (dungeons[visitIdx][0] > p) {    // 던전 추가 방문 종료
            maxCnt = Math.max(beforeVisitCnt, maxCnt);
            // System.out.printf("visitCnt %d에서 추가 방문 종료\n", beforeVisitCnt);
            return;
        }
        int nextP = p - dungeons[visitIdx][1];
        int nextVisitCnt = beforeVisitCnt + 1;
        visit[visitIdx] = true;
        
        // 다른 던전들 방문 요청
        for (int i=0; i<dungeons.length; i++) {
            explore(nextP, i, nextVisitCnt, visit, dungeons);
        }
        // i번 던전 미방문 상태로 복귀
        visit[visitIdx] = false;
    }
}
