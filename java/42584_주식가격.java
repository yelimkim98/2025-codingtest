import java.util.*;

class Record implements Comparable<Record> {
    public int idx;
    public int price;
    public Record(int idx, int price) {this.idx = idx; this.price = price;}
    
    @Override
    public int compareTo(Record r) {
        return Integer.compare(this.idx, r.idx);
    }
}

class Solution {
    
    public int[] solution(int[] prices) {
        Deque<Record> stack = new ArrayDeque<>();
        stack.push(new Record(0, prices[0]));
        
        int[] ans = new int[prices.length];
        
        for (int i=1; i<prices.length; i++) {
            Record current = new Record(i, prices[i]);
            
            while (!stack.isEmpty() && stack.peek().price > current.price) {
                Record pop = stack.pop();
                ans[pop.idx] = current.idx - pop.idx;
            }
            stack.push(current);
        }
        while (!stack.isEmpty()) {
            Record pop = stack.pop();
            ans[pop.idx] = prices.length - 1 - pop.idx;
        }
        return ans;
    }
}
