/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author blues
 */
public class SanBay {
    
    private String MaSB;
    private String TenSB;
    private String ThanhPho;

    public SanBay(String MaSB, String TenSB, String ThanhPho) {
        this.MaSB = MaSB;
        this.TenSB = TenSB;
        this.ThanhPho = ThanhPho;
    }

    
    public String getThanhPho() {
        return ThanhPho;
    }

    public void setThanhPho(String ThanhPho) {
        this.ThanhPho = ThanhPho;
    }
    

    
    public String getMaSB() {
        return MaSB;
    }

    public void setMaSB(String MaSB) {
        this.MaSB = MaSB;
    }

    public String getTenSB() {
        return TenSB;
    }

    public void setTenSB(String TenSB) {
        this.TenSB = TenSB;
    }
    
//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (MaSB != null ? MaSB.hashCode() : 0);
//        return hash;
//    }
    
//    @Override
//    public boolean equals(Object object) {
//        String otherMaSB = "";
//        
//        if (object instanceof HangMayBay) {
//            otherMaSB = ((SanBay)object).MaSB;
//        } else if(object instanceof String){
//            otherMaSB = (String)object;
//        } else {
//            return false;
//        }   
//
//        if ((this.MaSB == null && otherMaSB != null) || (this.MaSB != null && !this.MaSB.equals(otherMaSB))) {
//            return false;
//        }
//        
//        return true;
//    }
    
    @Override
    public String toString() {
        return this.getTenSB();
    }


}
