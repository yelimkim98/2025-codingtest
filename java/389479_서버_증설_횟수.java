class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        /*
         [0]:0~1  [1]:1~2  [2]:2~3
         */
        int[] serverCnt = new int[players.length];
        /*
         m=3이면
         playerCnt가
         0이상 3미만 : 0대 증설
         3이상 6미만 : 1대 증설
         6이상 9미만 : 2대 증설
         9이상 12미만 : 3대 증설
         -> playerCnt / 3 만큼 증설 필요
         
         i번 시간대에 증설할 서버의 수 : players[i] / m
         */
        for (int i=0; i<players.length; i++) {
            int requiredServerCnt = players[i] / m;
            int currentServerCnt = serverCnt[i];
            if (currentServerCnt < requiredServerCnt) {
                int increase = requiredServerCnt - currentServerCnt;
                for (int j=0 ;j<k && i+j<players.length; j++) {
                    serverCnt[i+j] += increase;
                }
                answer += increase;
            }
        }
        
        return answer;
    }
}
