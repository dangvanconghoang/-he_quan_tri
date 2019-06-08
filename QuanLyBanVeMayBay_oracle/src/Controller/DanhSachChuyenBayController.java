/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DanhSachChuyenBay;
import Model.DanhSachChuyenBayDAO;
import Model.LichSuBanVe;
import com.jfoenix.controls.JFXButton;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.String;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
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
public class DanhSachChuyenBayController implements Initializable {

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
    private Label LuotDiLabel;
    @FXML
    private Label NgayDi;
    @FXML
    private Label LuotVeLabel;
    @FXML
    private Label NgayVeLabel;
    @FXML
    private Label DiaDiem;

    @FXML
    private  TableView<DanhSachChuyenBay> table;
    @FXML
    private  TableColumn<DanhSachChuyenBay, String> giobayColumn;
    @FXML
    private  TableColumn<DanhSachChuyenBay, String> thoigianbayColumn;
     @FXML
    private  TableColumn<DanhSachChuyenBay, String> giatienColumn;
    @FXML
    private  TableColumn<DanhSachChuyenBay, String> hangmaybayColumn;
//    @FXML
//    private  TableColumn<DanhSachChuyenBay, String> maVeColumn;
      
    ObservableList<DanhSachChuyenBay> dscb = FXCollections.observableArrayList();

    
    private DanhSachChuyenBayDAO dscbdao;
    private String DiemDi ;
    private String DiemDen;
    private LocalDate NgayDate;
    private LocalDate NgayVe;
    private int LoaiVe;
    private int sv;
    private int SoNL;
    private int SoTreEm;
    private String HangMBString;
    private String MaCBString;
//        private String MaVeString;
    private int TGbay;
    private int GiaVe;
    private String GioKH;
    DanhSachChuyenBay ctChuyenBay = new DanhSachChuyenBay();

    public void ChuyenDuLieu(String DiemDi,String DiemDen, LocalDate NgayDate, int LoaiVe, int SoNL, int SoTreEm ){
        this.DiemDi = DiemDi;
        this.DiemDen=DiemDen;
        this.NgayDate=NgayDate;
        this.LoaiVe=LoaiVe;
        
        this.SoNL=SoNL;
        this.SoTreEm=SoTreEm;
         this.sv = (SoNL+SoTreEm);

        this.NgayDi.setText(NgayDate.toString());
//        this.NgayVe.setText('null');
        this.DiaDiem.setText(DiemDi+ " - " +DiemDen);
        
        
        try{
            LoadData();
        }
        catch(Exception e){
            System.out.println("Can't load data in initialize");
        }

    }
     public void ChuyenDuLieuKhuHoi( String DiemDi,String DiemDen, LocalDate NgayDate,LocalDate NgayVe, int LoaiVe, int SoNL, int SoTreEm ){
        this.DiemDi = DiemDi;
        this.DiemDen=DiemDen;
        this.NgayDate=NgayDate;
        this.NgayVe = NgayVe;
        this.LoaiVe=LoaiVe;
         this.sv = (SoNL+SoTreEm);
        this.SoNL=SoNL;
        this.SoTreEm=SoTreEm;
   
        this.NgayDi.setText(NgayDate.toString());
        this.NgayVeLabel.setText(NgayVe.toString());
        this.DiaDiem.setText(DiemDi+ " - " +DiemDen);
  
       
        try{
            LoadData();
        }
        catch(Exception e){
            System.out.println("Can't load data in initialize");
        }

    }
    @FXML
    public void handleBack(ActionEvent event) throws IOException{
        AnchorPane paneChiTietChuyenBay = new AnchorPane();
        FXMLLoader fXMLLoader = MainController.getMainController().createPage(paneChiTietChuyenBay, "/View/DatVeMayBay.fxml");
        paneChiTietChuyenBay.getChildren().add(paneChiTietChuyenBay); 
        GeneralFuntion.FitChildContent(paneChiTietChuyenBay);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // TODO

    }    
    
    // Load dữ liệu lên table
    void LoadData()throws SQLException{
        dscbdao = new DanhSachChuyenBayDAO();
        dscb=dscbdao.getDanhSachChuyenBays(this.DiemDi, this.DiemDen, this.sv, this.LoaiVe, this.NgayDate);
        
        setCellValueFactory();
        table.setItems(dscb);
    }
      //Gán giá trị vào cho cột
    void setCellValueFactory(){
        hangmaybayColumn.setCellValueFactory(new PropertyValueFactory<DanhSachChuyenBay, String>("MaHMB"));
        giobayColumn.setCellValueFactory(new PropertyValueFactory<DanhSachChuyenBay, String>("GioKH"));
        thoigianbayColumn.setCellValueFactory(new PropertyValueFactory<DanhSachChuyenBay, String>("TgBay"));
        giatienColumn.setCellValueFactory(new PropertyValueFactory<DanhSachChuyenBay, String>("GiaVe"));
    }

    @FXML
    public void handleNext(ActionEvent event) throws IOException{
   
        ctChuyenBay = table.getSelectionModel().getSelectedItem();

        ctChuyenBay.setDiemDen(DiemDen);
        ctChuyenBay.setDiemKhoiHanh(DiemDi);
        ctChuyenBay.setNgayBayDate(NgayDate);
        ctChuyenBay.setLoaiVe(LoaiVe);
        ctChuyenBay.setSLNgL(SoNL);
        ctChuyenBay.setSLTreEm(SoTreEm);

        this.GioKH = ctChuyenBay.getGioKH();
        this.HangMBString=ctChuyenBay.getMaHMB();
        this.TGbay=ctChuyenBay.getTgBay();
        this.GiaVe=(int) ctChuyenBay.getGiaVe();
        this.MaCBString=ctChuyenBay.getMaCB();
        if ( ctChuyenBay.getGioKH() == null ) {
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Lỗi rồi ");
          alert.setContentText("Vui lòng chọn chuyến bay :) ");
          alert.showAndWait();
        }
        if (this.NgayVe != null) {
            AnchorPane paneDanhSachChuyenBay1 = new AnchorPane();

             FXMLLoader fXMLLoader = MainController.getMainController().createPage(paneDanhSachChuyenBay1, "/View/DanhSachChuyenBay_1.fxml");
             fXMLLoader.<DanhSachChuyenBayController1>getController().ChuyenDuLieuKhuHoi(MaCBString,DiemDi, DiemDen, NgayDate, NgayVe, HangMBString,GioKH,TGbay, LoaiVe, SoNL, SoTreEm, GiaVe);

            GeneralFuntion.FitChildContent(paneDanhSachChuyenBay1);
            
        } else {
            AnchorPane paneThanhToan = new AnchorPane();
            FXMLLoader fXMLLoader = MainController.getMainController().createPage(paneThanhToan, "/View/ThanhToan.fxml");
            fXMLLoader.<ThanhToanController>getController().ChuyenDuLieu(MaCBString,DiemDi, DiemDen, NgayDate,TGbay,HangMBString, LoaiVe, ctChuyenBay.getGioKH(), SoNL, SoTreEm,GiaVe);
            GeneralFuntion.FitChildContent(paneThanhToan);
        }
   

    }
}
