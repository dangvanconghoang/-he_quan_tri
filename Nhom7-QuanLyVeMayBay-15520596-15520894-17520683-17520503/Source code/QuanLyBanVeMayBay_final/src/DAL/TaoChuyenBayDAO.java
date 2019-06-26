/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import Model.TaoChuyenBay;
import Controller.GeneralFuntion;
import Controller.MainController;
import DAL.DBConnect;
import Model.HangMayBay;
import Model.SanBay;
import java.sql.CallableStatement;
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
import javax.swing.*;
/**
 *
 * @author Nam
 */
public class TaoChuyenBayDAO 
{

    public static ObservableList<SanBay> getSanbay() throws SQLException {
        String sql = "SELECT * FROM SANBAY";
        ObservableList<SanBay> list = FXCollections.observableArrayList();
        ResultSet rs = DBConnect.dbExcute(sql);
        
        while(rs.next()) {
            SanBay sb = new SanBay(rs.getString("MaSB"), rs.getString("TenSB"), rs.getString("THANHPHO"));
            list.add(sb);
        }
        
        DBConnect.dbDisconnect();
        
        return list;
    }
    
    public static ObservableList<HangMayBay> getHangMayBay() throws SQLException {
        String sql = "SELECT * FROM HANGMAYBAY";
        ObservableList<HangMayBay> list = FXCollections.observableArrayList();
        ResultSet rs = DBConnect.dbExcute(sql);
        
        while(rs.next()) {
            HangMayBay hmb = new HangMayBay(rs.getString("MaHMB"), rs.getString("TenHMB"));
            list.add(hmb);   
        }
        
        DBConnect.dbDisconnect();
        
        return list;
    }
    
    private TaoChuyenBay createChuyenBay(ResultSet rs) {
        TaoChuyenBay tc = new TaoChuyenBay();
        
        try {
            tc.setMaCB(rs.getString("MaCB"));
            tc.setTenHMB(rs.getString("TenHMB"));
            tc.setHangMayBay(new HangMayBay(rs.getString("MaHMB"), rs.getString("TenHMB")));       
            tc.setSanBayDi(new SanBay(rs.getString("MaSBDi"), rs.getString("TenSBDi"), rs.getString("TpDi")));
            tc.setSanBayDen(new SanBay(rs.getString("MaSBDen"), rs.getString("TenSBDen"), rs.getString("TpDen")));
            tc.setSoGheVipTrong(rs.getInt("SoGheVipTrong"));
            tc.setSoGheThuongTrong(rs.getInt("SoGheThuongTrong"));
            tc.setThoiGianBay(rs.getInt("ThoiGianBay"));
            tc.setThoiGianKhoiHanh(rs.getTimestamp("ThoiGianKhoiHanh"));
            tc.setNgayKhoiHanh(rs.getTimestamp("ThoiGianKhoiHanh").toLocalDateTime().toLocalDate());
            tc.setGioKhoiHanh(rs.getTimestamp("ThoiGianKhoiHanh").toLocalDateTime().toLocalTime());
            tc.setGiaVe(rs.getInt("GiaVe"));
        }
        catch(Exception e) {
            e.printStackTrace();
            System.out.println("can't load ChuyenBay");
        }
        
        return tc;
    }
    public ObservableList<TaoChuyenBay> getlistChuyenBay() throws SQLException {
            String sql = "SELECT MaCB, HangMayBay.MaHMB, TenHMB, sbdi.THANHPHO as TpDi, sbden.THANHPHO as TpDen, sbdi.MaSB as MaSBDi, "
                    + "sbdi.TenSB as TenSBDi, sbden.MaSB as MaSBDen, sbden.TenSB as TenSBDen, "
                    + "SoGheVipTrong,SoGheThuongTrong,ThoiGianBay,ThoiGianKhoiHanh,GiaVe "
                    + "FROM ChuyenBay "
                    + "INNER JOIN HangMayBay ON ChuyenBay.MaHMB = HangMayBay.MaHMB "
                    + "INNER JOIN SANBAY sbdi ON sbdi.MaSB = ChuyenBay.DiemKhoiHanh "
                    + "INNER JOIN SANBAY sbden ON sbden.MaSB = ChuyenBay.DiemDen ";
        System.out.println(sql);
        ObservableList<TaoChuyenBay> list = FXCollections.observableArrayList();
        try {
            ResultSet rs = DBConnect.dbExcute(sql);
            while (rs.next()) { // Chạy từng dòng trong ResultSet
                TaoChuyenBay ls = createChuyenBay(rs); //Gán dòng vào đối tượng qua hàm ở trên
                list.add(ls); // Gán đối tượng vào List
            }
            DBConnect.dbDisconnect();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("Can't load ChuyenBay1");
        }
        return list;
    }
    
     public ObservableList<TaoChuyenBay> getlistTimKiem(String MaCB) throws SQLException {
        String sql = "SELECT MaCB, HangMayBay.MaHMB, TenHMB, sbdi.THANHPHO as TpDi, sbden.THANHPHO as TpDen, sbdi.MaSB as MaSBDi, "
                    + "sbdi.TenSB as TenSBDi, sbden.MaSB as MaSBDen, sbden.TenSB as TenSBDen, "
                    + "SoGheVipTrong,SoGheThuongTrong,ThoiGianBay,ThoiGianKhoiHanh,GiaVe "
                    + "FROM ChuyenBay "
                    + "INNER JOIN HangMayBay ON ChuyenBay.MaHMB = HangMayBay.MaHMB "
                    + "INNER JOIN SANBAY sbdi ON sbdi.MaSB = ChuyenBay.DiemKhoiHanh "
                    + "INNER JOIN SANBAY sbden ON sbden.MaSB = ChuyenBay.DiemDen "
                    + "WHERE ChuyenBay.MaCB = '" + MaCB + "'";
        System.out.println(sql);
        ObservableList<TaoChuyenBay> list = FXCollections.observableArrayList();
        try {
            ResultSet rs = DBConnect.dbExcute(sql);
            if (rs.next()==false)
            {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Thông báo");
            alert1.setHeaderText("Không tìm thấy!!");
            alert1.show();
            }
            else{
            ResultSet rs1 = DBConnect.dbExcute(sql);
            while (rs1.next()) { // Chạy từng dòng trong ResultSet
                TaoChuyenBay tc = createChuyenBay(rs1); //Gán dòng vào đối tượng qua hàm ở trên
                list.add(tc); // Gán đối tượng vào List
            }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("Can't load ChuyenBay");
        }
        return list;
    }
     
//     public void addChuyenBay(TaoChuyenBay tcb)throws SQLException{
//         String sql;
//         System.out.println(sql);
//        try {
//            int stmt = DBConnect.dbExcuteQuery(sql);
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            System.out.println("Can't add ChuyenBay!");
//        }
//     }
//     
     public void suaChuyenBay(TaoChuyenBay tcb)throws SQLException{
         String sql = "{ call suachuyenbay(?, ?, ?, ?, ?, ?, ?) }";
         System.out.println(sql);
         
        try {
            CallableStatement cs = DBConnect.dbExecuteQueryProcedure(sql);
            cs.setString(1, tcb.getMaCB());
            cs.setString(2, tcb.getMaHMB());
            cs.setNString(3, tcb.getSanBayDi().getMaSB());
            cs.setNString(4, tcb.getSanBayDen().getMaSB());
            cs.setTimestamp(5, tcb.getThoiGianKhoiHanh());
            cs.setInt(6, tcb.getThoiGianBay());
            cs.setInt(7, tcb.getGiaVe());
            cs.execute();
            DBConnect.dbDisconnect();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            System.out.println("Can't update ChuyenBay!");
        }
     }
    
    public void xoaChuyenBay(String maCB)throws SQLException{
        String sql ="{ call xoaChuyenBay(?) }";
        System.out.println(sql);
        
        try {
            CallableStatement cs = DBConnect.dbExecuteQueryProcedure(sql);
            cs.setString(1, maCB);
            cs.execute();
            DBConnect.dbDisconnect();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Can't Xoa ChuyenBay!");
        }
    }

    public void addChuyenBay(TaoChuyenBay tcb) throws SQLException {
        String sql = "{ call themChuyenBay(?, ?, ?, ?, ?, ?) }";
        
        CallableStatement cs = DBConnect.dbExecuteQueryProcedure(sql);
        cs.setString(1, tcb.getHangMayBay().getMaHMB());
        cs.setNString(2, tcb.getSanBayDi().getMaSB());
        cs.setNString(3, tcb.getSanBayDen().getMaSB());
        cs.setTimestamp(4, tcb.getThoiGianKhoiHanh());
        cs.setInt(5, tcb.getThoiGianBay());
        cs.setInt(6, tcb.getGiaVe());
        cs.execute();
        DBConnect.dbDisconnect();
    }
}
    
