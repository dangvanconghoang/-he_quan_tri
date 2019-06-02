/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.LichSuBanVeDAO;
import Model.LichSuBanVe;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.sun.javafx.scene.control.skin.TableViewSkin;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.sql.SQLException;
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
public class LichSuBanVeController implements Initializable {

    @FXML
    private TableColumn<LichSuBanVe,String> colMaVe;
    @FXML
    private TableColumn<LichSuBanVe,String> colMaCB;
    @FXML
    private TableColumn<LichSuBanVe,String> colTen;
    @FXML
    private TableColumn<LichSuBanVe,Integer> colGiaVe;
    @FXML
    private TableColumn<LichSuBanVe,Void> colChiTiet;
    @FXML
    private JFXButton btnTim;
    @FXML
    private JFXTextField tfTim;
    ObservableList<LichSuBanVe> listBanVe = FXCollections.observableArrayList();
    private LichSuBanVeDAO ls;
    @FXML
    private TableView<LichSuBanVe> tbBanVe;
    private AnchorPane rootPane;
    @FXML
    private JFXButton btnShow;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try{
        LoadData();
        }catch (SQLException e){
            System.out.println("Can't load data in initialize");
        }
        addButtonToTable();
    }    
    // Load dữ liệu lên table
    void LoadData()throws SQLException{
        ls = new LichSuBanVeDAO();
        listBanVe=ls.getlistLichSu();
        setCellValueFactory();
        tbBanVe.setItems(listBanVe);
    }
    
    //Gán giá trị vào cho cột
    void setCellValueFactory(){
        colMaVe.setCellValueFactory(new PropertyValueFactory<LichSuBanVe, String>("MaVe"));
        colMaCB.setCellValueFactory(new PropertyValueFactory<LichSuBanVe, String>("MaCB"));
        colTen.setCellValueFactory(new PropertyValueFactory<LichSuBanVe, String>("TenHK"));
        colGiaVe.setCellValueFactory(new PropertyValueFactory<LichSuBanVe, Integer>("Gia"));
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
    // Hiệu chỉnh độ dài cột
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
    
    //Tạo trang con để chuyển trang
    public FXMLLoader createPage(AnchorPane pane, String loc) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(loc));
        try {
            pane = fxmlLoader.load();
            rootPane.getChildren().add((Node) pane);
            GeneralFuntion.FitChildContent(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fxmlLoader;
    }
    
    //Thêm 1 nút vào table
    private void addButtonToTable(){
        Callback<TableColumn<LichSuBanVe,Void>,TableCell<LichSuBanVe,Void>> cellFactory = new Callback<TableColumn<LichSuBanVe, Void>, TableCell<LichSuBanVe, Void>>() {
        @Override
        public TableCell<LichSuBanVe, Void> call(final TableColumn<LichSuBanVe, Void> param) {
                final TableCell<LichSuBanVe, Void> cell = new TableCell<LichSuBanVe, Void>() {
                    private final Button btn = new Button("Xem chi tiết");
                    {
                        btn.setOnAction(new EventHandler<ActionEvent>(){
                            @Override
                            public void handle(ActionEvent event){
                            AnchorPane paneChiTietChuyenBay = new AnchorPane();
                            FXMLLoader fXMLLoader = MainController.getMainController().createPage(paneChiTietChuyenBay, "/View/ChiTietVe.fxml"); // Chuyển form
                            fXMLLoader.<ChiTietVeController>getController().ChuyenDuLieu(v_MaCB, v_MaVe); // Truyền dữ liệu qua form ChiTietVe
                            paneChiTietChuyenBay.getChildren().add(paneChiTietChuyenBay); 
                            GeneralFuntion.FitChildContent(paneChiTietChuyenBay);
                            }    
                        });
                    }
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
        }
        };
        colChiTiet.setCellFactory(cellFactory);
    }
    
    
    @FXML
    //hàm search
    private void btnTimClick(ActionEvent event) throws SQLException {
        ls = new LichSuBanVeDAO();
        listBanVe=ls.getlistTim(tfTim.getText());
        setCellValueFactory();
        tbBanVe.setItems(listBanVe);
    }
    public String v_MaVe;
    public String v_MaCB;
    
    
    //Lưu dữ liệu vào biến sau khi click vào table
    @FXML
    private void tbBanVeClick(MouseEvent e) {
        if(MouseButton.PRIMARY == e.getButton() && e.getClickCount() == 1){
        LichSuBanVe ls1 = tbBanVe.getSelectionModel().getSelectedItem();
        v_MaVe=ls1.getMaVe();
        v_MaCB=ls1.getMaCB();
        }
    }
    
    //Hàm chuyển form
    private void ChuyenForm()throws SQLException{
        AnchorPane paneChiTietChuyenBay = new AnchorPane();
                            FXMLLoader fXMLLoader = MainController.getMainController().createPage(paneChiTietChuyenBay, "/View/ChiTietVe.fxml");
                            fXMLLoader.<ChiTietVeController>getController().ChuyenDuLieu(v_MaCB, v_MaVe);
                            paneChiTietChuyenBay.getChildren().add(paneChiTietChuyenBay); 
                            GeneralFuntion.FitChildContent(paneChiTietChuyenBay);
    }

    @FXML
    private void btnShowClick(ActionEvent event) throws SQLException{
        LoadData();
    }

    
}
