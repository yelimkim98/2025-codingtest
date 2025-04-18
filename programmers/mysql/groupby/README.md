# CASE-WHEN-THEN
```sql
SELECT
    CASE
    WHEN 조건1
    THEN '결과값1 예시'
    WHEN 조건2
    THEN '결과값2 예시'
    END
    컬럼별칭,
    ...
FROM ...
```

<br/>

# WINDOW FUNCTION
```sql
SELECT WINDOW_FUNCTION (ARGUMENTS) OVER 
( [PARTITION BY 컬럼] [ORDER BY 컬럼] [WINDOWING 절] )
FROM 테이블명 ; 
```
