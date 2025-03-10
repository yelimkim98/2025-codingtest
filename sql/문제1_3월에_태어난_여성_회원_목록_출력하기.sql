select
    m.MEMBER_ID MEMBER_ID,
    m.MEMBER_NAME MEMBER_NAME,
    m.GENDER GENDER,
    DATE_FORMAT(m.DATE_OF_BIRTH, '%Y-%m-%d') DATE_OF_BIRTH
from
    MEMBER_PROFILE m
where
    MONTH(m.DATE_OF_BIRTH) = 3
    and
    m.GENDER = 'W'
    and
    m.TLNO is not null
order by
    m.MEMBER_ID asc
;
