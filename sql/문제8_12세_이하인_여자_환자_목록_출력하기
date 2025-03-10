SELECT
    PT_NAME,
    PT_NO,
    GEND_CD,
    AGE,
    IFNULL(TLNO, 'NONE') TLNO
FROM
    PATIENT p
WHERE
    p.AGE <= 12
    and
    p.GEND_CD = 'W'
ORDER BY
    p.AGE DESC, p.PT_NAME asc
;
