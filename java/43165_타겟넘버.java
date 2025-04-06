class Solution {
    public int solution(int[] numbers, int target) {
        return f(0, 0, numbers, target);
    }
    
    int f(int currentIdx, int beforeResult, int[] numbers, int target) {
        if (currentIdx == numbers.length) {
            if (beforeResult == target) {
                return 1;
            }
            return 0;
        }
        int result1 = f(currentIdx+1, beforeResult+numbers[currentIdx], numbers, target);
        int result2 = f(currentIdx+1, beforeResult-numbers[currentIdx], numbers, target);
        return result1+result2;
    }
}
