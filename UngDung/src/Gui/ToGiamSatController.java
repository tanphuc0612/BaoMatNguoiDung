/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import DAO.DAO;
import Entity.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Screen;
import javafx.stage.Stage;
import ungdung.UngDung;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class ToGiamSatController implements Initializable {
    @FXML
    private TableView<PhieuBau> phieuBau;

    @FXML
    private TableColumn<PhieuBau, Integer> p_STT;

    @FXML
    private TableColumn<PhieuBau, Integer> p_MaTV;

    @FXML
    private TableColumn<PhieuBau, Integer> p_MaUCV;

    @FXML
    private TableView<LichSuBau> lichSuBau;

    @FXML
    private TableColumn<LichSuBau, Integer> ls_STT;

    @FXML
    private TableColumn<LichSuBau, Integer> ls_MaTV;

    @FXML
    private TableColumn<LichSuBau, Integer> ls_MaUCV;

    @FXML
    private TableColumn<LichSuBau, Date> ls_Ngay;
    
    @FXML
    private TableView<BanToChuc> TableBTC;

    @FXML
    private TableColumn<BanToChuc, Integer> maBTC;

    @FXML
    private TableColumn<BanToChuc, String> tenBTC;

    @FXML
    private TableColumn<BanToChuc, String> phaiBTC;

    @FXML
    private TableColumn<BanToChuc, Date> ngaySinhBTC;
    @FXML
    private TableView<UngCuVien> ucvTable;

    @FXML
    private TableColumn<UngCuVien, Integer> maUCV;

    @FXML
    private TableColumn<UngCuVien, String> tenUCV;

    @FXML
    private TableColumn<UngCuVien, String> phaiUCV;

    @FXML
    private TableColumn<UngCuVien, Date> ngaySinhUCV;

    @FXML
    private TableView<LapDanhSachDiBau> lapDSTable;

    @FXML
    private TableColumn<LapDanhSachDiBau, Integer> maLDS;

    @FXML
    private TableColumn<LapDanhSachDiBau, String> tenLDS;

    @FXML
    private TableColumn<LapDanhSachDiBau, String> phaiLDS;

    @FXML
    private TableColumn<LapDanhSachDiBau, Date> ngaySinhLDS;
    
    @FXML
    private TableColumn<LapDanhSachDiBau, String> donviLDS;

    @FXML
    private TableView<TheoDoiKetQua> theoDoiTable;

    @FXML
    private TableColumn<TheoDoiKetQua, Integer> maTDKQ;

    @FXML
    private TableColumn<TheoDoiKetQua, String> tenTDKQ;

    @FXML
    private TableColumn<TheoDoiKetQua, String> phaiTDKQ;

    @FXML
    private TableColumn<TheoDoiKetQua, Date> ngaySinhTDKQ;

    @FXML
    private TableView<GiamSat> giamSatTable;

    @FXML
    private TableColumn<GiamSat, Integer> maGS;

    @FXML
    private TableColumn<GiamSat, String> tenGS;

    @FXML
    private TableColumn<GiamSat, String> phaiGS;

    @FXML
    private TableColumn<GiamSat, Date> ngaySinhGS;
    
    @FXML
    private TableView<NguoiDiBauView> TableNDB;

    @FXML
    private TableColumn<NguoiDiBauView, Integer> maNDB;

    @FXML
    private TableColumn<NguoiDiBauView, String> tenNDB;

    @FXML
    private TableColumn<NguoiDiBauView, String> phaiNDB;

    @FXML
    private TableColumn<NguoiDiBauView, Date> ngaySinhNDB;
    
    @FXML
    private TableColumn<NguoiDiBauView, String> donviNDB;
    
    @FXML
    private Button home;
    
    public void Change(String string,int width, int height,String title) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(string));
        Parent View = loader.load();
        Scene scene;
        scene = new Scene(View,width,height); 
        Stage s = UngDung.getPrimaryStage();
        s.setTitle(title);
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        s.setX((screenBounds.getWidth() - width) / 2);
        s.setY((screenBounds.getHeight() - height) / 2);
        s.setScene(scene);
    }
    public void LoadUCVTable()
    {
        ResultSet rs = null;
        try {
            rs= DAO.Select("SELECT * FROM QT.UNGCUVIEN");
        } catch (SQLException ex) {
            Logger.getLogger(ToGiamSatController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<UngCuVien> list = FXCollections.observableArrayList();
        try {
            while(rs.next()){
                list.add(new UngCuVien(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4)));

            }
                maUCV.setCellValueFactory(new PropertyValueFactory<>("maUCV"));
                tenUCV.setCellValueFactory(new PropertyValueFactory<>("tenUCV"));
                phaiUCV.setCellValueFactory(new PropertyValueFactory<>("phaiUCV"));
                ngaySinhUCV.setCellValueFactory(new PropertyValueFactory<>("ngaySinhUCV"));
                ucvTable.setItems(list);           
        } catch (SQLException ex) {
            Logger.getLogger(ToGiamSatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void LoadLDSTable()
    {
        ResultSet rs = null;
        try {
            rs= DAO.Select("SELECT L.MALDS,L.HOTEN,L.PHAI,L.NGAYSINH,D.TENDV FROM QT.TOLAPDANHSACH L, QT.DONVI D WHERE L.MADV = D.MADV");
        } catch (SQLException ex) {
            Logger.getLogger(ToGiamSatController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<LapDanhSachDiBau> list = FXCollections.observableArrayList();
        try {
            while(rs.next()){
                list.add(new LapDanhSachDiBau(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getString(5)));
            }
                maLDS.setCellValueFactory(new PropertyValueFactory<>("maLDS"));
                tenLDS.setCellValueFactory(new PropertyValueFactory<>("tenLDS"));
                phaiLDS.setCellValueFactory(new PropertyValueFactory<>("phaiLDS"));
                ngaySinhLDS.setCellValueFactory(new PropertyValueFactory<>("ngaySinhLDS"));
                donviLDS.setCellValueFactory(new PropertyValueFactory<>("donviLDS"));
                lapDSTable.setItems(list);            
        } catch (SQLException ex) {
            Logger.getLogger(ToGiamSatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void LoadTDKQTable()
    {
        ResultSet rs = null;
        try {
            rs= DAO.Select("SELECT * FROM QT.TOTHEODOI");
        } catch (SQLException ex) {
            Logger.getLogger(ToGiamSatController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<TheoDoiKetQua> list = FXCollections.observableArrayList();
        try {
            while(rs.next()){
                list.add(new TheoDoiKetQua(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4)));
            }
                maTDKQ.setCellValueFactory(new PropertyValueFactory<>("maTDKQ"));
                tenTDKQ.setCellValueFactory(new PropertyValueFactory<>("tenTDKQ"));
                phaiTDKQ.setCellValueFactory(new PropertyValueFactory<>("phaiTDKQ"));
                ngaySinhTDKQ.setCellValueFactory(new PropertyValueFactory<>("ngaySinhTDKQ"));
                theoDoiTable.setItems(list);            
        } catch (SQLException ex) {
            Logger.getLogger(ToGiamSatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void LoadGSTable()
    {
        ResultSet rs = null;
        try {
            rs= DAO.Select("SELECT * FROM QT.TOGIAMSAT");
        } catch (SQLException ex) {
            Logger.getLogger(ToGiamSatController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<GiamSat> list = FXCollections.observableArrayList();
        try {
            while(rs.next()){
                list.add(new GiamSat(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4)));
            }
                maGS.setCellValueFactory(new PropertyValueFactory<>("maGS"));
                tenGS.setCellValueFactory(new PropertyValueFactory<>("tenGS"));
                phaiGS.setCellValueFactory(new PropertyValueFactory<>("phaiGS"));
                ngaySinhGS.setCellValueFactory(new PropertyValueFactory<>("ngaySinhGS"));
                giamSatTable.setItems(list);            
        } catch (SQLException ex) {
            Logger.getLogger(ToGiamSatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void LoadNDBTable()
    {
        ResultSet rs = null;
        try {
            rs= DAO.Select("SELECT * FROM QT.THANHVIEN WHERE DUOCBAU = 1");
        } catch (SQLException ex) {
            Logger.getLogger(ToGiamSatController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<NguoiDiBauView> list = FXCollections.observableArrayList();
        try {
            while(rs.next()){
                list.add(new NguoiDiBauView(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(5),rs.getString(9)));
            }  
                maNDB.setCellValueFactory(new PropertyValueFactory<>("maNDB"));
                tenNDB.setCellValueFactory(new PropertyValueFactory<>("tenNDB"));
                phaiNDB.setCellValueFactory(new PropertyValueFactory<>("phaiNDB"));
                ngaySinhNDB.setCellValueFactory(new PropertyValueFactory<>("ngaySinhNDB"));
                donviNDB.setCellValueFactory(new PropertyValueFactory<>("donviNDB"));
                TableNDB.setItems(list);            
        } catch (SQLException ex) {
            Logger.getLogger(ToGiamSatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    public void LoadBTCTable()
    {
        ResultSet rs = null;
        try {
            rs= DAO.Select("SELECT * FROM QT.BANTOCHUC");
        } catch (SQLException ex) {
            Logger.getLogger(ToGiamSatController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<BanToChuc> list = FXCollections.observableArrayList();
        try {
            while(rs.next()){
                list.add(new BanToChuc(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4)));
            }   
                maBTC.setCellValueFactory(new PropertyValueFactory<>("maBTC"));
                tenBTC.setCellValueFactory(new PropertyValueFactory<>("tenBTC"));
                phaiBTC.setCellValueFactory(new PropertyValueFactory<>("phaiBTC"));
                ngaySinhBTC.setCellValueFactory(new PropertyValueFactory<>("ngaySinhBTC"));
                TableBTC.setItems(list);            
        } catch (SQLException ex) {
            Logger.getLogger(ToGiamSatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void LoadPBTable()
    {
        ResultSet rs = null;
        try {
            rs= DAO.Select("SELECT * FROM QT.LANBAUCUOI");
        } catch (SQLException ex) {
            Logger.getLogger(ToGiamSatController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<PhieuBau> list = FXCollections.observableArrayList();
        try {
            int i = 1;
            while(rs.next()){
                list.add(new PhieuBau(i,rs.getInt(2),rs.getInt(3)));
                i++;
            }
            p_STT.setCellValueFactory(new PropertyValueFactory<>("p_STT"));
            p_MaTV.setCellValueFactory(new PropertyValueFactory<>("p_MaTV"));
            p_MaUCV.setCellValueFactory(new PropertyValueFactory<>("p_MaUCV"));
            phieuBau.setItems(list);
        } catch (SQLException ex) {
            phieuBau.setItems(list);
            Logger.getLogger(ToGiamSatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void LoadLSBTable()
    {
        ResultSet rs = null;
        try {
            rs = DAO.Select("SELECT * FROM QT.LICHSUBAU");
        } catch (SQLException ex) {
            Logger.getLogger(ToGiamSatController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<LichSuBau> list = FXCollections.observableArrayList();
        try {
            while(rs.next()){
                list.add(new LichSuBau(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getDate(4)));
            }
            ls_STT.setCellValueFactory(new PropertyValueFactory<>("ls_STT"));
            ls_MaTV.setCellValueFactory(new PropertyValueFactory<>("ls_MaTV"));
            ls_MaUCV.setCellValueFactory(new PropertyValueFactory<>("ls_MaUCV"));
            ls_Ngay.setCellValueFactory(new PropertyValueFactory<>("ls_Ngay"));
            lichSuBau.setItems(list);
        } catch (SQLException ex) {
            Logger.getLogger(ToGiamSatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.LoadUCVTable();
        this.LoadLDSTable();
        this.LoadTDKQTable();
        this.LoadGSTable();
        this.LoadNDBTable();
        this.LoadBTCTable();
        this.LoadPBTable();
        this.LoadLSBTable();
        home.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Change("UserHome.fxml",541,296,"Chọn vai trò đăng nhập");
                } catch (IOException ex) {
                    Logger.getLogger(QuanTriHomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }    
    
}
