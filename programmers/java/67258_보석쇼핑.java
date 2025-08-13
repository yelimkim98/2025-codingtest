import java.util.*;

class Solution {
    
    public int[] solution(String[] gems) {
        Set<String> allProducts = new HashSet<>();
        Set<String> set = new HashSet<>();
        Map<String, Integer> map = new HashMap<>();
        int start=0;
        int end=0;
        boolean recorded=false;
        int resultStart=0;
        int resultEnd=0;
        
        for (String gem : gems) {
            allProducts.add(gem);
        }
        set.add(gems[0]);
        map.put(gems[0], 1);
        
        while (start <= end) {
            if (set.size() == allProducts.size()) { // 모든 보석이 선택된 경우
                if (map.get(gems[start]) == 1) {
                    // start가 빠지면 모든 보석이 포함되지 않게 되는 경우
                    // start ~ end 기록
                    // start+1 ~ end+1부터 next 시작
                    set.remove(gems[start]);
                    map.remove(gems[start]);
                    start++;
                    
                    if (!recorded || end-(start-1) < resultEnd-resultStart) {
                        recorded=true;
                        resultStart=start-1;
                        resultEnd=end;
                    }
                    end++;
                    if (end >= gems.length) {
                        break;
                    }
                    set.add(gems[end]);
                    map.put(gems[end], map.getOrDefault(gems[end], 0)+1);
                    
                } else {  // start가 빠져도 여전히 모든 보석이 포함되는 경우
                    map.put(gems[start], map.get(gems[start])-1);
                    start++;
                }
                
            } else {  // 모든 보석이 선택되지는 않은 경우
                end++;
                if (end >= gems.length) {
                    break;
                }
                set.add(gems[end]);
                map.put(gems[end], map.getOrDefault(gems[end], 0)+1);
            }
        }
        int[] answer = {resultStart+1, resultEnd+1};
        return answer;
    }
}
/*
 1. 보석 종류 뭐뭐있는지 수집 -> n (최대 100,000)
 2. 부분집합 확인 -> 50000*99000=4,950,000,000
    40억회면 완탐 가능하려나?
    - 근데 이게 단순 완탐은 내용물 넣는 연산을 해야돼서 안됨. 넣었다 뺐다 하는 방식으로 최적화
*/
