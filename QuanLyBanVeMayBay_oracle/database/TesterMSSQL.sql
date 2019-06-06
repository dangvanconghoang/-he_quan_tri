set dateformat dmy
select TenHMB,DiemKhoiHanh,DiemDen,TenSB as SanBayDi,NgayKhoiHanh,ThoiGianBay,GioKhoiHanh
from ChuyenBay,SanBay,HangMayBay
where ChuyenBay.MaHMB=HangMayBay.MaHMB and ChuyenBay.SanBayDi=SanBay.MaSB
and MaCB='VA01'

select TenSB as SanBayDen
from SanBay,ChuyenBay
where ChuyenBay.SanBayDen=SanBay.MaSB and MaCB='VA01'

select TenHK,CMND,SDT,Email,LoaiHK,LoaiVe,Gia from ChiTietChuyenBay,Ve where ChiTietChuyenBay.MaVe='VA0101' and Ve.MaVe=ChiTietChuyenBay.MaVe

update ChiTietChuyenBay set TenHK=N'Nguyễn Văn A', CMND='0000000', SDT='00000000', Email='asdasdasd', LoaiHK=0, Gia=100000.0 where MaVe='VA0101'


update Ve set TinhTrang=1 where MaVe='VA0101'


insert into Ve values('VA0102','VA01','false','true')
insert into ChiTietChuyenBay values('VA0102','VA01',N'Nguyễn Văn B','123456','12345678','NVB@gmail.com',0,2000000)


delete from ChiTietChuyenBay where MaVe='VA0102'