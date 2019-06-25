insert into SanBay values ('TSN',N'Tân Sơn Nhất')
insert into SanBay values ('NB',N'Nội Bài')
insert into HangMayBay values('VA',N'Vietnam Airlines')
insert into ChuyenBay(MaCB,MaHMB,SanBayDi,SanBayDen) values('VA01','VA','TSN','NB')
insert into Ve(MaVe) values ('VA0101')
insert into ChiTietChuyenBay(MaVe,MaCB,TenHK,Gia) values ('VA0101','VA01',N'Nguyễn Văn A',100000)

select MaVe,MaCB,TenHK,Gia from ChiTietChuyenBay