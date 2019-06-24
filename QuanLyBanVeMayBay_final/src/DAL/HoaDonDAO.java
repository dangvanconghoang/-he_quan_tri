/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import Model.HoaDon;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author blues
 */
public class HoaDonDAO {
    public static void lapHoaDon(HoaDon hoadon) throws SQLException {
        String sql = "INSERT INTO HOADON(KHACHHANG, TINHTRANG, NGAYLAP) VALUES ('" +hoadon.getKhachHang().getMaKhachHang()+"', " + hoadon.getTinhTrang() + ", TO_DATE('" + hoadon.getNgayLap().toString() +"', 'yyyy-mm-dd'))";
        System.out.println(sql);
        DBConnect.dbExcuteUpdate(sql);
        DBConnect.dbDisconnect();
    }

    public static String layMaHoaDon(HoaDon hoadon) throws SQLException {
        String sql = "SELECT MAHOADON FROM HOADON "
                + "WHERE KHACHHANG = '" + hoadon.getKhachHang().getMaKhachHang() + "' AND TINHTRANG = " + hoadon.getTinhTrang() +" AND NGAYLAP = TO_DATE('" + hoadon.getNgayLap().toString() + "', 'yyyy-mm-dd')";
        System.out.println(sql);
        String MaHD = "";
        ResultSet rs = DBConnect.dbExcute(sql);
        
        while(rs.next()) {
            MaHD = rs.getString("MAHOADON");
        }
        
        DBConnect.dbDisconnect();
        return MaHD;

        
    }
}
