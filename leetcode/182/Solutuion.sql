SELECT EMAIL
  FROM (
    SELECT EMAIL
        ,  COUNT(EMAIL) CNT
      FROM PERSON
     GROUP BY EMAIL
  )
 WHERE 1 < CNT