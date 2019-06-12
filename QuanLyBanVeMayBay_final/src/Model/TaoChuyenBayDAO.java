/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.GeneralFuntion;
import Controller.MainController;
import Model.DBConnect;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javax.naming.spi.DirStateFactory;
import java.sql.Date;
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
    private TaoChuyenBay createChuyenBay(ResultSet rs){
        TaoChuyenBay tc = new TaoChuyenBay();
    try{
        tc.setMaCB(rs.getString("MaCB"));
        tc.setTenHMB(rs.getString("TenHMB"));
        tc.setDiemKhoiHanh(rs.getString("DiemKhoiHanh"));
        tc.setDiemDen(rs.getString("DiemDen"));        
        tc.setSanBayDi(rs.getString("SanBayDi"));
        tc.setSanBayDen(rs.getString("SanBayDen"));
        tc.setSoGheVipTrong(rs.getInt("SoGheVipTrong"));
        tc.setSoGheThuongTrong(rs.getInt("SoGheThuongTrong"));
        tc.setNgayKhoiHanh(rs.getDate("NgayKhoiHanh"));
        tc.setThoiGianBay(rs.getInt("ThoiGianBay"));
        tc.setGioKhoiHanh(rs.getTime("GioKhoiHanh")); 
        tc.setGiaVe(String.valueOf(rs.getDouble("GiaVe")));
    }catch(Exception e){
        e.printStackTrace();
        System.out.println("can't load ChuyenBay");
    }
    return tc;
    }
    public ObservableList<TaoChuyenBay> getlistChuyenBay() throws SQLException {
            String sql = "select c.MaCB,TenHMB,DiemKhoiHanh,DiemDen,SanBayDi,SanBayDen,SoGheVipTrong,SoGheThuongTrong,NgayKhoiHanh,ThoiGianBay,GioKhoiHanh,GiaVe \n" +
    "from ChuyenBay c, HangMayBay h\n" +
    "where c.MaHMB=h.MaHMB";
        System.out.println(sql);
        ObservableList<TaoChuyenBay> list = FXCollections.observableArrayList();
        try {
            ResultSet rs = DBConnect.dbExcute(sql);
            while (rs.next()) { // Chạy từng dòng trong ResultSet
                TaoChuyenBay ls = createChuyenBay(rs); //Gán dòng vào đối tượng qua hàm ở trên
                list.add(ls); // Gán đối tượng vào List
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("Can't load ChuyenBay1");
        }
        return list;
    }
    
     public ObservableList<TaoChuyenBay> getlistTimKiem(String MaCB) throws SQLException {
        String sql = "select c.MaCB,TenHMB,DiemKhoiHanh,DiemDen,SanBayDi,SanBayDen,SoGheVipTrong,SoGheThuongTrong,NgayKhoiHanh,ThoiGianBay,GioKhoiHanh,GiaVe \n" +
    "from ChuyenBay c, HangMayBay h\n" +
    "where c.MaHMB=h.MaHMB and  c.MaCB like '%"+MaCB+"%'";
        System.out.println(sql);
        ObservableList<TaoChuyenBay> list = FXCollections.observableArrayList();
        try {
            ResultSet rs = DBConnect.dbExcute(sql);
            if (rs.first()==false)
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
     
     public void addChuyenBay(TaoChuyenBay tcb)throws SQLException{
         String sql ="exec addChuyenBay '"+tcb.getMaCB()+"','"+tcb.getMaHMB()+"',N'"+tcb.getDiemKhoiHanh()+"',N'"+tcb.getDiemDen()+"','"+tcb.getSanBayDi()+"','"+tcb.getSanBayDen()+"','"+tcb.getNgayKhoiHanh()+"',"+tcb.getThoiGianBay()+",'"+tcb.getGioKhoiHanh()+"',"+Double.parseDouble(tcb.getGiaVe());
         System.out.println(sql);
        try {
            int stmt = DBConnect.dbExcuteQuery(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Can't add ChuyenBay!");
        }
     }
     
     public void suaChuyenBay(TaoChuyenBay tcb)throws SQLException{
         String sql ="update ChuyenBay\n" +
"set MaHMB='"+tcb.getMaHMB()+"', DiemKhoiHanh=N'"+tcb.getDiemKhoiHanh()+"',DiemDen=N'"+tcb.getDiemDen()+"', SanBayDi=N'"+tcb.getSanBayDi()+"', SanBayDen=N'"+tcb.getSanBayDen()+"',NgayKhoiHanh='"+tcb.getNgayKhoiHanh()+"',ThoiGianBay="+tcb.getThoiGianBay()+",GioKhoiHanh='"+ tcb.getGioKhoiHanh()+"',GiaVe='"+tcb.getGiaVe()+"'\n" +
"where MaCB='"+tcb.getMaCB()+"' ";
         System.out.println(sql);
        try {
            int stmt = DBConnect.dbExcuteQuery(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Can't update ChuyenBay!");
        }
     }
    
       public void xoaChuyenBay(String maCB)throws SQLException{
           String sql ="exec xoaCB '"+maCB+"'";
         System.out.println(sql);
        try {
            int stmt = DBConnect.dbExcuteQuery(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Can't Xoa ChuyenBay!");
        }
       }
}
    
