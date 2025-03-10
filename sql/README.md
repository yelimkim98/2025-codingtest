# MySQL 날짜/시간
## 타입
- DATE
- DATETIME
- TIMESTAMP

## 함수
- 값 추출
    - YEAR()
    - MONTH()
    - DAY()
    - HOUR()
    - MINUTE()
    - SECOND()
    - DAYOFWEEK()
- 포맷팅
    - DATE_FORMAT()  
        - 4자리 년도 : %Y  
          2자리 년도 : %y
        - 숫자 월(두자리) : %m   
          숫자 월(한자리는 한자리) : %c  
        - 일자(두자리) : %d  
          일자(한자리는 한자리) : %e  

# MySQL 문자열 함수
- 검색 LIKE
    - 서울시로 시작하는 데이터 검색 : like '서울시%'
    - 서울시로 끝나는 데이터 검색 : like '%서울시'
    - 서울시가 들어가는 데이터 검색 : like '%서울시%'


# MySQL 실수
## 타입
- DECIMAL(전체자리수, 소수점이하자리수)
- FLOAT
- DOUBLE

## 함수
- 반올림 ROUND()
    - ROUND(123.456, 2) : 반올림하여 두자리까지 남김 -> 123.46
    - ROUND(123.456, 0) : 123
    - ROUND(123.456, -1) : 120
- 올림 CEIL()
    - CEIL(4.2) : 5
    - CEIL(-4.2) : -4
- 내림 FLOOR()
    - FLOOR(4.2) : 4
    - FLOOR(-4.2) : -5
- 버림 TRUNCATE()
    - TRUNCATE(123.456, 2) : 123.45
    - TRUNCATE(123.456, 0) : 123
    - TRUNCATE(123.456, -1) : 120
