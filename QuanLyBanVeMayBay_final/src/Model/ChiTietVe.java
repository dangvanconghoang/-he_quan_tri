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
 * @author win10pro
 */
public class ChiTietVe {
    private String MaVe;
    private String MaCB;
    private String TenHK;
    private String CMND;
    private String SDT;
    private String Email;
    private boolean LoaiHK;
    private String Gia;
    private boolean LoaiVe;
    private String TenHMB;
    private String DiemKhoiHanh;
    private String DiemDen;
    private String SanBayDi;
    private String SanBayDen;
    private Date NgayKhoiHanh;
    private int ThoiGianBay;
    private Time GioKhoiHanh;
    private int HanhLy;
    private String SoGhe;

    public ChiTietVe(String MaVe, String MaCB, String TenHK, String CMND, String SDT, String Email, boolean LoaiHK, String Gia, boolean LoaiVe, String TenHMB, String DiemKhoiHanh, String DiemDen, String SanBayDi, String SanBayDen, Date NgayKhoiHanh, int ThoiGianBay, Time GioKhoiHanh, int HanhLy,String SoGhe) {
        this.MaVe = MaVe;
        this.MaCB = MaCB;
        this.TenHK = TenHK;
        this.CMND = CMND;
        this.SDT = SDT;
        this.Email = Email;
        this.LoaiHK = LoaiHK;
        this.Gia = Gia;
        this.LoaiVe = LoaiVe;
        this.TenHMB = TenHMB;
        this.DiemKhoiHanh = DiemKhoiHanh;
        this.DiemDen = DiemDen;
        this.SanBayDi = SanBayDi;
        this.SanBayDen = SanBayDen;
        this.NgayKhoiHanh = NgayKhoiHanh;
        this.ThoiGianBay = ThoiGianBay;
        this.GioKhoiHanh = GioKhoiHanh;
        this.HanhLy = HanhLy;
        this.SoGhe=SoGhe;
    }
    

    public ChiTietVe() {
    }
    
    public void setHanhLy(int HanhLy){
        this.HanhLy=HanhLy;
    }   
    public void setSoGhe(String SoGhe){
        this.SoGhe=SoGhe;
    }
    public void setMaVe(String MaVe){
        this.MaVe=MaVe;
    }
    public void setMaCB(String MaCB){
        this.MaCB=MaCB;
    }
    public void setTenHK(String TenHK){
        this.TenHK=TenHK;
    }
    public void setCMND(String CMND){
        this.CMND=CMND;
    }
    public void setSDT(String SDT){
        this.SDT=SDT;
    }
    public void setEmail(String Email){
        this.Email=Email;
    }
    public void setLoaiHK(boolean LoaiHK){
        this.LoaiHK=LoaiHK;
    }
    public void setGia(String Gia){
        this.Gia=Gia;
    }
    public void setLoaiVe(boolean LoaiVe){
        this.LoaiVe=LoaiVe;
    }
    public void setTenHMB(String TenHMB){
        this.TenHMB=TenHMB;
    }
    public void setDiemKhoiHanh(String DiemKhoiHanh){
        this.DiemKhoiHanh=DiemKhoiHanh;
    }
    public void setDiemDen(String DiemDen){
        this.DiemDen=DiemDen;
    }
    public  void setSanBayDi(String SanBayDi){
        this.SanBayDi=SanBayDi;
    }
    public  void setSanBayDen(String SanBayDen){
        this.SanBayDen=SanBayDen;
    }
    public void setNgayKhoiHanh(Date NgayKhoiHanh){
        this.NgayKhoiHanh=NgayKhoiHanh;
    }
    public void setThoiGianBay(int ThoiGianBay){
        this.ThoiGianBay=ThoiGianBay;
    }
    public void setGioKhoiHanh(Time GioKhoiHanh){
        this.GioKhoiHanh=GioKhoiHanh;
    }
    public String getMaVe(){
        return MaVe;
    }
    public String getMaCB(){
        return MaCB;
    }
    public String getTenHK(){
        return TenHK;
    }
    public String getCMND(){
        return CMND;
    }
    public String getSDT(){
        return SDT;
    }
    public String getEmail(){
        return Email;
    }
    public boolean getLoaiHK(){
        return LoaiHK;
    }
    public String getGia(){
        return Gia;
    }
    public boolean getLoaiVe(){
        return LoaiVe;
    }
    public String getTenHMB(){
        return TenHMB;
    }
    public String getDiemKhoiHanh(){
        return DiemKhoiHanh;
    }
    public String getDiemDen(){
        return DiemDen;
    }
    public String getSanBayDi(){
        return SanBayDi;
    }
    public String getSanBayDen(){
        return SanBayDen;
    }
    public Date getNgayKhoiHanh(){
        return NgayKhoiHanh;
    }
    public int getThoiGianBay(){
        return ThoiGianBay;
    }
    public Time getGioKhoiHanh(){
        return GioKhoiHanh;
    }
    public int getHanhLy(){
        return HanhLy;
    }
    public String getSoGhe(){
        return SoGhe;
    }
}
