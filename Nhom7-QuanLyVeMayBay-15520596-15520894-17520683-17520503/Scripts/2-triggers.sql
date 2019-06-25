
--------------------------------------------------------
--  DDL for Trigger CHITIETHOADON_TRIGGER
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TRIGGER "GON"."CHITIETHOADON_TRIGGER" 
BEFORE INSERT ON CHITIETHOADON
FOR EACH ROW
BEGIN
  SELECT to_char(chitiethoadon_sequence.NEXTVAL)
  INTO   :new.MACHITIETHOADON
  FROM   dual;
END;
/
ALTER TRIGGER "GON"."CHITIETHOADON_TRIGGER" ENABLE;
--------------------------------------------------------
--  DDL for Trigger CHUYENBAY_TRIGGER
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TRIGGER "GON"."CHUYENBAY_TRIGGER" 
BEFORE INSERT ON CHUYENBAY
FOR EACH ROW
BEGIN
  SELECT to_char(chuyenbay_sequence.NEXTVAL)
  INTO   :new.MaCB
  FROM   dual;
END;
/
ALTER TRIGGER "GON"."CHUYENBAY_TRIGGER" ENABLE;
--------------------------------------------------------
--  DDL for Trigger GHE_CB
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TRIGGER "GON"."GHE_CB" 
AFTER INSERT 
ON VE
FOR EACH ROW
DECLARE 
  V_LOAIVE LOAIVE%TYPE;
BEGIN
  SELECT VE.LOAIVE INTO V_LOAIVE FROM VE WHERE VE.MAVE = :NEW.MAVE;
    IF (V_LOAIVE) = 1 THEN
      BEGIN
        IF (SELECT CB.SOGHEVIPTRONG FROM CHUYENBAY CB, VE WHERE VE.MAVE=:NEW.MAVE AND VE.MACB=CB.MACB) > 0
        THEN
          UPDATE CHUYENBAY CB 
          SET CB.SOGHEVIPTRONG = CB.SOGHEVIPTRONG - 1 
          WHERE VE.MAVE=:NEW.MAVE;
        ELSE
          BEGIN
            DBMS_OUTPUT.PUT('HET GHE !');
            ROLLBACK;   
          END;
       END IF;
      END;  
      ELSIF (SELECT VE.LOAIVE FROM VE WHERE VE.MAVE=:NEW.MAVE) = 0
      THEN
        BEGIN
        IF (SELECT CB.SOGHETHUONGTRONG FROM CHUYENBAY CB, VE WHERE VE.MAVE=:NEW.MAVE AND VE.MACB=CB.MACB) > 0
        THEN
          UPDATE CHUYENBAY CB 
          SET CB.SOGHETHUONGTRONG = CB.SOGHEVIPTRONG - 1 
          WHERE VE.MAVE=:NEW.MAVE; 
        ELSE
        BEGIN
          DBMS_OUTPUT.PUT('HET GHE !');
          ROLLBACK;
        END;
        END IF;
      END;
  
  END IF;
  
END;
/
ALTER TRIGGER "GON"."GHE_CB" ENABLE;
--------------------------------------------------------
--  DDL for Trigger HOADON_TRIGGER
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TRIGGER "GON"."HOADON_TRIGGER" 
BEFORE INSERT ON HOADON
FOR EACH ROW
BEGIN
  SELECT to_char(hoadon_sequence.NEXTVAL)
  INTO   :new.MAHOADON
  FROM   dual;
END;
/
ALTER TRIGGER "GON"."HOADON_TRIGGER" ENABLE;
--------------------------------------------------------
--  DDL for Trigger KH_TRIGGER
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TRIGGER "GON"."KH_TRIGGER" 
BEFORE INSERT ON KHACHHANG
FOR EACH ROW
BEGIN
  SELECT to_char(khachhang_sequence.NEXTVAL)
  INTO   :new.MAKHACHHANG
  FROM   dual;
END;
/
ALTER TRIGGER "GON"."KH_TRIGGER" ENABLE;
--------------------------------------------------------
--  DDL for Trigger KT_VE
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TRIGGER "GON"."KT_VE" 
BEFORE INSERT OR UPDATE
ON CHITIETVE
FOR EACH ROW
BEGIN
  IF ((SELECT VE.TINHTRANG FROM CHITIETVE CT INNER JOIN VE ON CT.MAVE = VE.MAVE WHERE CT.MAVE = :NEW.MAVE) != 0)
  THEN
   BEGIN
      UPDATE CHITIETVE CT SET CT.GIA = UP_GIA(:NEW.MAVE) WHERE CT.MAVE = :NEW.MAVE;
      DBMS_OUTPUT.PUT('THANH CONG!');
    END;
  ELSE
    DBMS_OUTPUT.PUT('LOI: TINH TRANG=0 !');
    ROLLBACK;
  END IF;
END;
/
ALTER TRIGGER "GON"."KT_VE" DISABLE;
--------------------------------------------------------
--  DDL for Trigger VE_TRIGGER
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TRIGGER "GON"."VE_TRIGGER" 
BEFORE INSERT ON Ve
FOR EACH ROW
BEGIN
  SELECT to_char(ve_sequence.NEXTVAL)
  INTO   :new.MaVe
  FROM   dual;
END;
/
ALTER TRIGGER "GON"."VE_TRIGGER" ENABLE;