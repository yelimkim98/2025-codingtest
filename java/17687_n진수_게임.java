import java.util.*;
class Solution {
    /*
    n진법
    구해놓을 숫자 갯수 t개
    게임참가인원 m명
    내 순서 p (<m)
    */
    public String solution(int n, int t, int m, int p) {
        StringBuilder sb = new StringBuilder();
        
        // System.out.println(sb.length());
        // System.out.println(toStr(8) + " " + toStr(15));
        // System.out.println(convert(154, 16));
        
        int nextOrder = 0; // 0~(m-1) 계속 순환,  내 순서 : (p-1)
        int nextNumber = 0;    // 계속 1씩 증가
        final int myOrder = p-1;
        
        while (sb.length() < t) {
            List<String> result = convert(nextNumber, n);
            
            for (int i=0; i<result.size(); i++) {
                // (nextOrder+i)%m 번째 사람이 result.get(i) 를 말함
                String current = result.get(i);
                // System.out.print(current);
                if (myOrder == (nextOrder+i)%m && sb.length() < t) {
                    // System.out.print("(myTurn)");
                    sb.append(current);
                }
                // System.out.print(" ");
            }
            nextOrder = (nextOrder+result.size())%m;
            nextNumber++;
        }
        return sb.toString();
    }
    /* 
    10진법 -> n진법
    8을 3진법으로
    7/3=2, 7%3=1
    */
    public List<String> convert(int num, int n) {
        List<String> l = new LinkedList<>();
        if (num == 0) {
            l.add("0");
        }
        while (num > 0) {
            int tmp = num;
            num = tmp / n;
            l.add(0, toStr(tmp % n));
        }
        return l;
    }
    public String toStr(int num) {   // 0 <= num <= 15
        if (num < 10) return String.valueOf(num);
        return (char) ('A' + num - 10) + "";
        
    }
}
/*
16진수
0 1 .. 9 A B C D E F -> 16개
10 11 12 .. 1F -> 32개
20 21 22 .. 2F -> 32개

9진수
0 1 .. 8  -> 9개
10 11 .. 18 -> 18개
20 21 .. 28 -> 28개
*/
