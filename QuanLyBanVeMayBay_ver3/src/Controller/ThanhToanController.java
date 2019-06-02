/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ThanhToan;
import Model.ThanhToanDAO;
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
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author hoangdang
 */
public class ThanhToanController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXTextField HoTenLienHe;
    @FXML
    private JFXTextField DienThoai;
    @FXML
    private JFXTextField Email;
    @FXML
    private JFXTextField Diachi;
    @FXML
    private JFXTextField HoTenKhachHang;
    @FXML
    private JFXDatePicker NgaySinh;
    @FXML
    private JFXTextField QuocTich;
    @FXML
    private Label DiemDi;
    @FXML
    private Label DiemDen;
    @FXML
    private Label ThoiDiemXuatPhat;
    @FXML
    private Label ThoiDiemXuatPhat1;
    @FXML
    private Label HangBay;
    @FXML
    private Label HangBay1;
    @FXML
    private Label LoaiGhe;
    @FXML
    private Label GioBay;
    @FXML
    private Label GioBay1;
//    @FXML
//    private Label GioDen;
    @FXML
    private Label ThoiGianBay;
    @FXML
    private Label ThoiGianBay1;
    @FXML
    private Label LoaiDuongBay;
    @FXML
    private Label SoVe;
    @FXML
    private Label TongTien;
    @FXML
    private JFXButton btnThanhToan;
    @FXML
    private JFXButton btnBack;
    @FXML
    private JFXComboBox<Integer> cbBoxHanhLy;
    @FXML
    private JFXComboBox<Integer> cbBoxHanhLy1;
    ObservableList cbBoxHanhLyList = FXCollections.observableArrayList(0,10,20,30 );
    ObservableList cbBoxHanhLyList1 = FXCollections.observableArrayList(0,10,20,30 );

    
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
    private String MaCBString;
    private String MaCBString1;
    private String MaVeString;
    private String MaVeString1;
    private String KhachhangString;
    private int HanhLy;
    private int HanhLy1;
    private double tienve;
    private double tienve1;
    private double tongtien;
    ThanhToanDAO thanhToanDAO = new ThanhToanDAO();
    ThanhToan tt = new ThanhToan();
    ThanhToan tt1 = new ThanhToan();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        HoTenLienHe.setText("hoang");
        cbBoxHanhLy.setItems(cbBoxHanhLyList);
        cbBoxHanhLy1.setItems(cbBoxHanhLyList1);
        cbBoxHanhLy.getSelectionModel().selectFirst();
        cbBoxHanhLy1.getSelectionModel().selectFirst();
    }    
    @FXML
    public void handleThanhToan(ActionEvent event) throws SQLException{
//         if ( this.HoTenLienHe.getText() == null ||  ) {
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("Lỗi rồi ");
//                alert.setContentText("Vui lòng chọn chuyến bay :) ");
//                alert.showAndWait();
//            }
        int hanhLy = this.cbBoxHanhLy.getValue();
        int hanhLy1 = this.cbBoxHanhLy1.getValue();
        double tong;
        if (ThoiDiemXuatPhat1==null) {
            tong = (thanhtien(LoaiVeInt, SLNgL, SLTreEm, GiaVeInt,hanhLy));
            this.tienve = tong;
        } else{
            this.tienve = (thanhtien(this.LoaiVeInt, this.SLNgL, this.SLTreEm, this.GiaVeInt,hanhLy)); 
            this.tienve1 =(thanhtien(this.LoaiVeInt, SLNgL, SLTreEm,GiaVeInt1,hanhLy1));
            tong = this.tienve+this.tienve1;
        }
        this.TongTien.setText(Double.toString(tong));
        KhachhangString = this.HoTenLienHe.getText();
        tt.setTienve(tienve);
        tt.setKhachhangString(KhachhangString);
        tt.setLoaiVeInt(LoaiVeInt);
        tt.setMaCBString(MaCBString);
        tt.setMaVeString(MaVeString);
        tt.setSove(SoLuongVeInt);
//        tt.setCmnd(MaCBString);
        //tt.setEmail(1);
        //tt.setSdt(1);
        if (this.MaCBString1 != null) {
            tt1.setTienve(tienve1);
            tt1.setKhachhangString(KhachhangString);
            tt1.setLoaiVeInt(LoaiVeInt);
            tt1.setMaCBString(MaCBString1);
            tt1.setMaVeString(MaVeString1);
            tt1.setSove(SoLuongVeInt1);
        }
//        tt.setCmnd(MaCBString);
        //tt.setEmail(1);
        //tt.setSdt(1);
        thanhToanDAO.LuuThongTinVeVoDB(tt);
        if (this.MaCBString1 != null) {
            thanhToanDAO.LuuThongTinVeVoDB(tt1);
        } 
        
        
        
//        lấy mã vé ra rồi bỏ vô mảng lặp nhét vô next 
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setContentText("ten khach hang: "+KhachhangString+"\n"+"tong tien phai thanh toan: "+tong);
        alert.show();
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
        int hanhLy = this.cbBoxHanhLy.getValue();
        int hanhLy1 = this.cbBoxHanhLy1.getValue();
        double tong;
        if (ThoiDiemXuatPhat1==null) {
            tong = (thanhtien(LoaiVeInt, SLNgL, SLTreEm, GiaVeInt,hanhLy));
            this.tienve = tong;
        } else{
            this.tienve = (thanhtien(this.LoaiVeInt, this.SLNgL, this.SLTreEm, this.GiaVeInt,hanhLy)); 
            this.tienve1 =(thanhtien(this.LoaiVeInt, SLNgL, SLTreEm,GiaVeInt1,hanhLy1));
            tong = this.tienve+this.tienve1;
        }
        this.TongTien.setText(Double.toString(tong));
    }
    public double thanhtien(int loaive, int slnglon, int sltreem, int giave,int hanhly){
        double tongtien=0;
        double tienhanhly = 0;
            if (loaive == 1) {
                tongtien = (slnglon*giave +sltreem*giave*0.7)*1.1;
            }
            if (loaive==0) {
                if (hanhly != 0) {
                    if (hanhly == 10) {
                        tienhanhly = 10 ;
                    } if (hanhly == 20) {
                        tienhanhly = 20 ;    
                    } if (hanhly == 30) {
                        tienhanhly = 30 ;    
                    } 
                }
                
                tongtien = (slnglon*giave +sltreem*giave*0.7)+tienhanhly;
                
            }
    
        return tongtien;
        
    }
 
    
    public void ChuyenDuLieu(String MaVeString, String MaCBString,String DiemDi,String DiemDen,LocalDate NgayDate, int TGBay,String HangMB,int LoaiVe,String GioKH,int SoNL,int SoTreEm, int GiaVe){
        this.DiemDi.setText(DiemDi);
        this.DiemDen.setText(DiemDen);
        this.ThoiDiemXuatPhat.setText(NgayDate.toString());
        this.HangBay.setText(HangMB);
        this.GioBay.setText(GioKH);
        this.ThoiGianBay.setText(Integer.toString(TGBay)+"h");
        if (LoaiVe==1) {
            this.LoaiGhe.setText("Thuong Gia");
        } else {this.LoaiGhe.setText("Pho Thong");}
        
        this.SLNgL = SoNL;
        this.SLTreEm=SoTreEm;
         this.SoLuongVeInt=(SoNL+SoTreEm);
        this.SoVe.setText(String.valueOf(SoLuongVeInt ));
        DiemDiString = DiemDi;
        DiemDenString = DiemDen;
        NgayDiDate = NgayDate;
        this.HangMBString = HangMB;
        LoaiVeInt = LoaiVe;
        GioKhoiHanhString = GioKH;
        GiaVeInt = GiaVe;
        this.MaCBString=MaCBString;
        this.MaVeString = MaVeString;
        this.HanhLy = this.cbBoxHanhLy.getValue();
        
        this.tongtien = (thanhtien(LoaiVe, SoNL, SoTreEm, GiaVe,HanhLy));
        this.TongTien.setText(Double.toString(tongtien));
    }
    public void ChuyenDuLieuKhuHoi(String MaVeString, String MaVeString1, String MaCBString,String MaCBString1,String DiemDi,String DiemDen,String DiemDi1,String DiemDen1,LocalDate NgayDi, LocalDate NgayVe,String GioKH,String GioKH1, int TGBay,int TGBay1,String HangMB,int LoaiVe,int SoNL,int SoTreEm, int GiaVe,int GiaVe1){
        this.DiemDi.setText(DiemDi);
        this.DiemDen.setText(DiemDen);
        this.HangBay.setText(HangMB);
        this.ThoiGianBay.setText(Integer.toString(TGBay)+"h");
        this.ThoiDiemXuatPhat.setText(NgayDi.toString());
        this.GioBay.setText(GioKH);
        
        this.ThoiGianBay1.setText(Integer.toString(TGBay1)+"h");
        this.ThoiDiemXuatPhat1.setText(NgayVe.toString());
        this.GioBay1.setText(GioKH1);
        
        if (LoaiVe==1) {
            this.LoaiGhe.setText("Thuong Gia");
        } else {this.LoaiGhe.setText("Pho Thong");}
        
        this.SLNgL = SoNL;
        this.SLTreEm=SoTreEm;
        this.SoLuongVeInt=(SoNL+SoTreEm);
        this.SoLuongVeInt1=(SoNL+SoTreEm);
        this.SoVe.setText(String.valueOf(SoLuongVeInt*2));
        
        this.HanhLy = this.cbBoxHanhLy.getValue();
        this.HanhLy1 = this.cbBoxHanhLy1.getValue();
        this.tongtien = (thanhtien(LoaiVe, SoNL, SoTreEm, GiaVe, HanhLy))+(thanhtien(LoaiVe, SoNL, SoTreEm, GiaVe1, HanhLy1));
        this.DiemDiString = DiemDi;
        this.DiemDenString = DiemDen;
        this.NgayDiDate = NgayDi;
        this.NgayVeDate = NgayVe;
        this.HangMBString = HangMB;
        this.LoaiVeInt = LoaiVe;
        this.GioKhoiHanhString = GioKH;
        this.GiaVeInt = GiaVe;
        this.GiaVeInt1 = GiaVe1;
        this.MaCBString=MaCBString;
        this.MaCBString1=MaCBString1;
        this.MaVeString = MaVeString;
        this.MaVeString1 = MaVeString1;

        this.TongTien.setText(Double.toString(tongtien));

    }
    
    
    
}
