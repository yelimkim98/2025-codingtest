class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];
        answer[0] = 0;
        answer[1] = 0;
        if (sequence[0] == k) return answer;
        
        long sum = sequence[0];
        
        int startIdx = 0;
        int endIdx = 0;
        
        while (true) {
            // System.out.printf("startIdx %d endIdx %d sum %d\n", startIdx, endIdx, sum);
            
            if (sum > k) {
                if (startIdx == endIdx) break;
                sum -= sequence[startIdx];
                startIdx++;
                continue;
            }
            if (sum < k) {
                if (endIdx == sequence.length-1) break;
                endIdx++;
                sum += sequence[endIdx];
                continue;
            }
            // sum == k
            if ((answer[0]==0 && answer[1]==0)
               || (endIdx-startIdx < answer[1]-answer[0])
               || (endIdx-startIdx == answer[1]-answer[0] && startIdx < answer[0])) {
                answer[0] = startIdx;
                answer[1] = endIdx;
            }
            if (endIdx == sequence.length-1) break;
            sum -= sequence[startIdx];
            startIdx++;
        }
        return answer;
    }
}
