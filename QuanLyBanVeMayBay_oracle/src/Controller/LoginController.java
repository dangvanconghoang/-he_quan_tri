/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.User;
import Model.UserDAO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author win10pro
 */
public class LoginController implements Initializable {

    @FXML
    private JFXPasswordField tfMatKhau;
    @FXML
    private JFXButton btnLogin;
    @FXML
    private JFXTextField tfTaiKhoan;
    @FXML
    private Hyperlink LinkDangKy;
    @FXML
    private Hyperlink LinkPass;
    @FXML
    private AnchorPane paneDangKy;
    @FXML
    private JFXPasswordField tfPasswordDK;
    @FXML
    private JFXButton btnDangKy;
    @FXML
    private JFXTextField tfUsernameDK;
    @FXML
    private JFXPasswordField tfPasswordConfirmDK;
    @FXML
    private AnchorPane paneDoiPass;
    @FXML
    private JFXPasswordField tfPassword;
    @FXML
    private JFXButton btnDoiMk;
    @FXML
    private JFXTextField tfUsername11;
    @FXML
    private JFXPasswordField tfConfirmpass1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tfTaiKhoan.setText("16521409@gm.uit.edu.vn");
        tfMatKhau.setText("1");
    }    

    @FXML
    private void btnLoginClick(ActionEvent event) throws IOException {
        ((Node) event.getSource()).getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/Main.fxml"));
        Parent parent = fxmlLoader.load();
        MainController main = fxmlLoader.<MainController>getController();
        Stage stage = new Stage();
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.setTitle("Login");
            stage.setMaximized(true);
            stage.show();
    }

    @FXML
    private void DangKyClick(ActionEvent event) {
    }

    @FXML
    private void LinkChangePassClick(ActionEvent event) {
    }

    @FXML
    private void btnDangKyClick(ActionEvent event) {
    }

    @FXML
    private void btnDoiMkClick(ActionEvent event) {
    }
    
}
