SELECT
    DATE_FORMAT(SALES_DATE, '%Y-%m-%d') SALES_DATE,
    PRODUCT_ID,
    USER_ID,
    SALES_AMOUNT
FROM (
        (
            SELECT
                onl.SALES_DATE SALES_DATE,
                onl.PRODUCT_ID PRODUCT_ID,
                onl.USER_ID USER_ID,
                onl.SALES_AMOUNT SALES_AMOUNT
            FROM
                ONLINE_SALE onl
            WHERE
                onl.SALES_DATE like '2022-03%'
        )
        UNION ALL
        (
            SELECT
                ofl.SALES_DATE SALES_DATE,
                ofl.PRODUCT_ID PRODUCT_ID,
                NULL AS USER_ID,
                ofl.SALES_AMOUNT SALES_AMOUNT
            FROM
                OFFLINE_SALE ofl
            WHERE
                ofl.SALES_DATE like '2022-03%'
        )
    ) d
ORDER BY
    SALES_DATE ASC, PRODUCT_ID ASC, USER_ID ASC
;
