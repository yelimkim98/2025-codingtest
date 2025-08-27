class Solution {
    public int[] solution(int n, int s) {
        if (n > s) {
            int[] ans = {-1};
            return ans;
        }
        
        int mid = s/n;
        // mid * n <= s < (mid+1) * n
        // n개의 mid의 합이 s가 될 때 까지 하나씩 (mid+1) 로 수정해본다.
        
        int[] answer = new int[n];
        int total = mid * n;
        
        for (int i=0; i<n; i++) {
            answer[i] = mid;
        }
        
        for (int i=n-1; i>=0 && total<s; i--) {
            // System.out.println("total " + total);
            answer[i] += 1;
            total++;
        }
        
        return answer;
    }
}
