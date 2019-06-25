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
public class LoaiVe {
    private int LoaiVe;
    private String TenLoaiVe;

    public LoaiVe(int LoaiVe, String TenLoaiVe) {
        this.LoaiVe = LoaiVe;
        this.TenLoaiVe = TenLoaiVe;
    }

    public LoaiVe() {
    }

    public int getLoaiVe() {
        return LoaiVe;
    }

    public void setLoaiVe(int LoaiVe) {
        this.LoaiVe = LoaiVe;
    }

    public String getTenLoaiVe() {
        return TenLoaiVe;
    }

    public void setTenLoaiVe(String TenLoaiVe) {
        this.TenLoaiVe = TenLoaiVe;
    }
    
    @Override
    public String toString() {
        return this.TenLoaiVe;
    }
}
