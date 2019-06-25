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
public class KhachHang {

    private String MaKhachHang;
    private String CMND;
    private String TenKH;
    private int Tuoi;
    private String DiaChi;
    private int LoaiHK;
    private String Email;

    public KhachHang() {
        
    }
    public KhachHang(String MaKhachHang, String TenKH, int Tuoi, String DiaChi, int LoaiHK) {
        this.MaKhachHang = MaKhachHang;
        this.TenKH = TenKH;
        this.Tuoi = Tuoi;
        this.DiaChi = DiaChi;
        this.LoaiHK = LoaiHK;
    }

    public String getCMND() {
        return CMND;
    }

    public void setCMND(String CMND) {
        this.CMND = CMND;
    }
    
    public String getMaKhachHang() {
        return MaKhachHang;
    }

    public void setMaKhachHang(String MaKhachHang) {
        this.MaKhachHang = MaKhachHang;
    }

    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String TenKH) {
        this.TenKH = TenKH;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
    
    public int getTuoi() {
        return Tuoi;
    }

    public void setTuoi(int Tuoi) {
        this.Tuoi = Tuoi;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public int getLoaiHK() {
        return LoaiHK;
    }

    public void setLoaiHK(int LoaiHK) {
        this.LoaiHK = LoaiHK;
    }

}
