# Write your MySQL query statement below
SELECT P.FirstName, P.LastName, A.City, A.State
  FROM Person AS P
     , Address AS A
 WHERE P.PersonID = A.PersonID
 UNION
SELECt P.FirstName, P.LastName, null, null
  FROM Person AS P
 WHERE P.PersonID NOT IN (
    SELECT A.PersonID
      FROM Address AS A
 )
;