/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author hoangdang
 */
public class DanhSachChuyenBay {
    private String MaCB;
    private HangMayBay HangMayBay;
    private String MaVe;
    private SanBay DiemKhoiHanh;
    private SanBay DiemDen;
    private String GioKH;
    private String GioKH1;
    private int TgBay;
    private int TgBay1;
    private Timestamp ThoiGianKhoiHanh;
    private LocalDate NgayKhoiHanh;
    private LocalTime GioKhoiHanh;

    public HangMayBay getHangMayBay() {
        return HangMayBay;
    }

    public void setHangMayBay(HangMayBay HangMayBay) {
        this.HangMayBay = HangMayBay;
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
    
    
    public SanBay getDiemKhoiHanh() {
        return DiemKhoiHanh;
    }

    public void setDiemKhoiHanh(SanBay DiemKhoiHanh) {
        this.DiemKhoiHanh = DiemKhoiHanh;
    }

    public SanBay getDiemDen() {
        return DiemDen;
    }

    public void setDiemDen(SanBay DiemDen) {
        this.DiemDen = DiemDen;
    }

    public Timestamp getThoiGianKhoiHanh() {
        return ThoiGianKhoiHanh;
    }

    public void setThoiGianKhoiHanh(Timestamp ThoiGianKhoiHanh) {
        this.ThoiGianKhoiHanh = ThoiGianKhoiHanh;
    }
    
    private double GiaVe;
    private LocalDate NgayBayDate;
    private LocalDate NgayBayDate1;
    private int SLNgL;
    private int SLTreEm;
    private int LoaiVe;

    public DanhSachChuyenBay() {
    }

    public DanhSachChuyenBay(String MaHMB, String MaCB, String MaVe, String DiemKhoiHanh, String DiemDen, String GioKH, String GioKH1, int TgBay, int TgBay1, double GiaVe, LocalDate NgayBayDate, LocalDate NgayBayDate1, int SLNgL, int SLTreEm, int LoaiVe) {
        this.MaCB = MaCB;
        this.MaVe = MaVe;
        this.GioKH = GioKH;
        this.GioKH1 = GioKH1;
        this.TgBay = TgBay;
        this.TgBay1 = TgBay1;
        this.GiaVe = GiaVe;
        this.NgayBayDate = NgayBayDate;
        this.NgayBayDate1 = NgayBayDate1;
        this.SLNgL = SLNgL;
        this.SLTreEm = SLTreEm;
        this.LoaiVe = LoaiVe;
    }


    public String getMaCB() {
        return MaCB;
    }

    public void setMaCB(String MaCB) {
        this.MaCB = MaCB;
    }

    public String getMaVe() {
        return MaVe;
    }

    public void setMaVe(String MaVe) {
        this.MaVe = MaVe;
    }

    public String getGioKH() {
        return GioKH;
    }

    public void setGioKH(String GioKH) {
        this.GioKH = GioKH;
    }

    public String getGioKH1() {
        return GioKH1;
    }

    public void setGioKH1(String GioKH1) {
        this.GioKH1 = GioKH1;
    }

    public int getTgBay() {
        return TgBay;
    }

    public void setTgBay(int TgBay) {
        this.TgBay = TgBay;
    }

    public int getTgBay1() {
        return TgBay1;
    }

    public void setTgBay1(int TgBay1) {
        this.TgBay1 = TgBay1;
    }

    public double getGiaVe() {
        return GiaVe;
    }

    public void setGiaVe(double GiaVe) {
        this.GiaVe = GiaVe;
    }

    public LocalDate getNgayBayDate() {
        return NgayBayDate;
    }

    public void setNgayBayDate(LocalDate NgayBayDate) {
        this.NgayBayDate = NgayBayDate;
    }

    public LocalDate getNgayBayDate1() {
        return NgayBayDate1;
    }

    public void setNgayBayDate1(LocalDate NgayBayDate1) {
        this.NgayBayDate1 = NgayBayDate1;
    }

    public int getSLNgL() {
        return SLNgL;
    }

    public void setSLNgL(int SLNgL) {
        this.SLNgL = SLNgL;
    }

    public int getSLTreEm() {
        return SLTreEm;
    }

    public void setSLTreEm(int SLTreEm) {
        this.SLTreEm = SLTreEm;
    }

    public int getLoaiVe() {
        return LoaiVe;
    }

    public void setLoaiVe(int LoaiVe) {
        this.LoaiVe = LoaiVe;
    }

   
   

    
    
   
    
    
}
