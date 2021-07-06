SELECT DISTINCT(L1.NUM) AS CONSECUTIVENUMS
  FROM LOGS L1
    ,  LOGS L2
    ,  LOGS L3
 WHERE L1.ID = L2.ID - 1
   AND L2.ID = L3.ID - 1
   AND L1.NUM = L2.NUM
   AND L2.NUM = L3.NUM

-- SELECT DISTINCT(CONSECUTIVENUMS)
--   FROM (
--     SELECT ID
--         ,  NUM AS CONSECUTIVENUMS
--       FROM LOGS L1
--      WHERE NUM = (
--         SELECT L2.NUM
--           FROM LOGS L2
--          WHERE L2.ID = L1.ID + 1     
--         )
--        AND NUM = (
--         SELECT L3.NUM
--           FROM LOGS L3
--          WHERE L3.ID = L1.ID + 2
--     )
--   )