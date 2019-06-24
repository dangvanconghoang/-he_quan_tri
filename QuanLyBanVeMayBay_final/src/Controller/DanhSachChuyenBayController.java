/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DanhSachChuyenBay;
import DAL.DanhSachChuyenBayDAO;
import Model.HangMayBay;
import Model.LichSuBanVe;
import Model.Ve;
import Model.ChiTietVe;
import Model.KhachHang;
import com.jfoenix.controls.JFXButton;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.String;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
/**
 * FXML Controller class
 *
 * @author hoangdang
 */
public class DanhSachChuyenBayController {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXButton btnChonVe;
    @FXML
    private JFXButton btnBack;
    @FXML
    private JFXButton btnNext;
    @FXML
    private Label LuotDi;
    @FXML
    private Label lbNgayDi;
    @FXML
    private Label LuotVeLabel;
    @FXML
    private Label NgayVeLabel;
    @FXML
    private Label DiaDiem;

    @FXML
    private  TableView<DanhSachChuyenBay> table;
    @FXML
    private  TableColumn<DanhSachChuyenBay, LocalTime> giobayColumn;
    @FXML
    private  TableColumn<DanhSachChuyenBay, Integer> thoigianbayColumn;
     @FXML
    private  TableColumn<DanhSachChuyenBay, String> giatienColumn;
    @FXML
    private  TableColumn<DanhSachChuyenBay, HangMayBay> hangmaybayColumn;
    @FXML
    private  TableColumn<DanhSachChuyenBay, String> maCBColumn;
//    @FXML
//    private  TableColumn<DanhSachChuyenBay, String> maVeColumn;
      
    ObservableList<DanhSachChuyenBay> dscb = FXCollections.observableArrayList();

    
    private DanhSachChuyenBayDAO dscbdao;
    private String DiemDi ;
    private String DiemDen;
    private LocalDate NgayDi;
    private LocalDate NgayVe;
    private int LoaiVe;
    private int sv;
    private int SoNL;
    private int SoTreEm;
    private String HangMBString;
    private String MaCBLuotDi;
    private String MaCBLuotVe;
    private int TGbay;
    private int GiaVe;
    private String GioKH;
    private boolean luot;
    
    ObservableList<Ve> listVeDat = FXCollections.observableArrayList();
    ObservableList<ChiTietVe> listChiTietVe = FXCollections.observableArrayList();
    ObservableList<KhachHang> listKhachHang = FXCollections.observableArrayList();
    
    DanhSachChuyenBay ctChuyenBay = new DanhSachChuyenBay();

    public void ChuyenDuLieu(String DiemDi,String DiemDen, LocalDate NgayDi, int SoNL, int SoTreEm, boolean luot, LocalDate NgayVe){
        this.DiemDi = DiemDi;
        this.DiemDen = DiemDen;
        this.NgayDi = NgayDi;
        this.NgayVe = NgayVe;
        this.SoNL = SoNL;
        this.SoTreEm = SoTreEm;
        this.sv = (SoNL+SoTreEm);
        this.luot = luot;
        
        if(this.luot == true)
            this.LuotDi.setText("Chọn lượt về");
        
        this.lbNgayDi.setText(NgayDi.toString());
        
        if(this.NgayVe != null)
            this.NgayVeLabel.setText(NgayVe.toString());

        this.DiaDiem.setText(DiemDi+ " - " +DiemDen);
        
        try{
            LoadData();
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
    @FXML
    public void handleBack(ActionEvent event) throws IOException{
        AnchorPane paneChiTietChuyenBay = new AnchorPane();
        FXMLLoader fXMLLoader = MainController.getMainController().createPage(paneChiTietChuyenBay, "/View/DatVeMayBay.fxml");
        paneChiTietChuyenBay.getChildren().add(paneChiTietChuyenBay); 
        GeneralFuntion.FitChildContent(paneChiTietChuyenBay);
    }
    
    public void ChuyenDuLieuKhuHoi(String MaCB, ObservableList<Ve> listVeDat, ObservableList<KhachHang> listKhachHang, ObservableList<ChiTietVe> listChiTietVe) {
        this.MaCBLuotDi = MaCB;
        this.listVeDat = listVeDat;
        this.listKhachHang = listKhachHang;
        this.listChiTietVe = listChiTietVe;
    }
    
    // Load dữ liệu lên table
    void LoadData()throws SQLException{
        dscbdao = new DanhSachChuyenBayDAO();
        dscb = dscbdao.getDanhSachChuyenBay(this.DiemDi, this.DiemDen, (this.luot == false) ? this.NgayDi : this.NgayVe);   
        setCellValueFactory();
        table.setItems(dscb);
    }
      //Gán giá trị vào cho cột
    void setCellValueFactory(){
        maCBColumn.setCellValueFactory(new PropertyValueFactory<DanhSachChuyenBay, String>("MaCB"));
        hangmaybayColumn.setCellValueFactory(new PropertyValueFactory<DanhSachChuyenBay, HangMayBay>("HangMayBay"));
        giobayColumn.setCellValueFactory(new PropertyValueFactory<DanhSachChuyenBay, LocalTime>("GioKhoiHanh"));
        thoigianbayColumn.setCellValueFactory(new PropertyValueFactory<DanhSachChuyenBay, Integer>("TgBay"));
        giatienColumn.setCellValueFactory(new PropertyValueFactory<DanhSachChuyenBay, String>("GiaVe"));
    }

    @FXML
    public void handleNext(ActionEvent event) throws IOException, SQLException{
        ctChuyenBay = table.getSelectionModel().getSelectedItem();
//        ctChuyenBay.setDiemDen(DiemDen);
//        ctChuyenBay.setDiemKhoiHanh(DiemDi);
        ctChuyenBay.setNgayBayDate(NgayDi);
        ctChuyenBay.setLoaiVe(LoaiVe);
        ctChuyenBay.setSLNgL(SoNL);
        ctChuyenBay.setSLTreEm(SoTreEm);

        this.GioKH = ctChuyenBay.getGioKH();
//        this.HangMBString= ctChuyenBay.getMaHMB();
        this.TGbay = ctChuyenBay.getTgBay();
        this.GiaVe =(int) ctChuyenBay.getGiaVe();
        
        if(this.luot == false)
            this.MaCBLuotDi = ctChuyenBay.getMaCB();
        else
            this.MaCBLuotVe = ctChuyenBay.getMaCB();
        
        if ( ctChuyenBay.getGioKhoiHanh() == null ) {
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Lỗi rồi ");
          alert.setContentText("Vui lòng chọn chuyến bay :) ");
          alert.showAndWait();
        }
        if (this.NgayVe != null) {
            if(this.luot == true) {
                AnchorPane paneDanhSachChuyenBay1 = new AnchorPane();
                FXMLLoader fXMLLoader = MainController.getMainController().createPage(paneDanhSachChuyenBay1, "/View/NhapThongTin.fxml");
                fXMLLoader.<NhapThongTinController>getController().ChuyenDuLieuKhuHoi(MaCBLuotDi, listVeDat, listKhachHang, listChiTietVe);
                fXMLLoader.<NhapThongTinController>getController().ChuyenDuLieu(MaCBLuotVe, LoaiVe, SoNL, SoTreEm, DiemDi, DiemDen, NgayDi, NgayVe, true);
                GeneralFuntion.FitChildContent(paneDanhSachChuyenBay1);     
            }
            else {
                AnchorPane paneThanhToan = new AnchorPane();
                FXMLLoader fXMLLoader = MainController.getMainController().createPage(paneThanhToan, "/View/NhapThongTin.fxml");
                fXMLLoader.<NhapThongTinController>getController().ChuyenDuLieu(MaCBLuotDi, LoaiVe, SoNL, SoTreEm, DiemDi, DiemDen, NgayDi, NgayVe, false);
                GeneralFuntion.FitChildContent(paneThanhToan);
            }
        } else {
            AnchorPane paneThanhToan = new AnchorPane();
            FXMLLoader fXMLLoader = MainController.getMainController().createPage(paneThanhToan, "/View/NhapThongTin.fxml");
            fXMLLoader.<NhapThongTinController>getController().ChuyenDuLieu(MaCBLuotDi, LoaiVe, SoNL, SoTreEm, DiemDi, DiemDen, NgayDi, NgayVe, false);
            GeneralFuntion.FitChildContent(paneThanhToan);
        }
    }
}
