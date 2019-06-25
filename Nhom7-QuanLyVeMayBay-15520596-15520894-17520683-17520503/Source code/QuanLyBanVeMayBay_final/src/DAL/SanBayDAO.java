/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import Controller.GeneralFuntion;
import Controller.MainController;
import DAL.DBConnect;
import Model.HangMayBay;
import Model.SanBay;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javax.naming.spi.DirStateFactory;
import java.sql.Date;
import java.sql.Timestamp;
import java.sql.Time;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
/**
 *
 * @author blues
 */
public class SanBayDAO {
    
    public static ObservableList<String> getThanhPho() throws SQLException {
        String sql = "SELECT DISTINCT THANHPHO FROM SANBAY";
        ResultSet rs = DBConnect.dbExcute(sql);
        ObservableList<String> listThanhPho = FXCollections.observableArrayList();
        
        while(rs.next()) {
            listThanhPho.add(rs.getString("THANHPHO"));
        }
        
        return listThanhPho;
    }
}
