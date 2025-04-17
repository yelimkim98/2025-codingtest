import java.util.*;

class Solution {
    
    /*
    banned id set으로 가능한 모든 경우
    */
    Set<Set<String>> sss = new HashSet<>();
    
    public int solution(String[] userIds, String[] bannedIds) {
        recursive(0, new HashSet<>(), bannedIds, userIds);
        // System.out.println(sss);
        return sss.size();
    }
    void recursive(int bannedIdsIdx, Set<String> currentSet, String[] bannedIds, String[] userIds) {
        if (bannedIdsIdx >= bannedIds.length) {
            sss.add(currentSet);
            return;
        }
        for (String userId : userIds) {
            // userId가 아직 set에 없고, 패턴상 맞는다면, 해당 userId를 추가하고 다음 패턴 이후꺼로 set을 만들도록 요청
            if (!currentSet.contains(userId) && match(userId, bannedIds[bannedIdsIdx])) {
                Set<String> nextSet = new HashSet<>(currentSet);
                nextSet.add(userId);
                recursive(bannedIdsIdx+1, nextSet, bannedIds, userIds);
            }
        }
    }
    boolean match(String userId, String bannedIdPattern) {
        if (userId.length() != bannedIdPattern.length()) return false;
        for (int i=0; i<userId.length(); i++) {
            char bannedC = bannedIdPattern.charAt(i);
            char targetC = userId.charAt(i);
            if (bannedC != '*' && bannedC != targetC) {
                return false;
            }
        }
        return true;
    }
}

/*
["frodo", "fradi", "crodo", "abc123", "frodoc"]
*/
