import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        // 끝점에 대해 정렬
        List<Attack> attacks = new ArrayList<>();
        for (int[] target : targets) {
            attacks.add(new Attack(target[0], target[1]));
        }
        Collections.sort(attacks);
        
        int nextIdx = 0;
        int beforeEnd = 0;
        int cnt = 0;
        while (nextIdx<attacks.size()) {
            Attack a = attacks.get(nextIdx);
            if (a.s < beforeEnd) {
                // 추가요격 없이 요격 완료처리
            } else {
                // 추가요격
                cnt++;
                beforeEnd = a.e;
            }
            nextIdx++;
        }
        return cnt;
    }
    
}
class Attack implements Comparable<Attack>{
    int s, e;
    public Attack(int start, int end) {
        s=start; e=end;
    }
    @Override
    public int compareTo(Attack a) {
        return Integer.compare(this.e, a.e);
    }
}
