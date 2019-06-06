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
            SELECT  cb.MaCB,cb.GioKhoiHanh,cb.ThoiGianBay,cb.GiaVe,hmb.TenHMB,v.MaVe
            from ChuyenBay cb,Ve v, HangMayBay hmb
            where cb.MaHMB = hmb.MaHMB AND  cb.MaCB = v.MaCB and cb.DiemKhoiHanh = p_diemkh and cb.DIEMDEN = p_diemden and cb.NgayKhoiHanh = p_ngaykh
            and v.TinhTrang = 0 and cb.SoGheVipTrong >= p_sove and 1 = v.LoaiVe;
    when 0 then
            open mycursor for
            SELECT  cb.MaCB,cb.GioKhoiHanh,cb.ThoiGianBay,cb.GiaVe,hmb.TenHMB,v.MaVe
            from ChuyenBay cb,Ve v, HangMayBay hmb
            where cb.MaHMB = hmb.MaHMB AND  cb.MaCB = v.MaCB and cb.DiemKhoiHanh = p_diemkh and  cb.DIEMDEN = p_diemden and cb.NgayKhoiHanh = p_ngaykh
            and v.TinhTrang = 0 and cb.SoGheThuongTrong >= p_sove and 0 = v.LoaiVe;
        end case;
end;

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
            SELECT  cb.MaCB,cb.GioKhoiHanh,cb.ThoiGianBay,cb.GiaVe,hmb.TenHMB,v.MaVe
            from ChuyenBay cb,Ve v, HangMayBay hmb
            where cb.MaHMB = hmb.MaHMB AND  cb.MaCB = v.MaCB
              and cb.DiemKhoiHanh = p_diemkh
              and cb.DIEMDEN = p_diemden
              and cb.NgayKhoiHanh = p_ngaykh
              and cb.DIEMDEN = p_diemden
              and v.TinhTrang = 0
              and cb.SoGheVipTrong >= p_sove
              and 1 = v.LoaiVe;
        when 0 then
            open mycursor for
            SELECT  cb.MaCB,cb.GioKhoiHanh,cb.ThoiGianBay,cb.GiaVe,hmb.TenHMB,v.MaVe
            from ChuyenBay cb,Ve v, HangMayBay hmb
            where cb.MaHMB = hmb.MaHMB AND  cb.MaCB = v.MaCB
              and cb.DiemKhoiHanh = p_diemkh
              and cb.DIEMDEN = p_diemden
              and cb.NgayKhoiHanh = p_ngaykh
              and cb.DIEMDEN = p_diemden
              and v.TinhTrang = 0
              and cb.SOGHETHUONGTRONG >= p_sove
              and 0 = v.LoaiVe;
        end case;
end;

--package de insert khi mua ve thanh cong
create or replace package pkg_insert is
   procedure insert_ve
    (
        p_macb in VARCHAR,
        p_sove in number,
        p_loaive in number,
        p_tenhk in VARCHAR,
        p_cmnd in varchar,
        p_sdt in varchar,
        p_email in varchar,
        p_loaihk in int,
        p_gia in NUMERIC,
        p_diachi in varchar
    );
end pkg_insert;

create or replace package body pkg_insert is
    procedure insert_ve
    (
        p_macb in VARCHAR,
        p_sove in number,
        p_loaive in number,
        p_tenhk in VARCHAR,
        p_cmnd in varchar,
        p_sdt in varchar,
        p_email in varchar,
        p_loaihk in int,
        p_gia in NUMERIC,
        p_diachi in varchar
    )
    as
        v_dem integer :=0;
        v_mave varchar(10);
        type arr_type IS VARRAY(5) OF Ve.mave%type;
        mave_arr arr_type := arr_type();
        cursor c1 is select ve.mave from ve;
        counter integer :=0;
    begin
        for n in c1 loop
            counter := counter + 1;
            mave_arr.extend;
            mave_arr(counter) := n.MAVE;
            insert into CHITIETCHUYENBAY(MAVE,MACB,TENHK,CMND,SDT,EMAIL,LOAIHK,GIA,DIACHI)
                    values(mave_arr(counter), p_macb,p_tenhk,p_cmnd,p_cmnd,p_email,p_loaihk,p_gia,p_diachi);
        end loop;
    end insert_ve;
end pkg_insert;

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

