class Solution {
    
    public long solution(int n, int[] times) {
        /*
        n분동안 몇명을 심사할 수 있는가?
        n이 클수록 처리할 수 있는 사람 수는 많아지거나 그대로이다.
        */
        // System.out.println(count(28, times))
        // 제일 금방 끝내는 사람이 모든걸 다 처리하는 경우보다 분업이 빠르다면 분업한다.
        // 즉, 최솟값은 [제일 금방 끝내는 사람이 모든걸 다 처리하는 경우 걸리는 시간] 이하이다.
        long start = 1;
        long end = (long)times[0];
        for (int i=0; i<times.length; i++) {
            if (times[i] < end) {
                end = times[i];
            }
        }
        end *= n;
        
        while (start != end) {
            long mid = (start+end)/2;
            if (count(mid, times) < (long)n) {
                start = mid+1;
            } else {
                end = mid;
            }
        }
        // System.out.println(count(28, times));
        return start;
    }
    
    /*
     @Return limitMinute 동안 심사 완료된 사람 수
     */
    public long count(long limitMinute, int[] times) {
        long result = 0;
        for (int i=0; i<times.length; i++) {
            result += limitMinute/times[i];
        }
        return result;
    }
}
