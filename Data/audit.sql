--audit
alter session set "_ORACLE_SCRIPT"=true;
/
GRANT AUDIT_VIEWER TO QT;
declare policyexist integer;
begin
  select count(*) into policyexist from dba_policies where policy_name='AUDIT_THANHVIEN';
  if (policyexist = 1) then
    execute immediate 'begin
                 DBMS_FGA.DROP_POLICY (
                 object_schema => ''QT'',
                 object_name => ''THANHVIEN'',
                 policy_name => ''AUDIT_THANHVIEN'');
                 end;';
  end if;
end;
/
begin
    DBMS_FGA.ADD_POLICY (
    object_schema => 'QT',
    object_name => 'THANHVIEN',
    policy_name => 'AUDIT_THANHVIEN',
    statement_types=>'SELECT,INSERT,UPDATE,DELETE',
    audit_trail => DBMS_FGA.DB+DBMS_FGA.EXTENDED);
 end;
/

create or replace PROCEDURE DISABLE_AUDIT(bang varchar2, varchar2)
AS
begin
    DBMS_FGA.DISABLE_POLICY (
    object_schema => 'QT',
    object_name => bang,
    policy_name => ten_audit);
end;
/
CREATE or replace PROCEDURE ENABLE_AUDIT(bang varchar2,ten_audit varchar2)
AS
begin
    dbms_fga.enable_policy (
    object_schema => 'QT',
    object_name => bang,
    policy_name => ten_audit,
    ENABLE => TRUE);
 end;
/
GRANT EXECUTE ON DISABLE_AUDIT TO QT;
GRANT EXECUTE ON ENABLE_AUDIT TO QT;
=======