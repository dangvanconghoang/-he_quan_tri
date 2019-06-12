/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 *
 * @author win10pro
 */
public class UserDAO {
    private User createUser(ResultSet rs) {
        User user = new User();
        try {
            user.setEmail(rs.getString("email"));
            user.setuserLevel(rs.getInt("userlevel"));
            user.setPass(rs.getString("pass"));

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Can't get user");
        }
        return user;

    }
    public ObservableList<User> getListUser() throws SQLException {
        String sql = "select * from dbo.DangNhap";
        ObservableList<User> list = FXCollections.observableArrayList();
        try {

            ResultSet rs = DBConnect.dbExcute(sql);
            while (rs.next()) {
                User user = createUser(rs);
                list.add(user);
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public int getUserLevel(String Email)throws SQLException{
        int level=0;
        String sql="select userlevel from DangNhap where email='"+Email+"'";
        try{
            ResultSet rs= DBConnect.dbExcute(sql);
            if(rs.first())
            level=rs.getInt("userlevel");
        }catch(Exception e){
            System.out.println("Cant lay userlevel!!");
        }
        return level;
    }
    
    public void UpdateUser(User user) {
        String sql = "UPDATE dbo.DangNhap  SET pass = '" + user.getPass() + "' WHERE email = '"+user.getEmail()+"'";
        System.out.println(sql);
        try {
            int stmt = DBConnect.dbExcuteQuery(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Can't change password!");
        }
    }
    
}
