-- Policy danh cho quan tri update username
-- Chi co the update neu username = null

CREATE OR REPLACE FUNCTION QT_UPDATE_USERNAME_NULL (
    p_schema   VARCHAR2,
    p_obj      VARCHAR2
) RETURN VARCHAR2 AS
    user VARCHAR2(100);
BEGIN
    user := sys_context('userenv', 'SESSION_USER'); 
    if user='QT' then
        RETURN 'username is null';
    else
        return '1=1';
    end if;
END;
/
DECLARE
    policyexist INTEGER;
BEGIN
    SELECT
        COUNT(*)
    INTO policyexist
    FROM
        dba_policies
    WHERE
        policy_name = 'QT_UPDATE_USERNAME_NULL';

    IF ( policyexist = 1 ) THEN
        EXECUTE IMMEDIATE 'BEGIN 
            DBMS_RLS.drop_policy
            (object_schema => ''QT'',
             object_name => ''THANHVIEN'',
             policy_name => ''QT_UPDATE_USERNAME_NULL'');
             END;'
        ;
    END IF;
END;
/
BEGIN
    dbms_rls.add_policy(object_schema => 'QT', object_name => 'THANHVIEN', policy_name => 'QT_UPDATE_USERNAME_NULL', policy_function => 'QT_UPDATE_USERNAME_NULL'
    , statement_types => 'UPDATE');
END;
/