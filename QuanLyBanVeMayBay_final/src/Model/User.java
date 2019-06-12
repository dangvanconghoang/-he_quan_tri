/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.collections.ObservableList;

/**
 *
 * @author win10pro
 */
public class User {
    private int userlevel;
    private String Email;
    private String pass;

    public User(int level, String Email, String Password) {
        this.userlevel = level;
        this.Email = Email;
        this.pass = Password;
    }

    public User() {
    }

    public int getuserLevel() {
        return userlevel;
    }

    public String getEmail() {
        return Email;
    }

    public String getPass() {
        return pass;
    }

    public void setuserLevel(int level) {
        this.userlevel = level;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setPass(String Pass) {
        this.pass = Pass;
    }

    public static User getbyEmail(String emailString, ObservableList<User> list) {
        for (User u : list) {
            if (u.getEmail().equals(emailString)) {
                return u;
            }
        }
        return null;
    }
}
