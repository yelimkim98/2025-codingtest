class Solution {
    public String solution(String number, int k) {        
        StringBuilder ans = new StringBuilder();
        
        int maxIdx = -1;
        
        for (int i=number.length()-k-1; i>=0; i--) {
            int maxValue = -1;
            int startIdx = maxIdx+1;
            int endAfterIdx = number.length()-i;
            
            for (int j=startIdx; j<endAfterIdx; j++) {
                int jValue = number.charAt(j) - '0';
                if (jValue > maxValue) {
                    maxIdx = j;
                    maxValue = jValue;
                }
            }
            ans.append(number.charAt(maxIdx));
        }
        return ans.toString();
    }
}
/*
4177252841, 4  10자리중 6자리 남길것임
1. 4177252841 오른쪽에서 5( (number.length()-k)-1 )자리를 제외한 숫자 중 가장 큰숫자 남기고 그 왼쪽 버리기 
-> 7 7252841  idx 0-4 -> maxIdx 2
2. 7252841 오른쪽에서 4 ( (number.length()-k)-1 -1 )자리를 제외한 숫자 중 가장 큰숫자 남기고 그 왼쪽 버리기
-> 77 252841  idx 3-5 -> maxIdx 3
3. 252841의 오른쪽에서 3 ( (number.length()-k)-1 -1 )자리를 제외한 숫자 중 가장 큰숫자 남기고 그 왼쪽 버리기
-> 775 2841   idx 4-6 -> maxIdx 5
4. 2841의 오른쪽에서 2 ( (number.length()-k)-1 -1 )자리를 제외한 숫자 중 가장 큰숫자 남기고 그 왼쪽 버리기
-> 7758 41    idx 6-
5. 41의 오른쪽에서 1 ( (number.length()-k)-1 -1 )자리를 제외한 숫자 중 가장 큰숫자 남기고 그 왼쪽 버리기
-> 77584 1
6. 1의 오른쪽에서 0 ( (number.length()-k)-1 -1 )자리를 제외한 숫자 중 가장 큰숫자 남기고 그 왼쪽 버리기
-> 775841
*/
