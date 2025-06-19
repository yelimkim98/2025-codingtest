WITH
SALES_RESULT AS (
    SELECT
        S.USER_ID USER_ID,
        DATE_FORMAT(S.SALES_DATE, '%Y') YEAR,
        DATE_FORMAT(S.SALES_DATE, '%m') MONTH
    FROM
        ONLINE_SALE S
    WHERE
        S.USER_ID IN (
            SELECT
                U.USER_ID USER_ID
            FROM
                USER_INFO U
            WHERE
                U.JOINED LIKE '2021-%'
        )
)
SELECT
    S.YEAR `YEAR`,
    S.MONTH `MONTH`,
    COUNT(DISTINCT S.USER_ID) `PURCHASED_USERS`,
    ROUND(COUNT(DISTINCT S.USER_ID) / (
            SELECT
                COUNT(*)
            FROM
                USER_INFO U
            WHERE
                U.JOINED LIKE '2021-%'
    ), 1) `PURCHASED_RATIO`
FROM
    SALES_RESULT S
GROUP BY
    S.YEAR, S.MONTH
ORDER BY `YEAR` ASC, `MONTH` ASC
;
