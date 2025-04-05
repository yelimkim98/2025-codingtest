class Solution {
    public int solution(String name) {
        int virticalCnt = 0;
        boolean[] completed = new boolean[name.length()];
        int completedCnt = 0;
        
        // 아래위 이동 갯수
        for (int i=0; i<name.length(); i++) {
            char c = name.charAt(i);
            if (c == 'A') {
                completed[i] = true;
                completedCnt++;
                continue;
            }
            int case1 = c-'A';
            int case2 = 1+'Z'-c;
            virticalCnt += Math.min(case1, case2);
        }
        
        // 좌우 이동 갯수
        if (!completed[0]) {
            completed[0] = true;
            completedCnt++;
        }
        int horizentalCnt = horizental(0, completedCnt, completed);
        
        return virticalCnt + horizentalCnt;
    }
    
    int horizental(int currentIdx, int completedCnt, boolean[] completed) {
        if (completedCnt == completed.length) return 0;
        
        final int LAST_IDX = completed.length-1;
        // left
        int leftIdx = currentIdx;
        int leftCnt = 0;
        do {
            leftIdx = (leftIdx == 0) ? LAST_IDX : leftIdx-1;
            leftCnt++;
        } while (completed[leftIdx]);
        
        completed[leftIdx] = true;
        leftCnt += horizental(leftIdx, completedCnt+1, completed);
        completed[leftIdx] = false;
        
        // right
        int rightIdx = currentIdx;
        int rightCnt = 0;
        do {
            rightIdx = (rightIdx == LAST_IDX) ? 0 : rightIdx+1;
            rightCnt++;
        } while (completed[rightIdx]);
        
        completed[rightIdx] = true;
        rightCnt += horizental(rightIdx, completedCnt+1, completed);
        completed[rightIdx] = false;
        
        return Math.min(leftCnt, rightCnt);
    }
}
