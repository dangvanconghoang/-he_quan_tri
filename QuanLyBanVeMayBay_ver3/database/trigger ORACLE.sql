-- trigger de cap nhat tinh trang va so luong ve
create or replace trigger trg_tinhtrangve
after insert or delete
    on CHITIETCHUYENBAY
    for each row
begin
    if inserting then
        UPDATE Ve
        SET TinhTrang = 1
        where  Ve.MaVe = :new.MaVe;

        UPDATE ChuyenBay
        set SoGheVipTrong = SOLUONGVECON(1,:new.MACB),
            SoGheThuongTrong = SOLUONGVECON(0,:new.MACB)
        where MaCB = :new.MACB;
    end if;
    if deleting then
        UPDATE Ve
        SET TinhTrang = 0
        where  Ve.MaVe = :old.MaVe;

        UPDATE ChuyenBay
        set SoGheVipTrong = SOLUONGVECON(1,:old.MACB),
            SoGheThuongTrong = SOLUONGVECON(0,:old.MACB)
        where MaCB = :old.MACB;
    end if;
end;