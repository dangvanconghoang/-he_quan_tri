/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author Nam
 */
public class TaoChuyenBay {
    private String MaCB;
    private String MaHMB;
    private String TenHMB;
    private String DiemKhoiHanh;
    private String DiemDen;
    private String SanBayDi;
    private String SanBayDen;
    private int SoGheVipTrong;
    private int SoGheThuongTrong;
    private Date NgayKhoiHanh;
    private int ThoiGianBay;
    private Time GioKhoiHanh;
    private String GiaVe;
    
    
    public TaoChuyenBay(String MaCB, String MaHMB, String TenHMB, String DiemKhoiHanh, String DiemDen, String SanBayDi, String SanBayDen, int SoGheVipTrong, int SoGheThuongTrong, Date NgayKhoiHanh, int ThoiGianBay, Time GioKhoiHanh, String GiaVe)
    {
        this.MaCB=MaCB;
        this.MaHMB=MaHMB;
        this.TenHMB=TenHMB;
        this.DiemKhoiHanh=DiemKhoiHanh;
        this.DiemDen=DiemDen;
        this.SanBayDi=SanBayDi;
        this.SoGheVipTrong=SoGheVipTrong;
        this.SoGheThuongTrong=SoGheThuongTrong;
        this.NgayKhoiHanh=NgayKhoiHanh;
        this.ThoiGianBay=ThoiGianBay;
        this.GioKhoiHanh=GioKhoiHanh;
        this.GiaVe=GiaVe;
    }
    public TaoChuyenBay(){};

    public String getMaCB() {
        return MaCB;
    }

    public void setMaCB(String MaCB) {
        this.MaCB = MaCB;
    }
    
    public String getMaHMB() {
        return MaHMB;
    }

    public void setMaHMB(String MaHMB) {
        this.MaHMB = MaHMB;
    }
    
    public String getTenHMB() {
        return TenHMB;
    }

    public void setTenHMB(String TenHMB) {
        this.TenHMB = TenHMB;
    }

    public String getDiemKhoiHanh() {
        return DiemKhoiHanh;
    }

    public void setDiemKhoiHanh(String DiemKhoiHanh) {
        this.DiemKhoiHanh = DiemKhoiHanh;
    }

    public String getDiemDen() {
        return DiemDen;
    }

    public void setDiemDen(String DiemDen) {
        this.DiemDen = DiemDen;
    }

    public String getSanBayDi() {
        return SanBayDi;
    }

    public void setSanBayDi(String SanBayDi) {
        this.SanBayDi = SanBayDi;
    }

    public String getSanBayDen() {
        return SanBayDen;
    }

    public void setSanBayDen(String SanBayDen) {
        this.SanBayDen = SanBayDen;
    }

    public int getSoGheVipTrong() {
        return SoGheVipTrong;
    }

    public void setSoGheVipTrong(int SoGheVipTrong) {
        this.SoGheVipTrong = SoGheVipTrong;
    }

    public int getSoGheThuongTrong() {
        return SoGheThuongTrong;
    }

    public void setSoGheThuongTrong(int SoGheThuongTrong) {
        this.SoGheThuongTrong = SoGheThuongTrong;
    }

    public Date getNgayKhoiHanh() {
        return NgayKhoiHanh;
    }

    public void setNgayKhoiHanh(Date NgayKhoiHanh) {
        this.NgayKhoiHanh = NgayKhoiHanh;
    }

    public int getThoiGianBay() {
        return ThoiGianBay;
    }

    public void setThoiGianBay(int ThoiGianBay) {
        this.ThoiGianBay = ThoiGianBay;
    }

    public Time getGioKhoiHanh() {
        return GioKhoiHanh;
    }

    public void setGioKhoiHanh(Time GioKhoiHanh) {
        this.GioKhoiHanh = GioKhoiHanh;
    }

    public String getGiaVe() {
        return GiaVe;
    }

    public void setGiaVe(String GiaVe) {
        this.GiaVe = GiaVe;
    }
   
    
}
    