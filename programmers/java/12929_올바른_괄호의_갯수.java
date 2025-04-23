class Solution {
    public int solution(int n) {
        return f(1, 0, n);
    }
    
    /*
    currentPosition : (1(2(3(4(5(6 에서 현재 위치
    usedCnt : 직전까지 사용된 닫는괄호 갯수
    totalCnt : 닫는 괄호 총 몇개 사용되어야하는지 갯수
    */
    public int f(int currentPosition, int usedCnt, int totalCnt) {
        if (currentPosition == totalCnt) {  // 현재 마지막 위치
            return 1;  // 무조건 남은 닫는괄호 갯수만큼 닫는괄호를 써야함
        }
        
        int cnt = 0;
        
        for (int i=0; i<=currentPosition-usedCnt; i++) {
            // 현재위치에 닫는괄호를 i개 사용한 경우의 수
            cnt += f(currentPosition+1, usedCnt+i, totalCnt);
        }
        return cnt;
    }
}
