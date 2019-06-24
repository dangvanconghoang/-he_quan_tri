/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import Model.DatVeMayBay;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author hoangdang
 */
public class DatVeMayBayDAO {

    public ObservableList <String> getDiaDiemSanBay () throws SQLException{
        String sql = "SELECT TENSB FROM SanBay";
        DatVeMayBay datVeMayBay = new DatVeMayBay();
        ObservableList<String> list = FXCollections.observableArrayList();
        ResultSet rs = DBConnect.dbExcute(sql);
        
        try {
            while (rs.next()) {
                 String sanBay = rs.getString("TenSB");
                 datVeMayBay.setDiemKhoiHanh(sanBay);
                 list.add(sanBay);
            }
            
            DBConnect.dbDisconnect();
        } catch (SQLException ex) {
            Logger.getLogger(DatVeMayBayDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
}
