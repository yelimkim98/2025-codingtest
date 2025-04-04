import java.util.*;
import java.util.stream.*;

class Solution {
    
    static int LOST = -1;
    static int RESERVE = 1;
    
    public int solution(int n, int[] lost, int[] reserve) {
        int[] students = new int[n+2];
        
        for (int l : lost) {
            students[l] = LOST;
        }
        List<Integer> reserveList = new ArrayList<>();    // 체육복 두 벌 있는 학생 목록
        int lostLength = lost.length;
        
        for (int r : reserve) {
            students[r] += RESERVE;
            if (students[r] == RESERVE) {
                reserveList.add(r);
            } else {    // 여벌 있는데 잃어버린 케이스
                lostLength--;
            }
        }
        System.out.println(Arrays.stream(students)
                           .mapToObj(x -> String.valueOf(x))
                           .collect(Collectors.joining(", ")));
        System.out.println(reserveList);
        
        // 한명한테만 빌려줄 수 있는 학생은 그냥 빌려준다.
        // 이 동작을 [더이상 동작하지 않을때까지] 한다.
        int answer = n - lostLength;
        HelpResult helpResult;
        do {
            helpResult = helpIfTargetClear(students, reserveList);
            answer += helpResult.successCnt;
        } while (helpResult.successCnt > 0);
        
        // 나머지는 아무한테나 빌려준다.
        for (int r : helpResult.reserve) {
            if (students[r-1] == -1) {
                students[r-1] = 0;
                students[r] = 0;
                answer++;
                continue;
            }
            if (students[r+1] == -1) {
                students[r+1] = 0;
                students[r] = 0;
                answer++;
            }
        }
        return answer;
    }
    
    // return 아직 빌려줄게 남아있는 학생 목록
    HelpResult helpIfTargetClear(int[] students, List<Integer> reserve) {
        List<Integer> newReserve = new ArrayList<>();
        int successCnt = 0;
        
        for (int i : reserve) {
            List<Integer> targets = new ArrayList<>();
            if (students[i-1] == -1) targets.add(i-1);
            if (students[i+1] == -1) targets.add(i+1);
            if (targets.size() == 1) {
                // 빌려줄수있는 친구가 1명이면 빌려준다.
                students[targets.get(0)] = 0;
                students[i] = 0;
                successCnt++;
            } else {
                // 빌려줄수있는 친구가 2명이면 빌려주지 않는다.
                newReserve.add(i);
            }
        }
        return new HelpResult(newReserve, successCnt);
    }
    
    static class HelpResult {
        List<Integer> reserve;
        int successCnt;    // 체육복 빌린 학생 수
        
        public HelpResult(List<Integer> reserve, int successCnt) {
            this.reserve = reserve;
            this.successCnt = successCnt;
        }
    }
}
/*
1 2 3 4 5 6
x   x   x
  o   o   o
   
1 2 3 4 5 6
  x   x   x
o   o   o

1 2 3 4 5 6 7 8 9 A B
  x   x   x   x   x
o   o   o       o

1 2 3 4 5 6
  x     x
      o

1 2 3 4 5 6
x x     x
    o o   o
 */
