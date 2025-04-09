import java.util.*;

class Footprint {
    int a;  // 도둑 a의 누적
    int b;  // 도둑 b의 누적
    public Footprint(int a, int b) {
        this.a = a;
        this.b = b;
    }
    @Override
    public String toString() {
        return String.format("(a누적 %d, b누적 %d)", a, b);
    }
    
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Footprint)) return false;
        Footprint f = (Footprint) o;
        return this.a == f.a && this.b == f.b;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }
}

class Solution {
    
    public int solution(int[][] info, int n, int m) {
        Set<Footprint> dp = new HashSet<>();
        dp.add(new Footprint(0, 0));
        
        for (int i=0; i<info.length; i++) {
            if (dp.isEmpty()) {
                return -1;
            }
            Set<Footprint> dpNext = new HashSet<>();
            
            for (Footprint f : dp) {        
                // A가 i를 터는 경우
                int newA = f.a + info[i][0];
                if (newA < n) {
                    dpNext.add(new Footprint(newA, f.b));
                }
                // B가 i를 터는 경우
                int newB = f.b + info[i][1];
                if (newB < m) {
                    dpNext.add(new Footprint(f.a, newB));
                }
            }
            dp = dpNext;
            // System.out.printf("물건 %d " + dp + "\n", i);
        }
        if (dp.isEmpty()) return -1;
        
        int min = n;
        for (Footprint f : dp) {
            if (f.a < min) {
                min = f.a;
            }
        }
        return min;
    }
}
/*
1 <= n, m <= 120
물건번호  도둑A남길흔적  도둑B남길흔적   (1 <= 흔적 <= 3)
    0       1           2
    1       2           3
    2       2           1
    3       1           3

도둑들이 물건1을 훔쳤을 때 n-(물건갯수-1) 초과 안하고 m-(물건갯수-1) 초과 안하는 cases
도둑들이 물건2를 훔쳤을 때 n-(물건갯수-2) 초과 안하고 m-(물건갯수-2) 초과 안하는 cases

*/
