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
    private double Gia;
    private String DiaChi;    

    public ChiTietVe(String MaVe, String MaCB, String TenHK, String CMND, String SDT,String Email, boolean LoaiHK, double Gia, String DiaChi) {
        this.MaVe = MaVe;
        this.MaCB = MaCB;
        this.TenHK = TenHK;
        this.CMND = CMND;
        this.SDT = SDT;
        this.Email=Email;
        this.LoaiHK = LoaiHK;
        this.Gia = Gia;
        this.DiaChi = DiaChi;
    }

    public ChiTietVe() {
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
    public void setGia(double Gia){
        this.Gia=Gia;
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
    public double getGia(){
        return Gia;
    }

}
