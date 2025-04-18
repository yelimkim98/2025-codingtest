import java.util.*;

class Solution {
    public int solution(int N, int number) {
        if (N==number) return 1;
        
        // key : N 사용 횟수, value : 모든 숫자
        Map<Integer, Set<Integer>> dp = new HashMap<>();
        
        Set<Integer> start = new HashSet<>();
        start.add(N);
        dp.put(1, start);
        
        int usedCount = 1;
        
        while (usedCount < 8) {
            usedCount++;
            Set<Integer> nextNums = new HashSet<>();
            
            int NNNN = 0;
            for (int i=0; i<usedCount; i++) {
                NNNN *= 10;
                NNNN += N;
            }
            if (number == NNNN) {
                return usedCount;
            }
            nextNums.add(NNNN);
            
            for (int i=1; i<usedCount; i++) {
                int j=usedCount-i;
                
                Set<Integer> targetSet1 = dp.get(i);
                Set<Integer>targetSet2 = dp.get(j);
                
                Set<Integer> temp = new HashSet<>();
                
                for (int t1 : targetSet1) {
                    for (int t2 : targetSet2) {
                        temp.add(t1 + t2);
                        temp.add(t1 - t2);
                        temp.add(t1 * t2);
                        if (t2 != 0) temp.add(t1 / t2);
                        
                        temp.add(t2 - t1);
                        if (t1 != 0) temp.add(t2 / t1);
                    }
                }
                for (int t : temp) {
                    if (number == t) return usedCount;
                }
                nextNums.addAll(temp);
            }
            dp.put(usedCount, nextNums);
        }
        return -1;
    }
}
/*
연산
- 나란히 잇기
- 사칙연산
괄호 존재 가능

1. 5 1개로 만들 수 있는 모든 숫자 만들기
  -> 5
2. 5 2개로 만들 수 있는 모든 숫자 만들기
  -> 55, 5+5=10, 5-5=0, 5*5=25, 5/5=1
3. 5 3개로 만들 수 있는 모든 숫자 만들기
  -> 555
  
  이전리스트가 앞에
  덧셈      뺄셈      곱셈      나눗셈
  55+5,    55-5,    55*5,    55/5,
  (5+5)+5, (5+5)-5, (5+5)*5, (5+5)/5,
  (5-5)+5, (5-5)-5, (5-5)*5, (5-5)/5,
  (5*5)+5, (5*5)-5, 
  (5/5)+5, (5/5)-5,
  
  이전리스트가 뒤에
  덧셈      뺄셈      곱셈      나눗셈
  5+55,    5-55,    5*55,    5/55,
  5+(5+5), 5-(5+5), 5*(5+5), 5/(5+5),
  5+(5-5), 5-(5-5), 5*(5-5), 5/(5-5),
  ...

4. 5 4개로 만들 수 있는 모든 숫자 만들기
   (55+5)+5, (55+5)-5, ...
   55+55, 55+(5+5), ...
 */
