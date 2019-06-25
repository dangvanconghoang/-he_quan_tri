
--------------------------------------------------------
--  DDL for Procedure CAPNHATTIENVE
--------------------------------------------------------
set define off;

  CREATE OR REPLACE NONEDITIONABLE PROCEDURE "GON"."CAPNHATTIENVE" 
(
    p_mave nvarchar2,
    p_tienmoi number
)
as 
begin 
    update CHITIETVE
    set GIA = p_tienmoi
    where p_mave = MAVE;
end;

/
--------------------------------------------------------
--  DDL for Procedure DANHSACHCHUYENBAY
--------------------------------------------------------
set define off;

  CREATE OR REPLACE NONEDITIONABLE PROCEDURE "GON"."DANHSACHCHUYENBAY" 
(
    p_diemkh in VARCHAR2,
    p_diemden in VARCHAR2,
    p_ngaykh in VARCHAR2,
    mycursor OUT SYS_REFCURSOR
)
as
begin
      open mycursor for
        SELECT MaCB, ChuyenBay.MaHMB, TenHMB, ThoiGianKhoiHanh, GiaVe, ThoiGianBay, 
          sanbaydi.MaSB as MaSBDi, sanbaydi.TENSB as TenSBDi, sanbaydi.THANHPHO as ThanhPhoDi, sanbayden.MaSB as MaSBDen, sanbayden.TENSB AS TenSBDen, sanbayden.THANHPHO AS ThanhPhoDen
        FROM ChuyenBay
        INNER JOIN HangMayBay ON ChuyenBay.MaHMB = HangMayBay.MaHMB
        INNER JOIN SANBAY sanbaydi ON ChuyenBay.DiemKhoiHanh = sanbaydi.MaSB
        INNER JOIN SANBAY sanbayden ON CHUYENBAY.DiemDen = sanbayden.MaSB
        WHERE sanbaydi.THANHPHO = p_diemkh
          AND sanbayden.THANHPHO = p_diemden
          AND trunc(ChuyenBay.ThoiGianKhoiHanh) = TO_DATE(p_ngaykh, 'yyyy-mm-dd');
      exception
      when CASE_NOT_FOUND then
          RAISE_APPLICATION_ERROR(-20001,'ERROR CASE STATEMENT',TRUE);
end;

/
--------------------------------------------------------
--  DDL for Procedure DANHSACHVECUA1CB
--------------------------------------------------------
set define off;

  CREATE OR REPLACE NONEDITIONABLE PROCEDURE "GON"."DANHSACHVECUA1CB" 
(
    p_macb nvarchar2,
    mycursor out sys_refcursor
)
as
begin
    open mycursor for
      select MAVE, MACB, LOAIVE, TINHTRANG, TILE
      from VE
      where MACB = p_macb;
end;

/
--------------------------------------------------------
--  DDL for Procedure DOANHTHUTHEOCB
--------------------------------------------------------
set define off;

  CREATE OR REPLACE NONEDITIONABLE PROCEDURE "GON"."DOANHTHUTHEOCB" 
(
    p_macb nvarchar2
)
as
    v_doanhthu number;
begin
    select sum(GIA) into v_doanhthu
    from CHITIETVE
    where p_macb = MACB;
    dbms_output.put_line('doanh thu: ' || v_doanhthu);
    commit;
    exception
        when NO_DATA_FOUND then
            raise_application_error(-2200,'khong tim thay macb',true);
            rollback;
end;

/
--------------------------------------------------------
--  DDL for Procedure DOANHTHUTHEONAM
--------------------------------------------------------
set define off;

  CREATE OR REPLACE NONEDITIONABLE PROCEDURE "GON"."DOANHTHUTHEONAM" 
(
    p_nam number
)
as
    v_doanhthu number;
begin
    select sum(GIA) into v_doanhthu
    from CHITIETVE ct, CHUYENBAY cb
    where cb.MACB = ct.MACB
        and extract(year from cb.THOIGIANKHOIHANH) = p_nam;
    DBMS_OUTPUT.PUT_LINE('doanh thu nam '||p_nam||': '||v_doanhthu);
end;

/
--------------------------------------------------------
--  DDL for Procedure HUYVE
--------------------------------------------------------
set define off;

  CREATE OR REPLACE NONEDITIONABLE PROCEDURE "GON"."HUYVE" (
    p_mave in varchar2
)
as
    ngkh timestamp;
    ex_date exception;
begin
    select THOIGIANKHOIHANH into ngkh from CHITIETVE ct, CHUYENBAY cb
    where ct.MACB = cb.MACB and p_mave = ct.MAVE;

    if systimestamp > ngkh then
        raise ex_date;
    else
        delete from CHITIETVE where p_mave = MAVE;

        update ve
        set TINHTRANG = 0
        where p_mave = MAVE;
        commit;
    end if;

exception
    when ex_date then
        raise_application_error(-20000, 'chuyen bay da khoi hanh', true);
        rollback;
    when NO_DATA_FOUND then
        raise_application_error(-20000, 've khong ton tai', true);
        rollback;
    when others then
        raise_application_error(-20000, 'khong xoa duoc', true);
        rollback;
end;

/
--------------------------------------------------------
--  DDL for Procedure INSERT_KHUYEN_MAI
--------------------------------------------------------
set define off;

  CREATE OR REPLACE NONEDITIONABLE PROCEDURE "GON"."INSERT_KHUYEN_MAI" (p_tenkm varchar2,
                                   p_mota nvarchar2,
                                   p_giamgia binary_double,
                                   p_sl number,
                                   p_hansd date,
                                   p_tinhtrang number)
as
    v_makm nvarchar2(10);
    n pls_integer;
begin
    n := dbms_random.value(1, 100);
    v_makm := p_tenkm || n;
    DBMS_OUTPUT.put_line(v_makm);
    insert into KHUYENMAI(MAKM, TENKM, mota, giamgia, soluongdiscount, hansd, tinhtrang)
    values (v_makm, p_tenkm, p_mota, p_giamgia, p_sl, p_hansd, p_tinhtrang);
    commit;
exception
    when others then
        DBMS_OUTPUT.put_line('loi gi do');
        rollback;
end;

/
--------------------------------------------------------
--  DDL for Procedure KHACHHANGPHOTHONG
--------------------------------------------------------
set define off;

  CREATE OR REPLACE NONEDITIONABLE PROCEDURE "GON"."KHACHHANGPHOTHONG" (mycursor OUT SYS_REFCURSOR)
as
begin
    open mycursor for
    select ct.MACB, kh.TENKH
    from CHITIETVE ct, KHACHHANG kh, VE
    where ct.MAKHACHHANG = kh.MAKHACHHANG and ve.MAVE = ct.MAVE and ve.LOAIVE = 0;
end;

/
--------------------------------------------------------
--  DDL for Procedure KHACHHANGVIP
--------------------------------------------------------
set define off;

  CREATE OR REPLACE NONEDITIONABLE PROCEDURE "GON"."KHACHHANGVIP" (mycursor OUT SYS_REFCURSOR)
as
begin
    open mycursor for
    select ct.MACB, kh.TENKH
    from CHITIETVE ct, KHACHHANG kh, VE
    where ct.MAKHACHHANG = kh.MAKHACHHANG and ve.MAVE = ct.MAVE and ve.LOAIVE = 1;
end;

/
--------------------------------------------------------
--  DDL for Procedure LAYCHUYENBAYBANGID
--------------------------------------------------------
set define off;

  CREATE OR REPLACE NONEDITIONABLE PROCEDURE "GON"."LAYCHUYENBAYBANGID" 
(
    p_macb in varchar2,
    mycursor OUT SYS_REFCURSOR
)
as
begin
    open mycursor for
    SELECT  cb.MaCB,cb.THOIGIANKHOIHANH,cb.ThoiGianBay,cb.GiaVe,hmb.TenHMB
    from ChuyenBay cb, HangMayBay hmb
    where cb.MaHMB = hmb.MaHMB
        and cb.MACB = p_macb;
end;

/
--------------------------------------------------------
--  DDL for Procedure LAYTHONGTINVECUAKH
--------------------------------------------------------
set define off;

  CREATE OR REPLACE NONEDITIONABLE PROCEDURE "GON"."LAYTHONGTINVECUAKH" 
(
    p_makhachhang varchar2,
    mycursor OUT SYS_REFCURSOR
)
as
begin
    open mycursor for
    select ct.MAVE,ct.MACB,ve.LOAIVE,ct.makhachhang,ct.gia
    from ve,CHITIETVE ct
    where ct.MAVE = ve.MAVE and p_makhachhang = ct.makhachhang;
end;

/
--------------------------------------------------------
--  DDL for Procedure SOLUONGVECUAHANGMBTHEONGAY
--------------------------------------------------------
set define off;

  CREATE OR REPLACE NONEDITIONABLE PROCEDURE "GON"."SOLUONGVECUAHANGMBTHEONGAY" 
(
    p_ngay in date,
    p_hmb nvarchar2,
    mycursor OUT SYS_REFCURSOR
)
as
begin
    open mycursor for
    select cb.DIEMKHOIHANH, cb.DIEMDEN, count(ct.MAVE) as sl
    from CHITIETVE ct, CHUYENBAY cb, HANGMAYBAY hmb
    where cb.MAHMB = hmb.MAHMB
      and ct.MACB = cb.MACB
      and hmb.TENHMB = p_hmb
      and trunc(cb.THOIGIANKHOIHANH) = p_ngay
    group by cb.DIEMKHOIHANH, cb.DIEMDEN;
end;

/
--------------------------------------------------------
--  DDL for Procedure SUACHUYENBAY
--------------------------------------------------------
set define off;

  CREATE OR REPLACE NONEDITIONABLE PROCEDURE "GON"."SUACHUYENBAY" 
(
    p_macb varchar2,
    p_mahmb varchar2,
    p_diemkh nvarchar2,
    p_diemden nvarchar2,
    p_ngaykh timestamp,
    p_tgbay number,
    p_giave number
)
as
begin
    update CHUYENBAY
    set MAHMB = p_mahmb,
        DIEMDEN = p_diemden,
        DIEMKHOIHANH = p_diemkh,
        THOIGIANKHOIHANH = p_ngaykh,
        THOIGIANBAY = p_tgbay,
        GIAVE = p_giave
    where MACB = p_macb;
    commit;
    exception
        when others then
            DBMS_OUTPUT.put_line('khong sua chuyen bay duoc');
            raise_application_error(-20343,'khong sua chuyen bay duoc',true);
            rollback ;
end;

/
--------------------------------------------------------
--  DDL for Procedure SUAGIAKHUYENMAI
--------------------------------------------------------
set define off;

  CREATE OR REPLACE NONEDITIONABLE PROCEDURE "GON"."SUAGIAKHUYENMAI" 
(
    p_makm nvarchar2,
    p_giamgia number
)
as
begin
    update KHUYENMAI
    set GIAMGIA = p_giamgia
    where p_makm = MAKM;
    commit;
    exception
        when others then
            raise_application_error(-2000,'loi khong cap nhat',true);
            rollback;
end;

/
--------------------------------------------------------
--  DDL for Procedure THEMCHUYENBAY
--------------------------------------------------------
set define off;

  CREATE OR REPLACE NONEDITIONABLE PROCEDURE "GON"."THEMCHUYENBAY" 
(
    p_mahmb varchar2,
    p_diemkh nvarchar2,
    p_diemden nvarchar2,
    p_ngaykh timestamp,
    p_tgbay number,
    p_giave number
)
as
    v_macb varchar2(10);
    v_dem number := 50;
    v_mave nvarchar2(10);
    v_soghe varchar2(2) := 0;
    ex_notfound exception ;
begin

    insert into CHUYENBAY(mahmb, diemkhoihanh, diemden, thoigiankhoihanh, SOGHEVIPTRONG, SOGHETHUONGTRONG, thoigianbay, giave)
        values(p_mahmb,p_diemkh,p_diemden,p_ngaykh,10, 40,p_tgbay,p_giave);
        
    SELECT TO_CHAR(chuyenbay_sequence.CURRVAL) INTO v_macb FROM DUAL;
    
    while v_dem >= 1
    loop
        if v_dem <= 10 then
             insert into VE(macb, loaive, tinhtrang, tile, SOGHE) values (v_macb,1,0,2,v_soghe);
        end if;
        if v_dem > 10 then
             insert into VE(macb, loaive, tinhtrang, tile,SOGHE) values (v_macb,0,0,1,v_soghe);
        end if;
        v_soghe := v_soghe + 1;
        v_dem := v_dem - 1;
    end loop;
    commit;
    exception
       when others then
            DBMS_OUTPUT.put_line('1 khong them chuyen bay duoc');
            raise_application_error(-20343,'khong them chuyen bay duoc',true);
            rollback ;
end;

/
--------------------------------------------------------
--  DDL for Procedure THONGTINKHACHHANGMOTCHUYENBAY
--------------------------------------------------------
set define off;

  CREATE OR REPLACE NONEDITIONABLE PROCEDURE "GON"."THONGTINKHACHHANGMOTCHUYENBAY" 
(
    p_macb varchar2,
    mycursor OUT SYS_REFCURSOR
)
as
begin
    open mycursor for
    select kh.MAKHACHHANG, kh.CMND, kh.TENKH,kh.EMAIL,kh.TUOI,kh.DIACHI
    from KHACHHANG kh, CHITIETVE ct
    where kh.MAKHACHHANG = ct.MAKHACHHANG and p_macb = ct.MACB;
end;

/
--------------------------------------------------------
--  DDL for Procedure XOACHUYENBAY
--------------------------------------------------------
set define off;

  CREATE OR REPLACE NONEDITIONABLE PROCEDURE "GON"."XOACHUYENBAY" (
  p_MaCB varchar2
)
as
begin
  DELETE FROM CHUYENBAY WHERE MaCB = p_MaCB;

  commit;
  exception
      when others then
          DBMS_OUTPUT.put_line('khong xoa chuyen bay duoc');
          raise_application_error(-20343,'khong xoa chuyen bay duoc',true);
          rollback ;
end;

/
--------------------------------------------------------
--  DDL for Package Body PKG_INSERT
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE PACKAGE BODY "GON"."PKG_INSERT" is
    function fn_thanhtien(p_macb in varchar2,
                          p_loaihk in number,
                          p_km in nvarchar2,
                          p_mave in varchar2)
        return number
    as
        v_giave number;
        v_thanhtien number := 0;
        v_loaive number;
        v_giamgia number := 0;
        v_tile number := 0;
    begin
        select GIAVE into v_giave from CHUYENBAY where MACB = p_macb;
        select LOAIVE into v_loaive from VE where MAVE = p_mave;
        select TILE into v_tile from VE where MAVE = p_mave;
        -- neu la nguoi lon
        if p_loaihk = 1 then
            v_thanhtien := v_giave * v_tile;
            -- neu la tre em
        elsif p_loaihk = 0 then
            v_thanhtien := v_giave * v_tile * 0.8;
        end if;

        if p_km is not null then
            select GIAMGIA into v_giamgia from KHUYENMAI where MAKM = p_km;
            v_thanhtien := v_thanhtien * v_giamgia;
        end if;

        return v_thanhtien;
    end fn_thanhtien;

    procedure insert_ve(p_macb in VARCHAR2,
                        p_sove in number,
                        p_makh in varchar2,
                        p_loaive in number,
                        p_km in nvarchar2)
    as
        p_loaihk number;
        v_dem integer := 0;
        v_thanhtien number;
        type arr_type IS VARRAY (5) OF Ve.mave%
        type;
        mave_arr arr_type := arr_type();
        cursor c1 is select ve.mave
                     from ve
                     where MACB = p_macb
                       and LOAIVE = p_loaive
                       and TINHTRANG = 0;
        counter integer := 0;
    begin
        select LOAIHK into p_loaihk from KHACHHANG where MAKHACHHANG = p_makh;

        for n in c1
            loop
                counter := counter + 1;
                mave_arr.extend;
                mave_arr(counter) := n.MAVE;
                v_thanhtien := fn_thanhtien(p_macb, p_loaihk, p_km, mave_arr(counter));

--                 DBMS_OUTPUT.put_line(v_thanhtien);
                insert into CHITIETVE(MAVE, MACB, MAKHACHHANG, GIA, KHUYENMAI)
                values (mave_arr(counter), p_macb, p_makh, v_thanhtien, p_km);

                update ve
                set tinhtrang = 1
                where mave = mave_arr(counter);
            end loop;
        commit;
    exception
        WHEN DUP_VAL_ON_INDEX THEN
            DBMS_OUTPUT.put_line('trung khoa chinh');
        WHEN OTHERS THEN
            DBMS_OUTPUT.put_line('loi gi do');
            ROLLBACK;
    end insert_ve;
end pkg_insert;

/
--------------------------------------------------------
--  DDL for Package PKG_INSERT
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE PACKAGE "GON"."PKG_INSERT" is
     procedure insert_ve
    (
        p_macb in VARCHAR2,
        p_sove in number,
        p_makh in varchar2,
        p_loaive in number,
        p_km in nvarchar2
    );
     function fn_thanhtien
    (
        p_macb in varchar2,
        p_loaihk in number,
        p_km in nvarchar2,
        p_mave in varchar2
    ) return number;
end pkg_insert;

/
--------------------------------------------------------
--  DDL for Function SOLUONGVECON
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE FUNCTION "GON"."SOLUONGVECON" 
(
    p_loaive in int,
    p_macb in varchar
)
return int
as
    v_sove int;
begin
    select count(*) into v_sove from  Ve where MaCB = p_macb and TinhTrang = 0 and LoaiVe = p_loaive ;
    return v_sove;

    EXCEPTION
    WHEN OTHERS THEN
        raise_application_error(-20001,'An error was encountered - '||SQLCODE||' -ERROR- '||SQLERRM);
end;

/