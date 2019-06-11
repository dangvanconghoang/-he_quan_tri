/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ChiTietVe;
import DAL.ChiTietVeDAO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import com.sun.javafx.scene.control.skin.TableViewSkin;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import  javafx.scene.control.Button;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;


/**
 * FXML Controller class
 *
 * @author win10pro
 */
public class ChiTietVeController implements Initializable {

    @FXML
    private JFXTextField tfMaVe;
    @FXML
    private JFXTextField tfMaCB;
    @FXML
    private JFXTextField tfHangMB;
    @FXML
    private JFXTextField tfTGBay;
    @FXML
    private JFXTextField tfTenHK;
    @FXML
    private JFXTextField tfCMND;
    @FXML
    private JFXTextField tfSDT;
    @FXML
    private JFXTextField tfEmail;
    @FXML
    private JFXTextField tfLoaiHK;
    @FXML
    private JFXTextField tfGiaVe;
    @FXML
    private JFXButton btnHuyVe;
    @FXML
    private JFXButton btnSua;
    @FXML
    private JFXButton btnLuu;
    @FXML
    private JFXTimePicker tpGioKH;
    @FXML
    private JFXDatePicker dpNgayKH;
    ObservableList<ChiTietVe> listChuyenBay = FXCollections.observableArrayList();
    ObservableList<ChiTietVe> listSanBayDen = FXCollections.observableArrayList();
    ObservableList<ChiTietVe> listChiTietVe = FXCollections.observableArrayList();
    private ChiTietVeDAO ct;
    @FXML
    private JFXTextField tfDiemKH;
    @FXML
    private JFXTextField tfDiemDen;
    @FXML
    private JFXTextField tfSanBayDi;
    @FXML
    private JFXTextField tfSanBayDen;
    @FXML
    private JFXTextField tfLoaiVe;
    /**ChiTietVeDAO
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btnLuu.setVisible(false);
        tfMaVe.setEditable(false);
        tfMaCB.setEditable(false);
        tfHangMB.setEditable(false);
        tfDiemKH.setEditable(false);
        tfDiemDen.setEditable(false);
        tfSanBayDi.setEditable(false);
        tfSanBayDen.setEditable(false);
        dpNgayKH.setEditable(false);
        tfTGBay.setEditable(false);
        tpGioKH.setEditable(false);
        tfTenHK.setEditable(false);
        tfCMND.setEditable(false);
        tfSDT.setEditable(false);
        tfEmail.setEditable(false);
        tfLoaiHK.setEditable(false);
        tfLoaiVe.setEditable(false);
        tfGiaVe.setEditable(false);
    }    
    
    //Chuyển dữ liệu từ LichSuBanVe qua from này
    public void ChuyenDuLieu(String MaCB,String MaVe){
        tfMaVe.setText(MaVe); 
        tfMaCB.setText(MaCB);
        try{
            LoadData();
        }
        catch(Exception e){
            System.out.println("Can't load data in initialize");
        }
        if(dpNgayKH.getValue().compareTo(java.time.LocalDate.now())>=0)
            btnHuyVe.setDisable(true);
        else btnHuyVe.setDisable(false);
    }
    
    //Load data
    public void LoadData()throws SQLException{
        ct=new ChiTietVeDAO();
        ChiTietVe ctv=new ChiTietVe();
        ctv=ct.getChuyenBay(tfMaCB.getText());
        tfHangMB.setText(String.valueOf(ctv.getTenHMB()));
        tfDiemKH.setText(String.valueOf(ctv.getDiemKhoiHanh()));
        tfDiemDen.setText(String.valueOf(ctv.getDiemDen()));
        tfSanBayDi.setText(String.valueOf(ctv.getSanBayDi()));
        dpNgayKH.setValue(LocalDate.parse(ctv.getNgayKhoiHanh().toString()));
        tfTGBay.setText(String.valueOf(ctv.getThoiGianBay()));
        tpGioKH.setValue(LocalTime.parse(ctv.getGioKhoiHanh().toString()));
        ctv=ct.getSanBayDen(tfMaCB.getText());
        tfSanBayDen.setText(String.valueOf(ctv.getSanBayDen()));
        ctv=ct.getChiTietVe(tfMaVe.getText());
        tfTenHK.setText(ctv.getTenHK());
        tfCMND.setText(ctv.getCMND());
        tfSDT.setText(ctv.getSDT());
        tfEmail.setText(String.valueOf(ctv.getEmail()));
        if(ctv.getLoaiHK()==false)
            tfLoaiHK.setText("Trẻ em");
        else tfLoaiHK.setText("Người lớn");
        if(ctv.getLoaiVe()==false)
            tfLoaiVe.setText("Thường");
        else tfLoaiVe.setText("Thương gia");
        tfGiaVe.setText(String.valueOf(ctv.getGia()));
    }
    
    //Hủy vé
    @FXML
    private void btnHuyVeClick(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cảnh báo!");
        alert.setHeaderText("Cảnh báo!");
        alert.setContentText("Bạn có thật sự muốn hủy vé không?");
        ButtonType buttonTypeYes = new ButtonType("Đồng ý");
        ButtonType buttonTypeNo = new ButtonType("Hủy");
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
        Optional<ButtonType> result = alert.showAndWait();
        switch (result.get().getText()) {
            case "Đồng ý": {
                try{
                    ct.CapNhatLaiVe(tfMaVe.getText());
                    ct.Huy(tfMaVe.getText());
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("Thông báo");
                    alert1.setHeaderText("Thông báo");
                    alert1.setContentText("Thành công!!");
                    alert1.showAndWait();
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Thông báo");
                    alert2.setHeaderText("Thông báo");
                    alert2.setContentText("Chuyển về lịch sử bản vé!");
                    alert2.showAndWait();
                    ChuyenFormLichSuBanVe();
                }catch(Exception e){
                    System.out.println("Hủy không thành công!");
                }
                }
                break;
            case "Hủy":
                
                break;
            default:
                break;
        }
    }

    @FXML
    private void btnSuaClick(ActionEvent event) {
        btnSua.setVisible(false);
        btnLuu.setVisible(true);
        tfTenHK.setEditable(true);
        tfCMND.setEditable(true);
        tfSDT.setEditable(true);
        tfEmail.setEditable(true);
        tfLoaiHK.setEditable(true);
        tfGiaVe.setEditable(true);
    }

    //Lưu thông tin vừa sửa
    @FXML
    private void btnLuuClick(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION); 
        alert.setTitle("Cảnh báo!");
        alert.setHeaderText("Cảnh báo!");
        alert.setContentText("Bạn có thật sự muốn lưu không?");
        ButtonType buttonTypeYes = new ButtonType("Đồng ý");
        ButtonType buttonTypeNo = new ButtonType("Hủy");
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
        Optional<ButtonType> result = alert.showAndWait();
        switch (result.get().getText()) {
            case "Đồng ý": {
                btnSua.setVisible(true);
                btnLuu.setVisible(false);
                ChiTietVe ct1=new ChiTietVe();
                ct1.setTenHK(tfTenHK.getText());
                ct1.setCMND(tfCMND.getText());
                ct1.setSDT(tfSDT.getText());
                ct1.setEmail(tfEmail.getText());
                ct1.setMaVe(tfMaVe.getText());
                if(tfLoaiHK.getText()=="Người lón")
                ct1.setLoaiHK(false);
                else  ct1.setLoaiHK(true);
                ct1.setGia(tfGiaVe.getText());
                try{
                if(tfLoaiHK.getText().equals("Người lớn")){
                ct.Luu2(ct1);
                }else ct.Luu(ct1);
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Thông báo");
                alert1.setHeaderText("Thông báo");
                alert1.setContentText("Thành công!!");
                alert1.showAndWait();
                btnSua.setVisible(true);
                btnLuu.setVisible(false);
                tfTenHK.setEditable(false);
                tfCMND.setEditable(false);
                tfSDT.setEditable(false);
                tfEmail.setEditable(false);
                tfLoaiHK.setEditable(false);
                tfGiaVe.setEditable(false);
                try {
                    LoadData();
                } catch (Exception e) {
                    System.out.print("Can't load data!!!!!");
                }
                }catch(Exception e){
                    System.out.println("Can't save ChiTietVe!");
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                        alert2.setTitle("Thông báo");
                        alert2.setHeaderText("Thông báo");
                        alert2.setContentText("Lỗi cập nhật dữ liệu!!");
                        alert2.showAndWait();
                }
                try {
                    LoadData();
                } catch (Exception e) {
                    System.out.print("Can't load data!!!!!");
                }
                }
                break;
            case "Hủy":
                try {
                    LoadData();
                } catch (Exception e) {
                    System.out.print("Can't load data!!!!!");
                }
                btnSua.setVisible(true);
                btnLuu.setVisible(false);
                tfTenHK.setEditable(false);
                tfCMND.setEditable(false);
                tfSDT.setEditable(false);
                tfEmail.setEditable(false);
                tfLoaiHK.setEditable(false);
                tfGiaVe.setEditable(false);
                break;
            default:
                break;
        }
    }
    
    //Hàm chuyển form
    public void ChuyenFormLichSuBanVe(){
        AnchorPane paneChiTietChuyenBay = new AnchorPane();
        FXMLLoader fXMLLoader = MainController.getMainController().createPage(paneChiTietChuyenBay, "/View/LichSuBanVe.fxml");
        paneChiTietChuyenBay.getChildren().add(paneChiTietChuyenBay); 
        GeneralFuntion.FitChildContent(paneChiTietChuyenBay);
    }
}
