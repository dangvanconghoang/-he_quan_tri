/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
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

            ds.setGioKH(rs.getString("GioKhoiHanh"));
            ds.setGiaVe((rs.getDouble("GiaVe")));
            ds.setMaHMB(rs.getString("TenHMB"));
            ds.setMaCB(rs.getString("MaCB"));
            ds.setTgBay((int) rs.getDouble("ThoiGianBay"));
            ds.setDiemDen(rs.getString("DiemDen"));
            ds.setDiemKhoiHanh(rs.getString("DiemKhoiHanh"));
        } catch (SQLException ex) {
            Logger.getLogger(DanhSachChuyenBayDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ds;
    }
    
    public ObservableList<DanhSachChuyenBay> getDanhSachChuyenBays(String DiemKH,String DiemDen,int sv, int loaive, LocalDate ngaykh) throws SQLException{
        
        ObservableList<DanhSachChuyenBay> ds = FXCollections.observableArrayList();
//        ObservableList<String> maVeList = FXCollections.observableArrayList();
        
        String sql="exec danhsachchuyenbay N'"+DiemKH+"',N'"+DiemDen+"',"+sv+","+loaive+",'"+ngaykh+"'";
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
        String sql="exec danhsachchuyenbaykh N'"+DiemKH+"',N'"+DiemDen+"','"+HangMB+"',"+sv+","+loaive+",'"+ngaykh+"'";
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
//        System.out.println(rs.);
        return ds;
    } 
    
}  

