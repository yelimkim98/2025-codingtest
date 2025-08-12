class Solution {
    public int[] solution(int[] sequence, int k) {
        boolean found=false;
        int resultStart=0;
        int resultEnd=0;
        
        int start=0;
        int end=0;
        int val = sequence[start];
        
        while (start<=end && end<sequence.length) {
            if (val < k) {
                end++;
                if (end >= sequence.length) {
                    break;
                }
                val += sequence[end];
                
            } else if (val > k) {
                // start~end+i (i=1,2,3,..)는 무조건 k보다 큼
                // -> start 시작 케이스 탐색 완료
                // start+1 ~ end-1까지는 무조건 k보다 작음 (start~end-1이 k보다 작았으니까)
                // -> start+1 ~ end 부터 탐색 시작
                val -= sequence[start];
                start++;
                
            } else { // val == k
                if (!found) {
                    resultStart=start;
                    resultEnd=end;
                    found=true;
                } else {
                    // 비교해서 resultStart, resultEnd update
                    if (end-start < resultEnd-resultStart) {
                        resultStart=start;
                        resultEnd=end;
                    }
                }
                val -= sequence[start];
                start++;
                
                end++;
                if (end >= sequence.length) {
                    break;
                }
                val += sequence[end];
            }
        }
        int[] answer = {resultStart, resultEnd};
        return answer;
    }
}
