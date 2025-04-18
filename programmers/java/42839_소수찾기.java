import java.util.*;

class Solution {
    
    Set<Integer> allNumbers = new HashSet<>();
    
    public int solution(String numbers) {
        makeNum("", numbers);
        
        int answer = 0;
        
        for (int num : allNumbers) {
            if (isPrime(num)) answer++;
        }
        return answer;
    }
    
    boolean isPrime(int num) {
        if (num < 2) return false;
        
        int squareRoot = (int) Math.sqrt(num);
        
        for (int i=2; i<=squareRoot; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
    
    /*
     others : prefix 에 사용되지 않은 종이조각 목록
     */
    void makeNum(String prefix, String others) {
        if (!"".equals(prefix)) {
            allNumbers.add(Integer.parseInt(prefix));
        }
        for (int i=0; i<others.length(); i++) {
            String nextPrefix = prefix + others.charAt(i);
            String nextOthers = others.substring(0, i) + others.substring(i+1);
            makeNum(nextPrefix, nextOthers);
        }
    }
}
