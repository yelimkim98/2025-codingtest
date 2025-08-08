class Solution {
    public int solution(String s) {
        int ans = s.length();
        
        for (int i=1; i<=s.length()/2; i++) {  // i개로 split
            
            // 01 23   ab ab bc    ab ab ab bc   ab bc bc
            // 01 23 4
            // 012 345 678
            // 012 345 67
            // 반복이 안되면? 바로 붙이기
            // 반복이 되면? 그 다음꺼도 반복 되는지 보기
            int frontStartIdx = 0;
            int backStartIdx = frontStartIdx+i;
            String frontStr = s.substring(frontStartIdx, backStartIdx);
            int cnt = 1;
            StringBuilder sb = new StringBuilder();
            // System.out.println(i+" 비교");
            while (backStartIdx+i <= s.length()) {
                String backStr = s.substring(backStartIdx, backStartIdx+i);
                // System.out.printf("front(%d) %s back(%d) %s\n", frontStartIdx, frontStr, backStartIdx, backStr);
                
                if (!frontStr.equals(backStr)) {  // 반복 안됨
                    if (cnt == 1) {
                        sb.append(frontStr);
                    } else {
                        sb.append(cnt + frontStr);
                    }
                    frontStartIdx = backStartIdx;
                    backStartIdx += i;
                    frontStr = s.substring(frontStartIdx, backStartIdx);
                    cnt = 1;
                    
                } else {  // 반복 됨
                    cnt++;
                    backStartIdx += i;
                }
                
            }
            // System.out.printf("front(%d)%s back(%d), cnt(%d)\n", frontStartIdx, frontStr, backStartIdx, cnt);
            // 남은거 붙이기
            
            // ab ab ab cd -> front=c, back=초과, cnt=1
            // ab ab cd cd -> front=c(4), back=초과, cnt=2
            
            if (cnt == 1) {
                sb.append(frontStr);
            } else {
                sb.append(cnt + frontStr);
            }
            if (backStartIdx < s.length()) {
                sb.append(s.substring(backStartIdx, s.length()));
            }
            // System.out.println(sb.toString());
            if (sb.length() < ans) {
                ans = sb.length();
            }
        }
        return ans;
    }
}
/*
1, 2, 3, 4, .. s/2 (최대500개) 개까지 다해보면?
500가지 경우의수
한번당 500, 500/2, 500/3, ... 2
(500 + 499) / 2
ababcbc
2abcbc
aba2bc
*/
