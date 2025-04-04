import java.util.*;

class Solution {
    
    static char[] chars = new char[5];
    
    static {
        chars[0] = 'A';
        chars[1] = 'E';
        chars[2] = 'I';
        chars[3] = 'O';
        chars[4] = 'U';
    }
    
    Set<String> wordSet = new HashSet<>();
    
    public int solution(String word) {
        createWord("");
        List<String> l = new ArrayList<>(wordSet);
        Collections.sort(l);
        
        for (int i=0; i<l.size(); i++) {
            if (word.equals(l.get(i))) {
                return i+1;
            }
        }
        throw new IllegalStateException("?");
    }
    
    void createWord(String prefix) {
        if (!"".equals(prefix)) {
            wordSet.add(prefix);
            
            if (prefix.length() == 5) {
                return;
            }
        }
        for (char ch : chars) {
            createWord(prefix + ch);
        }
    }
}
