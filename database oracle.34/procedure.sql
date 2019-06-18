create procedure capnhattienve
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
            SELECT  cb.MaCB,cb.NGAYKHOIHANH,cb.ThoiGianBay,cb.GiaVe,hmb.TenHMB
            from ChuyenBay cb,Ve v, HangMayBay hmb
            where cb.MaHMB = hmb.MaHMB AND  cb.MaCB = v.MaCB
              and cb.DiemKhoiHanh = p_diemkh
              and  cb.DIEMDEN = p_diemden
              and cb.NgayKhoiHanh = p_ngaykh
              and v.TinhTrang = 0
              and cb.SOGHEVIPTRONG >= p_sove
              and 1 = v.LoaiVe
            GROUP by cb.MaCB,cb.NGAYKHOIHANH,cb.ThoiGianBay,cb.GiaVe,hmb.TenHMB;
    when 0 then
            open mycursor for
            SELECT  cb.MaCB,cb.NGAYKHOIHANH,cb.ThoiGianBay,cb.GiaVe,hmb.TenHMB
            from ChuyenBay cb,Ve v, HangMayBay hmb
            where cb.MaHMB = hmb.MaHMB AND  cb.MaCB = v.MaCB
              and cb.DiemKhoiHanh = p_diemkh
              and  cb.DIEMDEN = p_diemden
              and cb.NgayKhoiHanh = p_ngaykh
              and v.TinhTrang = 0
              and cb.SoGheThuongTrong >= p_sove
              and 0 = v.LoaiVe
            GROUP by cb.MaCB,cb.NGAYKHOIHANH,cb.ThoiGianBay,cb.GiaVe,hmb.TenHMB;
     end case;

    exception
    when CASE_NOT_FOUND then
        RAISE_APPLICATION_ERROR(-20001,'ERROR CASE STATEMENT',TRUE);
end;
/

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
            SELECT  cb.MaCB,cb.NGAYKHOIHANH,cb.ThoiGianBay,cb.GiaVe,hmb.TenHMB
            from ChuyenBay cb,Ve v, HangMayBay hmb
            where cb.MaHMB = hmb.MaHMB AND  cb.MaCB = v.MaCB
              and cb.DiemKhoiHanh = p_diemkh
              and cb.DIEMDEN = p_diemden
              and cb.NgayKhoiHanh = p_ngaykh
              and cb.DIEMDEN = p_diemden
              and v.TinhTrang = 0
              and cb.SoGheVipTrong >= p_sove
              and 1 = v.LoaiVe
            GROUP by cb.MaCB,cb.NGAYKHOIHANH,cb.ThoiGianBay,cb.GiaVe,hmb.TenHMB;
        when 0 then
            open mycursor for
            SELECT  cb.MaCB,cb.NGAYKHOIHANH,cb.ThoiGianBay,cb.GiaVe,hmb.TenHMB
            from ChuyenBay cb,Ve v, HangMayBay hmb
            where cb.MaHMB = hmb.MaHMB AND  cb.MaCB = v.MaCB
              and cb.DiemKhoiHanh = p_diemkh
              and cb.DIEMDEN = p_diemden
              and cb.NgayKhoiHanh = p_ngaykh
              and cb.DIEMDEN = p_diemden
              and v.TinhTrang = 0
              and cb.SOGHETHUONGTRONG >= p_sove
              and 0 = v.LoaiVe
            GROUP by cb.MaCB,cb.NGAYKHOIHANH,cb.ThoiGianBay,cb.GiaVe,hmb.TenHMB;
        end case;
    exception
    when CASE_NOT_FOUND then
        RAISE_APPLICATION_ERROR(-20001,'ERROR CASE STATEMENT',TRUE);
end;
/

create procedure danhsachvecua1cb
(
    p_macb nvarchar2,
    mycursor out sys_refcursor
)
as
begin
    open mycursor for
    select ct.MAVE,ct.MACB,ct.CMND, ct.GIA, kh.TENKH, ve.LOAIVE ,ve.SOGHE
    from CHITIETVE ct, KHACHHANG kh, VE
    where ct.MAVE = ve.MAVE and kh.CMND = ct.CMND
        and ct.MACB = p_macb;
end;
/

create procedure doanhthutheocb
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

create or replace procedure doanhthutheonam
(
    p_nam number
)
as
    v_doanhthu number;
begin
    select sum(GIA) into v_doanhthu
    from CHITIETVE ct, CHUYENBAY cb
    where cb.MACB = ct.MACB
        and extract(year from cb.NGAYKHOIHANH) = p_nam;
    DBMS_OUTPUT.PUT_LINE('doanh thu nam '||p_nam||': '||v_doanhthu);
end;
/

create or replace procedure huyve(
    p_mave in varchar2
)
as
    ngkh timestamp;
    ex_date exception;
begin
    select NGAYKHOIHANH into ngkh from CHITIETVE ct, CHUYENBAY cb
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

create procedure insert_khuyen_mai(p_tenkm varchar2,
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

create procedure khachhangphothong(mycursor OUT SYS_REFCURSOR)
as
begin
    open mycursor for
    select ct.MACB, kh.TENKH
    from CHITIETVE ct, KHACHHANG kh, VE
    where ct.CMND = kh.CMND and ve.MAVE = ct.MAVE and ve.LOAIVE = 0;
end;
/

create procedure khachhangVip(mycursor OUT SYS_REFCURSOR)
as
begin
    open mycursor for
    select ct.MACB, kh.TENKH
    from CHITIETVE ct, KHACHHANG kh, VE
    where ct.CMND = kh.CMND and ve.MAVE = ct.MAVE and ve.LOAIVE = 1;
end;
/

create or replace procedure laychuyenbaybangid
(
    p_macb in varchar2,
    mycursor OUT SYS_REFCURSOR
)
as
begin
    open mycursor for
    SELECT  cb.MaCB,cb.NGAYKHOIHANH,cb.ThoiGianBay,cb.GiaVe,hmb.TenHMB
    from ChuyenBay cb, HangMayBay hmb
    where cb.MaHMB = hmb.MaHMB
        and cb.MACB = p_macb;
end;
/

create or replace procedure laythongtinvecuakh
(
    p_cmnd varchar2,
    mycursor OUT SYS_REFCURSOR
)
as
begin
    open mycursor for
    select ct.MAVE,ct.MACB,ve.LOAIVE,ct.CMND,ct.gia
    from ve,CHITIETVE ct
    where ct.MAVE = ve.MAVE and p_cmnd = ct.CMND;
end;
/

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
/

create or replace procedure soluongvecuahangmbtheongay
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
      and hmb.TENHMB = 'VietnamAirline'
      and cb.NGAYKHOIHANH = p_ngay
    group by cb.DIEMKHOIHANH, cb.DIEMDEN;
end;
/

create or replace procedure suachuyenbay
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
        NGAYKHOIHANH = p_ngaykh,
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

create or replace procedure suagiakhuyenmai
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

create or replace procedure themchuyenbay
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
    v_dem number := 50;
    v_mave nvarchar2(10);
    v_soghe varchar2(2) := 0;
    ex_notfound exception ;
begin

    insert into CHUYENBAY(macb, mahmb, diemkhoihanh, diemden, ngaykhoihanh, thoigianbay, giave)
        values(p_macb,p_mahmb,p_diemkh,p_diemden,p_ngaykh,p_tgbay,p_giave);
    while v_dem >= 1
    loop
        v_mave := p_macb||v_dem;
        if v_dem <= 10 then
             insert into VE(mave, macb, loaive, tinhtrang, tile,SOGHE) values (v_mave, p_macb,1,0,2,v_soghe);
        end if;
        if v_dem > 10 then
             insert into VE(mave, macb, loaive, tinhtrang, tile,SOGHE) values (v_mave, p_macb,0,0,1,v_soghe);
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

create or replace procedure thongtinkhachhangmotchuyenbay
(
    p_macb varchar2,
    mycursor OUT SYS_REFCURSOR
)
as
begin
    open mycursor for
    select kh.CMND, kh.TENKH,kh.EMAIL,kh.TUOI,kh.DIACHI
    from KHACHHANG kh, CHITIETVE ct
    where kh.CMND = ct.CMND and p_macb = ct.MACB;
end;
/

create or replace procedure suachuyenbay
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
        NGAYKHOIHANH = p_ngaykh,
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

create or replace package pkg_insert is
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

create or replace package body pkg_insert is
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
        select LOAIHK into p_loaihk from KHACHHANG where CMND = p_makh;

        for n in c1
            loop
                counter := counter + 1;
                mave_arr.extend;
                mave_arr(counter) := n.MAVE;
                v_thanhtien := fn_thanhtien(p_macb, p_loaihk, p_km, mave_arr(counter));

--                 DBMS_OUTPUT.put_line(v_thanhtien);
                insert into CHITIETVE(MAVE, MACB, CMND, GIA, KHUYENMAI)
                values (mave_arr(counter), p_macb, p_makh, v_thanhtien, p_km);
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

