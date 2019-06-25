/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import Model.KhachHang;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author blues
 */
public class KhachHangDAO {
    public static void themKhachHang(KhachHang kh) throws SQLException {
        String sql = "INSERT INTO KHACHHANG(TENKH, EMAIL, CMND, TUOI, DIACHI, LOAIHK) VALUES ('" + kh.getTenKH() + "', '"+ kh.getEmail()+"',"+ kh.getCMND()+", '" + kh.getTuoi() + "', '"+kh.getDiaChi()+"', " + kh.getLoaiHK() + ")";
        System.out.println(sql);
        
        int rs = DBConnect.dbExcuteUpdate(sql);
        DBConnect.dbDisconnect();
    }

    public static String getMaKhachHang(KhachHang kh) throws SQLException {
        String sql = "SELECT MAKHACHHANG FROM KHACHHANG "
                + "WHERE EMAIL ='" + kh.getEmail() + "' AND TENKH = '" + kh.getTenKH() + "' AND Tuoi = "+ kh.getTuoi() + " AND DIACHI = '" +kh.getDiaChi() +"'";
        String MaKH = "";
        ResultSet rs = DBConnect.dbExcute(sql);
        System.out.println(sql);
        while(rs.next()) {
            MaKH = rs.getString("MAKHACHHANG");
        }
        DBConnect.dbDisconnect();
        return MaKH;
    }
}
