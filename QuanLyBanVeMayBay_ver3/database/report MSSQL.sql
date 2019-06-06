SELECT MONTH(cb.NgayKhoiHanh) as thang, ve.LoaiVe, SUM(ct.Gia) as tongtien 
from ChiTietChuyenBay ct, ChuyenBay cb,Ve
WHERE ct.MaCB = cb.MaCB and Ve.MaVe = ct.MaVe and ve.LoaiVe = 1
GROUP by MONTH(cb.NgayKhoiHanh), ve.LoaiVe
ORDER by thang

select loaihk, count(*) as sl 
from ChiTietChuyenBay
GROUP by LoaiHK

select MONTH(cb.NgayKhoiHanh) as thang, sum(ct.Gia ) as doanhthu,sum(cb.GiaVe) as tienve,-sum(cb.GiaVe)+sum(ct.Gia ) as ln, COUNT(MaVe) as slve
from ChuyenBay cb, ChiTietChuyenBay ct
where cb.MaCB = ct.MaCB
GROUP by MONTH(cb.NgayKhoiHanh)
