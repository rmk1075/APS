SELECT B.NAME AS "DEPARTMENT"
    ,  A.NAME AS "EMPLOYEE"
    ,  A.SALARY AS "SALARY"
  FROM EMPLOYEE A
    ,  DEPARTMENT B
    ,  (
    SELECT DEPARTMENTID
        ,  MAX(SALARY) AS "SALARY"
      FROM EMPLOYEE
     GROUP BY DEPARTMENTID
    ) C
 WHERE A.DEPARTMENTID = B.ID
   AND A.DEPARTMENTID = C.DEPARTMENTID
   AND A.SALARY = C.SALARY