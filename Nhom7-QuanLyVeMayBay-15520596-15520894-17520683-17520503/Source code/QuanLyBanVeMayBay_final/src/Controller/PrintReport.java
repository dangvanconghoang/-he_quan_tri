///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package Controller;
//
////import DAL.DBConnect;
////import java.awt.HeadlessException;
////import javax.swing.JFrame;
////import javax.swing.JOptionPane;
////import net.sf.jasperreports.engine.JasperCompileManager;
////import net.sf.jasperreports.engine.JasperFillManager;
////import net.sf.jasperreports.engine.JasperPrint;
////import net.sf.jasperreports.engine.JasperReport;
////import net.sf.jasperreports.swing.JRViewer;
//
///**
// *
// * @author blues
// */
//public class PrintReport extends JFrame {
//    
//    public PrintReport() throws HeadlessException {
//        
//    }
//    
//    public void showReport() {
//        try {
//            JasperReport jasperReport = JasperCompileManager.compileReport("C:\\Users\\blues\\OneDrive\\Documents\\banvechuyenbay\\-he_quan_tri\\QuanLyBanVeMayBay_final\\src\\Report\\DoanhThuThang.jrxml");
//            DBConnect.dbConnect();
//            JasperPrint JasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnect.connection);
//            JRViewer viewer = new JRViewer(JasperPrint);
//            viewer.setOpaque(true);
//            viewer.setVisible(true);
//            
//            this.add(viewer);
//            this.setSize(900, 500);
//            this.setVisible(true);
//            
//        } catch(Exception e) {
//            JOptionPane.showMessageDialog(rootPane, e.getMessage());
//        }
//    }
//}
