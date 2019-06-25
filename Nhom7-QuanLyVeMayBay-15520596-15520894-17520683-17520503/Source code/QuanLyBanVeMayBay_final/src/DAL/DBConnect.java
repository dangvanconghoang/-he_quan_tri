/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.lang.reflect.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.Alert;
import javax.sql.rowset.CachedRowSet;
import jdk.nashorn.internal.codegen.CompilerConstants;
import jdk.nashorn.internal.objects.Global;

/**
 *
 * @author MyPC
 */
public class DBConnect {

    public static Connection connection;
//    public static final String hostName = "localhost";
//    public static final String sqlInstanceName = "SQLEXPRESS";
//    public static final String database = "QuanLyBanVeMayBay";

    public static void dbConnect() {
        try {
            System.getProperties().setProperty("oracle.jdbc.J2EE13Compliant", "true");
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@localhost:1521:orcl";
            String Username = "Gon";
            String Password = "hunterxhunter";
            connection = DriverManager.getConnection(url, Username, Password);
            System.out.println("\nconnected");
        } catch (Exception exception) {
            System.out.print(exception);
            System.out.print("can't connect database! ");
        }

    }
    public static int dbExcuteUpdate(String sql) throws SQLException{
        Statement stmt = null;
        int i = 0;
        try {
            dbConnect();
            stmt = connection.createStatement();
             i = stmt.executeUpdate(sql);
        } catch (SQLException e) {
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Thông báo");
            alert1.setHeaderText("");
            alert1.setContentText(e.getMessage());
            alert1.show();
            System.out.print(e);
            System.out.print("Can't execute update!");
        }
       dbDisconnect();
       return i;
        }
    public static void dbDisconnect() throws SQLException{
       
        try {
            if(connection != null )
            {
                connection.close();
            }
            
        } catch (Exception e) {
                throw e;
        }

        
    }
    public static ResultSet dbExcute(String sql) throws SQLException{
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            dbConnect();
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.out.print(e);
            System.out.print("Can't execute!");
        }

       return rs;
    }
    
    public static CallableStatement dbExecuteQueryProcedure(String sql) throws SQLException {
        ResultSet rs = null;
        
        dbConnect();
        Statement statement = connection.createStatement();
        CallableStatement cs = connection.prepareCall(sql);
         
        return cs;
    }
}
