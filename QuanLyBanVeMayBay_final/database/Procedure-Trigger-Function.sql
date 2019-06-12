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

create trigger countVe on Ve
for insert, update
as
begin
	declare @SoVeThuongTrong int
	declare @SoVeVipTrong int
	declare @MaCB varchar(10)
	set @MaCB=(select MaVe from Ve,ChuyenBay where Ve.MaCB=ChuyenBay.MaCB)
	set @SoVeThuongTrong= (select count(MaVe) from Ve where TinhTrang=0 and LoaiVe=0 and MaCB=@MaCB)
	set @SoVeVipTrong=(select count(MaVe) from Ve where TinhTrang=0 and LoaiVe=0 and MaCB=@MaCB)
end
drop trigger countVe

create proc addChuyenBay(@maCB varchar(10),@maHMB varchar(10),@diemKH nvarchar(50),@diemDen nvarchar(50),@sbDi varchar(10),@sbDen varchar(10),@ngayKH date,@tgBay int,@gioKH time,@giaVe money)
as
begin
	insert into ChuyenBay values(@maCB,@maHMB,@diemKH,@diemDen,@sbDi,@sbDen,10,40,@ngayKH,@tgBay,@gioKH,@giaVe)
	insert into Ve values (CONCAT(@maCB,'01'),@maCB,1,0,'1A')
	insert into Ve values (CONCAT(@maCB,'02'),@maCB,1,0,'1B')
	insert into Ve values (CONCAT(@maCB,'03'),@maCB,1,0,'1C')
	insert into Ve values (CONCAT(@maCB,'04'),@maCB,1,0,'1D')
	insert into Ve values (CONCAT(@maCB,'05'),@maCB,1,0,'1E')
	insert into Ve values (CONCAT(@maCB,'06'),@maCB,1,0,'2A')
	insert into Ve values (CONCAT(@maCB,'07'),@maCB,1,0,'2B')
	insert into Ve values (CONCAT(@maCB,'08'),@maCB,1,0,'2C')
	insert into Ve values (CONCAT(@maCB,'09'),@maCB,1,0,'2D')
	insert into Ve values (CONCAT(@maCB,'10'),@maCB,1,0,'2E')
	insert into Ve values (CONCAT(@maCB,'11'),@maCB,0,0,'3A')
	insert into Ve values (CONCAT(@maCB,'12'),@maCB,0,0,'3B')
	insert into Ve values (CONCAT(@maCB,'13'),@maCB,0,0,'3C')
	insert into Ve values (CONCAT(@maCB,'14'),@maCB,0,0,'3D')
	insert into Ve values (CONCAT(@maCB,'15'),@maCB,0,0,'3E')
	insert into Ve values (CONCAT(@maCB,'16'),@maCB,0,0,'4A')
	insert into Ve values (CONCAT(@maCB,'17'),@maCB,0,0,'4B')
	insert into Ve values (CONCAT(@maCB,'18'),@maCB,0,0,'4C')
	insert into Ve values (CONCAT(@maCB,'19'),@maCB,0,0,'4D')
	insert into Ve values (CONCAT(@maCB,'20'),@maCB,0,0,'4E')
	insert into Ve values (CONCAT(@maCB,'21'),@maCB,0,0,'5A')
	insert into Ve values (CONCAT(@maCB,'22'),@maCB,0,0,'5B')
	insert into Ve values (CONCAT(@maCB,'23'),@maCB,0,0,'5C')
	insert into Ve values (CONCAT(@maCB,'24'),@maCB,0,0,'5D')
	insert into Ve values (CONCAT(@maCB,'25'),@maCB,0,0,'5E')
	insert into Ve values (CONCAT(@maCB,'26'),@maCB,0,0,'6A')
	insert into Ve values (CONCAT(@maCB,'27'),@maCB,0,0,'6B')
	insert into Ve values (CONCAT(@maCB,'28'),@maCB,0,0,'6C')
	insert into Ve values (CONCAT(@maCB,'29'),@maCB,0,0,'6D')
	insert into Ve values (CONCAT(@maCB,'30'),@maCB,0,0,'6E')
	insert into Ve values (CONCAT(@maCB,'31'),@maCB,0,0,'7A')
	insert into Ve values (CONCAT(@maCB,'32'),@maCB,0,0,'7B')
	insert into Ve values (CONCAT(@maCB,'33'),@maCB,0,0,'7C')
	insert into Ve values (CONCAT(@maCB,'34'),@maCB,0,0,'7D')
	insert into Ve values (CONCAT(@maCB,'35'),@maCB,0,0,'7E')
	insert into Ve values (CONCAT(@maCB,'36'),@maCB,0,0,'8A')
	insert into Ve values (CONCAT(@maCB,'37'),@maCB,0,0,'8B')
	insert into Ve values (CONCAT(@maCB,'38'),@maCB,0,0,'8C')
	insert into Ve values (CONCAT(@maCB,'39'),@maCB,0,0,'8D')
	insert into Ve values (CONCAT(@maCB,'40'),@maCB,0,0,'8E')
	insert into Ve values (CONCAT(@maCB,'41'),@maCB,0,0,'9A')
	insert into Ve values (CONCAT(@maCB,'42'),@maCB,0,0,'9B')
	insert into Ve values (CONCAT(@maCB,'43'),@maCB,0,0,'9C')
	insert into Ve values (CONCAT(@maCB,'44'),@maCB,0,0,'9D')
	insert into Ve values (CONCAT(@maCB,'45'),@maCB,0,0,'9E')
	insert into Ve values (CONCAT(@maCB,'46'),@maCB,0,0,'10A')
	insert into Ve values (CONCAT(@maCB,'47'),@maCB,0,0,'10B')
	insert into Ve values (CONCAT(@maCB,'48'),@maCB,0,0,'10C')
	insert into Ve values (CONCAT(@maCB,'49'),@maCB,0,0,'10D')
	insert into Ve values (CONCAT(@maCB,'50'),@maCB,0,0,'10E')
end
drop proc addChuyenBay
exec addChuyenBay 'VA03','VA',N'Hà Nội',N'TP Hồ Chí Minh','NB','TSN','01-01-2019',2,'20:00',2000000

create proc xoaCB (@maCB varchar(10))
as
begin
	delete from ChiTietChuyenBay where MaCB=@maCB
	delete from Ve where MaCB=@maCB
	delete from ChuyenBay where MaCB=@maCB
end
drop proc xoaCB
exec xoaCB 'BA02'
