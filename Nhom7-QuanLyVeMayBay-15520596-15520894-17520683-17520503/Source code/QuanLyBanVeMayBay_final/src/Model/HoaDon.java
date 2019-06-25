/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.time.LocalDate;

/**
 *
 * @author blues
 */
public class HoaDon {
    private String MaHoaDon;
    private KhachHang KhachHang;
    private int TinhTrang;
    private LocalDate NgayLap;

    public HoaDon() {
        
    }
    
    public HoaDon(String MaHoaDon, KhachHang KhachHang, int TinhTrang, LocalDate NgayLap) {
        this.MaHoaDon = MaHoaDon;
        this.KhachHang = KhachHang;
        this.TinhTrang = TinhTrang;
        this.NgayLap = NgayLap;
    }

    public String getMaHoaDon() {
        return MaHoaDon;
    }

    public void setMaHoaDon(String MaHoaDon) {
        this.MaHoaDon = MaHoaDon;
    }

    public KhachHang getKhachHang() {
        return KhachHang;
    }

    public void setKhachHang(KhachHang KhachHang) {
        this.KhachHang = KhachHang;
    }

    public int getTinhTrang() {
        return TinhTrang;
    }

    public void setTinhTrang(int TinhTrang) {
        this.TinhTrang = TinhTrang;
    }

    public LocalDate getNgayLap() {
        return NgayLap;
    }

    public void setNgayLap(LocalDate NgayLap) {
        this.NgayLap = NgayLap;
    }
    
    
    
}
