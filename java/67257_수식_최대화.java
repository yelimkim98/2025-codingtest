import java.util.*;

class Solution {
    List<String> l = new ArrayList<>();
    long max = 0;
    
    public long solution(String expression) {
        StringBuilder sb = new StringBuilder();
        Set<String> operators = new HashSet<>();
        List<String> operatorList = new ArrayList<>();
        
        for (int i=0; i<expression.length(); i++) {
            char c = expression.charAt(i);
            if ('+' == c || '-' == c || '*' == c) {
                String operator = String.valueOf(c);
                l.add(sb.toString());
                l.add(operator);
                sb = new StringBuilder();
                
                if (!operators.contains(operator)) {
                    operators.add(operator);
                    operatorList.add(operator);
                }
                
            } else {
                sb.append(c);
            }
        }
        if (sb.length() > 0) {
            l.add(sb.toString());
        }
        // System.out.println(operators);
        
        /* 연산자 우선순위 경우의 수 만들기 */
        
        recursive(operators, new HashMap<>(), 0);
        
        return max;
    }
    
    void recursive(Set<String> operators, Map<String, Integer> priorities, int p) {
        if (operators.isEmpty()) {
            long result = calc(l, priorities);
            // System.out.println(priorities);
            max = Math.max(max, result);
            return;
        }
        for (String op : operators) {
            // op의 priority를 p로 지정
            priorities.put(op, p);
            
            Set<String> nextOperators = new HashSet<>(operators);
            nextOperators.remove(op);
            
            recursive(nextOperators, priorities, p+1);
            
            priorities.remove(op);
        }
    }
    
    long calc(List<String> ll, Map<String, Integer> priorities) {
        List<String> l = new LinkedList<>(ll);
        
        // System.out.println(priorities);
        String[] ops = new String[priorities.size()];
        // System.out.println("op size " + op.length);
        priorities.forEach((k, v) -> {
            ops[v] = k;
            // System.out.printf("ops[%d]=%s ", v, k);
        });
        
        // 우선순위 값 작은 연산부터 수행
        for (int i=0; i<ops.length; i++) {
            // System.out.printf("ops[%d]=%s ", i, ops[i]);
            String op = ops[i];
            int idx = 1;
            while (idx < l.size() - 1) {
                if (l.get(idx).equals(op)) {
                    // System.out.println(l);
                    /* 
                    현재 idx의 앞 뒤를 계산
                    
                    현재 idx = 3
                    
                    0 1 2 3 4 5 6
                    1 + 2 * 5 + 1
                    
                    0 1 2 3 4 5
                    1 + + 1
                    
                    계산 결과는 2에 넣어야함
                    
                    0 1 2  3 4 5
                    1 + 10 + 1
                    
                    다음 idx 는 3이 되어야함
                    
                    */
                    long operand1 = Long.parseLong(l.get(idx-1));
                    long operand2 = Long.parseLong(l.get(idx+1));
                    
                    l.remove(idx+1);
                    l.remove(idx);
                    l.remove(idx-1);
                    
                    String calcResult = calc(operand1, op, operand2);
                    
                    l.add(idx-1, calcResult);
                    
                    // System.out.println(l);
                    
                } else {
                    idx++;
                }
            }
        }
        
        // System.out.printf(" %d \n", Math.abs(Long.parseLong(l.get(0))));
        return Math.abs(Long.parseLong(l.get(0)));
    }
    
    String calc(long op1, String operator, long op2) {
        long calcResult = 0;
        if ("+".equals(operator)) {
            calcResult = op1+op2;
        } else if ("-".equals(operator)) {
            calcResult = op1-op2;
        } else if ("*".equals(operator)) {
            calcResult = op1*op2;
        }
        return String.valueOf(calcResult);
    }
}
