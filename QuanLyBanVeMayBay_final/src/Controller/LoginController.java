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
    private Hyperlink LinkPass;
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
    private User userChung;
    UserDAO userDAO;
    ObservableList<User> listUser = FXCollections.observableArrayList();
    @FXML
    private JFXButton btnQuayLai;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        userDAO = new UserDAO();
        try {
            listUser = userDAO.getListUser();
            tfTaiKhoan.setText("16521396@gm.uit.edu.vn");
            tfMatKhau.setText("1");
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void btnLoginClick(ActionEvent event) throws IOException {
        User user = new User();
        int userlevel=0;
        user.setEmail(tfTaiKhoan.getText());
        user.setPass(tfMatKhau.getText());
        int check = CheckUser(user, listUser);
        if (check == 0) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("Error");
            alert.setContentText("Không đúng tên hoặc mật khẩu! Vui lòng nhập lại");
            alert.show();
        } else {
            try{
                user.setuserLevel(userDAO.getUserLevel(tfTaiKhoan.getText()));
                userlevel=user.getuserLevel();
            }catch(Exception e){
                System.out.println("Error get userlevel");
            }
            ((Node) event.getSource()).getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/Main.fxml"));
            
            Parent parent = fxmlLoader.load();
            fxmlLoader.<MainController>getController().layUsername(tfTaiKhoan.getText(),userlevel);
            //MainController main = fxmlLoader.<MainController>getController();
            Stage stage = new Stage();
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.setTitle("Login");
            stage.setMaximized(true);
            stage.show();
        }
    }
    
    private int CheckUser(User user, ObservableList<User> list) {
        for (User u : list) {
            if (u.getEmail().equals(user.getEmail())) {
                if (u.getPass().equals(user.getPass())) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
        return 0;
    }

    @FXML
    private void LinkChangePassClick(ActionEvent event) {
        paneDoiPass.setVisible(true);
        tfUsername11.setText(tfTaiKhoan.getText());
        tfPassword.setText(null);
        tfConfirmpass1.setText(null);
        
        btnDoiMk.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int userlevel=0;
                if (tfPassword.getText().length() > 50) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setHeaderText("Message: ");
                    alert.setContentText("Mật khẩu quá dài. vui lòng nhập lại");
                    alert.showAndWait();
                } else {
                    
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setHeaderText("Message: ");
                    alert.setContentText("Bạn chắc chắn đổi mật khẩu? ");
                    alert.showAndWait();
                    User user = new User();
                    user = User.getbyEmail(tfUsername11.getText(), listUser);
                    user.setPass(tfConfirmpass1.getText());
                    try{
                        user.setuserLevel(userDAO.getUserLevel(tfTaiKhoan.getText()));
                        userlevel=user.getuserLevel();
                    }catch(Exception e){
                        System.out.println("Error get userlevel");
                    }
                    userDAO.UpdateUser(user);
                    ((Node) event.getSource()).getScene().getWindow().hide();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/Main.fxml"));
                    Parent parent=null;
                    try {
                        parent = fxmlLoader.load();
                    } catch (IOException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    fxmlLoader.<MainController>getController().layUsername(tfTaiKhoan.getText(),userlevel);             
                    MainController main = fxmlLoader.<MainController>getController();
                    Stage stage = new Stage();
                    Scene scene = new Scene(parent);
                    stage.setScene(scene);
                    stage.setTitle("Login");
                    stage.setMaximized(true);
                    stage.show();
                }
            }
        }
        );
    }
    
    @FXML
    private void btnDoiMkClick(ActionEvent event) {
    }

    @FXML
    private void btnQuayLaiClick(ActionEvent event) {
        paneDoiPass.setVisible(false);
    }

    
}
