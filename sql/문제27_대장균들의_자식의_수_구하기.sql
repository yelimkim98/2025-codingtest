SELECT
    parent.ID ID,
    COUNT(child.ID) CHILD_COUNT
FROM
    ECOLI_DATA parent LEFT JOIN ECOLI_DATA child 
    ON
    parent.ID = child.PARENT_ID
GROUP BY
    parent.ID
ORDER BY
    ID ASC
;
