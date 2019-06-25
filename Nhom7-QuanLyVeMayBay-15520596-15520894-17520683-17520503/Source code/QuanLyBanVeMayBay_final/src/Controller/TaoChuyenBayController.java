/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import Model.TaoChuyenBay;
import DAL.TaoChuyenBayDAO;
import Model.HangMayBay;
import Model.SanBay;
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
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import  javafx.scene.control.Button;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

/**
 *
 * @author Nam
 */
public class TaoChuyenBayController implements Initializable {
   @FXML
   private TableColumn<TaoChuyenBay,String> colMaCB;
   @FXML
   private TableColumn<TaoChuyenBay,String> colTenHMB;
   @FXML
   private TableColumn<TaoChuyenBay,String> colSBDi;
   @FXML
   private TableColumn<TaoChuyenBay,String> colSBDen;
   @FXML
   private TableColumn<TaoChuyenBay,Integer> colSoGheVipTrong;
   @FXML
   private TableColumn<TaoChuyenBay,Integer> colSoGheThuongTrong;
   @FXML
   private TableColumn<TaoChuyenBay,LocalDate> colNgayKH;
   @FXML
   private TableColumn<TaoChuyenBay,Integer> colTGBay;
   @FXML
   private TableColumn<TaoChuyenBay,LocalTime> colGioKH;
   @FXML
   private TableColumn<TaoChuyenBay,String> colGiaVe;
   ObservableList<TaoChuyenBay> listChuyenBay = FXCollections.observableArrayList();
   private TaoChuyenBayDAO tc;
   private AnchorPane rootPane;
   @FXML
   private JFXButton btnThem;
   @FXML
   private JFXButton btnSua;
   @FXML
   private JFXButton btnXoa;
   @FXML
   private JFXTextField tfMaCB;

    @FXML
    private JFXButton btnTimkiem;
    @FXML
    private JFXDatePicker dpNgayKhoiHanh;
    private JFXTextField tfSBdi;
    @FXML
    private TableView<TaoChuyenBay> tbchuyenbay;
    @FXML
    private JFXTextField tfGiaVe;
    private JFXTextField tpThoiGianBay;
    @FXML
    private JFXTimePicker tpGioKhoiHanh;
    @FXML
    private JFXTextField tfThoiGianBay;
    @FXML
    private JFXTextField tfTimKiem;
    @FXML
    private JFXComboBox<HangMayBay> cbHangMB;
    
    @FXML
    private JFXComboBox<SanBay> cbSBDi;
    
    @FXML
    private JFXComboBox<SanBay> cbSBDen;
    
    ObservableList<HangMayBay> cbBoxHangMBList;

    public TaoChuyenBayController() {
    }
    @Override
    public void initialize(URL url, ResourceBundle rb){
        //TODO
//        tfSBDi.setEditable(false);
//        tfSBDen.setEditable(false);
        btnSua.setDisable(true);
        btnXoa.setDisable(true);
        
        try {
           setCombobox();
        } catch (SQLException ex) {
           Logger.getLogger(TaoChuyenBayController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try{
            LoadData();
        }catch (Exception e){
            System.out.println("Loi load data");
        }
    }    
    void setCombobox() throws SQLException{
        cbHangMB.setItems(TaoChuyenBayDAO.getHangMayBay());
        cbHangMB.setPromptText("Chọn hãng máy bay");
        cbSBDi.setItems(TaoChuyenBayDAO.getSanbay());
        cbSBDi.setPromptText("Chọn sân bay đi");
        cbSBDen.setItems(TaoChuyenBayDAO.getSanbay());
        cbSBDen.setPromptText("Chọn sân bay đến");

    }
    void setCellValueFactory(){
        colMaCB.setCellValueFactory(new PropertyValueFactory<TaoChuyenBay, String>("MaCB"));
        colTenHMB.setCellValueFactory(new PropertyValueFactory<TaoChuyenBay, String>("TenHangMayBay"));
        colSBDi.setCellValueFactory(new PropertyValueFactory<TaoChuyenBay, String>("TenSanBayDi"));
        colSBDen.setCellValueFactory(new PropertyValueFactory<TaoChuyenBay, String>("TenSanBayDen"));
        colSoGheVipTrong.setCellValueFactory(new PropertyValueFactory<TaoChuyenBay, Integer>("SoGheVipTrong"));
        colSoGheThuongTrong.setCellValueFactory(new PropertyValueFactory<TaoChuyenBay, Integer>("SoGheThuongTrong"));
        colTGBay.setCellValueFactory(new PropertyValueFactory<TaoChuyenBay, Integer>("ThoiGianBay"));
        colNgayKH.setCellValueFactory(new PropertyValueFactory<TaoChuyenBay, LocalDate>("NgayKhoiHanh"));
        colGioKH.setCellValueFactory(new PropertyValueFactory<TaoChuyenBay, LocalTime>("GioKhoiHanh"));
        colGiaVe.setCellValueFactory(new PropertyValueFactory<TaoChuyenBay, String>("GiaVe"));
    }
    private static Method columnToFitMethod;

    static {
        try {
            columnToFitMethod = TableViewSkin.class.getDeclaredMethod("resizeColumnToFitContent", TableColumn.class, int.class);
            columnToFitMethod.setAccessible(true);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
    void LoadData()throws SQLException{
        tc = new TaoChuyenBayDAO();
        listChuyenBay = tc.getlistChuyenBay();
        setCellValueFactory();
        tbchuyenbay.setItems(listChuyenBay);
    }
    public static void autoFitTable(TableView tableView) {
        tableView.getItems().addListener(new ListChangeListener<Object>() {
            @Override
            public void onChanged(ListChangeListener.Change<?> c) {
                for (Object column : tableView.getColumns()) {
                    try {
                        columnToFitMethod.invoke(tableView.getSkin(), column, -1);

                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @FXML
    private void TimKiemCick(ActionEvent event) throws SQLException{
        tc = new TaoChuyenBayDAO();
        listChuyenBay=tc.getlistTimKiem(tfTimKiem.getText());
        setCellValueFactory();
        tbchuyenbay.setItems(listChuyenBay);
    }

    @FXML
    private void tbClick(MouseEvent e) {
        if(MouseButton.PRIMARY == e.getButton() && e.getClickCount() == 1){
            btnSua.setDisable(false);
            btnXoa.setDisable(false);
            tfMaCB.setEditable(false);
            TaoChuyenBay tcb = tbchuyenbay.getSelectionModel().getSelectedItem();
            
            tfMaCB.setText(tcb.getMaCB());
            
            cbHangMB.getSelectionModel().select(0);
            cbHangMB.getSelectionModel().select(tcb.getHangMayBay());
//            cbDiemKH.getSelectionModel().select(0);
//            cbDiemKH.getSelectionModel().select(tcb.getDiemKhoiHanh());
//            cbDiemDen.getSelectionModel().select(0);
//            cbDiemDen.getSelectionModel().select(tcb.getDiemDen());
            cbSBDen.getSelectionModel().select(0);
            cbSBDen.getSelectionModel().select(tcb.getSanBayDen());
            cbSBDi.getSelectionModel().select(0);
            cbSBDi.getSelectionModel().select(tcb.getSanBayDi());
            dpNgayKhoiHanh.setValue(LocalDate.parse(tcb.getNgayKhoiHanh().toString()));
            tfThoiGianBay.setText(String.valueOf(tcb.getThoiGianBay()));
            tpGioKhoiHanh.setValue(LocalTime.parse(tcb.getGioKhoiHanh().toString()));
            tfGiaVe.setText(String.valueOf(tcb.getGiaVe()));
        }
    }

    @FXML
    private void btnThemClick(ActionEvent event) throws SQLException {
        TaoChuyenBay tcb = new TaoChuyenBay();
        
        try {
//            tcb.setMaCB(tfMaCB.getText());
            tcb.setHangMayBay(cbHangMB.getSelectionModel().getSelectedItem());
            tcb.setSanBayDi(cbSBDi.getSelectionModel().getSelectedItem());
            tcb.setSanBayDen(cbSBDen.getSelectionModel().getSelectedItem());
            tcb.setNgayKhoiHanh(dpNgayKhoiHanh.getValue());
            tcb.setGioKhoiHanh(tpGioKhoiHanh.getValue());
            LocalDateTime datetime = LocalDateTime.of(dpNgayKhoiHanh.getValue(), tpGioKhoiHanh.getValue());
            tcb.setThoiGianKhoiHanh(Timestamp.valueOf(datetime));
            tcb.setThoiGianBay(Integer.parseInt(tfThoiGianBay.getText()));
            tcb.setGiaVe(Integer.parseInt(tfGiaVe.getText()));
            tc.addChuyenBay(tcb);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Successfully");
            alert.setContentText("Xin chúc mừng!");
            alert.show();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error");
            alert.setContentText("Xin lỗi bạn. Không thể lưu chuyến bay!");
            alert.show();
        }
        
        try {
            LoadData();
            btnThem.setDisable(false);
            btnSua.setDisable(true);
            btnXoa.setDisable(true);
            tfMaCB.setEditable(true);
            tfMaCB.setText("");
//            tfSBDi.setText("");
//            tfSBDen.setText("");
            tfGiaVe.setText("");
            tfThoiGianBay.setText("");
            btnThem.setDisable(false);
            btnSua.setDisable(true);
            btnXoa.setDisable(true);
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Error");
        alert.setContentText("Xin lỗi bạn. Không thể load ds Chuyến bay!");
        alert.show();
        }
    }

    @FXML
    private void btnSuaClick(ActionEvent event) throws SQLException {
        TaoChuyenBay tcb = new TaoChuyenBay();
        
        try {
            tcb.setMaCB(tfMaCB.getText());
            tcb.setMaHMB(cbHangMB.getSelectionModel().getSelectedItem().getMaHMB());
            tcb.setSanBayDi(cbSBDi.getSelectionModel().getSelectedItem());
            tcb.setSanBayDen(cbSBDen.getSelectionModel().getSelectedItem());
            tcb.setThoiGianBay(Integer.parseInt(tfThoiGianBay.getText()));
            tcb.setGiaVe(Integer.parseInt(tfGiaVe.getText()));
            LocalDateTime datetime = LocalDateTime.of(dpNgayKhoiHanh.getValue(), tpGioKhoiHanh.getValue());
            tcb.setThoiGianKhoiHanh(Timestamp.valueOf(datetime));
            tc.suaChuyenBay(tcb);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Successfully");
            alert.setContentText("Xin chúc mừng!");
            alert.show();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error");
            alert.setContentText("Xin lỗi bạn. Không thể sửa chuyến bay!");
            alert.show();
        }
        
        try {
            LoadData();
            btnThem.setDisable(false);
            btnSua.setDisable(true);
            btnXoa.setDisable(true);
            tfMaCB.setEditable(true);
            tfMaCB.setText("");
//            tfSBDi.setText("");
//            tfSBDen.setText("");
            tfGiaVe.setText("");
            tfThoiGianBay.setText("");
            btnThem.setDisable(false);
            btnSua.setDisable(true);
            btnXoa.setDisable(true);
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Error");
        alert.setContentText("Xin lỗi bạn. Không thể load ds Chuyến bay!");
        alert.show();
        }
    }

    @FXML
    private void btnXoaClick(ActionEvent event) {
        try { 
            tc.xoaChuyenBay(tfMaCB.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Successfully");
            alert.setContentText("Xin chúc mừng!");
            alert.show();
        } catch (Exception e) {
             Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Error");
        alert.setContentText("Xin lỗi bạn. Không thể xóa chuyến bay!");
        alert.show();
        }
        try {
            btnThem.setDisable(false);
            btnSua.setDisable(true);
            btnXoa.setDisable(true);
            LoadData();
            tfMaCB.setEditable(true);
            tfMaCB.setText("");
//            tfSBDi.setText("");
//            tfSBDen.setText("");
            tfGiaVe.setText("");
            tfThoiGianBay.setText("");
            btnThem.setDisable(false);
            btnSua.setDisable(true);
            btnXoa.setDisable(true);
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Error");
        alert.setContentText("Xin lỗi bạn. Không thể load ds Chuyến bay!");
        alert.show();
        }
    }


    @FXML
    private void cbHangMBClick(ActionEvent event) {
//        cbHangMB.setOnKeyReleased(event -> {
//        if (event.getCode().equals(KeyCode.ENTER)) {
//            HangMayBay airport = cbHangMB.getSelectionModel().getSelectedItem();
//            System.out.println(airport.getId());
//        }
//    });
        if(String.valueOf(cbHangMB.getValue()).equals("Vietnam Airlines"))
            System.out.println("VA");
            else if (String.valueOf(cbHangMB.getValue()).equals("Jetstar Pacific"))
            System.out.println("JP");
            else if (String.valueOf(cbHangMB.getValue()).equals("Vietjet Air"))
            System.out.println("VJ");
            else System.out.println("BA");
    }

    
    @FXML
    private void cbSanBayDenEvent(ActionEvent event) {
        
    }
    
    @FXML
    private void cbSanBayDiEvent(ActionEvent event) {
        
    }
    
}
