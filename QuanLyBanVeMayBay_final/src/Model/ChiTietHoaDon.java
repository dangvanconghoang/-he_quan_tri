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
public class ChiTietHoaDon {
    ChiTietVe Ve;
    HoaDon HoaDon;

    public ChiTietHoaDon() {
    }

    public ChiTietHoaDon(ChiTietVe Ve, HoaDon HoaDon) {
        this.Ve = Ve;
        this.HoaDon = HoaDon;
    }

    
    public ChiTietVe getVe() {
        return Ve;
    }

    public void setVe(ChiTietVe Ve) {
        this.Ve = Ve;
    }

    public HoaDon getHoaDon() {
        return HoaDon;
    }

    public void setHoaDon(HoaDon HoaDon) {
        this.HoaDon = HoaDon;
    }
    
    
}
