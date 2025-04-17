class Solution {
    public int[] solution(int n) {
        try{
        int[][] arr = new int[n][];
        for (int i=0; i<n; i++) {
            arr[i] = new int[i+1];
        }
        int row = -1,  col = 0;
        int moveStatus = 0;    // 0이면 하향, 1이면 우향, 2면 좌우향
        int num = 1;
        for (int i=n; i>0; i--) {
            for (int j=0; j<i; j++) {
                if (moveStatus == 0) {
                    row++;
                }
                else if (moveStatus == 1) {
                    col++;
                }
                else if (moveStatus == 2) {
                    row--; col--;
                }
                arr[row][col] = num;
                num++;
            }
            moveStatus = (moveStatus+1) % 3;
        }
        int[] answer = new int[(n*(n+1))/2];
        int idx = 0;
        for (int i=0; i<arr.length; i++) {
            for (int j=0; j<arr[i].length; j++) {
                answer[idx] = arr[i][j];
                idx++;
            }
            System.out.println();
        }
        return answer;
        }catch(Exception e) {
            System.out.println("ERROR");
            int[] answer = new int[0];
            return answer;
        }
    }
}
/*
[ 1 ]
[ 2  12 ]
[ 3  13  11 ]
[ 4  14  15  10 ]
[ 5  6    7   8   9]
*/
