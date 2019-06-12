/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import java.sql.Date;
import java.time.LocalDate;
/**
 *
 * @author Van Tich
 */
public class DatVeMayBay {
    private String DiemKhoiHanh;
    private String DiemDen;
    private  LocalDate Ngay;
    private int SLNguoiLon;
    private int SLTreEM;
    private int LoaiVe;
    private int isKhuHoi;

    public DatVeMayBay(String DiemKhoiHanh, String DiemDen, LocalDate Ngay, int SLNguoiLon, int SLTreEM, int LoaiVe, int isKhuHoi) {
        this.DiemKhoiHanh = DiemKhoiHanh;
        this.DiemDen = DiemDen;
        this.Ngay = Ngay;
        this.SLNguoiLon = SLNguoiLon;
        this.SLTreEM = SLTreEM;
        this.LoaiVe = LoaiVe;
        this.isKhuHoi = isKhuHoi;
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

    public LocalDate getNgay() {
        return Ngay;
    }

    public void setNgay(LocalDate Ngay) {
        this.Ngay = Ngay;
    }

    public int getSLNguoiLon() {
        return SLNguoiLon;
    }

    public void setSLNguoiLon(int SLNguoiLon) {
        this.SLNguoiLon = SLNguoiLon;
    }

    public int getSLTreEM() {
        return SLTreEM;
    }

    public void setSLTreEM(int SLTreEM) {
        this.SLTreEM = SLTreEM;
    }

    public int getLoaiVe() {
        return LoaiVe;
    }

    public void setLoaiVe(int LoaiVe) {
        this.LoaiVe = LoaiVe;
    }

    public int getIsKhuHoi() {
        return isKhuHoi;
    }

    public void setIsKhuHoi(int isKhuHoi) {
        this.isKhuHoi = isKhuHoi;
    }
   
    
    
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }
    public DatVeMayBay()
    {}
}