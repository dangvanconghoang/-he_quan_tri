--PROCEDURE truy van ra list chuyen bay ( 1 chieu )
create or replace procedure danhsachchuyenbay
(
    p_diemkh in VARCHAR,
    p_diemden in VARCHAR,
    p_sove in number,
    p_loaive in number,--1 vip,0 thuong
    p_ngaykh in DATE,
    mycursor OUT SYS_REFCURSOR
)
as
begin
    case p_loaive
        when 1 then
            open mycursor for
            SELECT  cb.MaCB,cb.GioKhoiHanh,cb.ThoiGianBay,cb.GiaVe,hmb.TenHMB
            from ChuyenBay cb,Ve v, HangMayBay hmb
            where cb.MaHMB = hmb.MaHMB AND  cb.MaCB = v.MaCB
              and cb.DiemKhoiHanh = p_diemkh
              and  cb.DIEMDEN = p_diemden
              and cb.NgayKhoiHanh = p_ngaykh
              and v.TinhTrang = 0
              and cb.SOGHEVIPTRONG >= p_sove
              and 1 = v.LoaiVe
            GROUP by cb.MaCB,cb.GioKhoiHanh,cb.ThoiGianBay,cb.GiaVe,hmb.TenHMB;
    when 0 then
            open mycursor for
            SELECT  cb.MaCB,cb.GioKhoiHanh,cb.ThoiGianBay,cb.GiaVe,hmb.TenHMB
            from ChuyenBay cb,Ve v, HangMayBay hmb
            where cb.MaHMB = hmb.MaHMB AND  cb.MaCB = v.MaCB
              and cb.DiemKhoiHanh = p_diemkh
              and  cb.DIEMDEN = p_diemden
              and cb.NgayKhoiHanh = p_ngaykh
              and v.TinhTrang = 0
              and cb.SoGheThuongTrong >= p_sove
              and 0 = v.LoaiVe
            GROUP by cb.MaCB,cb.GioKhoiHanh,cb.ThoiGianBay,cb.GiaVe,hmb.TenHMB;
     end case;

    exception
    when CASE_NOT_FOUND then
        RAISE_APPLICATION_ERROR(-20001,'ERROR CASE STATEMENT',TRUE);
end;
/



--procedure truy van ra list chuyen bay chuyen ve (khu hoi)
create or replace procedure danhsachchuyenbaykh
(
    p_diemkh in VARCHAR,
    p_diemden in VARCHAR,
    p_hmb in varchar,
    p_sove in number,
    p_loaive in number,--1 vip,0 thuong
    p_ngaykh in DATE,
    mycursor OUT SYS_REFCURSOR
)
as
begin
    case p_loaive
        when 1 then
            open mycursor for
            SELECT  cb.MaCB,cb.GioKhoiHanh,cb.ThoiGianBay,cb.GiaVe,hmb.TenHMB
            from ChuyenBay cb,Ve v, HangMayBay hmb
            where cb.MaHMB = hmb.MaHMB AND  cb.MaCB = v.MaCB
              and cb.DiemKhoiHanh = p_diemkh
              and cb.DIEMDEN = p_diemden
              and cb.NgayKhoiHanh = p_ngaykh
              and cb.DIEMDEN = p_diemden
              and v.TinhTrang = 0
              and cb.SoGheVipTrong >= p_sove
              and 1 = v.LoaiVe
            GROUP by cb.MaCB,cb.GioKhoiHanh,cb.ThoiGianBay,cb.GiaVe,hmb.TenHMB;
        when 0 then
            open mycursor for
            SELECT  cb.MaCB,cb.GioKhoiHanh,cb.ThoiGianBay,cb.GiaVe,hmb.TenHMB
            from ChuyenBay cb,Ve v, HangMayBay hmb
            where cb.MaHMB = hmb.MaHMB AND  cb.MaCB = v.MaCB
              and cb.DiemKhoiHanh = p_diemkh
              and cb.DIEMDEN = p_diemden
              and cb.NgayKhoiHanh = p_ngaykh
              and cb.DIEMDEN = p_diemden
              and v.TinhTrang = 0
              and cb.SOGHETHUONGTRONG >= p_sove
              and 0 = v.LoaiVe
            GROUP by cb.MaCB,cb.GioKhoiHanh,cb.ThoiGianBay,cb.GiaVe,hmb.TenHMB;
        end case;
    exception
    when CASE_NOT_FOUND then
        RAISE_APPLICATION_ERROR(-20001,'ERROR CASE STATEMENT',TRUE);
end;
/



--package de insert khi mua ve thanh cong
create or replace package pkg_insert is
   procedure insert_ve
    (
        p_macb in VARCHAR,
        p_sove in number,
        p_tenhk in VARCHAR,
        p_cmnd in varchar,
        p_sdt in varchar,
        p_email in varchar,
        p_loaihk in int,
        p_diachi in varchar,
        p_km in number
    );
    function fn_thanhtien
    (
        p_macb in varchar2,
        p_loaihk in number,
        p_km in number,
        p_mave in varchar2
    ) return number;
end pkg_insert;
/



create or replace package body pkg_insert is
    function fn_thanhtien
    (
        p_macb in varchar2,
        p_loaihk in number,
        p_km in number,
        p_mave in varchar2
    )
    return number
    as
        v_giave number;
        v_thanhtien number:=0;
        v_loaive number;
        v_giamgia number := 0;
    begin
        select GIAVE into v_giave  from CHUYENBAY where MACB = p_macb;
        select LOAIVE into v_loaive from VE where MAVE = p_mave;

        -- neu la nguoi lon
        if p_loaihk = 1 then
            -- neu la vip
            if v_loaive = 1 then
                v_thanhtien := v_giave*2;
             -- neu la thuong
            elsif  v_loaive = 0 then
                v_thanhtien := v_giave;
            end if;
         -- neu la tre em
        elsif p_loaihk = 0 then
             -- neu la vip
            if v_loaive = 1 then
                v_thanhtien := v_giave*2*0.8;
             -- neu la thuong
            elsif  v_loaive = 0 then
                v_thanhtien := v_giave*0.8;
            end if;
        end if;

        if p_km is not null then
            select GIAMGIA into v_giamgia from KHUYENMAI where MAKM = p_km;
            v_thanhtien := v_thanhtien*v_giamgia;
        end if;

        return v_thanhtien;
    end fn_thanhtien;

    procedure insert_ve
    (
        p_macb in VARCHAR,
        p_sove in number,
        p_tenhk in VARCHAR,
        p_cmnd in varchar,
        p_sdt in varchar,
        p_email in varchar,
        p_loaihk in int,
        p_diachi in varchar,
        p_km in number
    )
    as
        v_dem integer :=0;
--         v_mave varchar(10);
        v_thanhtien number;
        type arr_type IS VARRAY(5) OF Ve.mave%type;
        mave_arr arr_type := arr_type();
        cursor c1 is select ve.mave from ve;
        counter integer :=0;
    begin
        for n in c1 loop
            counter := counter + 1;
            mave_arr.extend;
            mave_arr(counter) := n.MAVE;
            v_thanhtien := fn_thanhtien(p_macb,p_loaihk,p_km,mave_arr(counter));
            insert into CHITIETVE(MAVE,MACB,TENHK,CMND,SDT,EMAIL,LOAIHK,GIA,DIACHI,KHUYENMAI)
                    values(mave_arr(counter), p_macb,p_tenhk,p_cmnd,p_cmnd,p_email,p_loaihk,v_thanhtien,p_diachi,p_km);
        end loop;
        commit;
        exception
            WHEN OTHERS THEN
            ROLLBACK;
    end insert_ve;
end pkg_insert;
/



-- function tra ve so luong ve con cua mot chuyen bay 
create or replace function soluongvecon
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

