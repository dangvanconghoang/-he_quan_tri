/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author blues
 */
public class Ve {
    private String MaVe;
    private DanhSachChuyenBay ChuyenBay;
    private LoaiVe LoaiVe;
    private int TinhTrang;
    private float TiLe;
    private String TenTinhTrang;
    private Double Gia;
    
    public String getTenLoaiVe() {
        return LoaiVe.getTenLoaiVe();
    }
    
    public Double getGia() {
        return Gia;
    }

    public void setGia(Double Gia) {
        this.Gia = Gia;
    }
    
    public String getTenTinhTrang() {
        return TenTinhTrang;
    }

    public void setTenTinhTrang(String TenTinhTrang) {
        this.TenTinhTrang = TenTinhTrang;
    }
    
    public Ve() {
        
    }

    public Ve(String MaVe, DanhSachChuyenBay ChuyenBay, LoaiVe LoaiVe, int TinhTrang, float TiLe) {
        this.MaVe = MaVe;
        this.ChuyenBay = ChuyenBay;
        this.LoaiVe = LoaiVe;
        this.TinhTrang = TinhTrang;
        this.TiLe = TiLe;
    }

    public String getMaVe() {
        return MaVe;
    }

    public void setMaVe(String MaVe) {
        this.MaVe = MaVe;
    }

    public DanhSachChuyenBay getChuyenBay() {
        return ChuyenBay;
    }

    public void setChuyenBay(DanhSachChuyenBay ChuyenBay) {
        this.ChuyenBay = ChuyenBay;
    }

    public LoaiVe getLoaiVe() {
        return LoaiVe;
    }

    public void setLoaiVe(LoaiVe LoaiVe) {
        this.LoaiVe = LoaiVe;
    }

    public int getTinhTrang() {
        return TinhTrang;
    }

    public void setTinhTrang(int TinhTrang) {
        this.TinhTrang = TinhTrang;
    }

    public float getTiLe() {
        return TiLe;
    }

    public void setTiLe(float TiLe) {
        this.TiLe = TiLe;
    }
    
    
    
}
