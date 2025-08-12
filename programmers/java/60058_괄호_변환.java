import java.util.*;
class Solution {
    public String solution(String p) {
        return f(p);
    }
    
    String f(String w) {
        if ("".equals(w)) return "";
        
        int openCnt=0;
        int closeCnt=0;
        if (w.charAt(0) == '(') openCnt++;
        else closeCnt++;
        
        for (int i=1; i<w.length(); i++) {
            if (w.charAt(i) == '(') openCnt++;
            else closeCnt++;
            
            if (openCnt == closeCnt) break;
        }
        String u = w.substring(0, openCnt+closeCnt);
        String v = w.substring(openCnt+closeCnt);
        
        String vAfter = f(v);
        
        if (isCorrect(u)) {
            return u+vAfter;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        sb.append(vAfter);
        sb.append(")");
        
        for (int i=1; i<u.length()-1; i++) {
            sb.append(u.charAt(i)==')'?'(':')');
        }
        return sb.toString();
    }
    boolean isCorrect(String s) {
        if (s.isEmpty())return true;
        Deque<String> stack = new ArrayDeque<>();
        stack.push(s.charAt(0)+"");
        for (int i=1; i<s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (stack.isEmpty()) return false;
                stack.pop();
            }
            else {
                stack.push("(");
            }
        }
        return stack.isEmpty();
    }
}
