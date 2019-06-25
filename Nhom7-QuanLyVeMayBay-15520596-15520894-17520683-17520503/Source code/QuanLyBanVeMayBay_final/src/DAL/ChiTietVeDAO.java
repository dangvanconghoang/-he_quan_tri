/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import Model.KhachHang;
import Model.DanhSachChuyenBay;
import DAL.DBConnect;
import Model.ChiTietVe;
import Model.HangMayBay;
import Model.LoaiVe;
import Model.SanBay;
import Model.Ve;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javax.naming.spi.DirStateFactory;
import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author win10pro
 */
public class ChiTietVeDAO {

    public static void themChiTietVe(Ve ve, KhachHang kh, String MaCB, int Gia) throws SQLException {
        String sql = "INSERT INTO CHITIETVE(MAVE, MACB, MAKHACHHANG, GIA) VALUES ('"+ ve.getMaVe()+"', '" +MaCB +"', '"+ kh.getMaKhachHang()+"',"+Gia+")";
        String sql1 = "UPDATE VE SET TINHTRANG = 1 WHERE MAVE = '" + ve.getMaVe() + "'";
        System.out.println(sql);
        
        int result = DBConnect.dbExcuteUpdate(sql);
        int result2 = DBConnect.dbExcuteUpdate(sql1);
        DBConnect.dbDisconnect();
        
    }
    
    //Lấy kết quả từ resultset bỏ vào model
    private DanhSachChuyenBay createChuyenBay(ResultSet rs){
        DanhSachChuyenBay cb = new DanhSachChuyenBay();
        
        try{
            cb.setMaCB(rs.getString("MaCB"));
            cb.setHangMayBay(new HangMayBay(rs.getString("MaHMB"), rs.getString("TenHMB")));
            cb.setDiemKhoiHanh(new SanBay(rs.getString("MaSBDi"),rs.getString("TenSBDi"), rs.getString("ThanhPhoDi")));
            cb.setDiemDen(new SanBay(rs.getString("MaSBDen"), rs.getString("TenSBDen"), rs.getString("ThanhPhoDen")));
            cb.setThoiGianKhoiHanh(rs.getTimestamp("ThoiGianKhoiHanh"));
            cb.setNgayKhoiHanh(rs.getTimestamp("ThoiGianKhoiHanh").toLocalDateTime().toLocalDate());
            cb.setGioKhoiHanh(rs.getTimestamp("ThoiGianKhoiHanh").toLocalDateTime().toLocalTime());
            cb.setTgBay(rs.getInt("ThoiGianBay"));
        }catch(Exception e){
            e.printStackTrace();
            System.out.print("Can't load ChuyenBay1");
        }
        
        return cb;
    }
    
    //chạy hàm select rồi bỏ vào resultset rồi bỏ vào model
    public DanhSachChuyenBay getChuyenBay(String MaCB)throws SQLException{
        DanhSachChuyenBay cb = new DanhSachChuyenBay();
        String sql = "SELECT MaCB, ChuyenBay.MaHMB, TenHMB, sbdi.MaSB as MaSBDi, sbdi.TenSB as TenSBDi, sbdi.THANHPHO as ThanhPhoDi, "
                + "sbden.MaSB as MaSBDen, sbden.TenSB as TenSBDen, sbden.THANHPHO as ThanhPhoDen, ThoiGianKhoiHanh, ThoiGianBay "
                + "FROM ChuyenBay "
                + "INNER JOIN HangMayBay ON ChuyenBay.MaHMB = HangMayBay.MaHMB "
                + "INNER JOIN SanBay sbdi ON ChuyenBay.DiemKhoiHanh = sbdi.MaSB "
                + "INNER JOIN SanBay sbden ON ChuyenBay.DiemDen = sbden.MaSB "
                + "WHERE MaCB = '" + MaCB + "'";
        
        System.out.println(sql);
        
        try {
            ResultSet rs1 = DBConnect.dbExcute(sql); 
            rs1.next();
            cb = createChuyenBay(rs1); //Gán dòng vừa select vào model qua hàm ở trên
            System.out.println(cb.getMaCB());
            DBConnect.dbDisconnect();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("Can't load ChuyenBay");
        }
        
        return cb;
    }
    
    public KhachHang createKhachHang(ResultSet rs){
        KhachHang nv = new KhachHang();
        
        try{
            nv.setTenKH(rs.getString("TenKH"));
            nv.setMaKhachHang(rs.getString("MaKhachHang"));
            nv.setLoaiHK(rs.getInt("LoaiHK"));
            nv.setDiaChi(rs.getString("DiaChi"));
            nv.setEmail(rs.getString("Email"));
            nv.setTuoi(rs.getInt("Tuoi"));
        }catch(Exception e){
            e.printStackTrace();
            System.out.print("Can't load ChiTietVe1");
        }
        
        return nv;
    }
    public KhachHang getKhachHang(String MaVe) throws SQLException {
        String sql = "SELECT TenKH, KHACHHANG.MaKhachHang, DiaChi, Tuoi, Email, LoaiHK, LoaiVe, Gia "
                + "FROM KhachHang "
                + "INNER JOIN ChiTietVe ON KhachHang.MaKhachHang = ChiTietVe.MaKhachHang "
                + "INNER JOIN Ve ON ChiTietVe.MaVe = Ve.MaVe "
                + "WHERE ChiTietVe.MaVe = '"+MaVe+"'";
        
        System.out.println(sql);
        KhachHang kh = new KhachHang();
        
        try {
            ResultSet rs1 = DBConnect.dbExcute(sql);
            rs1.next();
            kh = createKhachHang(rs1); //Gán dòng vào đối tượng qua hàm ở trên
            DBConnect.dbDisconnect();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("Can't load KhachHang");
        }
        
        return kh;
    }
    
    public Ve getVe(String MaVe) {
        String sql = "SELECT LoaiVe, VE.MAVE "
                + "FROM VE "
                + "INNER JOIN CHITIETVE ON VE.MAVE = CHITIETVE.MAVE "
                + "WHERE VE.MaVe = '"+MaVe+"'";
        
        System.out.println(sql);
        Ve ve = new Ve();
        
        try {
            ResultSet rs1 = DBConnect.dbExcute(sql);
            rs1.next();
            ve.setLoaiVe(new LoaiVe(rs1.getInt("LoaiVe"), (rs1.getInt("LoaiVe")==0) ? "Thường" : "VIP" ));
            ve.setMaVe(rs1.getString("MaVe"));
            DBConnect.dbDisconnect();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("Can't load ChuyenBay");
        }
        
        return ve;
    }
    
    public int getGia(String MaVe) throws SQLException {
        String sql = "SELECT Gia FROM ChiTietVe WHERE MaVe = '"+MaVe+"'";
        int gia = 0;
        ResultSet rs;
        rs = DBConnect.dbExcute(sql);
        rs.next();
        gia = rs.getInt("Gia");
        DBConnect.dbDisconnect();
        
        return gia;
        
    }
    // Lưu thông tin vừa sửa ở form
   public void Luu(ChiTietVe ct) throws SQLException{
           String sql1="UPDATE KHACHHANG SET TenKH='" + ct.getKhachHang().getTenKH() + "', MaKhachHang='" + ct.getKhachHang().getMaKhachHang() + "', Email='"+ct.getKhachHang().getEmail()+"', LoaiHK= "+ ct.getKhachHang().getLoaiHK() +" WHERE MaKhachHang='"+ct.getKhachHang().getMaKhachHang()+"'";
           String sql2 = "UPDATE CHITIETVE SET Gia=" + ct.getGia()+ " WHERE MaVe='"+ct.getVe().getMaVe()+"'";
           System.out.println(sql1);
           try {
               int rs;
               rs = DBConnect.dbExcuteUpdate(sql1);
               DBConnect.dbDisconnect();
               rs = 0;
               rs = DBConnect.dbExcuteUpdate(sql2);
               DBConnect.dbDisconnect();
           } catch (Exception e) {
               e.printStackTrace();
               System.err.println("Can't Luu ChiTietVe!");
           }
       }
////    
    //Xóa lịch sử bán vé
    public void Huy(String MaVe)throws SQLException{
          String sql = "{ call huyve(?) }";

//        String sql="DELETE FROM CHITIETVE WHERE MaVe = '"+MaVe+"'";
        System.out.println(sql);
        try{
           int rs;
//            rs = DBConnect.dbExcuteUpdate(sql);
          CallableStatement cs = DBConnect.dbExecuteQueryProcedure(sql);
          cs.setString(1, MaVe);
          cs.execute();
          DBConnect.dbDisconnect();
        }catch (Exception e){
            e.printStackTrace();
            System.err.println("Can't hủy vé!");
        }
    }
//    
//    
    public void CapNhatLaiVe(String MaVe)throws SQLException{
        String sql="UPDATE Ve SET TinhTrang = 0 WHERE MaVe='"+MaVe+"'";
        System.out.println(sql);
        try{
            int rs;
            rs = DBConnect.dbExcuteUpdate(sql);
            DBConnect.dbDisconnect();
        }catch (Exception e){
            e.printStackTrace();
            System.err.println("Can't hủy vé!");
        }
    }
}
