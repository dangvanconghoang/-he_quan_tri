/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author hoangdang
 */
public class ThanhToanDAO {
    public void LuuThongTinVeVoDB(ThanhToan tt)throws SQLException{
            String macb = tt.getMaCBString();
            int sv = tt.getSove();
            int loaiVe = tt.getLoaiVeInt();
            double tienve = tt.getTienve();
            String tenhk = tt.getKhachhangString();
            String cmnd = tt.getCmnd();
            String sdt = tt.getSdt();
            String email = tt.getEmail();
            String sql = "exec insertVe "+"'"+macb+"'"+","+sv+","+loaiVe+","+tenhk+","+loaiVe+","+tienve;
            try{
            int rs;
            rs = DBConnect.dbExcuteQuery(sql);
   
            }catch (SQLException e){
                System.err.println("Can't insert ticket!");
            }
    }
    
}
