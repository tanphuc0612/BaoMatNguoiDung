GRANT SELECT ON THONGTINROLE TO PUBLIC;
GRANT CREATE SESSION, CREATE ANY CONTEXT, CREATE PROCEDURE, CREATE TRIGGER, ADMINISTER DATABASE TRIGGER TO QT;
GRANT EXECUTE ON DBMS_SESSION TO QT;
GRANT EXECUTE ON SYS.DBMS_CRYPTO TO LAPDANHSACHDIBAU;
GRANT EXECUTE ON QT.F_DECRYPT_thanhvien_username TO LAPDANHSACHDIBAU;
--AUDIT
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

create or replace PROCEDURE DISABLE_AUDIT(bang varchar2,ten_audit varchar2)
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

-- tao function lay matv
CREATE OR REPLACE FUNCTION  LayMaTV (v_UserName VARCHAR2)
    RETURN INTEGER
AS
    A INTEGER;
BEGIN
    Select matv into A from qt.thanhvien where QT.F_DECRYPT_thanhvien_username(matv) = v_UserName;
    RETURN A;
END;
/
grant execute on LayMaTV to NGUOIDIBAU;
/
-- tao function lay username
CREATE OR REPLACE FUNCTION  LayUserName (v_MaTV NUMBER)
    RETURN VARCHAR2
AS
    A VARCHAR2(100);
BEGIN
    Select QT.F_DECRYPT_thanhvien_username(matv) into A from qt.thanhvien where matv = v_MaTV;
    RETURN A;
END;
/
grant execute on LayUserName to LAPDANHSACHDIBAU;
/

connect QT/1;
--BTC (RBAC)
GRANT BANTOCHUC to TV1;
GRANT BANTOCHUC to TV10;
GRANT SELECT, DELETE, UPDATE(HoTen,Phai,NgaySinh) ON UNGCUVIEN TO BANTOCHUC;
GRANT SELECT, DELETE, UPDATE(HoTen,Phai,NgaySinh) ON TOLAPDANHSACH TO BANTOCHUC;
GRANT SELECT, DELETE, UPDATE(HoTen,Phai,NgaySinh) ON TOTHEODOI TO BANTOCHUC;
GRANT SELECT, DELETE, UPDATE(HoTen,Phai,NgaySinh) ON TOGIAMSAT TO BANTOCHUC;
GRANT SELECT ON DONVI TO BANTOCHUC;
---Nguoi di bau
GRANT NGUOIDIBAU to TV1; --btc
GRANT NGUOIDIBAU to TV2; 
grant select on qt.UNGCUVIEN TO NGUOIDIBAU;
GRANT NGUOIDIBAU to TV3; -- lsd don vi 1
GRANT NGUOIDIBAU to TV4; -- lsd don vi 2
GRANT NGUOIDIBAU to TV6; -- tdkq
GRANT NGUOIDIBAU to TV7; -- tgs
GRANT NGUOIDIBAU to TV8; -- ucv
GRANT NGUOIDIBAU to TV11; -- tgs
GRANT NGUOIDIBAU to TV12; -- ucv
GRANT NGUOIDIBAU to TV14; -- lsd don vi 3
GRANT NGUOIDIBAU to TV15; -- ucv
GRANT NGUOIDIBAU to TV16; -- ucv
GRANT INSERT ON LICHSUBAU TO NGUOIDIBAU;
GRANT INSERT,DELETE ON LANBAUCUOI TO NGUOIDIBAU;
GRANT SELECT ON UNGCUVIEN TO NGUOIDIBAU;

---To lap danh sach nguoi di bau (VPD)
GRANT LAPDANHSACHDIBAU to TV3; -- lsd don vi 1
GRANT LAPDANHSACHDIBAU to TV4; -- lsd don vi 2
GRANT LAPDANHSACHDIBAU to TV14; -- lsd don vi 3
GRANT SELECT ON THANHVIEN to LAPDANHSACHDIBAU;
GRANT UPDATE ON THANHVIEN to LAPDANHSACHDIBAU;
grant select on TOLAPDANHSACH TO LAPDANHSACHDIBAU;
/
--- TAO SYS_CONTEXT lay MADV tu USERNAME
CREATE OR REPLACE CONTEXT madv_ctx USING set_madv_ctx_pkg;
/
CREATE OR REPLACE PACKAGE set_madv_ctx_pkg IS 
   PROCEDURE set_madv; 
 END; 
 /
 CREATE OR REPLACE PACKAGE BODY set_madv_ctx_pkg IS
   PROCEDURE set_madv
   IS 
    madv NUMBER;
   BEGIN 
    SELECT MADV into madv FROM QT.TOLAPDANHSACH where QT.F_DECRYPT_tolapdanhsach_username(MALDS) = SYS_CONTEXT('USERENV', 'SESSION_USER');
    DBMS_SESSION.SET_CONTEXT('madv_ctx', 'madv', madv);
   EXCEPTION
    WHEN NO_DATA_FOUND THEN NULL;
  END;
 END;
/
CREATE or REPLACE TRIGGER set_madv_ctx_trig AFTER LOGON ON DATABASE
 BEGIN
  qt.set_madv_ctx_pkg.set_madv;
 END;
/
--VPD (Nguoi thuoc to lap danh sach nguoi di bau chi co the loc duoc nhan vien chung don vi)
CREATE OR REPLACE FUNCTION sl_dsndb (
    p_schema   VARCHAR2,
    p_obj      VARCHAR2
) RETURN VARCHAR2 AS
    checkrole  number(1);
    checkroleQT number(1);
BEGIN
    select count(*) into checkrole from sys.THONGTINROLE where granted_role = 'LAPDANHSACHDIBAU';
    select count(*) into checkroleQT from sys.THONGTINROLE where granted_role = 'QUANTRI';
    IF (checkroleQT = 1) THEN 
        RETURN '';
    else if (checkrole = 1) then RETURN 'MaDV = '||SYS_CONTEXT('madv_ctx', 'madv');
    else
        return '';
    end if;
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
        policy_name = 'SL_DSNDB';

    IF ( policyexist = 1 ) THEN
        EXECUTE IMMEDIATE 'BEGIN 
            DBMS_RLS.drop_policy
            (object_schema => ''QT'',
             object_name => ''THANHVIEN'',
             policy_name => ''sl_dsndb'');
             END;'
        ;
    END IF;
END;
/
BEGIN
    dbms_rls.add_policy(object_schema => 'QT', object_name => 'THANHVIEN', policy_name => 'sl_dsndb', policy_function => 'sl_dsndb'
    , statement_types => 'SELECT');
END;
/
--To theo doi ket qua
CREATE OR REPLACE VIEW DSNGUOIDADIBAU
AS
SELECT DISTINCT TV.MATV, TEN, PHAI, NGAYSINH, DV.TENDV, 
    case when LBC.IDLBC is not null then '1' else '0' end AS TINHTRANG
FROM QT.THANHVIEN TV
JOIN QT.DONVI DV ON TV.MADV = DV.MADV
LEFT JOIN QT.LANBAUCUOI LBC ON TV.MATV = LBC.MATV
ORDER BY TV.MATV;
/

CREATE OR REPLACE VIEW DSPHIEUBAUUCV
AS
SELECT UCV.MAUCV, UCV.HOTEN, UCV.PHAI, UCV.NGAYSINH, COUNT(*) AS SOPHIEUBAU
FROM QT.UNGCUVIEN UCV
JOIN QT.THANHVIEN TV ON ucv.maucv = tv.matv
JOIN QT.LANBAUCUOI LBC ON TV.MATV = LBC.maucv
WHERE LBC.MATV IN (select MATV from QT.LANBAUCUOI GROUP BY MATV HAVING COUNT(MAUCV) = 3)
GROUP BY UCV.MAUCV, UCV.HOTEN, UCV.PHAI, UCV.NGAYSINH;
/
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
GRANT SELECT ON DSNGUOIDADIBAU TO THEODOIKETQUA;
GRANT SELECT ON DSPHIEUBAUUCV TO THEODOIKETQUA;
GRANT SELECT ON UNGCUVIEN TO THEODOIKETQUA;
GRANT THEODOIKETQUA to TV6;
GRANT THEODOIKETQUA to TV13;

--To giam sat (RBAC)
GRANT GIAMSAT to TV7;
GRANT GIAMSAT to TV11;
grant select on THANHVIEN to GIAMSAT;
grant select on BANTOCHUC to GIAMSAT;
grant select on UNGCUVIEN to GIAMSAT;
grant select on TOGIAMSAT to GIAMSAT;
grant select on TOLAPDANHSACH to GIAMSAT;
grant select on TOTHEODOI to GIAMSAT;
grant select on DONVI to GIAMSAT;
grant select on LANBAUCUOI to GIAMSAT;
grant select on LICHSUBAU to GIAMSAT;