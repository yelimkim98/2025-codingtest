import java.util.*;

class Boarding {
    int busMin;         // 버스 도착 시간
    int lastUserMin=0;  // 마지막 탑승자 도착시간
    int userCnt=0;      // 탑승자 수
    public Boarding(int busMin) {
        this.busMin=busMin;
    }
}

class Solution {
    int m;
    public String solution(int n, int t, int m, String[] timetable) {
        this.m=m;
        
        List<Integer> userMins = new ArrayList<>();
        for (String time : timetable) {
            userMins.add(timeToMin(time));
        }
        Collections.sort(userMins);
        
        List<Integer> busMins = new ArrayList<>();
        
        busMins.add(timeToMin("09:00"));
        
        for (int i=1; i<n; i++) {
            busMins.add(busMins.get(busMins.size()-1) + t);
        }
        // key 버스도착시간 value 마지막 탑승자 도착시간
        Map<Integer, Boarding> boardingMap = new HashMap<>();
        
        int userIdx=0;
        for (int busMin : busMins) {
            // 버스 도착 시간 이하에 도착한 user들 중 빨리 온 순서로 m명 태우기
            // userIdx = 대기열 1번 user의 index
            for (int i=0; i<m && userIdx < userMins.size(); i++) {
                int userMin = userMins.get(userIdx);
                
                if (userMin <= busMin) { // 현재 버스에 탑승
                    Boarding b = boardingMap.getOrDefault(busMin, new Boarding(busMin));
                    b.lastUserMin=userMin;
                    b.userCnt++;
                    boardingMap.put(busMin, b);
                    
                    userIdx++;
                }
            }
        }
        int lastBusMin = busMins.get(busMins.size()-1);
                
        Boarding emptyBoarding = new Boarding(lastBusMin);
        emptyBoarding.lastUserMin=lastBusMin+1;
        emptyBoarding.userCnt=0;
        
        Boarding b = boardingMap.getOrDefault(lastBusMin, emptyBoarding);
        
        return minToTime(calcBoardingMin(b));
    }
    
    public int calcBoardingMin(Boarding b) {
        if (b.userCnt < m) {
            return b.busMin;
        }
        return b.lastUserMin-1;
    }
    int timeToMin(String time) {
        String[] split = time.split(":");
        int min=Integer.parseInt(split[1]);
        min += (Integer.parseInt(split[0]) * 60);
        return min;
    }
    String minToTime(int min) {
        int h = min / 60;
        int m = min % 60;
        return String.format("%02d:%02d", h, m);
    }
}

/*
오전 9시부터 n회 t분 간격으로 도착, 최대 m명 탑승 가능

1회 1분 간격, 최대 5명 탑승
9:00 <- 4명 탑승하고 마지막에 콘 탑승

2회 10분 간격, 최대 2명 탑승
9:00 <- 1명 탑승
9:10 <- 2명 탑승인데 이게 마지막 차니까 타야함. 따라서 9:09에 나와야함

2회 1분 간격, 최대 2명 탑승
9:00 <- 9:00 도착자 2명 탑승
9:01 <- 9:00 도착자 2명 탑승
8:59분에 도착해야 차를 탈 수 있음

1회 1분 간격, 최대 5명 탑승
5명 다 타면 콘이 못타니까 1분 일찍 -> 00:00

일단 콘 빼고 탑승시키기
> 마지막 차에 빈 자리가 있다면 마지막 차 도착시간
> 마지막 차에 빈 자리가 없다면 마지막 차 마지막으로 탄 사람보다 1분 빨리 도착

*/
