/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author hoangdang
 */
public class ThanhToan {
    private int LoaiVeInt;
//    private String GioKhoiHanhString;
//    private int SoLuongVeInt;
    private double tienve;
    private int sove;
    private int soveTE;
    private String MaCBString;
    private String MaVeString;
    private String KhachhangString;
    private String cmnd;
    private String sdt;
    private String email;
    private int HanhLy;
    private double giaNL;
    private double giaTE;

    public ThanhToan(int LoaiVeInt, double tienve, int sove, int soveTE, String MaCBString, String MaVeString, String KhachhangString, String cmnd, String sdt, String email, int HanhLy, double giaNL,double giaTE) {
        this.LoaiVeInt = LoaiVeInt;
        this.tienve = tienve;
        this.sove = sove;
        this.soveTE = soveTE;
        this.MaCBString = MaCBString;
        this.MaVeString = MaVeString;
        this.KhachhangString = KhachhangString;
        this.cmnd = cmnd;
        this.sdt = sdt;
        this.email = email;
        this.HanhLy = HanhLy;
        this.giaNL = giaNL;
        this.giaTE=giaTE;
    }
    public ThanhToan() {
    }

    public double getgiaNL(){
        return giaNL;
    }
    public void setGiaNL(double giaNL){
        this.giaNL=giaNL;
    }
    
    public double getgiaTE(){
        return giaTE;
    }
    public void setGiaTE(double giaTE){
        this.giaTE=giaTE;
    }
    
    public int getsoveTE()
    {
        return soveTE;
    }
    public void setsoveTE(int soveTE){
        this.soveTE=soveTE;
    }
    
    public int getHanhLy(){
        return HanhLy;
    }
    public void setHanhLy(int HanhLy){
        this.HanhLy=HanhLy;
    }
    
    public int getLoaiVeInt() {
        return LoaiVeInt;
    }

    public void setLoaiVeInt(int LoaiVeInt) {
        this.LoaiVeInt = LoaiVeInt;
    }

    public double getTienve() {
        return tienve;
    }

    public void setTienve(double tienve) {
        this.tienve = tienve;
    }

    public int getSove() {
        return sove;
    }

            
    public void setSove(int sove) {
        this.sove = sove;
    }

    public String getMaCBString() {
        return MaCBString;
    }

    public void setMaCBString(String MaCBString) {
        this.MaCBString = MaCBString;
    }

    public String getMaVeString() {
        return MaVeString;
    }

    public void setMaVeString(String MaVeString) {
        this.MaVeString = MaVeString;
    }

    public String getKhachhangString() {
        return KhachhangString;
    }

    public void setKhachhangString(String KhachhangString) {
        this.KhachhangString = KhachhangString;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    

    
    
}
