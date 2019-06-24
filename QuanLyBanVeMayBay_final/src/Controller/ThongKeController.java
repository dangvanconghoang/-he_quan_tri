/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import DAL.ThongKeDAO;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.net.URL;
import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

/**
 * FXML Controller class
 *
 * @author win10pro
 */
public class ThongKeController implements Initializable {

    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;
    @FXML
    private BarChart<?, ?> bcHK;
    @FXML
    private NumberAxis xChiPhiTB;
    @FXML
    private CategoryAxis yChiPhiTB;
    @FXML
    private BarChart<?, ?> bcHKTB;
    @FXML
    private NumberAxis yHS;
    @FXML
    private CategoryAxis xHS;
    @FXML
    private BarChart<?, ?> bcCB;
    @FXML
    private NumberAxis yCD;
    @FXML
    private CategoryAxis xCD;
    @FXML
    private JFXButton btnXem;
    @FXML
    private JFXComboBox<Integer> cboNam;
    @FXML
    private BarChart<?, ?> bcDoanhThu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<Integer> list=FXCollections.observableArrayList();
        list.add(2019);
        list.add(2020);
        list.add(2021);
        list.add(2022);
        list.add(2023);
        cboNam.setItems(list);
    }    
    private void chartDoanhThu(BarChart bc)throws SQLException{
        ThongKeDAO tkdao=new ThongKeDAO();
        double t1 = 0;
        double t2 = 0;
        double t3=0;
        double t4=0;
        double t5=0;
        double t6=0;
        double t7=0;
        double t8=0;
        double t9=0;
        double t10=0;
        double t11=0;
        double t12=0;
        try{
            t1=tkdao.tkDoanhThu(cboNam.getSelectionModel().getSelectedItem(),1);
            t2=tkdao.tkDoanhThu(cboNam.getSelectionModel().getSelectedItem(),2);
            t3=tkdao.tkDoanhThu(cboNam.getSelectionModel().getSelectedItem(),3);
            t4=tkdao.tkDoanhThu(cboNam.getSelectionModel().getSelectedItem(),4);
            t5=tkdao.tkDoanhThu(cboNam.getSelectionModel().getSelectedItem(),5);
            t6=tkdao.tkDoanhThu(cboNam.getSelectionModel().getSelectedItem(),6);
            t7=tkdao.tkDoanhThu(cboNam.getSelectionModel().getSelectedItem(),7);
            t8=tkdao.tkDoanhThu(cboNam.getSelectionModel().getSelectedItem(),8);
            t9=tkdao.tkDoanhThu(cboNam.getSelectionModel().getSelectedItem(),9);
            t10=tkdao.tkDoanhThu(cboNam.getSelectionModel().getSelectedItem(),10);
            t11=tkdao.tkDoanhThu(cboNam.getSelectionModel().getSelectedItem(),11);
            t11=tkdao.tkDoanhThu(cboNam.getSelectionModel().getSelectedItem(),12);
        }catch(Exception e){
            System.out.println("can't lay doanh thu");
        }
        XYChart.Series set1 = new XYChart.Series<String,Number>();
        set1.getData().add(new XYChart.Data<>("t1",t1));
        set1.getData().add(new XYChart.Data<>("t2",t2));
        set1.getData().add(new XYChart.Data<>("t3",t3));
        set1.getData().add(new XYChart.Data<>("t4",t4));
        set1.getData().add(new XYChart.Data<>("t5",t5));
        set1.getData().add(new XYChart.Data<>("6",t6));
        set1.getData().add(new XYChart.Data<>("7",t7));
        set1.getData().add(new XYChart.Data<>("8",t8));
        set1.getData().add(new XYChart.Data<>("9",t9));
        set1.getData().add(new XYChart.Data<>("10",t10));
        set1.getData().add(new XYChart.Data<>("11",t11));
        set1.getData().add(new XYChart.Data<>("12",t12));
        
        bc.getData().add(set1);
        bc.setLegendSide(Side.LEFT);

    }
    
    private void chartTongCB(BarChart bc)throws SQLException{
        ThongKeDAO tkdao=new ThongKeDAO();
        XYChart.Series set1 = new XYChart.Series<String,Number>();
        try {
        set1.getData().add(new XYChart.Data<>("t1",tkdao.tkTongCB(cboNam.getSelectionModel().getSelectedItem(),1)));
        set1.getData().add(new XYChart.Data<>("t2",tkdao.tkTongCB(cboNam.getSelectionModel().getSelectedItem(),2)));
        set1.getData().add(new XYChart.Data<>("t3",tkdao.tkTongCB(cboNam.getSelectionModel().getSelectedItem(),3)));
        set1.getData().add(new XYChart.Data<>("t4",tkdao.tkTongCB(cboNam.getSelectionModel().getSelectedItem(),4)));
        set1.getData().add(new XYChart.Data<>("t5",tkdao.tkTongCB(cboNam.getSelectionModel().getSelectedItem(),5)));
        set1.getData().add(new XYChart.Data<>("6",tkdao.tkTongCB(cboNam.getSelectionModel().getSelectedItem(),6)));
        set1.getData().add(new XYChart.Data<>("7",tkdao.tkTongCB(cboNam.getSelectionModel().getSelectedItem(),7)));
        set1.getData().add(new XYChart.Data<>("8",tkdao.tkTongCB(cboNam.getSelectionModel().getSelectedItem(),8)));
        set1.getData().add(new XYChart.Data<>("9",tkdao.tkTongCB(cboNam.getSelectionModel().getSelectedItem(),9)));
        set1.getData().add(new XYChart.Data<>("10",tkdao.tkTongCB(cboNam.getSelectionModel().getSelectedItem(),10)));
        set1.getData().add(new XYChart.Data<>("11",tkdao.tkTongCB(cboNam.getSelectionModel().getSelectedItem(),11)));
        set1.getData().add(new XYChart.Data<>("12",tkdao.tkTongCB(cboNam.getSelectionModel().getSelectedItem(),12)));
        } catch (Exception e) {
            System.out.println("Can't not load TongCB!");
        }
        bc.getData().add(set1);
        bc.setLegendSide(Side.LEFT);

    }
        
    private void chartTongHK(BarChart bc)throws SQLException{
        ThongKeDAO tkdao=new ThongKeDAO();
        XYChart.Series set1 = new XYChart.Series<String,Number>();
        try {
        set1.getData().add(new XYChart.Data<String,Number>("t1",tkdao.tkTongHK(cboNam.getSelectionModel().getSelectedItem(),1)));
        set1.getData().add(new XYChart.Data<String,Number>("t2",tkdao.tkTongHK(cboNam.getSelectionModel().getSelectedItem(),2)));
        set1.getData().add(new XYChart.Data<String,Number>("t3",tkdao.tkTongHK(cboNam.getSelectionModel().getSelectedItem(),3)));
        set1.getData().add(new XYChart.Data<String,Number>("t4",tkdao.tkTongHK(cboNam.getSelectionModel().getSelectedItem(),4)));
        set1.getData().add(new XYChart.Data<String,Number>("t5",tkdao.tkTongHK(cboNam.getSelectionModel().getSelectedItem(),5)));
        set1.getData().add(new XYChart.Data<String,Number>("6",tkdao.tkTongHK(cboNam.getSelectionModel().getSelectedItem(),6)));
        set1.getData().add(new XYChart.Data<String,Number>("7",tkdao.tkTongHK(cboNam.getSelectionModel().getSelectedItem(),7)));
        set1.getData().add(new XYChart.Data<String,Number>("8",tkdao.tkTongHK(cboNam.getSelectionModel().getSelectedItem(),8)));
        set1.getData().add(new XYChart.Data<String,Number>("9",tkdao.tkTongHK(cboNam.getSelectionModel().getSelectedItem(),9)));
        set1.getData().add(new XYChart.Data<String,Number>("10",tkdao.tkTongHK(cboNam.getSelectionModel().getSelectedItem(),10)));
        set1.getData().add(new XYChart.Data<String,Number>("11",tkdao.tkTongHK(cboNam.getSelectionModel().getSelectedItem(),11)));
        set1.getData().add(new XYChart.Data<String,Number>("12",tkdao.tkTongHK(cboNam.getSelectionModel().getSelectedItem(),12)));
        } catch (Exception e) {
            System.out.println("Can't not load TongCB!");
        }
        bc.getData().add(set1);
        bc.setLegendSide(Side.LEFT);

    }
    
    private void chartTongHKTB(BarChart bc)throws SQLException{
        ThongKeDAO tkdao=new ThongKeDAO();
        XYChart.Series set1 = new XYChart.Series<String,Float>();
        try {
        set1.getData().add(new XYChart.Data<String,Float>("t1",tkdao.tkTongHKTB(cboNam.getSelectionModel().getSelectedItem(),1)));
        set1.getData().add(new XYChart.Data<String,Float>("t2",tkdao.tkTongHKTB(cboNam.getSelectionModel().getSelectedItem(),2)));
        set1.getData().add(new XYChart.Data<String,Float>("t3",tkdao.tkTongHKTB(cboNam.getSelectionModel().getSelectedItem(),3)));
        set1.getData().add(new XYChart.Data<String,Float>("t4",tkdao.tkTongHKTB(cboNam.getSelectionModel().getSelectedItem(),4)));
        set1.getData().add(new XYChart.Data<String,Float>("t5",tkdao.tkTongHKTB(cboNam.getSelectionModel().getSelectedItem(),5)));
        set1.getData().add(new XYChart.Data<String,Float>("6",tkdao.tkTongHKTB(cboNam.getSelectionModel().getSelectedItem(),6)));
        set1.getData().add(new XYChart.Data<String,Float>("7",tkdao.tkTongHKTB(cboNam.getSelectionModel().getSelectedItem(),7)));
        set1.getData().add(new XYChart.Data<String,Float>("8",tkdao.tkTongHKTB(cboNam.getSelectionModel().getSelectedItem(),8)));
        set1.getData().add(new XYChart.Data<String,Float>("9",tkdao.tkTongHKTB(cboNam.getSelectionModel().getSelectedItem(),9)));
        set1.getData().add(new XYChart.Data<String,Float>("10",tkdao.tkTongHKTB(cboNam.getSelectionModel().getSelectedItem(),10)));
        set1.getData().add(new XYChart.Data<String,Float>("11",tkdao.tkTongHKTB(cboNam.getSelectionModel().getSelectedItem(),11)));
        set1.getData().add(new XYChart.Data<String,Float>("12",tkdao.tkTongHKTB(cboNam.getSelectionModel().getSelectedItem(),12)));
        } catch (Exception e) {
            System.out.println("Can't not load TongHKTB!");
        }
        bc.getData().add(set1);
        bc.setLegendSide(Side.LEFT);

    }
    
    @FXML
    private void btnXemClick(ActionEvent event) {
        System.out.println(String.valueOf(cboNam.getSelectionModel().getSelectedItem()));
        bcCB.getData().clear();
        bcDoanhThu.getData().clear();
        bcHK.getData().clear();
        bcHKTB.getData().clear();
        try{
            chartDoanhThu(bcDoanhThu);
            chartTongCB(bcCB);
            chartTongHKTB(bcHKTB);
            chartTongHK(bcHK);
        }catch(Exception e){
            System.out.println("Cant load barchartDoanhThu");
        }
    }
    
}
