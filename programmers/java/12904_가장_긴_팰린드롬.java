import java.util.*;

class P implements Comparable<P> {
    int startIdx, endIdx;
    public P(int s, int e) {
        startIdx=s;
        endIdx=e;
    }
    public void expand() {
        startIdx--;
        endIdx++;
    }
    public int len() {
        return endIdx-startIdx+1;
    }
    @Override 
    public int compareTo(P p) {
        return Integer.compare(p.len(), this.len());  // 내림차순
    }
    @Override
    public int hashCode() {
        return Objects.hash(startIdx, endIdx);
    }
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof P)) {
            return false;
        }
        P op = (P) o;
        return this.startIdx == op.startIdx && this.endIdx == op.endIdx;
    }
    @Override
    public String toString() {
        return String.format("start%d end%d", startIdx, endIdx);
    }
}
class Solution {
    String s;
    public int solution(String s) {
        this.s = s;
        
        List<P> p2s = new ArrayList<>();  // 길이 2 미완성 팰린드롬 수집
        List<P> p3s = new ArrayList<>();  // 길이 3 미완성 팰린드롬 수집
        P maxP = null;  // 완성 팰린드롬 중 가장 긴 팰린드롬
        
        // 길이 2
        for (int i=0; i+1<s.length(); i++) {
            String subS = s.substring(i, i+2);
            if (subS.charAt(0) == subS.charAt(1)) {
                P p = new P(i, i+1);
                if (canExpand(p)) {
                    p2s.add(p);
                } else {
                    maxP = p;
                }
            }
        }
        
        // 길이 3
        for (int i=0; i+2<s.length(); i++) {
            String subS = s.substring(i, i+3);
            
            if (subS.charAt(0) == subS.charAt(2)) {
                P p = new P(i, i+2);
                if (canExpand(p)) {
                    p3s.add(p);
                } else {
                    maxP = p;
                }
            }
        }
        
        while (!p2s.isEmpty()) {
            List<P> p2Filter = new ArrayList<>();
            
            for (P p2 : p2s) {
                if (canExpand(p2)) {
                    p2.expand();

                    if (canExpand(p2)) {
                        p2Filter.add(p2);
                    } else if (maxP==null || maxP.len() < p2.len()) {
                        maxP = p2;
                    }
                }
            }
            p2s = p2Filter;
        }
        
        while (!p3s.isEmpty()) {
            List<P> p3Filter = new ArrayList<>();
            
            for (P p3 : p3s) {
                if (canExpand(p3)) {
                    p3.expand();

                    if (canExpand(p3)) {
                        p3Filter.add(p3);
                    } else if (maxP==null || maxP.len() < p3.len()) {
                        maxP = p3;
                    }
                }
            }
            p3s = p3Filter;
        }
        return (maxP == null) ? 1 : (maxP.len());
    }
    
    public boolean canExpand(P p) {
        return p.startIdx > 0 && p.endIdx+1 < s.length()
            && s.charAt(p.startIdx-1) == s.charAt(p.endIdx+1);
    }
}
