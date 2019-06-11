/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import Model.DanhSachChuyenBay;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javax.naming.spi.DirStateFactory;

/**
 *
 * @author hoangdang
 */
public class DanhSachChuyenBayDAO {
    public DanhSachChuyenBay createDanhSachChuyenBay(ResultSet rs){
        DanhSachChuyenBay ds = new DanhSachChuyenBay();
        try {

            ds.setThoiGianKhoiHanh(rs.getTimestamp("ThoiGianKhoiHanh"));
            ds.setGiaVe(rs.getDouble("GiaVe"));
            ds.setMaHMB(rs.getString("MaHMB"));
            ds.setMaCB(rs.getString("MaCB"));
//            ds.setMaVe(rs.getString("MaVe"));
//            ds.setTgBay((int) rs.getDouble("ThoiGianBay"));
            ds.setDiemDen(rs.getString("DiemDen"));
            ds.setDiemKhoiHanh(rs.getString("DiemKhoiHanh"));
        } catch (SQLException ex) {
            Logger.getLogger(DanhSachChuyenBayDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ds;
    }
    
    public ObservableList<DanhSachChuyenBay> getDanhSachChuyenBay() throws SQLException{
        
        ObservableList<DanhSachChuyenBay> ds = FXCollections.observableArrayList();
//        ObservableList<String> maVeList = FXCollections.observableArrayList();
        
        String sql="SELECT * FROM CHUYENBAY";
        System.out.println(sql);
        try {
            ResultSet rs = DBConnect.dbExcute(sql);
            while (rs.next()) {
                DanhSachChuyenBay chuyenBay = createDanhSachChuyenBay(rs);
                ds.add(chuyenBay);
//                maVeList.add(rs.getString("MaVe"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DanhSachChuyenBayDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("k the load danh sach chuyen bay");
        }
//        System.out.println(rs.);
        return ds;
    } 
    public ObservableList<DanhSachChuyenBay> getDanhSachChuyenBays1(String DiemKH,String DiemDen, String HangMB,int sv, int loaive, LocalDate ngaykh) throws SQLException{
        
        ObservableList<DanhSachChuyenBay> ds = FXCollections.observableArrayList();
        String sql="exec danhsachchuyenbaykh '"+DiemKH+"','"+DiemDen+"','"+HangMB+"',"+sv+","+loaive+",'"+ngaykh+"'";
        System.out.println(sql);
        try {
            ResultSet rs = DBConnect.dbExcute(sql);
            while (rs.next()) {
                DanhSachChuyenBay chuyenBay = createDanhSachChuyenBay(rs);   
                ds.add(chuyenBay);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DanhSachChuyenBayDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("k the load danh sach chuyen bay");
        }
        DBConnect.dbDisconnect();

//        System.out.println(rs.);
        return ds;
    } 
    
    public static void main(String[] args) throws SQLException {
    	DanhSachChuyenBayDAO a = new DanhSachChuyenBayDAO();
        ObservableList<DanhSachChuyenBay> ds = a.getDanhSachChuyenBay();
        for(DanhSachChuyenBay e : ds) {
        	System.out.println(e.getMaCB() + " " +e.getGiaVe() + " " + e.getMaHMB());
        }
    }
}  

