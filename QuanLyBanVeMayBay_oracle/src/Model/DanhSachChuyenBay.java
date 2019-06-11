/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Timestamp;
import java.time.LocalDate;
/**
 *
 * @author hoangdang
 */
public class DanhSachChuyenBay {
    private String MaHMB;
    private String MaCB;
    private String DiemKhoiHanh;
    private String DiemDen;
    private Timestamp ThoiGianKhoiHanh;
    private int TgBay;
    private int TgBay1;

    private double GiaVe;
    private LocalDate NgayBayDate;
    private LocalDate NgayBayDate1;
    private int SLNgL;
    private int SLTreEm;
    private int LoaiVe;

    public DanhSachChuyenBay() {
    }

    public DanhSachChuyenBay(String MaHMB, String MaCB, String DiemKhoiHanh, String DiemDen, Timestamp ThoiGianKhoiHanh, int TgBay, int TgBay1, double GiaVe, LocalDate NgayBayDate, LocalDate NgayBayDate1, int SLNgL, int SLTreEm, int LoaiVe) {
        this.MaHMB = MaHMB;
        this.MaCB = MaCB;
        this.DiemKhoiHanh = DiemKhoiHanh;
        this.DiemDen = DiemDen;
        this.ThoiGianKhoiHanh = ThoiGianKhoiHanh;
        this.TgBay = TgBay;
        this.TgBay1 = TgBay1;
        this.GiaVe = GiaVe;
        this.NgayBayDate = NgayBayDate;
        this.NgayBayDate1 = NgayBayDate1;
        this.SLNgL = SLNgL;
        this.SLTreEm = SLTreEm;
        this.LoaiVe = LoaiVe;
    }

    public String getMaHMB() {
        return MaHMB;
    }

    public void setMaHMB(String MaHMB) {
        this.MaHMB = MaHMB;
    }

    public String getMaCB() {
        return MaCB;
    }

    public void setMaCB(String MaCB) {
        this.MaCB = MaCB;
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

    public Timestamp getThoiGianKhoiHanh() {
        return this.ThoiGianKhoiHanh;
    }

    public void setThoiGianKhoiHanh(Timestamp timestamp) {
        this.ThoiGianKhoiHanh = timestamp;
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
