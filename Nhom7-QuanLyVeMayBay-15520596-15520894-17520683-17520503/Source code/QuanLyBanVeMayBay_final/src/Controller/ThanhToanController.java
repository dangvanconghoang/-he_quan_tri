/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAL.ChiTietHoaDonDAO;
import DAL.DanhSachChuyenBayDAO;
import DAL.HoaDonDAO;
import Model.ThanhToan;
import DAL.ThanhToanDAO;
import Model.ChiTietHoaDon;
import Model.ChiTietVe;
import Model.DanhSachChuyenBay;
import Model.HoaDon;
import Model.KhachHang;
import Model.Ve;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author hoangdang
 */
public class ThanhToanController {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label TongTien;
    @FXML
    private JFXButton btnThanhToan;
    @FXML
    private JFXButton btnBack;
    private String DiemDiString;
    private String DiemDenString;
    private LocalDate NgayDiDate;
    private LocalDate NgayVeDate;
    private String HangMBString;
    private int LoaiVeInt;
    private String GioKhoiHanhString;
    private String GioKhoiHanhString1;
    private int SLNgL;
    private int SLTreEm;
    private int SoLuongVeInt;
    private int SoLuongVeInt1;
    private int GiaVeInt;
     private int GiaVeInt1;
    private String MaCBLuotDi;
    private String MaCBLuotVe;
//    private String MaVeString;
//    private String MaVeString1;
    private String KhachhangString;
    private int HanhLy;
    private int HanhLy1;
    private double tienve;
    private double tienve1;
    private double tongtien;
    ThanhToanDAO thanhToanDAO = new ThanhToanDAO();
    ThanhToan tt = new ThanhToan();
    ThanhToan tt1 = new ThanhToan();
    @FXML
    private JFXTextField tfCMND;
    @FXML
    private JFXTextField tfHoTenLienHe;
    @FXML
    private JFXTextField tfDiaChi;
    @FXML
    private JFXTextField tfEmail;
    @FXML
    private Label lbDiemDen;
    @FXML
    private Label lbNgayKH;
    @FXML
    private Label lbGioKH;
    @FXML
    private Label lbThoiGianBay;
    @FXML
    private Label lbSoVe;
    @FXML
    private JFXComboBox<Integer> cbxHanhLy;
    @FXML
    private Label lbNgayKHKH;
    @FXML
    private Label lbGioKHKH;
    @FXML
    private JFXComboBox<Integer> cbxHanhLyKH;
    @FXML
    private Label lbHangMB;
    @FXML
    private Label lbLoaiVe;
    private Label DiemDi;
    @FXML
    private Label lbDiemDi;
    @FXML
    private Label lbTGBayKH;
    @FXML
    private Label lbSoVeTE;
    @FXML
    private Label lbSoVeKH;
    @FXML
    private Label lbSoVeTEKH;
    @FXML
    private Label lbGiaNL;
    @FXML
    private Label lbGiaTE;
    @FXML
    private Label lbGiaNLKH;
    @FXML
    private Label lbGiaTEKH;
    private Label lbHMBKH;
    @FXML
    private Label tfUsername;
    @FXML
    private Label LoaiDuongBay2;
    @FXML
    private Label LoaiDuongBay21;
    @FXML
    private Label lbHangMBKH;
    @FXML
    private Label lbThanhToan;
    
    private boolean luot;
    ObservableList<KhachHang> listKhachHang = FXCollections.observableArrayList();
    ObservableList<Ve> listVe = FXCollections.observableArrayList();
    ObservableList<ChiTietVe> listChiTietVe = FXCollections.observableArrayList();
      
    @FXML
    public void handleThanhToan(ActionEvent event) throws SQLException{
        HoaDon hoadon = new HoaDon();
        hoadon.setKhachHang(this.listKhachHang.get(0));
        hoadon.setNgayLap(LocalDate.now());
        hoadon.setTinhTrang(1);
        HoaDonDAO.lapHoaDon(hoadon);
        String MaHoaDon = HoaDonDAO.layMaHoaDon(hoadon);
        ObservableList<ChiTietHoaDon> ctHoaDonList = FXCollections.observableArrayList();
        
        for(ChiTietVe ctVe : this.listChiTietVe) {
            ChiTietHoaDon ctHoaDon = new ChiTietHoaDon();
            ctHoaDon.setVe(ctVe);
            ctHoaDon.setHoaDon(hoadon);
            ChiTietHoaDonDAO.lapChiTietHoaDon(ctHoaDon, MaHoaDon);
        }
        
        this.btnThanhToan.setVisible(false);
        this.lbThanhToan.setVisible(true);
    }
    
    @FXML
    public void handleBack(ActionEvent event) throws IOException{
        AnchorPane paneChiTietChuyenBay = new AnchorPane();
        FXMLLoader fXMLLoader = MainController.getMainController().createPage(paneChiTietChuyenBay, "/View/DanhSachChuyenBay.fxml");
        paneChiTietChuyenBay.getChildren().add(paneChiTietChuyenBay); 
        GeneralFuntion.FitChildContent(paneChiTietChuyenBay);
    }
    @FXML
    public void handleTongTien(ActionEvent event) throws IOException{
        
    }
    
    public void ChuyenDuLieu(String MaCBString, ObservableList<Ve> listVe, ObservableList<KhachHang> listKhachHang, int SoNL,int SoTreEm, ObservableList<ChiTietVe> listChiTietVe) throws SQLException{
          this.lbThanhToan.setVisible(false);
          DanhSachChuyenBayDAO cbdb = new DanhSachChuyenBayDAO();
          DanhSachChuyenBay cb = cbdb.getChuyenBay((this.luot == false) ? MaCBString : this.MaCBLuotDi);
          
          if(this.luot == true) {
             DanhSachChuyenBay cbve = cbdb.getChuyenBay(MaCBString);
             this.lbNgayKHKH.setText(cbve.getNgayKhoiHanh().toString());
             this.lbGioKHKH.setText(cbve.getGioKhoiHanh().toString());
             this.lbTGBayKH.setText(String.valueOf(cbve.getTgBay()));
             this.lbHangMBKH.setText(cbve.getHangMayBay().getTenHMB());
          }
          
          this.listKhachHang = listKhachHang;
          this.listVe = listVe;
          this.listChiTietVe = listChiTietVe;
          int SoVeThuong = 0;
          int SoVeVip = 0;
          int GiaVeNL = 0;
          int GiaVeTreEm = 0;
          tfHoTenLienHe.setText(listKhachHang.get(0).getTenKH()); tfHoTenLienHe.setEditable(false);
          tfDiaChi.setText(listKhachHang.get(0).getDiaChi()); tfDiaChi.setEditable(false);
          tfEmail.setText(listKhachHang.get(0).getEmail()); tfEmail.setEditable(false);
          tfCMND.setText(listKhachHang.get(0).getCMND()); tfCMND.setEditable(false);
          
          this.lbDiemDi.setText(cb.getDiemKhoiHanh().getThanhPho());
          this.lbDiemDen.setText(cb.getDiemDen().getThanhPho());
          this.lbNgayKH.setText(cb.getNgayKhoiHanh().toString());
          this.lbGioKH.setText(cb.getGioKhoiHanh().toString());
          this.lbThoiGianBay.setText(String.valueOf(cb.getTgBay()));
          this.lbHangMB.setText(cb.getHangMayBay().getTenHMB());
          
          for(Ve ve : listVe) {        
              if(ve.getLoaiVe().getLoaiVe() == 0) 
                  SoVeThuong++;
              else if(ve.getLoaiVe().getLoaiVe() == 1)
                  SoVeVip++;
          }
           
         this.lbLoaiVe.setText("VIP x" + SoVeVip +" Thường x" +SoVeThuong);
           
        this.SLNgL = SoNL;
        this.SLTreEm = SoTreEm;
        this.SoLuongVeInt =(SoNL+SoTreEm);
        this.lbSoVe.setText(String.valueOf(SLNgL));
        this.lbSoVeTE.setText(String.valueOf(SLTreEm));
        this.lbSoVeKH.setText(String.valueOf(SLNgL));
        this.lbSoVeTEKH.setText(String.valueOf(SLNgL));
        
        for(ChiTietVe ctVe : listChiTietVe) {
            if(ctVe.getKhachHang().getLoaiHK() == 1) {
                GiaVeNL += ctVe.getGia();
            }
            else if(ctVe.getKhachHang().getLoaiHK() == 0) {
                GiaVeTreEm += ctVe.getGia();
            }
        }

        this.TongTien.setText(String.valueOf(GiaVeNL + GiaVeTreEm));
    }

    void ChuyenDuLieuKhuHoi(String MaCBLuotDi, String DiemDi, String DiemDen, LocalDate NgayDi, LocalDate NgayVe, boolean luot) {
        this.MaCBLuotDi = MaCBLuotDi;
        this.DiemDiString = DiemDi;
        this.DiemDenString = DiemDen;
        this.NgayDiDate = NgayDi;
        this.NgayVeDate = NgayVe;
        this.luot = luot;
    }


    
    
}
