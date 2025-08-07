import java.util.*;
class Solution {
    
    Deque<Integer> stack = new ArrayDeque<>();
    
    public int[] solution(int[] numbers) {
        // 스택 peek -> 나보다 큰수가 아니면 pop (remove)
        //             나보다 크면 pop 안하고 해당 값 기록, 나자신도 삽입
        //             비어버리면 -1, 나자신 삽입
        int[] ans = new int[numbers.length];
        
        for (int i=numbers.length-1; i>=0; i--) {
            ans[i] = f(numbers[i]);
        }
        return ans;
    }
    int f(int num) {
        while (!stack.isEmpty()) {
            int stackPeek = stack.peek();
            
            if (stackPeek > num) {
                stack.push(num);
                return stackPeek;
            }
            stack.pop();
        }
        stack.push(num);
        return -1;
    }
}
