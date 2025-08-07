import java.util.*;

class Solution {
    int[] elements = {};
    
    public int solution(int[] elements) {
        this.elements = elements;
        Set<Integer> set = new HashSet<>();
        
        // System.out.println(f(3, 3));
        for (int len=1; len<elements.length; len++) {
            for (int startIdx=0; startIdx<elements.length; startIdx++) {
                set.add(f(startIdx, len));
            }
        }
        return set.size()+1;
    }
    
    int f(int startIdx, int length) {
        int cnt = 0;
        int sum = 0;
        
        int idx = startIdx;
        
        while (cnt < length) {
            sum += elements[idx];
            idx = (idx+1)%elements.length;
            cnt++;
        }
        return sum;
    }
}
