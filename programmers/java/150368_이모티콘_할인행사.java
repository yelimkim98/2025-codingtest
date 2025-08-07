class Solution {
    
    int[][] users;
    int[] emoticons;
    int[] sales;
    int[] saleRates;
    int[] RATES = {10, 20, 30, 40};
    int[] ans = {0, 0};
    int[] amounts;  // user 별 이모티콘 구입 금액 (플러스 미반영)
    
    public int[] solution(int[][] users, int[] emoticons) {
        this.users = users;
        this.emoticons = emoticons;
        this.sales = new int[emoticons.length];
        this.saleRates = new int[emoticons.length];
        this.amounts = new int[users.length];
        
        // System.out.println(Math.pow(4, 7));
        // 이모지 가격 책정 케이스 13860 가지
        // user 별 가격 계산 700
        // 완전탐색하자.
        f(0);
        
        
        return ans;
    }
    
    void f(int idx) {
        // 종료조건 : 마지막 이모지까지 할인율이 결정된 경우 (idx >= emoticons.length)
        // 1. 이모티콘플러스 서비스 가입자 수
        // 2. 이모티콘플러스 미가입자 이모티콘 판매액
        // 두 값을 계산하여 
        // 이전 기록보다 1이 크거나, 1이 같고 2가 크면 update하고 종료
        if (idx >= emoticons.length) {
            int plusCnt = 0;      // 이모티콘플러스 서비스 가입자 수
            int salesResult = 0;  // 이모티콘플러스 미가입자 이모티콘 판매액
            
            for (int i=0; i<users.length; i++) {
                int money = 0; // user i가 이모티콘 구매에 얼마를 쓸것인가?
                
                for(int j=0; j<emoticons.length; j++) {
                    // sales[j] : j번 이모티콘의 판매가격
                    // saleRates[j] : j번 이모티콘의 할인률
                    // user i는 j번 이모티콘에 대해서 saleRates[j] >= user[i][0] 이면 이모티콘을 구매한다.
                    if (saleRates[j] >= users[i][0]) {
                        money += sales[j];
                    }
                }
                if (money >= users[i][1]) {  // 이모티콘을 구매하지 않고 플러스 가입
                    plusCnt++;
                } else {  // 이모티콘 그냥 구매
                    salesResult += money;
                }
            }
            if (plusCnt > ans[0] || (plusCnt==ans[0] && salesResult > ans[1])) {
                ans[0] = plusCnt;
                ans[1] = salesResult;
            }
            return;
        }
        // 현재 idx 이모티콘의 할인율에 대해 가능한 모든 케이스 실행
        for (int i=0; i<4; i++) {
            sales[idx] = emoticons[idx] - ((emoticons[idx] * RATES[i]) / 100);
            saleRates[idx] = RATES[i];
            f(idx+1);
        }
    }
}
/*
플러스 가입자 수 최대가 되도록
이모티콘 할인률을 조정

플러스 가입자 수가 같은 경우, 플러스 미가입자의 구매 금액 최대화

사용자는 할인율이 일정 이상인 상품만 구매함
이 할인율이 낮은 사용자는 많이 구매하고, 할인율이 높은 사용자는 덜 구매할것임

*/
