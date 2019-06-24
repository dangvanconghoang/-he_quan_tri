package Controller;

import DAL.ChiTietVeDAO;
import DAL.DanhSachChuyenBayDAO;
import DAL.KhachHangDAO;
import DAL.VeDAO;
import Model.ChiTietVe;
import Model.DanhSachChuyenBay;
import Model.KhachHang;
import Model.LoaiVe;
import Model.Ve;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class NhapThongTinController {
    
    @FXML
    private TableView<Ve> tbVe;

    @FXML
    private TableColumn<Ve, String> colMaVe;

    @FXML
    private TableColumn<Ve, String> colLoaiVe;

    @FXML
    private TableColumn<Ve, String> colTinhTrang;

    @FXML
    private TableColumn<Ve, Float> colTiLe;
    
    ObservableList<Ve> listVe = FXCollections.observableArrayList();
    ObservableList<Ve> listVeDat = FXCollections.observableArrayList();
    ObservableList<ChiTietVe> listChiTietVe = FXCollections.observableArrayList();
    
    @FXML
    private Label labelKhachHang;

    @FXML
    private JFXTextField tfTenHK;

    @FXML
    private JFXTextField tfCMND;

    @FXML
    private JFXTextField tfEmail;

    @FXML
    private JFXTextField tfLoaiHK;

    @FXML
    private JFXTextField tfTuoi;

    @FXML
    private JFXTextField tfDiaChi;
    
    @FXML
    private JFXButton btnTieptheo;
    
    
    private ObservableList<KhachHang> listKhachHang = FXCollections.observableArrayList();;
    
    private int SoNLconst;
    private int SoTreEmconst;
    private int SoNL;
    private int SoTreEm;
    private LocalDate NgayDi;
    private LocalDate NgayVe;
    private String DiemDi;
    private String DiemDen;
    private String MaCB;
    private String MaCBLuotDi;
    private int indexHanhKhach = 1;
    private boolean luot;
    
    public void chuyenForm() throws SQLException {
         if(this.SoNL != 0) {
             labelKhachHang.setText("Thông tin hành khách " + ++this.indexHanhKhach + " (người lón):"); 
             tfLoaiHK.setText("Người lớn");
             tfLoaiHK.setEditable(false);

         }
         else if(this.SoTreEm != 0) {
             labelKhachHang.setText("Thông tin hành khách " + ++this.indexHanhKhach + " (trẻ em)");
             tfLoaiHK.setText("Trẻ em");
             tfLoaiHK.setEditable(false);
         }
         loadData();
    }
    public void loadData() throws SQLException {
        if(this.luot == true) {
            KhachHang kh = listKhachHang.get(this.indexHanhKhach - 1);
            this.tfTenHK.setText(kh.getTenKH());
            this.tfEmail.setText(kh.getEmail());
            this.tfDiaChi.setText(kh.getDiaChi());
            this.tfCMND.setText(kh.getCMND());
            this.tfLoaiHK.setText((kh.getLoaiHK() == 1) ? "Người lớn" : "Trẻ em");
            this.tfTuoi.setText(String.valueOf(kh.getTuoi()));
        }
        
        listVe = VeDAO.getVe(MaCB);
        setCellValueFactory();
        tbVe.setItems(listVe);
    }

    void ChuyenDuLieu(String MaCBString, int LoaiVe, int SoNL, int SoTreEm, String DiemDi, String DiemDen, LocalDate NgayDi, LocalDate NgayVe, boolean luot) throws SQLException {
        this.DiemDi = DiemDi;
        this.DiemDen = DiemDen;
        this.NgayDi = NgayDi;
        this.NgayVe = NgayVe;
        this.luot = luot;
        this.SoNLconst = SoNL;
        this.SoTreEmconst = SoTreEm;
        this.SoNL = SoNL;
        this.SoTreEm = SoTreEm;
        this.MaCB = MaCBString;
        labelKhachHang.setText("Thông tin hành khách " + this.indexHanhKhach + " (Thông tin liên hệ):");
        tfLoaiHK.setText("Người lớn");
        tfLoaiHK.setEditable(false);
        loadData();
    }
    
    void ChuyenDuLieuKhuHoi(String MaCBString, ObservableList<Ve> listVeDat, ObservableList<KhachHang> listKhachHang, ObservableList<ChiTietVe> listChiTietVe) throws SQLException {
        this.MaCBLuotDi = MaCBString;
        this.listVeDat = listVeDat;
        this.listKhachHang = listKhachHang;
        this.listChiTietVe = listChiTietVe;
    }
    
    @FXML
    void btnTieptheoClick(ActionEvent event) throws SQLException {
        KhachHang kh = new KhachHang();
        
        if(this.luot == false) {
            kh.setTenKH(tfTenHK.getText());
            kh.setMaKhachHang(tfCMND.getText());
            kh.setDiaChi(tfDiaChi.getText());
            kh.setEmail(tfEmail.getText());
            kh.setCMND(tfCMND.getText());
            kh.setTuoi(Integer.parseInt(tfTuoi.getText()));
            
            if("Người lớn".equals(tfLoaiHK.getText())) {
                kh.setLoaiHK(1);
                SoNL--;
            }
            else {
                kh.setLoaiHK(0);
                SoTreEm--;
            }
            
            KhachHangDAO.themKhachHang(kh);
            kh.setMaKhachHang(KhachHangDAO.getMaKhachHang(kh));
            this.listKhachHang.add(kh);     
        }
        else {
            kh = this.listKhachHang.get(this.indexHanhKhach - 1);
            
            if(kh.getLoaiHK() == 1)
                SoNL--;
            else
                SoTreEm--;
        }              
        
        Ve ve = tbVe.getSelectionModel().getSelectedItem();
        ChiTietVe ctVe = new ChiTietVe();
        ctVe.setVe(ve);  
        this.listVeDat.add(ve);
        xoaTextField();
        DanhSachChuyenBayDAO dscb = new DanhSachChuyenBayDAO();
        DanhSachChuyenBay cb = dscb.getChuyenBay(MaCB);
        ctVe.setKhachHang(kh);
        ctVe.setGia((int) (cb.getGiaVe()*ve.getTiLe()));
        ChiTietVeDAO.themChiTietVe(ve, kh, MaCB, ctVe.getGia());
        this.listChiTietVe.add(ctVe);
        

        if(SoNL + SoTreEm != 0)
            chuyenForm();
        else {
            if(this.luot == false && this.NgayVe == null) {
                AnchorPane paneThanhToan = new AnchorPane();
                FXMLLoader fXMLLoader = MainController.getMainController().createPage(paneThanhToan, "/View/ThanhToan.fxml");
                fXMLLoader.<ThanhToanController>getController().ChuyenDuLieu(MaCB, listVeDat, listKhachHang, SoNLconst, SoTreEmconst, listChiTietVe);
                GeneralFuntion.FitChildContent(paneThanhToan);
            }
            else if(this.luot == false && this.NgayVe != null) {
                // Chuyen du lieu danh sach CB
                AnchorPane paneDanhSachChuyenBay = new AnchorPane();
                FXMLLoader fXMLLoader = MainController.getMainController().createPage(paneDanhSachChuyenBay, "/View/DanhSachChuyenBay.fxml");
                fXMLLoader.<DanhSachChuyenBayController>getController().ChuyenDuLieuKhuHoi(MaCB, listVeDat, listKhachHang, listChiTietVe);
                fXMLLoader.<DanhSachChuyenBayController>getController().ChuyenDuLieu(DiemDen, DiemDi, NgayDi, SoNLconst, SoTreEmconst, true, NgayVe);
                GeneralFuntion.FitChildContent(paneDanhSachChuyenBay);
            }
            else if(this.luot == true && this.NgayVe != null) {
                AnchorPane paneThanhToan = new AnchorPane();
                FXMLLoader fXMLLoader = MainController.getMainController().createPage(paneThanhToan, "/View/ThanhToan.fxml");
                fXMLLoader.<ThanhToanController>getController().ChuyenDuLieuKhuHoi(MaCBLuotDi, DiemDen, DiemDi , NgayDi, NgayVe, luot);
                fXMLLoader.<ThanhToanController>getController().ChuyenDuLieu(MaCB, listVeDat, listKhachHang, SoNLconst, SoTreEmconst, listChiTietVe);
                GeneralFuntion.FitChildContent(paneThanhToan);
            }
            
        }
    }

    private void xoaTextField() {
        tfCMND.clear();
        tfTenHK.clear();
        tfDiaChi.clear();
        tfEmail.clear();
        tfTuoi.clear();
        tfLoaiHK.clear();
        
    }
    
    @FXML
    void tbClick(MouseEvent event) {

    }

    void setCellValueFactory() {
        colMaVe.setCellValueFactory(new PropertyValueFactory<Ve, String>("MaVe"));
        colLoaiVe.setCellValueFactory(new PropertyValueFactory<Ve, String>("TenLoaiVe"));
        colTinhTrang.setCellValueFactory(new PropertyValueFactory<Ve, String>("TenTinhTrang"));
        colTiLe.setCellValueFactory(new PropertyValueFactory<Ve, Float>("TiLe"));
    }
}
