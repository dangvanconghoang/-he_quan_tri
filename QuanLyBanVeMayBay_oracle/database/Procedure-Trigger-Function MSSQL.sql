create proc chartDoanhThu(@nam int, @thang int)
as
begin
	select sum(Gia) as DoanhThu
	from ChiTietChuyenBay,ChuyenBay
	where ChiTietChuyenBay.MaCB=ChuyenBay.MaCB and year(ChuyenBay.NgayKhoiHanh)=@nam and month(ChuyenBay.NgayKhoiHanh)=@thang
end
drop proc chartDoanhThu

exec chartDoanhThu 2019,4

create proc chartTongCB(@nam int, @thang int)
as
begin
	select count(MaCB) as TongCB
	from ChuyenBay
	where year(NgayKhoiHanh)=@nam and month(NgayKhoiHanh)=@thang
end

exec chartTongCB 2019,4

create proc chartTongHKTB(@nam int,@thang int)
as
begin
	declare @tb float
	declare @tong float
	declare @count float
	set @count=(select count(distinct(ChiTietChuyenBay.MaCB)) as TongCB
	from ChuyenBay,ChiTietChuyenBay
	where year(NgayKhoiHanh)=@nam and month(NgayKhoiHanh)=@thang and ChiTietChuyenBay.MaCB=ChuyenBay.MaCB)
	if(@count =0) set @count=1
	set @tong=(select count(MaVe) as TongHKTB
	from ChiTietChuyenBay,ChuyenBay
	where year(NgayKhoiHanh)=@nam and month(NgayKhoiHanh)=@thang and ChiTietChuyenBay.MaCB=ChuyenBay.MaCB)
	set @tb=@tong/@count
	select round(@tb,0) as TongHKTB
end
drop proc chartTongHKTB
exec chartTongHKTB 2019,2

create proc chartTongHK(@nam int,@thang int)
as
begin
	select count(MaVe) as TongHK
	from ChiTietChuyenBay,ChuyenBay
	where ChuyenBay.MaCB=ChiTietChuyenBay.MaCB and year(NgayKhoiHanh)=@nam and month(NgayKhoiHanh)=@thang
end
drop proc chartTongHK
exec chartTongHK 2019,1