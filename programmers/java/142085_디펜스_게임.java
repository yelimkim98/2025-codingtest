import java.util.*;

class Solution {
    
    int skillCnt;    // 남은 무적권 갯수
    int junhoCnt;    // 남은 병사 수
    PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
    
    public int solution(int n, int k, int[] enemy) {
        this.skillCnt = k;
        this.junhoCnt = n;
        
        // 앞에서부터 적의 수 합산 & 적이 가장 많은 판 적의 수 갱신
        // 그러다가 적의 수 합산이 junhoCnt를 넘으면 무적권 사용 
        //      (적의 수) -= (적이 가장 많은 판 적의 수)
        //      (무적권갯수)--
        // 무적권 사용을 위해, (적이 가장 많은 판 적의 수) 상위 k개를 관리해야함
        
        for (int i=0; i<enemy.length; i++) {
            // [i+1]라운드에서 처리해야하는 적의 수 : enemy[i]
            if (junhoCnt >= enemy[i]) {  // 현재 라운드 적을 처리할 수 있음
                pq.offer(enemy[i]);
                junhoCnt -= enemy[i];
                
            } else if (skillCnt > 0) {  // 현재 라운드 적을 처리하기에 병사가 부족한데, 무적권이 남아있음
                // 현재판까지 중 가장 적이 많았던 판에서 무적권을 쓴 것으로 처리
                pq.offer(enemy[i]);
                junhoCnt -= enemy[i];  // 보유 병사 수가 일시적으로 음수가 된다.
                skillCnt--;
                junhoCnt += pq.poll();
                
            } else {  // 이번 판(i+1 판)은 이길 수 없음 - 전판까지 방어 가능
                return i;
            }
        }
        return enemy.length;
    }
}
/*
적이 최대한 많은 판에서 무적권을 사용하는게 좋지만, 제일 적이 많을 때를 계속 기다리다가는 그 전에 병사 수가 다 떨어지면 손해다.

1. 무적권 없이 몇번째 판까지 할 수 있는지?
    2
2. 이 중 최대값에 무적권 사용하면 어디에 사용하고, 몇판까지 할 수 있는지?
    1판에 사용, 3판까지 할수있음
3. 무적권이 아직 사용되지 않은 판에 무적권 사용하면 어디에 사용하고, 몇판까지 할 수 있는지?
    3판에 사용, 4판까지 할수있음
4, 
*/
