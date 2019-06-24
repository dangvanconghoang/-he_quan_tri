/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import Model.DanhSachChuyenBay;
import Model.HangMayBay;
import Model.SanBay;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javax.naming.spi.DirStateFactory;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author hoangdang
 */
public class DanhSachChuyenBayDAO {
    public DanhSachChuyenBay createDanhSachChuyenBay(ResultSet rs){
        DanhSachChuyenBay ds = new DanhSachChuyenBay();
        
        try {
            ds.setGioKhoiHanh(rs.getTimestamp("ThoiGianKhoiHanh").toLocalDateTime().toLocalTime());
            ds.setNgayKhoiHanh(rs.getTimestamp("ThoiGianKhoiHanh").toLocalDateTime().toLocalDate());
            ds.setGiaVe((rs.getDouble("GiaVe")));
            ds.setHangMayBay(new HangMayBay(rs.getString("MaHMB"), rs.getString("TenHMB")));
            ds.setMaCB(rs.getString("MaCB"));
            ds.setTgBay((int) rs.getDouble("ThoiGianBay"));
            ds.setDiemKhoiHanh(new SanBay(rs.getString("MaSBDi"), rs.getString("TenSBDi"), rs.getString("ThanhPhoDi")));
            ds.setDiemDen(new SanBay(rs.getString("MaSBDen"), rs.getString("TenSBDen"), rs.getString("ThanhPhoDen")));
            ds.setThoiGianKhoiHanh(rs.getTimestamp("ThoiGianKhoiHanh"));
        } catch (SQLException ex) {
            Logger.getLogger(DanhSachChuyenBayDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ds;
    }
    
    public DanhSachChuyenBay getChuyenBay(String MaCB) throws SQLException {
        DanhSachChuyenBay chuyenBay = null;
        String sql = "SELECT MaCB, ChuyenBay.MaHMB, TenHMB, ThoiGianKhoiHanh, GiaVe, ThoiGianBay, "
                + "sanbaydi.MaSB as MaSBDi, sanbaydi.TENSB as TenSBDi, sanbaydi.THANHPHO as ThanhPhoDi, sanbayden.MaSB as MaSBDen, sanbayden.TENSB AS TenSBDen, sanbayden.THANHPHO AS ThanhPhoDen "
                + "FROM ChuyenBay "
                + "INNER JOIN HangMayBay ON ChuyenBay.MaHMB = HangMayBay.MaHMB "
                + "INNER JOIN SANBAY sanbaydi ON ChuyenBay.DiemKhoiHanh = sanbaydi.MaSB "
                + "INNER JOIN SANBAY sanbayden ON CHUYENBAY.DiemDen = sanbayden.MaSB "
                + "WHERE ChuyenBay.MaCB = '" + MaCB + "'";
        System.out.println(sql);
        
        try {
            ResultSet rs = DBConnect.dbExcute(sql);
            
            while (rs.next()) {
                chuyenBay = createDanhSachChuyenBay(rs);
            }
            
            DBConnect.dbDisconnect();
        } catch (SQLException ex) {
            Logger.getLogger(DanhSachChuyenBayDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("k the load danh sach chuyen bay");
        }

        return chuyenBay;
    }
    
    public ObservableList<DanhSachChuyenBay> getDanhSachChuyenBay(String diemkh, String diemden, LocalDate ngaykh) throws SQLException{
        ObservableList<DanhSachChuyenBay> ds = FXCollections.observableArrayList();        
        String sql = "{ call danhsachchuyenbay(?, ?, ?, ?) }" ;
        System.out.println(sql);
        
        try {
            CallableStatement cs = DBConnect.dbExecuteQueryProcedure(sql);
            cs.setString(1, diemkh);
            cs.setString(2, diemden);
            cs.setString(3, ngaykh.toString());
            cs.registerOutParameter(4, OracleTypes.CURSOR);
            cs.execute();
            ResultSet rs = (ResultSet) cs.getObject(4);
            
            while (rs.next()) {
                DanhSachChuyenBay chuyenBay = createDanhSachChuyenBay(rs);
                ds.add(chuyenBay);
            }
            
            DBConnect.dbDisconnect();
        } catch (SQLException ex) {
            Logger.getLogger(DanhSachChuyenBayDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("k the load danh sach chuyen bay");
        }

        return ds;
    }
}  

