/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.naming.spi.DirStateFactory;
import oracle.jdbc.OracleTypes;
/**
 *
 * @author win10pro
 */
public class ThongKeDAO {
    public double tkDoanhThu(int nam, int thang)throws SQLException{
        double tong=0;
        String sql="{ call chartDoanhThu(?, ?, ?) }";
        System.out.println(sql);
        try{
//            ResultSet rs = null;
            CallableStatement cs = DBConnect.dbExecuteQueryProcedure(sql);
            cs.setInt(1, nam);
            cs.setInt(2, thang);
            cs.registerOutParameter(3, OracleTypes.CURSOR);
            cs.execute();
            ResultSet rs = (ResultSet) cs.getObject(3);
            while(rs.next()){
                tong = rs.getInt("DoanhThu");
            }
            DBConnect.dbDisconnect();
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Can't lay doanh thu!");
        }
        return tong;
    }
    
    public int tkTongCB(int nam, int thang)throws SQLException{
        int tong = 0;
        String sql="{ call chartTongCB(?, ?, ?) }";
        System.out.println(sql);
        try{
            CallableStatement cs = DBConnect.dbExecuteQueryProcedure(sql);
            cs.setInt(1, nam);
            cs.setInt(2, thang);
            cs.registerOutParameter(3, OracleTypes.CURSOR);
            cs.execute();
            ResultSet rs = (ResultSet) cs.getObject(3);
            while(rs.next()){
                tong = rs.getInt("TongCB");
            }
            DBConnect.dbDisconnect();
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Can't lay TongHKTB!");
        }
        return tong;
    }
    
    public float tkTongHKTB(int nam, int thang)throws SQLException{
        float tong=0;
        String sql="{ call chartTongHKTB(?,?,?) }";
        System.out.println(sql);
        try{
            CallableStatement cs = DBConnect.dbExecuteQueryProcedure(sql);
            cs.setInt(1, nam);
            cs.setInt(2, thang);
            cs.registerOutParameter(3, OracleTypes.CURSOR);
            cs.execute();
            ResultSet rs = (ResultSet) cs.getObject(3);
            
            while(rs.next()){
                tong=rs.getFloat(":B1");
            }
            
            DBConnect.dbDisconnect();
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Can't lay TongHKTB!");
        }
        return tong;

    }
    
    public int tkTongHK(int nam, int thang)throws SQLException{
        int tong=0;
        String sql="{ call chartTongHK(?, ?, ?) }";
        System.out.println(sql);
        try{
            CallableStatement cs = DBConnect.dbExecuteQueryProcedure(sql);
            cs.setInt(1, nam);
            cs.setInt(2, thang);
            cs.registerOutParameter(3, OracleTypes.CURSOR);
            cs.execute();
            ResultSet rs = (ResultSet) cs.getObject(3);
            
            while(rs.next()){
                tong=rs.getInt("TongHK");
            }
            
            DBConnect.dbDisconnect();
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Can't lay TongHK!");
        }
        return tong;
    }
}
