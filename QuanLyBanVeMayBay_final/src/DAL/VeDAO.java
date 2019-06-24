/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import Model.LoaiVe;
import Model.Ve;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author blues
 */
public class VeDAO {
    public static ObservableList<Ve> getVe(String MaCB) throws SQLException {
        String sql = "{ call danhsachvecua1cb(?, ?) }";
        CallableStatement cs = DBConnect.dbExecuteQueryProcedure(sql);
        cs.setString(1, MaCB);
        cs.registerOutParameter(2, OracleTypes.CURSOR);
        cs.execute();
        ResultSet rs = (ResultSet) cs.getObject(2);
        
//        String sql = "SELECT MaVe, Ve.MaCB, LoaiVe, TinhTrang, TiLe "
//                + "FROM Ve "
//                + "INNER JOIN ChuyenBay ON ChuyenBay.MaCB = Ve.MaCB "
//                + "WHERE Ve.MaCB = '" + MaCB + "' ";
        ObservableList<Ve> list = FXCollections.observableArrayList();
//        System.out.println(sql);
//        ResultSet rs = DBConnect.dbExcute(sql);
        
        while(rs.next()) {
           Ve ve = new Ve();
           ve.setMaVe(rs.getString("MaVe"));
           ve.setLoaiVe(new LoaiVe(rs.getInt("LoaiVe"), (rs.getInt("LoaiVe") == 0) ? "Thường" : "VIP"));
           ve.setTenTinhTrang((rs.getInt("TinhTrang") == 0) ? "Còn chỗ" : "Đã đặt");
           ve.setTiLe((rs.getFloat("TiLe")));
           list.add(ve);
        }
        
        DBConnect.dbDisconnect();
        
        return list;
        
    }
}
