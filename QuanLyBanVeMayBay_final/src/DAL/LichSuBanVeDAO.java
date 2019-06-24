/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import Model.LichSuBanVe;
import DAL.DBConnect;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javax.naming.spi.DirStateFactory;


/**
 *
 * @author win10pro
 */
public class LichSuBanVeDAO {
    //Tạo đối tượng giữ 1 dòng của ResultSet
    private LichSuBanVe createLichSu(ResultSet rs){
        LichSuBanVe ls = new LichSuBanVe();
        try{
            ls.setMaVe(rs.getString("MaVe"));
            ls.setTenHK(rs.getString("TenKH"));
            ls.setMaCB(rs.getString("MaCB"));
            ls.setGia(rs.getInt("Gia"));
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.print("Can't load Lich");
        }
        return ls;
    }
    
    // Chạy hàm select rồi gán từng dòng ResultSet vào List
    public ObservableList<LichSuBanVe> getlistLichSu() throws SQLException {
        String sql = "SELECT CHITIETVE.MaVe, CHITIETVE.MaCB, TenKH, KHACHHANG.MaKhachHang, LoaiHK, CAST(Gia as int) as Gia "
                + "FROM KHACHHANG "
                + "INNER JOIN CHITIETVE ON CHITIETVE.MaKhachHang = KHACHHANG.MaKhachHang";
        System.out.println(sql);
        ObservableList<LichSuBanVe> list = FXCollections.observableArrayList();
        
        try {
            ResultSet rs = DBConnect.dbExcute(sql);
            while (rs.next()) { // Chạy từng dòng trong ResultSet
                LichSuBanVe ls = createLichSu(rs); //Gán dòng vào đối tượng qua hàm ở trên
                list.add(ls); // Gán đối tượng vào List
            }
            DBConnect.dbDisconnect();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("Can't load LichSu");
        }
        
        return list;
    }
    
    public ObservableList<LichSuBanVe> getlistTim(String MaVe) throws SQLException {
        String sql = "SELECT MaVe, MaCB, TenKH, CAST(Gia as int) as Gia "
                + "FROM CHITIETVE "
                + "INNER JOIN KHACHHANG ON KHACHHANG.MaKhachHang = CHITIETVE.MaKhachHang "
                + "WHERE MaVe = '"+MaVe+"' ";
        System.out.println(sql);
        ObservableList<LichSuBanVe> list = FXCollections.observableArrayList();
        try {
            ResultSet rs = DBConnect.dbExcute(sql);
            if(rs.first() == false) {
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Thông báo");
                alert1.setHeaderText("Không tìm thấy!!");
                alert1.show();
            }
            else {
                ResultSet rs1 = DBConnect.dbExcute(sql);
                while(rs1.next()) { // Chạy từng dòng trong ResultSet
                    LichSuBanVe ls = createLichSu(rs1); //Gán dòng vào đối tượng qua hàm ở trên
                    list.add(ls); // Gán đối tượng vào List
                }
                DBConnect.dbDisconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("Can't load LichSu");
        }
        return list;
    }
    
}
