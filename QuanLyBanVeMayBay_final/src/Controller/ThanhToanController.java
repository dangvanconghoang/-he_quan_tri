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
import javafx.scene.input.MouseEvent;
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
    private Label TongTien;
    @FXML
    private JFXButton btnThanhToan;
    @FXML
    private JFXButton btnBack;
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
    private JFXTextField tfDienThoai;
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tfHoTenLienHe.setText("Nguyễn Đức Tùng");
        cbxHanhLy.setItems(cbBoxHanhLyList);
        cbxHanhLyKH.setItems(cbBoxHanhLyList1);
        cbxHanhLy.getSelectionModel().selectFirst();
        cbxHanhLyKH.getSelectionModel().selectFirst();
    }    
    @FXML
    public void handleThanhToan(ActionEvent event) throws SQLException{
        tt.setTienve(tienve);
        tt.setKhachhangString(tfHoTenLienHe.getText());
        tt.setCmnd(tfCMND.getText());
        tt.setSdt(tfDienThoai.getText());
        tt.setEmail(tfEmail.getText());
        tt.setLoaiVeInt(LoaiVeInt);
        tt.setMaCBString(MaCBString);
        tt.setSove(Integer.parseInt(lbSoVe.getText()));
        tt.setsoveTE(Integer.parseInt(lbSoVeTE.getText()));
        tt.setHanhLy(cbxHanhLy.getSelectionModel().getSelectedItem());
        tt.setGiaNL(Double.parseDouble(lbGiaNL.getText()));
        tt.setGiaTE(Double.parseDouble(lbGiaTE.getText()));
        if (this.MaCBString1 != null) {
            tt1.setTienve(tienve1);
            tt1.setKhachhangString(tfHoTenLienHe.getText());
            tt1.setLoaiVeInt(LoaiVeInt);
            tt1.setMaCBString(MaCBString1);
            tt1.setCmnd(tfCMND.getText());
            tt1.setSdt(tfDienThoai.getText());
            tt1.setEmail(tfEmail.getText());
            tt1.setSove(Integer.parseInt(lbSoVeKH.getText()));
            tt1.setsoveTE(Integer.parseInt(lbSoVeTEKH.getText()));
            tt1.setHanhLy(cbxHanhLyKH.getSelectionModel().getSelectedItem());
            tt1.setGiaNL(Double.parseDouble(lbGiaNLKH.getText()));
            tt1.setGiaTE(Double.parseDouble(lbGiaTEKH.getText()));
        }
        try{
        thanhToanDAO.LuuThongTinVeVoDB(tt);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Lưu vé đi thành công!");
        alert.show();
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Lưu vé đi thất bại!");
        alert.show();
        }
        if (this.MaCBString1 != null) {
            try{
            thanhToanDAO.LuuThongTinVeVoDB(tt1);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Lưu vé về thành công!");
        alert.show();
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Lưu vé về thất bại!");
        alert.show();
        }
        } 
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
        int hanhLy = this.cbxHanhLy.getValue()*15000;
        int hanhLy1 = this.cbxHanhLyKH.getValue()*15000;
        double tong;
        double tongcong;
        double tongNL=GiaVeInt+hanhLy;
        double tongVipNL= GiaVeInt*1.5+hanhLy;
        double tongTE=GiaVeInt*0.3+hanhLy;
        double tongVipTE=GiaVeInt*0.3*1.5+hanhLy;
        if (this.MaCBString1 == null){
        if(LoaiVeInt==1){
            lbGiaNL.setText(String.valueOf(Math.round(tongVipNL)));
            lbGiaTE.setText(String.valueOf(Math.round(tongVipTE)));
        }
        else {
            lbGiaNL.setText(String.valueOf(Math.round(tongNL)));
            lbGiaTE.setText(String.valueOf(Math.round(tongTE)));
        }
        }else{
        double tongNL1=GiaVeInt1+hanhLy1;
        double tongVipNL1= GiaVeInt1*1.5+hanhLy1;
        double tongTE1=GiaVeInt1*0.3+hanhLy1;
        double tongVipTE1=GiaVeInt1*0.3*1.5+hanhLy1;
        if(LoaiVeInt==1){
            lbGiaNL.setText(String.valueOf(Math.round(tongVipNL)));
            lbGiaTE.setText(String.valueOf(Math.round(tongVipTE)));
            lbGiaNLKH.setText(String.valueOf(Math.round(tongVipNL1)));
            lbGiaTEKH.setText(String.valueOf(Math.round(tongVipTE1)));
        }
        else {
            lbGiaNL.setText(String.valueOf(tongNL));
            lbGiaTE.setText(String.valueOf(tongTE));
            lbGiaNLKH.setText(String.valueOf(tongNL1));
            lbGiaTEKH.setText(String.valueOf(tongTE1));
        }
        }
        tong=Double.parseDouble(lbGiaNL.getText())*Integer.parseInt(lbSoVe.getText())+Double.parseDouble(lbGiaTE.getText())*Integer.parseInt(lbSoVeTE.getText());
        double tongKH=Double.parseDouble(lbGiaNLKH.getText())*Integer.parseInt(lbSoVeKH.getText())+Double.parseDouble(lbGiaTEKH.getText())*Integer.parseInt(lbSoVeTEKH.getText());
        tongcong=tong+tongKH;
        this.TongTien.setText(String.valueOf(Math.round(tongcong)));
    }
//    public double thanhtien(int hanhly){
//        double tongtien=0;
//        int giahanhly=hanhly*150;
//        tongtien=Double.parseDouble(lbGiaNL.getText())*Integer.parseInt(lbSoVe.getText())+Double.parseDouble(lbGiaTE.getText())*Integer.parseInt(lbSoVeTE.getText())+Double.parseDouble(lbGiaNLKH.getText())*Integer.parseInt(lbSoVeKH.getText())+Double.parseDouble(lbGiaTEKH.getText())*Integer.parseInt(lbSoVeTEKH.getText());
//        tongtien=tongtien*giahanhly;
//        Math.round(tongtien);
//        return tongtien;
//    }
 
    
    public void ChuyenDuLieu( String MaCBString,String DiemDi,String DiemDen,LocalDate NgayDate, int TGBay,String HangMB,int LoaiVe,String GioKH,int SoNL,int SoTreEm, int GiaVe){
        this.lbDiemDi.setText(DiemDi);
        this.lbDiemDen.setText(DiemDen);
        this.lbNgayKH.setText(NgayDate.toString());
        this.lbGioKH.setText(GioKH);
        this.lbThoiGianBay.setText(Integer.toString(TGBay)+"h");
        if (LoaiVe==1) {
            this.lbLoaiVe.setText("Thuong Gia");
        } else {this.lbLoaiVe.setText("Pho Thong");}
           this.lbHangMB.setText(HangMB);
        this.SLNgL = SoNL;
        this.SLTreEm=SoTreEm;
        this.SoLuongVeInt=(SoNL+SoTreEm);
        this.lbSoVe.setText(String.valueOf(SLNgL ));
        this.lbSoVeTE.setText(String.valueOf(SLTreEm));
        DiemDiString = DiemDi;
        DiemDenString = DiemDen;
        NgayDiDate = NgayDate;
        this.HangMBString = HangMB;
        LoaiVeInt = LoaiVe;
        GioKhoiHanhString = GioKH;
        GiaVeInt = GiaVe;
        double tongVipNL= GiaVeInt*1.5;
        double tongTE=GiaVeInt*0.3;
        double tongVipTE=GiaVeInt*0.3*1.5;
        if(LoaiVeInt==1){
            lbGiaNL.setText(String.valueOf(Math.round(tongVipNL)));
            lbGiaTE.setText(String.valueOf(Math.round(tongVipTE)));
        }
        else {
            lbGiaNL.setText(String.valueOf(GiaVeInt));
            lbGiaTE.setText(String.valueOf(Math.round(tongTE)));
        }
        this.MaCBString=MaCBString;
        this.HanhLy = this.cbxHanhLy.getValue();
        tongtien=Double.parseDouble(lbGiaNL.getText())*Integer.parseInt(lbSoVe.getText())+Double.parseDouble(lbGiaTE.getText())*Integer.parseInt(lbSoVeTE.getText())+Double.parseDouble(lbGiaNLKH.getText())*Integer.parseInt(lbSoVeKH.getText())+Double.parseDouble(lbGiaTEKH.getText())*Integer.parseInt(lbSoVeTEKH.getText());
        this.TongTien.setText(String.valueOf(Math.round(tongtien)));
    }
    
    
    
    public void ChuyenDuLieuKhuHoi(String MaCBString,String MaCBString1,String DiemDi,String DiemDen,String DiemDi1,String DiemDen1,LocalDate NgayDi, LocalDate NgayVe,String GioKH,String GioKH1, int TGBay,int TGBay1,String HangMB,int LoaiVe,int SoNL,int SoTreEm, int GiaVe,int GiaVe1){
        this.lbDiemDi.setText(DiemDi);
        this.lbDiemDen.setText(DiemDen);
        this.lbThoiGianBay.setText(Integer.toString(TGBay)+"h");
        this.lbNgayKH.setText(NgayDi.toString());
        this.lbThoiGianBay.setText(GioKH);
        this.lbTGBayKH.setText(Integer.toString(TGBay1)+"h");
        this.lbNgayKHKH.setText(NgayVe.toString());
        this.lbGioKHKH.setText(GioKH1);
        this.lbHangMB.setText(HangMB);
        if (LoaiVe==1) {
            this.lbLoaiVe.setText("Thương Gia");
        } else {this.lbLoaiVe.setText("Phổ thông");}
        this.SLNgL = SoNL;
        this.SLTreEm=SoTreEm;
        this.SoLuongVeInt=(SoNL+SoTreEm);
        this.SoLuongVeInt1=(SoNL+SoTreEm);
        lbSoVe.setText(String.valueOf(SoNL));
        lbSoVeTE.setText(String.valueOf(SoTreEm));
        lbSoVeKH.setText(String.valueOf(SoNL));
        lbSoVeTEKH.setText(String.valueOf(SoTreEm));
        this.lbSoVeKH.setText(String.valueOf(SLNgL));
        this.lbSoVeTEKH.setText(String.valueOf(SLTreEm));
        this.HanhLy = this.cbxHanhLy.getValue();
        this.HanhLy1 = this.cbxHanhLyKH.getValue();
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
        double tongVipNL= GiaVeInt*1.5;
        double tongTE=GiaVeInt*0.3;
        double tongVipTE=GiaVeInt*0.3*1.5;
        double tongVipNL1= GiaVeInt1*1.5;
        double tongTE1=GiaVeInt1*0.3;
        double tongVipTE1=GiaVeInt1*0.3*1.5;
        if(LoaiVeInt==1){
            lbGiaNL.setText(String.valueOf(Math.round(tongVipNL)));
            lbGiaTE.setText(String.valueOf(Math.round(tongVipTE)));
            lbGiaNLKH.setText(String.valueOf(Math.round(tongVipNL1)));
            lbGiaTEKH.setText(String.valueOf(Math.round(tongVipTE1)));
        }
        else {
            lbGiaNL.setText(String.valueOf(GiaVeInt));
            lbGiaTE.setText(String.valueOf(Math.round(tongTE)));
            lbGiaNLKH.setText(String.valueOf(GiaVeInt1));
            lbGiaTEKH.setText(String.valueOf(Math.round(tongTE1)));
        }
        tongtien=Double.parseDouble(lbGiaNL.getText())*Integer.parseInt(lbSoVe.getText())+Double.parseDouble(lbGiaTE.getText())*Integer.parseInt(lbSoVeTE.getText())+Double.parseDouble(lbGiaNLKH.getText())*Integer.parseInt(lbSoVeKH.getText())+Double.parseDouble(lbGiaTEKH.getText())*Integer.parseInt(lbSoVeTEKH.getText());
        this.TongTien.setText(String.valueOf(Math.round(tongtien)));
    }

    @FXML
    private void handleTongTien(MouseEvent event) {
    }

    
    
}
