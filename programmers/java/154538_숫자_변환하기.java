import java.util.*;

class Solution {
    
    public int solution(int x, int y, int n) {
        Set<Integer> set = new HashSet<>();
        set.add(x);
        int cnt = 0;
        if (x==y) return 0;
        
        do {
            cnt++;
            // System.out.println("set " + set + " cnt " + cnt);
            Set<Integer> nextSet = new HashSet<>();
            
            for (int xn : set) {
                Set<Integer> tmp = new HashSet<>();
                if (xn+n <= y) tmp.add(xn+n);
                if (2*xn <= y) tmp.add(2*xn);
                if (3*xn <= y) tmp.add(3*xn);

                for (int t : tmp) {
                    if (t == y) return cnt;
                }
                nextSet.addAll(tmp);
            }
            set = nextSet;
        } while (!set.isEmpty());
        return -1;
    }
}
