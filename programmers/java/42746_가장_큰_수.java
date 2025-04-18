import java.util.*;
import java.util.stream.*;

class Solution {
    public String solution(int[] numbers) {
        List<String> numberList = Arrays.stream(numbers)
            .mapToObj(number -> String.valueOf(number))
            .collect(Collectors.toList());

        Collections.sort(numberList, (s1, s2) -> {
            int s1s2 = Integer.parseInt(s1 + s2);
            int s2s1 = Integer.parseInt(s2 + s1);

            if (s1s2 > s2s1) {  // s1이 더 큰수
                return -1;      // 내림차순이라서 부호 반대
            }
            if (s2s1 > s1s2) {
                return 1;
            }
            return 0;
        });
        
        if (Integer.parseInt(numberList.get(0)) == 0) return "0";
        
        return numberList.stream()
            .collect(Collectors.joining());
    }
}
