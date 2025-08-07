class Solution {
    public int solution(int n, int k) {
        // System.out.println(Math.pow(3, 12));
        // 3^13이 백만자리 -> 진법 변환한 숫자 길이는 최대 13자리
        String kn = n10ToK(n, k);

        String[] ps = kn.split("0");
        
        int cnt = 0;
        
        for (String p : ps) {
            if ("".equals(p)) continue;
            Long pLong = Long.parseLong(p);
            if (isPrime(pLong)) {
                cnt++;
            }
        }
        return cnt;
    }
    
    String n10ToK(int n, int k) {
        StringBuilder sb = new StringBuilder();
        
        while (n > 0) {
            sb.insert(0, n % k);
            n = n / k;
        }
        return sb.toString();
    }
    
    boolean isPrime(long num) {
        if (num == 1) return false;
        if (num == 2) return true;
        
        for (int i=2; i<= (int) Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}
