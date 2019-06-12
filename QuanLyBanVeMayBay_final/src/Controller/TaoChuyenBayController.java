/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import Model.TaoChuyenBay;
import Model.TaoChuyenBayDAO;
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
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import  javafx.scene.control.Button;
import java.util.ResourceBundle;
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
   private TableColumn<TaoChuyenBay,String> colDiemKH;
   @FXML
   private TableColumn<TaoChuyenBay,String> colDiemDen;
   @FXML
   private TableColumn<TaoChuyenBay,String> colSBDi;
   @FXML
   private TableColumn<TaoChuyenBay,String> colSBDen;
   @FXML
   private TableColumn<TaoChuyenBay,Integer> colSoGheVipTrong;
   @FXML
   private TableColumn<TaoChuyenBay,Integer> colSoGheThuongTrong;
   @FXML
   private TableColumn<TaoChuyenBay,Date> colNgayKH;
   @FXML
   private TableColumn<TaoChuyenBay,Integer> colTGBay;
   @FXML
   private TableColumn<TaoChuyenBay,Time> colGioKH;
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
    private JFXButton btnThemMoi;
    @FXML
    private JFXComboBox<String> cbHangMB;
    @FXML
    private JFXComboBox<String> cbDiemKH;
    @FXML
    private JFXComboBox<String> cbDiemDen;
    String[] array1 ={"VA","JP","VJ","BA"};
    @FXML
    private JFXTextField tfSBDi;
    @FXML
    private JFXTextField tfSBDen;
    public TaoChuyenBayController() {
    }
    @Override
    public void initialize(URL url, ResourceBundle rb){
        //TODO
        tfSBDi.setEditable(false);
        tfSBDen.setEditable(false);
        btnSua.setDisable(true);
        btnXoa.setDisable(true);
        setCombobox();
        try{
        LoadData();
        }catch (Exception e){
            System.out.println("Loi load data");
        }
    }    
    void setCombobox(){
        cbHangMB.getItems().add(new String("Vietnam Airlines"));
        cbHangMB.getItems().add(new String("Jetstar Pacific"));
        cbHangMB.getItems().add(new String("Vietjet Air"));
        cbHangMB.getItems().add(new String("Bamboo Airways"));
        cbHangMB.setPromptText("Chọn hãng máy bay");
        cbDiemKH.getItems().add(new String("Tp Hồ Chí Minh"));
        cbDiemKH.getItems().add(new String("Hà Nội"));
        cbDiemKH.getItems().add(new String("Đà Nẵng"));
        cbDiemKH.getItems().add(new String("Thừa Thiên - Huế"));
        cbDiemKH.setPromptText("Chọn điểm khởi hành");
        cbDiemDen.getItems().add(new String("Tp Hồ Chí Minh"));
        cbDiemDen.getItems().add(new String("Hà Nội"));
        cbDiemDen.getItems().add(new String("Đà Nẵng"));
        cbDiemDen.getItems().add(new String("Thừa Thiên - Huế"));
        cbDiemDen.setPromptText("Chọn điểm đến");
    }
    void setCellValueFactory(){
         colMaCB.setCellValueFactory(new PropertyValueFactory<TaoChuyenBay, String>("MaCB"));
         colTenHMB.setCellValueFactory(new PropertyValueFactory<TaoChuyenBay, String>("TenHMB"));
         colDiemKH.setCellValueFactory(new PropertyValueFactory<TaoChuyenBay, String>("DiemKhoiHanh"));
         colDiemDen.setCellValueFactory(new PropertyValueFactory<TaoChuyenBay, String>("DiemDen"));
         colSBDi.setCellValueFactory(new PropertyValueFactory<TaoChuyenBay, String>("SanBayDi"));
        colSBDen.setCellValueFactory(new PropertyValueFactory<TaoChuyenBay, String>("SanBayDen"));
        colSoGheVipTrong.setCellValueFactory(new PropertyValueFactory<TaoChuyenBay, Integer>("SoGheVipTrong"));
        colSoGheThuongTrong.setCellValueFactory(new PropertyValueFactory<TaoChuyenBay, Integer>("SoGheThuongTrong"));
        colNgayKH.setCellValueFactory(new PropertyValueFactory<TaoChuyenBay, Date>("NgayKhoiHanh"));
        colTGBay.setCellValueFactory(new PropertyValueFactory<TaoChuyenBay, Integer>("ThoiGianBay"));
        colGioKH.setCellValueFactory(new PropertyValueFactory<TaoChuyenBay, Time>("GioKhoiHanh"));
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
        listChuyenBay=tc.getlistChuyenBay();
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
            btnThem.setDisable(true);
            btnSua.setDisable(false);
            btnXoa.setDisable(false);
            tfMaCB.setEditable(false);
            TaoChuyenBay tcb=tbchuyenbay.getSelectionModel().getSelectedItem();
            tfMaCB.setText(String.valueOf(tcb.getMaCB()));
            if(tcb.getTenHMB().equals("Vietnam Airlines"))
            cbHangMB.getSelectionModel().select(0);
            else if (tcb.getTenHMB().equals("Jetstar Pacific"))
            cbHangMB.getSelectionModel().select(1);
            else if (tcb.getTenHMB().equals("Vietjet Air"))
            cbHangMB.getSelectionModel().select(2);
            else cbHangMB.getSelectionModel().select(3);
            
            if(tcb.getDiemKhoiHanh().equals("Tp Hồ Chí Minh"))
                cbDiemKH.getSelectionModel().select(0);
            else if (tcb.getDiemKhoiHanh().equals("Hà Nội"))
                cbDiemKH.getSelectionModel().select(1);
            else if (tcb.getDiemKhoiHanh().equals("Đà Nẵng"))
                cbDiemKH.getSelectionModel().select(2);
            else cbDiemKH.getSelectionModel().select(3);
            
            if(tcb.getDiemDen().equals("Tp Hồ Chí Minh"))
                cbDiemDen.getSelectionModel().select(0);
            else if (tcb.getDiemDen().equals("Hà Nội"))
                cbDiemDen.getSelectionModel().select(1);
            else if (tcb.getDiemDen().equals("Đà Nẵng"))
                cbDiemDen.getSelectionModel().select(2);
            else cbDiemDen.getSelectionModel().select(3);
            
            tfSBDi.setText(String.valueOf(tcb.getSanBayDi()));
            tfSBDen.setText(String.valueOf(tcb.getSanBayDen()));
            dpNgayKhoiHanh.setValue(LocalDate.parse(tcb.getNgayKhoiHanh().toString()));
            tfThoiGianBay.setText(String.valueOf(tcb.getThoiGianBay()));
            tpGioKhoiHanh.setValue(LocalTime.parse(tcb.getGioKhoiHanh().toString()));
            tfGiaVe.setText(String.valueOf(tcb.getGiaVe()));
        }
    }

    @FXML
    private void btnThemClick(ActionEvent event) {
        TaoChuyenBay tcb=new TaoChuyenBay();
        try {
            tcb.setMaCB(tfMaCB.getText());
            if(String.valueOf(cbHangMB.getValue()).equals("Vietnam Airlines"))
            tcb.setMaHMB("VA");
            else if (String.valueOf(cbHangMB.getValue()).equals("Jetstar Pacific"))
            tcb.setMaHMB("JP");
            else if (String.valueOf(cbHangMB.getValue()).equals("Vietjet Air"))
            tcb.setMaHMB("VJ");
            else tcb.setMaHMB("BA");
            tcb.setDiemKhoiHanh(cbDiemKH.getValue());
            tcb.setDiemDen(cbDiemDen.getValue());
            if(tfSBDi.getText().equals("Tân Sơn Nhất"))
                tcb.setSanBayDi("TSN");
            else if(tfSBDi.getText().equals("Nội Bài"))
                tcb.setSanBayDi("NB");
            else if(tfSBDi.getText().equals("Đà Nẵng"))
                tcb.setSanBayDi("DN");
            else tcb.setSanBayDi("PB");
            if(tfSBDen.getText().equals("Tân Sơn Nhất"))
                tcb.setSanBayDen("TSN");
            else if(tfSBDen.getText().equals("Nội Bài"))
                tcb.setSanBayDen("NB");
            else if(tfSBDen.getText().equals("Đà Nẵng"))
                tcb.setSanBayDen("DN");
            else tcb.setSanBayDen("PB");
            tcb.setNgayKhoiHanh(Date.valueOf(dpNgayKhoiHanh.getValue()));
            tcb.setGioKhoiHanh(Time.valueOf(tpGioKhoiHanh.getValue()));
            tcb.setThoiGianBay(Integer.parseInt(tfThoiGianBay.getText()));
            tcb.setGiaVe(tfGiaVe.getText());
            tc.addChuyenBay(tcb);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Successfully");
            alert.setContentText("Xin chúc mừng!");
            alert.show();
        } catch (Exception e) {
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
            tfSBDi.setText("");
            tfSBDen.setText("");
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
    private void btnSuaClick(ActionEvent event) {
        TaoChuyenBay tcb=new TaoChuyenBay();
        try {
            tcb.setMaCB(tfMaCB.getText());
            if(String.valueOf(cbHangMB.getValue()).equals("Vietnam Airlines"))
            tcb.setMaHMB("VA");
            else if (String.valueOf(cbHangMB.getValue()).equals("Jetstar Pacific"))
            tcb.setMaHMB("JP");
            else if (String.valueOf(cbHangMB.getValue()).equals("Vietjet Air"))
            tcb.setMaHMB("VJ");
            else tcb.setMaHMB("BA");
            tcb.setDiemKhoiHanh(cbDiemKH.getValue());
            tcb.setDiemDen(cbDiemDen.getValue());
            if(tfSBDi.getText().equals("Tân Sơn Nhất"))
                tcb.setSanBayDi("TSN");
            else if(tfSBDi.getText().equals("Nội Bài"))
                tcb.setSanBayDi("NB");
            else if(tfSBDi.getText().equals("Đà Nẵng"))
                tcb.setSanBayDi("DN");
            else tcb.setSanBayDi("PB");
            if(tfSBDen.getText().equals("Tân Sơn Nhất"))
                tcb.setSanBayDen("TSN");
            else if(tfSBDen.getText().equals("Nội Bài"))
                tcb.setSanBayDen("NB");
            else if(tfSBDen.getText().equals("Đà Nẵng"))
                tcb.setSanBayDen("DN");
            else tcb.setSanBayDen("PB");
            tcb.setNgayKhoiHanh(Date.valueOf(dpNgayKhoiHanh.getValue()));
            tcb.setGioKhoiHanh(Time.valueOf(tpGioKhoiHanh.getValue()));
            tcb.setThoiGianBay(Integer.parseInt(tfThoiGianBay.getText()));
            tcb.setGiaVe(tfGiaVe.getText());
            tc.suaChuyenBay(tcb);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Successfully");
            alert.setContentText("Xin chúc mừng!");
            alert.show();
        } catch (Exception e) {
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
            tfSBDi.setText("");
            tfSBDen.setText("");
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
            tfSBDi.setText("");
            tfSBDen.setText("");
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
    private void btnThemMoiClick(ActionEvent event) {
        btnThem.setDisable(false);
        btnSua.setDisable(true);
        btnXoa.setDisable(true);
        tfMaCB.setEditable(true);
        tfMaCB.setText("");
        tfSBDi.setText("");
        tfSBDen.setText("");
        tfGiaVe.setText("");
        tfThoiGianBay.setText("");
        btnThem.setDisable(false);
        btnSua.setDisable(true);
        btnXoa.setDisable(true);
    }

    @FXML
    private void cbHangMBClick(ActionEvent event) {
        if(String.valueOf(cbHangMB.getValue()).equals("Vietnam Airlines"))
            System.out.println("VA");
            else if (String.valueOf(cbHangMB.getValue()).equals("Jetstar Pacific"))
            System.out.println("JP");
            else if (String.valueOf(cbHangMB.getValue()).equals("Vietjet Air"))
            System.out.println("VJ");
            else System.out.println("BA");
    }

    @FXML
    private void cbDiemKHchangeText(ActionEvent event) {
        if(String.valueOf(cbDiemKH.getValue()).equals("Tp Hồ Chí Minh"))
            tfSBDi.setText("Tân Sơn Nhất");
        else if (String.valueOf(cbDiemKH.getValue()).equals("Hà Nội"))
            tfSBDi.setText("Nội Bài");
        else if (String.valueOf(cbDiemKH.getValue()).equals("Đà Nẵng"))
            tfSBDi.setText("Đà Nẵng");
        else tfSBDi.setText("Phú Bài");
    }

    @FXML
    private void cbDiemDenchangeText(ActionEvent event) {
        if(String.valueOf(cbDiemDen.getValue()).equals("Tp Hồ Chí Minh"))
            tfSBDen.setText("Tân Sơn Nhất");
        else if (String.valueOf(cbDiemDen.getValue()).equals("Hà Nội"))
            tfSBDen.setText("Nội Bài");
        else if (String.valueOf(cbDiemDen.getValue()).equals("Đà Nẵng"))
            tfSBDen.setText("Đà Nẵng");
        else tfSBDen.setText("Phú Bài");
    }
    
}
