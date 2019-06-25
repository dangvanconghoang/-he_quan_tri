/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.HangMayBay;
import Model.SanBay;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author Nam
 */
public class TaoChuyenBay {
    
    private String MaCB;
    private String MaHMB;
    private String TenHMB;
    private HangMayBay HangMayBay;
    private SanBay SanBayDi;
    private SanBay SanBayDen;
    private int SoGheVipTrong;
    private int SoGheThuongTrong;
    private int ThoiGianBay;
    private LocalDate NgayKhoiHanh;
    private LocalTime GioKhoiHanh;
    private int GiaVe;
    private Timestamp ThoiGianKhoiHanh;

    public String getTenHangMayBay() {
        return HangMayBay.getTenHMB();
    }

    public String getTenSanBayDi() {
        return SanBayDi.getTenSB();
    }
    
    public String getTenSanBayDen() {
        return SanBayDen.getTenSB();
    }
    
    
    public LocalDate getNgayKhoiHanh() {
        return NgayKhoiHanh;
    }

    public void setNgayKhoiHanh(LocalDate NgayKhoiHanh) {
        this.NgayKhoiHanh = NgayKhoiHanh;
    }

    public LocalTime getGioKhoiHanh() {
        return GioKhoiHanh;
    }

    public void setGioKhoiHanh(LocalTime GioKhoiHanh) {
        this.GioKhoiHanh = GioKhoiHanh;
    }

    
    public SanBay getSanBayDi() {
        return SanBayDi;
    }

    public void setSanBayDi(SanBay SanBayDi) {
        this.SanBayDi = SanBayDi;
    }

    public SanBay getSanBayDen() {
        return SanBayDen;
    }

    public void setSanBayDen(SanBay SanBayDen) {
        this.SanBayDen = SanBayDen;
    }
    public HangMayBay getHangMayBay() {
        return HangMayBay;
    }

    public void setHangMayBay(HangMayBay HangMayBay) {
        this.HangMayBay = HangMayBay;
    }

//    private String SanBayDi;
//    private String SanBayDen;


    public Timestamp getThoiGianKhoiHanh() {
        return ThoiGianKhoiHanh;
    }

    public void setThoiGianKhoiHanh(Timestamp ThoiGianKhoiHanh) {
        this.ThoiGianKhoiHanh = ThoiGianKhoiHanh;
    }
    
    
    public TaoChuyenBay(String MaCB, String MaHMB, String TenHMB, String DiemKhoiHanh, String DiemDen, String SanBayDi, String SanBayDen, int SoGheVipTrong, int SoGheThuongTrong, Date NgayKhoiHanh, int ThoiGianBay, Time GioKhoiHanh, int GiaVe)
    {
        this.MaCB=MaCB;
        this.MaHMB=MaHMB;
        this.TenHMB=TenHMB;

//        this.SanBayDi=SanBayDi;
        this.SoGheVipTrong=SoGheVipTrong;
        this.SoGheThuongTrong=SoGheThuongTrong;
//        this.NgayKhoiHanh=NgayKhoiHanh;
        this.ThoiGianBay=ThoiGianBay;
//        this.GioKhoiHanh=GioKhoiHanh;
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

    public int getThoiGianBay() {
        return ThoiGianBay;
    }

    public void setThoiGianBay(int ThoiGianBay) {
        this.ThoiGianBay = ThoiGianBay;
    }

    public int getGiaVe() {
        return GiaVe;
    }

    public void setGiaVe(int GiaVe) {
        this.GiaVe = GiaVe;
    }
   
    
}
    