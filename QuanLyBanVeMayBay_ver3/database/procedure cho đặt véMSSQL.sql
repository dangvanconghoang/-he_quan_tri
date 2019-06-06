alter FUNCTION kiemtraconvekhong(
    @loaive INT,
    @sove INT,
    @macb VARCHAR(50)
)
RETURNS INT
AS
BEGIN

    declare @sv int
    set @sv = (select count(*) from Ve where MaCB = @macb and TinhTrang = 0 and LoaiVe = @loaive )
    
    if(@sove <= @sv) return 1
    RETURN 0
END;
go
SELECT dbo.kiemtraconvekhong(1,5,'1');

GO
alter FUNCTION soluongvecon(
    @loaive INT,
    @macb VARCHAR(50)
)
RETURNS INT
AS
BEGIN

    declare @sv int
    set @sv = (select count(*) from Ve where MaCB = @macb and TinhTrang = 0 and LoaiVe = @loaive )
    RETURN @sv
END;
go
SELECT dbo.soluongvecon(0,3)

go
alter PROCEDURE danhsachchuyenbay
    @diemkh VARCHAR(50) OUT,
    @diemden VARCHAR(50) OUT,
    @sove int,
    @loaive int,--1 vip,0 thuong
    @ngaykh DATE
AS
BEGIN
  
    if(@loaive = 1 )
        BEGIN
            SELECT  cb.MaCB,cb.GioKhoiHanh,cb.ThoiGianBay,cb.GiaVe,hmb.TenHMB,v.MaVe
            from ChuyenBay cb,Ve v, HangMayBay hmb
            where cb.MaHMB = hmb.MaHMB AND  cb.MaCB = v.MaCB and DiemKhoiHanh = @diemkh and NgayKhoiHanh = @ngaykh 
            and v.TinhTrang = 0 and cb.SoGheVipTrong >= @sove and @loaive = v.LoaiVe
        END
    else 
        BEGIN
            SELECT  cb.MaCB,cb.GioKhoiHanh,cb.ThoiGianBay,cb.GiaVe,hmb.TenHMB,v.MaVe
            from ChuyenBay cb,Ve v, HangMayBay hmb
            where cb.MaHMB = hmb.MaHMB AND  cb.MaCB = v.MaCB and DiemKhoiHanh = @diemkh and NgayKhoiHanh = @ngaykh 
            and v.TinhTrang = 0 and cb.SoGheThuongTrong >= @sove and @loaive = v.LoaiVe
        END
END;
             
-- exec danhsachchuyenbay 'Ha Noi','Ha Noi',1,1,'2019-05-30'

go
alter PROC insertVe
    @macb VARCHAR(50),
    @sove INT,
    @loaive INT,
    @tenhk VARCHAR(50),
    @loaihk int,
    @gia NUMERIC
AS
BEGIN
    CREATE TABLE #table_temp (
    mave VARCHAR(50),
    macb VARCHAR(50),
    id int IDENTITY(1, 1)
    );
    DECLARE @dem int = 1;
    DECLARE @mave VARCHAR(50);

    insert into #table_temp
    select MaVe,MaCB
    from Ve
    WHERE @macb = MaCB and TinhTrang = 0 and LoaiVe = @loaive

    WHILE @dem <= @sove
        BEGIN
            set @mave = (select mave from #table_temp where @dem = id)
            INSERT into ChiTietChuyenBay(MaVe,MaCB,TenHK,LoaiHK,Gia) VALUES(@mave, @macb,@tenhk,@loaihk,@gia)
            SET @dem = @dem + 1
            print 'day la ma ve'+ @mave
        END
    -- DROP TABLE ##table_temp;
end;
GO
-- exec insertVe 1,0,1

GO
alter TRIGGER trg_tinhtrangve ON ChiTietChuyenBay
AFTER INSERT AS 
BEGIN
	UPDATE Ve
    SET TinhTrang = 1
    from Ve
    join inserted on Ve.MaVe = inserted.MaVe

    DECLARE @macb VARCHAR(50)
    DECLARE cs_tg CURSOR FOR (  SELECT ChuyenBay.MaCB from ChuyenBay 
                                join inserted on ChuyenBay.MaCB = inserted.MaCB)

    OPEN cs_tg 
    FETCH NEXT FROM cs_tg INTO @macb

    WHILE @@FETCH_STATUS = 0
        BEGIN
            UPDATE ChuyenBay
            set SoGheVipTrong = dbo.soluongvecon(1,@macb),
                SoGheThuongTrong = dbo.soluongvecon(0,@macb)
            where MaCB = @macb

            FETCH NEXT FROM cs_tg INTO  @macb
        END
    CLOSE cs_tg;
    DEALLOCATE cs_tg;
END

go
alter PROCEDURE danhsachchuyenbaykh
    @diemkh VARCHAR(50) OUT,
    @diemden VARCHAR(50) OUT,
     @hangmb VARCHAR(50) out,
    @sove int,
    @loaive int,--1 vip,0 thuong
    @ngaykh DATE
AS
BEGIN
    if(@loaive = 1 )
        BEGIN
            SELECT  cb.MaCB,cb.GioKhoiHanh,cb.ThoiGianBay,cb.GiaVe,hmb.TenHMB,v.MaVe
            from ChuyenBay cb,Ve v, HangMayBay hmb
            where   cb.MaHMB = hmb.MaHMB AND  cb.MaCB = v.MaCB 
                and DiemKhoiHanh = @diemkh and NgayKhoiHanh = @ngaykh 
                and cb.SoGheVipTrong >= @sove and @loaive = v.LoaiVe and hmb.TenHMB = @hangmb
                and v.TinhTrang = 0
        END
    else 
        BEGIN
            SELECT  cb.MaCB,cb.GioKhoiHanh,cb.ThoiGianBay,cb.GiaVe,hmb.TenHMB,v.MaVe
            from ChuyenBay cb,Ve v, HangMayBay hmb
            where   cb.MaHMB = hmb.MaHMB AND  cb.MaCB = v.MaCB 
                and DiemKhoiHanh = @diemkh and NgayKhoiHanh = @ngaykh 
                and cb.SoGheThuongTrong >= @sove and @loaive = v.LoaiVe and hmb.TenHMB = @hangmb
                and v.TinhTrang = 0
        END
END;

exec danhsachchuyenbaykh 'ha noi','ha noi','VietnamAirLine',1,1,'2019-05-27';
        
