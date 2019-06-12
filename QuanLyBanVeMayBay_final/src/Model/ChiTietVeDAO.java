/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.DBConnect;
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
    //Lấy kết quả từ resultset bỏ vào model
    private ChiTietVe createChuyenBay(ResultSet rs){
        ChiTietVe ct = new ChiTietVe();
        try{
            ct.setTenHMB(rs.getString("TenHMB"));
            ct.setDiemKhoiHanh(rs.getString("DiemKhoiHanh"));
            ct.setDiemDen(rs.getString("DiemDen"));
            ct.setSanBayDi(rs.getString("SanBayDi"));
            ct.setNgayKhoiHanh(rs.getDate("NgayKhoiHanh"));
            ct.setThoiGianBay(rs.getInt("ThoiGianBay"));
            ct.setGioKhoiHanh(rs.getTime("GioKhoiHanh"));
        }catch(Exception e){
            e.printStackTrace();
            System.out.print("Can't load ChuyenBay1");
        }
        return ct;
    }
    
    //chạy hàm select rồi bỏ vào resultset rồi bỏ vào model
    public ChiTietVe getChuyenBay(String MaCB)throws SQLException{
        ChiTietVe ct =new ChiTietVe();
        String sql1 = "select TenHMB,DiemKhoiHanh,DiemDen,TenSB as SanBayDi,NgayKhoiHanh,ThoiGianBay,GioKhoiHanh from ChuyenBay,SanBay,HangMayBay where ChuyenBay.MaHMB=HangMayBay.MaHMB and ChuyenBay.SanBayDi=SanBay.MaSB and MaCB='"+MaCB+"'";
        System.out.println(sql1);
        try {
            ResultSet rs1 = DBConnect.dbExcute(sql1);
            if(rs1.first()==false) System.out.println("sai");
            else {
            ct = createChuyenBay(rs1); //Gán dòng vừa select vào model qua hàm ở trên
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("Can't load ChuyenBay");
        }
        return ct;
    }
    
    private ChiTietVe createSanBayDen(ResultSet rs){
        ChiTietVe ct = new ChiTietVe();
        try{
            ct.setSanBayDen(rs.getString("SanBayDen"));
        }catch(Exception e){
            e.printStackTrace();
            System.out.print("Can't load SanBayDen1");
        }
        return ct;
    }
    
    public ChiTietVe getSanBayDen(String MaCB) throws SQLException {
        String sql = "select TenSB as SanBayDen\n" +
"from SanBay,ChuyenBay\n" +
"where ChuyenBay.SanBayDen=SanBay.MaSB and MaCB='"+MaCB+"'";
        System.out.println(sql);
        ChiTietVe ct=new ChiTietVe();
        try {
            ResultSet rs1 = DBConnect.dbExcute(sql);
            if(rs1.first()==false) System.out.println("sai");
            else {
            ct = createSanBayDen(rs1); //Gán dòng vào đối tượng qua hàm ở trên
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("Can't load ChuyenBay");
        }
        return ct;
    }
    
    public ChiTietVe createChiTietVe(ResultSet rs){
        ChiTietVe ct = new ChiTietVe();
        try{
            ct.setTenHK(rs.getString("TenHK"));
            ct.setCMND(rs.getString("CMND"));
            ct.setSDT(rs.getString("SDT"));
            ct.setLoaiHK(rs.getBoolean("LoaiHK"));
            ct.setLoaiVe(rs.getBoolean("LoaiVe"));
            ct.setGia(String.valueOf(rs.getDouble("Gia")));
            ct.setEmail(rs.getString("Email")); 
            ct.setHanhLy(rs.getInt("HanhLy"));
            ct.setSoGhe(rs.getString("SoGhe"));
        }catch(Exception e){
            e.printStackTrace();
            System.out.print("Can't load ChiTietVe1");
        }
        return ct;
    }
    public ChiTietVe getChiTietVe(String MaVe) throws SQLException {
        String sql = "select TenHK,CMND,SDT,Email,LoaiHK,LoaiVe,Gia,HanhLy,SoGhe from ChiTietChuyenBay,Ve where ChiTietChuyenBay.MaVe='"+MaVe+"' and Ve.MaVe=ChiTietChuyenBay.MaVe";
        System.out.println(sql);
        ChiTietVe ct =new ChiTietVe();
        try {
            ResultSet rs1 = DBConnect.dbExcute(sql);
            if(rs1.first()==false) System.out.println("sai");
            else {
            ct = createChiTietVe(rs1); //Gán dòng vào đối tượng qua hàm ở trên
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("Can't load ChuyenBay");
        }
        return ct;
    }
    
    //Lưu thông tin vừa sửa ở form
    public void Luu(ChiTietVe ct) throws SQLException{
        
            String sql="update ChiTietChuyenBay set TenHK=N'"+ct.getTenHK()+"', CMND='"+ct.getCMND()+"', SDT='"+ct.getSDT()+"', Email='"+ct.getEmail()+"', LoaiHK=0, Gia="+Double.parseDouble(ct.getGia()) +" where MaVe='"+ct.getMaVe()+"'";
            System.out.println(sql);
            try {
                ResultSet rs;
                rs = DBConnect.dbExcute(sql);
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Can't Luu ChiTietVe!");
            }
        }
    
    public void Luu2(ChiTietVe ct) throws SQLException{
            String sql="update ChiTietChuyenBay set TenHK=N'"+ct.getTenHK()+"', CMND='"+ct.getCMND()+"', SDT='"+ct.getSDT()+"', Email='"+ct.getEmail()+"', LoaiHK=1, Gia="+Double.parseDouble(ct.getGia()) +" where MaVe='"+ct.getMaVe()+"'";
            System.out.println(sql);
            try {
                ResultSet rs;
                rs = DBConnect.dbExcute(sql);
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Can't Luu ChiTietVe!");
            }
        }
    
    //Xóa lịch sử bán vé
    public void Huy(String MaVe)throws SQLException{
        String sql="delete from ChiTietChuyenBay where MaVe='"+MaVe+"'";
        System.out.println(sql);
        try{
            ResultSet rs;
            rs = DBConnect.dbExcute(sql);
        }catch (Exception e){
            e.printStackTrace();
            System.err.println("Can't hủy vé!");
        }
    }
    
    
    public void CapNhatLaiVe(String MaVe)throws SQLException{
        String sql="update Ve set TinhTrang=0 where MaVe='"+MaVe+"'";
        System.out.println(sql);
        try{
            ResultSet rs;
            rs = DBConnect.dbExcute(sql);
        }catch (Exception e){
            e.printStackTrace();
            System.err.println("Can't hủy vé!");
        }
    }
}
