class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int levelMin = 1;
        int levelMax = diffs[0]; // levelMax = diffs 배열에서 최댓값 (최대 10만)
        
        for (int i=1; i<diffs.length; i++) {
            levelMax = Math.max(levelMax, diffs[i]);
        }
        // level이 낮아지면 더 오래걸리고, level이 높아지면 더 적게 걸린다.
        while (levelMin < levelMax) {
            int midLevel = (levelMax+levelMin)/2;
            long midLevelTime = totalTimeForLevel(midLevel, diffs, times);
            
            // 현재 레벨에서 제한시간을 넘기지 않음 -> 숙련도를 줄일 수 있다.
            if (midLevelTime <= limit) {
                levelMax = midLevel;
            }
            // 현재 레벨에서 제한시간을 넘김 -> 숙련도를 늘려야한다.
            else {
                levelMin = midLevel+1;
            }
            /*
            levelMin=4, levelmax=5일때 mid 4
            if 제한시간 초과 안함 
            */
        }
        if (levelMin == levelMax) {
            return levelMin;
        }
        int answer = 0;
        return answer;
    }
    
    long totalTimeForLevel(int level, int[] diffs, int[] times) {
        long timeSum = 0;
        
        for (int i=0; i<diffs.length; i++) {
            int time;
            if (diffs[i] > level) {
                int wrongCnt = diffs[i]-level;
                int oneWrongTime = times[i] + (i==0 ? 0 : times[i-1]);
                time = wrongCnt * oneWrongTime + times[i];
            } else {
                time = times[i];
            }
            timeSum += time;
        }
        return timeSum;
    }
}
/*
하나도 안틀리고 다하는 숙련도 최솟값을 levelMax로 두자.
levelMax = diffs 배열에서 최댓값 : 최대 10만
level이 낮아지면 더 오래걸리고, level이 높아지면 더 적게 걸린다.

level이 정해졌을때 수행시간 계산
diffs 0~마지막 쭉 순회돌면서 전부 계산해서 sum    << diff 최대길이 30만
if diffes[i]>level; 
   i번 문제 틀린횟수 = (diffs[i]-level)
   i번 문제를 한번 틀렸을 때 손해보는 시간 = times[i] + (i==0? 0 : times[i-1])
   i번 문제를 다시푸는 시간 = times[i]
   걸린시간 = (i번 문제 틀린횟수)*(i번 문제를 한번 틀렸을 때 손해보는 시간) + (i번 문제를 다시푸는 시간)
else diffes[i] <= level;
   걸린시간 = times[i]
*/
