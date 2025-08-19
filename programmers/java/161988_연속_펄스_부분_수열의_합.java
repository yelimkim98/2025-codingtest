class Solution {
    
    int[] sequence1;
    int[] sequence2;
    
    public long solution(int[] sequence) {
        this.sequence1=new int[sequence.length];
        this.sequence2=new int[sequence.length];
        
        for (int i=0; i<sequence.length; i++) {
            if (i % 2 == 0) {
                sequence1[i] = sequence[i] * -1;
                sequence2[i] = sequence[i];
            } else {
                sequence1[i] = sequence[i];
                sequence2[i] = sequence[i] * -1;
            }
        }
        long tmp1=sequence1[0];
        long tmp2=sequence2[0];
        long maxValue = Math.max(tmp1, tmp2);
        
        for (int i=1; i<sequence.length; i++) {
            // maxValue : (?, i-1) 까지의 부분수열 중 합이 가장 큰 경우
            // tmp1 : sequence1에 대해 i-1번까지 합한 부분수열 중 가장 큰 합
            // tmp2 : sequence2에 대해 i-1번까지 합한 부분수열 중 가장 큰 합
            tmp1 = Math.max(tmp1+sequence1[i], sequence1[i]);
            tmp2 = Math.max(tmp2+sequence2[i], sequence2[i]);
            maxValue = Math.max(maxValue, Math.max(tmp1, tmp2));
        }
        return maxValue;
    }
}
