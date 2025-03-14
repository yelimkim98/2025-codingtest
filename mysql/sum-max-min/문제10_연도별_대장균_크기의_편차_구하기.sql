WITH
EY AS (
    SELECT
        E.ID ID,
        YEAR(E.DIFFERENTIATION_DATE) DIFF_YEAR,
        E.SIZE_OF_COLONY SIZE
    FROM ECOLI_DATA E
),
MY AS (
    SELECT
        EY.DIFF_YEAR DIFF_YEAR,
        MAX(EY.SIZE) MAX_SIZE
    FROM EY
    GROUP BY
        EY.DIFF_YEAR
)
SELECT
    EY.DIFF_YEAR YEAR,
    (MY.MAX_SIZE - EY.SIZE) YEAR_DEV,
    EY.ID ID
FROM
    EY INNER JOIN MY
    ON EY.DIFF_YEAR=MY.DIFF_YEAR
ORDER BY YEAR ASC, YEAR_DEV ASC
;
