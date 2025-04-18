/*
 세로개수 3부터 시작, 가로개수보다 작을때까지 가능
 노란색 격자 갯수 계산법은? (세로개수-2) * (가로개수-2)
 */
class Solution {
    public int[] solution(int brown, int yellow) {
        /* 
        brown은 항상 짝수라고 가정
        (height * 2) + (width * 2) - 4 = brown
        width * 2 = brown - (height*2) + 4
         */
        if (brown % 2 != 0) {
            throw new IllegalStateException("?!?!?!");
        }
        int height = 3;
        int width = (brown - height * 2 + 4) / 2;
        
        while (height <= width) {
            System.out.printf("w:%d h:%d\n", width, height);
            int currentYellow = (height-2) * (width-2);
            if (yellow == currentYellow) {
                int[] answer = {width, height};
                return answer;
            }
            height++;
            width = (brown - height * 2 + 4) / 2;
        }
        throw new IllegalStateException("앵");
    }
}
