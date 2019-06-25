
	-- trigger GHE_CB
	-- original query: 
	SELECT CB.SOGHEVIPTRONG FROM CHUYENBAY CB, VE 
	WHERE VE.MAVE=:NEW.MAVE AND VE.MACB=CB.MACB;

	-- trigger GHE_CB
	-- optimized query: 
	select SOGHEVIPTRONG from(
		(select SOGHEVIPTRONG, macb from CHUYENBAY CB)
		inner join
		(select MACB, MAVE from VE where MAVE=:new.MAVE)
		on cb.MACB = ve.MACB
	)


	-- procedure danhsachvecua1cb
	-- original query: 
	select ct.MAVE,ct.MACB,ct.CMND, ct.GIA, kh.TENKH, ve.LOAIVE ,ve.SOGHE
   from CHITIETVE ct, KHACHHANG kh, VE
   where ct.MAVE = ve.MAVE and kh.CMND = ct.CMND
      and ct.MACB = p_macb;

	-- procedure danhsachvecua1cb
	-- optimized query: 
	select ct.MAVE,ct.MACB,ct.CMND, ct.GIA, kh.TENKH, ve.LOAIVE ,ve.SOGHE
   from CHITIETVE ct, KHACHHANG kh, VE
   where ct.MAVE = ve.MAVE and kh.CMND = ct.CMND and ct.MACB = p_macb;
   select CTV.MAVE, CTV.macb, CTV.makh, gia, TENKH, LOAIVE, SOGHE from(
   	(select MAVE, makh, macb, gia from CHITIETVE CTV where macb = p_macb)
   	inner join
   	(select TENKH, makh from KHACHHANG KH)
   	on CTV.makh = kh.makh
   	inner join
   	(select LOAIVE, SOGHE, MAVE from VE)
   	on CTV.MAVE = VE.MAVE
   )


	-- procedure soluongvecuahangmbtheongay
	-- original query: 
	select cb.DIEMKHOIHANH, cb.DIEMDEN, count(ct.MAVE) as sl
   from CHITIETVE ct, CHUYENBAY cb, HANGMAYBAY hmb
   where cb.MAHMB = hmb.MAHMB
     and ct.MACB = cb.MACB
     and hmb.TENHMB = 'VietnamAirline'
     and cb.NGAYKHOIHANH = p_ngay
	group by cb.DIEMKHOIHANH, cb.DIEMDEN;

	-- procedure soluongvecuahangmbtheongay
	-- optimized query: 
   select CB.DIEMKHOIHANH, cb.DIEMDEN, count(CTV.MAVE) as sl from(
   	(select CTV.MACB, CTV.MAVE from CHITIETVE CTV)
   	inner join
   	(	select CB.MACB, CB.MAHMB, CN.DIEMKHOIHANH, CB.DIEMDEN
   		from CHUYENBAY CB where CB.NGAYKHOIHANH = p_ngay)
   	on CTV.MACB = CB.MACB
   	inner join
   	(select HMB.MAHMB from HANGMAYBAY HMB where HMB.TENHMB = "VietnamAirline")
   	on CB.MAHMB = HMB.MAHMB
   )
   group by cb.DIEMKHOIHANH, cb.DIEMDEN;


