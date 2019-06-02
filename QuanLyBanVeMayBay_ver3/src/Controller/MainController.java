/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXNodesList;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author win10pro
 */
public class MainController implements Initializable {

    @FXML
    private JFXButton btnTaoChuyenBay;
    @FXML
    private JFXButton btnDatChuyenBay;
    @FXML
    private JFXButton btnLichSuChuyenBay;
    public static MainController mainController;
    @FXML
    private JFXButton btnThongKe;
    @FXML
    private JFXNodesList NodeList;
    @FXML
    private AnchorPane rootPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public MainController() {
        mainController = this;
    }

    public static MainController getMainController() {
        return mainController;
    }
    
    @FXML
    private void btnTaoChuyenBayClick(ActionEvent event) {
    }

    @FXML
    private void btnDatChuyenBayClick(ActionEvent event) throws IOException {
        loadDatVeMayBay(event);
    }

    @FXML
    private void btnLichSuChuyenBayClick(ActionEvent event) throws IOException {
        loadLichSuBanVe(event);
    }

    @FXML
    private void btnThongKeClick(ActionEvent event) throws IOException{
        loadThongKe(event);
    }
    
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
    
    //Hàm load form
    public void loadLichSuBanVe(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/View/LichSuBanVe.fxml"));
        rootPane.getChildren().setAll(pane);
        GeneralFuntion.FitChildContent(pane);
    }
    
    public void loadThongKe(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/View/ThongKe.fxml"));
        rootPane.getChildren().setAll(pane);
        GeneralFuntion.FitChildContent(pane);
    }
    
    public void loadDatVeMayBay(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/View/DatVeMayBay.fxml"));
        rootPane.getChildren().setAll(pane);
        GeneralFuntion.FitChildContent(pane);
    }
}
