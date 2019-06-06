/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.naming.spi.DirStateFactory;
/**
 *
 * @author win10pro
 */
public class ThongKeDAO {
    public double tkDoanhThu(int nam, int thang)throws SQLException{
        double tong=0;
        String sql="exec chartDoanhThu "+nam+","+thang;
        System.out.println(sql);
        try{
            ResultSet rs;
            rs=DBConnect.dbExcute(sql);
            if(rs.first()==true){
                tong=rs.getDouble("DoanhThu");
            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Can't lay doanh thu!");
        }
        return tong;
    }
    
    public int tkTongCB(int nam, int thang)throws SQLException{
        int tong=0;
        String sql="exec chartTongCB "+nam+","+thang;
        System.out.println(sql);
        try{
            ResultSet rs;
            rs=DBConnect.dbExcute(sql);
            if(rs.first()==true){
                tong=rs.getInt("TongCB");
            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Can't lay TongHKTB!");
        }
        return tong;
    }
    
    public float tkTongHKTB(int nam, int thang)throws SQLException{
        float tong=0;
        String sql="exec chartTongHKTB "+nam+","+thang;
        System.out.println(sql);
        try{
            ResultSet rs;
            rs=DBConnect.dbExcute(sql);
            if(rs.first()==true){
                tong=rs.getFloat("TongHKTB");
            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Can't lay TongHKTB!");
        }
        return tong;
    }
    
    public int tkTongHK(int nam, int thang)throws SQLException{
        int tong=0;
        String sql="exec chartTongHK "+nam+","+thang;
        System.out.println(sql);
        try{
            ResultSet rs;
            rs=DBConnect.dbExcute(sql);
            if(rs.first()==true){
                tong=rs.getInt("TongHK");
            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Can't lay TongHK!");
        }
        return tong;
    }
}
