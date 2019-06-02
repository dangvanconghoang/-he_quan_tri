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
    private String MaCBString;
        private String MaVeString;

    private String KhachhangString;
    private String cmnd;
    private String sdt;
    private String email;
    public ThanhToan() {
    }

    public ThanhToan(int LoaiVeInt, double tienve, int sove, String MaCBString, String MaVeString, String KhachhangString, String cmnd, String sdt, String email) {
        this.LoaiVeInt = LoaiVeInt;
        this.tienve = tienve;
        this.sove = sove;
        this.MaCBString = MaCBString;
        this.MaVeString = MaVeString;
        this.KhachhangString = KhachhangString;
        this.cmnd = cmnd;
        this.sdt = sdt;
        this.email = email;
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
