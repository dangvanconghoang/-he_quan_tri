/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author win10pro
 */
public class ChiTietVe {
    
    private Ve Ve;
    private DanhSachChuyenBay ChuyenBay;
    private KhachHang KhachHang;
    private int Gia;
    private KhuyenMai KhuyenMai; 

    public ChiTietVe(Ve Ve, DanhSachChuyenBay ChuyenBay, KhachHang KhachHang, int Gia, KhuyenMai KhuyenMai) {
        this.Ve = Ve;
        this.ChuyenBay = ChuyenBay;
        this.KhachHang = KhachHang;
        this.Gia = Gia;
        this.KhuyenMai = KhuyenMai;
    }

    public ChiTietVe() {
        
    }

    public Ve getVe() {
        return Ve;
    }

    public void setVe(Ve Ve) {
        this.Ve = Ve;
    }


    public DanhSachChuyenBay getChuyenBay() {
        return ChuyenBay;
    }

    public void setChuyenBay(DanhSachChuyenBay ChuyenBay) {
        this.ChuyenBay = ChuyenBay;
    }

    public KhachHang getKhachHang() {
        return KhachHang;
    }

    public void setKhachHang(KhachHang KhachHang) {
        this.KhachHang = KhachHang;
    }

    public int getGia() {
        return Gia;
    }

    public void setGia(int Gia) {
        this.Gia = Gia;
    }

    public KhuyenMai getKhuyenMai() {
        return KhuyenMai;
    }

    public void setKhuyenMai(KhuyenMai KhuyenMai) {
        this.KhuyenMai = KhuyenMai;
    }

    
   
}
