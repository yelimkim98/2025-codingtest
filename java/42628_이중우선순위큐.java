import java.util.*;

class Solution {
    
    public int[] solution(String[] operations) {
        LinkedList<Integer> l = new LinkedList<>();
        
        for (String op : operations) {
            if ("D 1".equals(op)) {
                if (l.isEmpty()) continue;
                int result = l.remove(l.size()-1);
                // System.out.printf("remove %d\n", result);
            }
            if ("D -1".equals(op)) {
                if (l.isEmpty()) continue;
                int result = l.remove(0);
                // System.out.printf("remove %d\n", result);
            }
            String[] split = op.split(" ");
            if ("I".equals(split[0])) {
                insert(l, Integer.parseInt(split[1]));
                // System.out.printf("insert %s\n", split[1]);
            }
            // System.out.println(l);
        }
        if (l.isEmpty()) {
            int[] answer = {0, 0};
            return answer;
        }
        int[] answer = new int[2];
        answer[0] = l.get(l.size()-1);  // 최댓값
        answer[1] = l.get(0);           // 최솟값
        return answer;
    }
    
    
    
    void insert(LinkedList<Integer> l, int n) {
        int startIdx = 0;
        int endIdx = l.size() - 1;
        
        while (startIdx < endIdx && startIdx >= 0 && endIdx <= l.size()) {
            int middleIdx = startIdx + (endIdx - startIdx + 1)/2;
            int middle = l.get(middleIdx);
            if (middle == n) {
                l.add(middleIdx, n);
                return;
            }
            if (middle < n) {
                startIdx = middleIdx + 1;
                continue;
            }
            if (middle > n) {
                endIdx = middleIdx - 1;
            }
        }
        if (startIdx == -1 || endIdx == -1) {
            l.add(0, n);
            return;
        }
        if (endIdx >= l.size() || startIdx >= l.size()) {
            l.add(n);
            return;
        }
        if (l.get(endIdx) < n) {
            l.add(endIdx + 1, n);
            return;
        }
        l.add(endIdx, n);
        
    }
}
