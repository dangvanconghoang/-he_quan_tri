/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.scene.control.Button;

/**
 *
 * @author win10pro
 */
public class LichSuBanVe {
    private String MaVe;
    private String MaCB;
    private String TenHK;
    private String MaKhachHang;
    private boolean LoaiHK;
    private int Gia;
    private Button btnChiTiet;

    public LichSuBanVe(String MaVe, String MaCB, String TenHK, String MaKhachHang, boolean LoaiHK, int Gia, Button btnChiTiet) {
        this.MaVe = MaVe;
        this.MaCB = MaCB;
        this.TenHK = TenHK;
        this.MaKhachHang = MaKhachHang;
        this.LoaiHK=LoaiHK;
        this.Gia=Gia;
        this.btnChiTiet=btnChiTiet;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }

    public LichSuBanVe() {
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
    public void setMaKhachHang(String MaKhachHang){
        this.MaKhachHang=MaKhachHang;
    }

    public void setLoaiHK(boolean LoaiHK){
        this.LoaiHK=LoaiHK;
    }
    public void setGia(int Gia){
        this.Gia=Gia;
    }
    public void setChiTiet(Button btnChiTiet){
        this.btnChiTiet=btnChiTiet;
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
    public String getMaKhachHang(){
        return MaKhachHang;
    }

    public boolean getLoaiHK(){
        return LoaiHK;
    }
    public int getGia(){
        return Gia;
    }
    public Button getChiTiet(){
        return btnChiTiet;
    }
    }
