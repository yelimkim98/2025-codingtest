import java.util.*;

class CandidateKey {
    List<Integer> l;
    public CandidateKey(List<Integer> l) {
        this.l = new ArrayList<>(l);
    }
    public boolean poham(CandidateKey k) {
        Set<Integer> thisS = new HashSet<>(l);
        Set<Integer> kS = new HashSet<>(k.l);
        return thisS.containsAll(kS);
    }
    @Override
    public String toString() {
        return l.toString();
    }
}

class Solution {
    
    String[][] relation;
    boolean[] used;
    List<CandidateKey> keys = new ArrayList<>();
    
    public int solution(String[][] r) {
        this.relation = r;
        used = new boolean[r[0].length];
        
        // 1. 1개 컬럼으로 유일하게 식별되는 컬럼 -> 최대 8개
        // 2. 1에서 걸러지지 않은 컬럼 중 2개 컬럼 조합으로 유일하게 식별되는 컬럼 -> 최대 28개
        // 3. 1, 2에서 걸러지지 않은 컬럼 중 3개 컬럼 조합으로 유일하게 식별되는 컬럼 -> 최대 56개
        // ..
        // 8. 최대 1개
        
        for (int colCnt=1; colCnt<=r[0].length; colCnt++) {
            // colCnt 갯수로 유일하게 식별되는 컬럼 찾기
            f(colCnt, new ArrayList<>());
        }
        // System.out.println(keys);
        
        return keys.size();
    }
    void f(int size, List<Integer> cols) {
        if (cols.size() == size) {
            // System.out.println("size " + size + " cols " + cols);
            
            CandidateKey ck = new CandidateKey(cols);
            for (CandidateKey key : keys) {
                if (ck.poham(key)) {
                    return;
                }
            }
            
            // 유니크하면 keys에 추가
            Set<String> rowStrs = new HashSet<>();
            for (int i=0; i<relation.length; i++) {
                String[] row = relation[i];
                
                StringBuilder rowStr = new StringBuilder();
                
                for (int col : cols) {
                    rowStr.append(row[col] + "_");
                }
                // System.out.println(rowStrs + " contains " + rowStr.toString() + "?");
                if (rowStrs.contains(rowStr.toString())) {    // 유니크하지 않다
                    return;
                }
                rowStrs.add(rowStr.toString());
            }
            // 유니크하다
            keys.add(ck);
            return;
        }
        for (int col = cols.isEmpty()?0:cols.get(cols.size()-1)+1; col<used.length; col++) {
            if (used[col]) {
                continue;
            }
            cols.add(col);  // 추가
            f(size, cols);
            cols.remove(cols.size()-1);  // 추가했던거 삭제
        }
    }
}
