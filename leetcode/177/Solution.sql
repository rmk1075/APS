CREATE FUNCTION getNthHighestSalary(N IN NUMBER) RETURN NUMBER IS
result NUMBER;
BEGIN
    /* Write your PL/SQL query statement below */
    SELECT nvl(SALARY, NULL) into result
    FROM (
        SELECT ROWNUM num, SALARY
            FROM (
                SELECT DISTINCT(SALARY)
                  FROM EMPLOYEE
                 ORDER BY SALARY DESC
            )
    )
    WHERE num = N
    ;

    RETURN result;
END;