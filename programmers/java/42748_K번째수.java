import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        
        for (int i=0; i<commands.length; i++) {
            int[] command = commands[i];
            int startIdx = command[0]-1;
            int endIdx = command[1]-1;
            int[] arr = new int[endIdx - startIdx + 1];
            for (int j=startIdx; j <= endIdx; j++) {
                arr[j-startIdx] = array[j];
            }
            Arrays.sort(arr);
            answer[i] = arr[command[2]-1];
        }
        return answer;
    }
}