/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import Model.ChiTietHoaDon;
import java.sql.SQLException;

/**
 *
 * @author blues
 */
public class ChiTietHoaDonDAO {
    
    public static void lapChiTietHoaDon(ChiTietHoaDon ctHoaDon, String MaHoaDon) throws SQLException {
        String sql = "INSERT INTO CHITIETHOADON(MAHOADON, MAVE) VALUES ('" + MaHoaDon + "', '" + ctHoaDon.getVe().getVe().getMaVe() + "')";
        System.out.println(sql);
        DBConnect.dbExcuteUpdate(sql);
    }
}
