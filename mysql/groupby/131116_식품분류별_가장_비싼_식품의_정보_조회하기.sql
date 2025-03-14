WITH MAX_PRICE_PER_CATEGORY AS (
    SELECT
        F.CATEGORY CATEGORY,
        MAX(F.PRICE) MAX_PRICE
    FROM
        FOOD_PRODUCT F
    WHERE
        F.CATEGORY IN ('과자', '국', '김치', '식용유')
    GROUP BY
        F.CATEGORY
)
SELECT
    F.CATEGORY CATEGORY,
    F.PRICE MAX_PRICE,
    F.PRODUCT_NAME PRODUCT_NAME
FROM
    FOOD_PRODUCT F INNER JOIN MAX_PRICE_PER_CATEGORY M
    ON F.CATEGORY=M.CATEGORY
WHERE
    F.PRICE = M.MAX_PRICE
ORDER BY
    F.PRICE DESC
;
