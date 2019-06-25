/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Objects;

/**
 *
 * @author blues
 */
public class HangMayBay {
    private String MaHMB;
    private String TenHMB;

    public HangMayBay(String MaHMB, String TenHMB) {
        this.MaHMB = MaHMB;
        this.TenHMB = TenHMB;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (MaHMB != null ? MaHMB.hashCode() : 0);
        return hash;
    }
    @Override
    public boolean equals(Object object) {
        String otherMaHMB = "";
        if (object instanceof HangMayBay) {
            otherMaHMB = ((HangMayBay)object).MaHMB;
        } else if(object instanceof String){
            otherMaHMB = (String)object;
        } else {
            return false;
        }   

        if ((this.MaHMB == null && otherMaHMB != null) || (this.MaHMB != null && !this.MaHMB.equals(otherMaHMB))) {
            return false;
        }
        
        return true;
    }

    
    @Override
    public String toString() {
        return this.getTenHMB();
    }
    public String getMaHMB() {
        return MaHMB;
    }

    public void setMaHMB(String MaHMB) {
        this.MaHMB = MaHMB;
    }

    public String getTenHMB() {
        return TenHMB;
    }

    public void setTenHMB(String TenHMB) {
        this.TenHMB = TenHMB;
    }
    
}
