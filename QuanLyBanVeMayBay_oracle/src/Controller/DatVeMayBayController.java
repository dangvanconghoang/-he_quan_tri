/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ChiTietVe;
import DAL.ChiTietVeDAO;
import Model.DatVeMayBay;
import DAL.DatVeMayBayDAO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import fxsampler.Sample;
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

import javafx.scene.layout.AnchorPane;


/**
 * FXML Controller class
 *
 * @author hoangdang
 */
public class DatVeMayBayController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXComboBox<String> cbBoxKhoiHanh;
    @FXML
    private JFXComboBox<String> cbBoxDiemDen;
    @FXML
    private JFXDatePicker dPNgayDi;
     @FXML
    private JFXDatePicker dPNgayVe;
    @FXML
    private JFXComboBox<Integer> cbBoxTreEm;
    @FXML
    private JFXComboBox<Integer> cbBoxNguoiLon;
    @FXML
    private JFXComboBox<String> cbBoxLoaiGhe;

    @FXML
    private JFXButton btnTimChuyenBay;
    
    ObservableList cbBoxSoLuongVeList = FXCollections.observableArrayList(0,1,2,3 );
    ObservableList cbBoxLoaiGheList = FXCollections.observableArrayList("Thương Gia","Phổ Thông" );
    ObservableList<String> listSanBays = FXCollections.observableArrayList();
    
    private DatVeMayBayDAO datVeDAO;
            private AnchorPane rootPane;

    @FXML
    public void handleTimChuyenBay(ActionEvent event) throws IOException{
        
            if ( cbBoxKhoiHanh.getValue() ==  null  ||  cbBoxDiemDen.getValue() ==  null ||  dPNgayDi.getValue() ==  null ||  cbBoxLoaiGhe.getValue() ==  null ) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Lỗi rồi ");
                alert.setContentText("phải điền dủ thông tin :) ");
                alert.showAndWait();
            }
             DatVeMayBay dvmb = new DatVeMayBay();

            dvmb.setDiemKhoiHanh(cbBoxKhoiHanh.getValue());
            dvmb.setDiemDen(cbBoxDiemDen.getValue());
            dvmb.setNgay(dPNgayDi.getValue());
            dvmb.setSLNguoiLon(cbBoxNguoiLon.getValue());
            dvmb.setSLTreEM(cbBoxTreEm.getValue());
             dvmb.setLoaiVe(1);
            if (cbBoxLoaiGhe.getValue().equals("Thương Gia")) {
                dvmb.setLoaiVe(1);
            } else {dvmb.setLoaiVe(0); }
              
            String DiemDi = dvmb.getDiemKhoiHanh();
            String DiemDen = dvmb.getDiemDen();
            LocalDate NgayDate = dvmb.getNgay();
            int LoaiVe = dvmb.getLoaiVe();
            int SoNL = dvmb.getSLNguoiLon();
            int SoTreE = dvmb.getSLTreEM();
            LocalDate ngayve = this.dPNgayVe.getValue();
            
//            if ( DiemDi ==  DiemDen) {
//                    Alert alert = new Alert(Alert.AlertType.ERROR);
//                    alert.setTitle("Lỗi rồi ");
//                    alert.setContentText("Điểm  đi và điểm đến không được trùng nhau :) ");
//                    alert.showAndWait();
//                }
//           
        
            AnchorPane paneDanhSachChuyenBay = new AnchorPane();

      
            if (ngayve==null) {
                FXMLLoader fXMLLoader = MainController.getMainController().createPage(paneDanhSachChuyenBay, "/View/DanhSachChuyenBay.fxml");
                fXMLLoader.<DanhSachChuyenBayController>getController().ChuyenDuLieu(DiemDi, DiemDen, NgayDate, LoaiVe, SoNL, SoTreE);
                GeneralFuntion.FitChildContent(paneDanhSachChuyenBay);

            } else {
               
                if ( ngayve.isBefore(NgayDate) ) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Lỗi rồi ");
                    alert.setContentText("Ngày về phải lớn hơn này đi  :) ");
                    alert.showAndWait();
                } else {
                    FXMLLoader fXMLLoader = MainController.getMainController().createPage(paneDanhSachChuyenBay, "/View/DanhSachChuyenBay.fxml");
                    fXMLLoader.<DanhSachChuyenBayController>getController().ChuyenDuLieuKhuHoi(DiemDi, DiemDen, NgayDate, ngayve, LoaiVe, SoNL, SoTreE);
                    GeneralFuntion.FitChildContent(paneDanhSachChuyenBay);
                }
            }
            
            

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbBoxNguoiLon.setItems(cbBoxSoLuongVeList);
        cbBoxTreEm.setItems(cbBoxSoLuongVeList);
        cbBoxLoaiGhe.setItems(cbBoxLoaiGheList);
        cbBoxNguoiLon.getSelectionModel().selectFirst();
        cbBoxTreEm.getSelectionModel().selectFirst();
        try {
            LoadData();
        } catch (SQLException ex) {
            Logger.getLogger(DatVeMayBayController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void LoadData()throws SQLException{
        datVeDAO = new DatVeMayBayDAO();
        listSanBays = datVeDAO.getDiaDiemSanBay();
        cbBoxKhoiHanh.setItems(listSanBays);
        cbBoxDiemDen.setItems(listSanBays);
      

    }
    
   
  
}
