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
public class DanhSachChuyenBayController1 implements Initializable {

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
    private Label NgayDiLabel;

    @FXML
    private Label NgayVeLabel;
    @FXML
    private Label DiaDiemLabel;

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
    @FXML
    private  TableColumn<DanhSachChuyenBay, String> maVeColumn;
   
    ObservableList<DanhSachChuyenBay> dscb = FXCollections.observableArrayList();

    private int sv;
    private int SoNL;
    private int SoTreEm;
    private LocalDate NgayDi;
    private LocalDate NgayVe;
    
    private String DiemDi ;
    private String DiemDen;
    private int TGbay;
    private String GioKH;
    private String HangMBString;
    private int LoaiVe;
    private int GiaVe;

    private String DiemDi1 ;
    private String DiemDen1;
    private int TGbay1;
    private String GioKH1;
   private int GiaVe1;  
    
    private DanhSachChuyenBayDAO dscbdao;
//    private String DiemDi ;
//    private String DiemDen;
//    private LocalDate NgayDate;
//    private LocalDate NgayVe;
//    private int LoaiVe;
//    private int sv;
//    private int SoNL;
//    private int SoTreEm;
//    private String HangMBString;
    private String MaCBString; 
    private String MaCBString1;
    private String MaVeString; 
    private String MaVeString1;
//    private int TGbay;
//    private int GiaVe;
//    private String GioKH;
//    // khu hoi
//    private String DiemDi1 ;
//    private String DiemDen1;
//    private int TGbay1;
//    private int GiaVe1;
    
    DanhSachChuyenBay ctChuyenBay = new DanhSachChuyenBay();

    
    public void ChuyenDuLieuKhuHoi(String MaVe, String MaCB,String DiemDi,String DiemDen, LocalDate NgayDi,LocalDate NgayVe, String HangMB, String GioKH,int TGBay, int LoaiVe, int SoNL, int SoTreEm, int GiaVe ){
        this.MaCBString = MaCB;
        this.MaVeString = MaVe;
        this.DiemDi = DiemDi;
        this.DiemDen=DiemDen;
        this.NgayDi=NgayDi;
        this.NgayVe = NgayVe;
        this.LoaiVe=LoaiVe;
        this.GiaVe = GiaVe;
         this.sv = (SoNL+SoTreEm);
        this.SoNL=SoNL;
        this.SoTreEm=SoTreEm;
        this.HangMBString = HangMB;
        this.GioKH = GioKH;
        this.TGbay = TGBay;
//        System.out.println("Controller.DanhSachChuyenBayController1.ChuyenDuLieuKhuHoi()"+MaVeString);
        this.NgayDiLabel.setText(NgayDi.toString());
        this.NgayVeLabel.setText(NgayVe.toString());
        this.DiaDiemLabel.setText(DiemDi+ " - " +DiemDen);
        
       this.DiemDi1 = this.DiemDen;
       this.DiemDen1 = this.DiemDi;
           
       
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
        System.out.println("initialize"+this.NgayVe);

    }    
    
    // Load dữ liệu lên table
    void LoadData()throws SQLException{
        dscbdao = new DanhSachChuyenBayDAO();
        dscb=dscbdao.getDanhSachChuyenBays1(this.DiemDi1, this.DiemDen1,this.HangMBString, this.sv, this.LoaiVe, this.NgayVe);
         setCellValueFactory();
        table.setItems(dscb);
    }
      //Gán giá trị vào cho cột
    void setCellValueFactory(){
        hangmaybayColumn.setCellValueFactory(new PropertyValueFactory<DanhSachChuyenBay, String>("MaHMB"));
        giobayColumn.setCellValueFactory(new PropertyValueFactory<DanhSachChuyenBay, String>("GioKH"));
        thoigianbayColumn.setCellValueFactory(new PropertyValueFactory<DanhSachChuyenBay, String>("TgBay"));
        giatienColumn.setCellValueFactory(new PropertyValueFactory<DanhSachChuyenBay, String>("GiaVe"));
        maVeColumn.setCellValueFactory(new PropertyValueFactory<DanhSachChuyenBay, String>("MaVe"));
    }
    
    
    @FXML
    public void handleNext(ActionEvent event) throws IOException{
         if ( ctChuyenBay.getMaVe() == null ) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Lỗi rồi ");
                alert.setContentText("Vui lòng chọn chuyến bay :) ");
                alert.showAndWait();
            }
        ctChuyenBay = table.getSelectionModel().getSelectedItem();
////        ctChuyenBay.setMaHMB(HangMBString);
//        ctChuyenBay.setDiemDen(DiemDen);
//        ctChuyenBay.setDiemKhoiHanh(DiemDi);
//        ctChuyenBay.setNgayBayDate(NgayDate);
//        ctChuyenBay.setLoaiVe(LoaiVe);
//        ctChuyenBay.setSLNgL(SoNL);
//        ctChuyenBay.setSLTreEm(SoTreEm);
//        
//        this.HangMBString=ctChuyenBay.getMaHMB();
        this.TGbay1=ctChuyenBay.getTgBay();
        this.GioKH1 = ctChuyenBay.getGioKH();
        this.GiaVe1=(int) ctChuyenBay.getGiaVe();
        this.MaCBString1=ctChuyenBay.getMaCB();
        this.MaVeString1=ctChuyenBay.getMaVe();

       
        AnchorPane paneThanhToan = new AnchorPane();
        FXMLLoader fXMLLoader = MainController.getMainController().createPage(paneThanhToan, "/View/ThanhToan.fxml");
        fXMLLoader.<ThanhToanController>getController().ChuyenDuLieuKhuHoi(MaVeString,MaVeString1,MaCBString,MaCBString1,DiemDi, DiemDen,DiemDi1, DiemDen1, NgayDi, NgayVe,GioKH, GioKH1,TGbay,TGbay1,HangMBString, LoaiVe, SoNL, SoTreEm,GiaVe,GiaVe1);
        GeneralFuntion.FitChildContent(paneThanhToan);
// 
//   

    }
}
