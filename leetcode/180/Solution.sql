SELECT DISTINCT(CONSECUTIVENUMS)
  FROM (
    SELECT ID
        ,  NUM AS CONSECUTIVENUMS
      FROM LOGS L1
     WHERE NUM = (
        SELECT L2.NUM
          FROM LOGS L2
         WHERE L2.ID = L1.ID + 1     
        )
       AND NUM = (
        SELECT L3.NUM
          FROM LOGS L3
         WHERE L3.ID = L1.ID + 2
    )
  )