SELECT 
    d.ID,
    d.EMAIL,
    d.FIRST_NAME,
    d.LAST_NAME
FROM DEVELOPERS d
WHERE (
    d.SKILL_CODE & (
        SELECT SUM(s.CODE)
        FROM SKILLCODES s
        WHERE s.NAME IN ('Python', 'C#')
    ) > 0
)
ORDER BY d.ID ASC
;
