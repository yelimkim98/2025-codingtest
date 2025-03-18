import java.util.*;

class Solution {
    public int solution(int[][] sizes) {
        List<Namecard> ns = new ArrayList<>();
        for (int i=0; i<sizes.length; i++) {
            ns.add(new Namecard(sizes[i][0], sizes[i][1]));
        }
        Namecard result = ns.stream()
            .reduce((n1, n2) -> Namecard.spacius(n1, n2))
            .orElseThrow(() -> new IllegalStateException());
                
        return result.ln * result.shrt;
    }
}

class Namecard {
    int ln, shrt;
    
    public Namecard(int w, int h) {
        if (w >= h) {
            ln = w; shrt = h;
        }
        else {
            ln = h; shrt = w;
        }
    }
    
    public static Namecard spacius(Namecard n1, Namecard n2) {
        return new Namecard(Math.max(n1.ln, n2.ln), Math.max(n1.shrt, n2.shrt));
    }
}
