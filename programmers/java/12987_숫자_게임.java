import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);
        
        int aIdx=0;
        int bIdx=0;
        
        int ans=0;
        
        while (aIdx < A.length && bIdx < B.length) {
            if (A[aIdx] < B[bIdx]) {
                ans++;
                aIdx++;
                bIdx++;
                
            } else {
                bIdx++;
            }
        }
        return ans;
    }
}
/*
최대한 상대보다 숫자가 큰 경우가 많으면 승리
A[i]보다 큰게 있다면 그중 제일 작은거 가져다대면 이기지않을까?

A 3 4 5 6
B 4 1 2 3

*/
