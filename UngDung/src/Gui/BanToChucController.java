/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import DAO.DAO;
import DAO.DBConnector;
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

    
public class BanToChucController implements Initializable {

    @FXML
    private TabPane tab;

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
    private Button suaUCV;

    @FXML
    private Button xoaUCV;

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
    private Button suaLDS;

    @FXML
    private Button xoaLDS;

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
    private Button suaTDKQ;

    @FXML
    private Button xoaTDKQ;

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
    private Button suaGS;
    
    @FXML
    private Button xoaGS;

    @FXML
    private Button home;
    @FXML
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
            Logger.getLogger(BanToChucController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<UngCuVien> list = FXCollections.observableArrayList();
        try {
            while(rs.next()){
                System.out.println();
                list.add(new UngCuVien(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4)));
                maUCV.setCellValueFactory(new PropertyValueFactory<>("maUCV"));
                tenUCV.setCellValueFactory(new PropertyValueFactory<>("tenUCV"));
                phaiUCV.setCellValueFactory(new PropertyValueFactory<>("phaiUCV"));
                ngaySinhUCV.setCellValueFactory(new PropertyValueFactory<>("ngaySinhUCV"));
                ucvTable.refresh();
                ucvTable.setItems(list);
            }   } catch (SQLException ex) {
            Logger.getLogger(BanToChucController.class.getName()).log(Level.SEVERE, null, ex);
        }
        suaUCV.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                UngCuVien temp = ucvTable.getSelectionModel().getSelectedItem();
                if(temp != null)
                {
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("SuaThongTin.fxml"));
                        Scene scene = new Scene(fxmlLoader.load(), 439, 436);
                        Stage stage = new Stage();
                        stage.setTitle("Sửa thông tin");
                        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
                        stage.setX((screenBounds.getWidth() - 439) / 2);
                        stage.setY((screenBounds.getHeight() - 436) / 2);
                        SuaThongTinController control = fxmlLoader.getController();
                        control.getParameter(temp.getMaUCV(),temp.getTenUCV(),temp.getPhaiUCV(),temp.getNgaySinhUCV(),"ẩn","UNGCUVIEN","UCV");
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(QuanTriHomeController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        xoaUCV.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                UngCuVien temp = ucvTable.getSelectionModel().getSelectedItem();
                if(temp != null)
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    String query = "DELETE FROM QT.UNGCUVIEN WHERE MaUCV = '"+temp.getMaUCV()+"'";
                    try {
                        if(DAO.Insert(query))
                        {
                            alert.setHeaderText(null);
                            alert.setContentText("Xóa thành công.");
                            alert.show();
                            DBConnector.con.setAutoCommit(false);
                            DBConnector.con.commit();
                            Change("BanToChuc.fxml",1000,600,"Ban tổ chức");
                        }
                        else
                        {
                            alert.setHeaderText(null);
                            alert.setContentText("Xóa thất bại.");
                            alert.show();
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(BanToChucController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(BanToChucController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }
    public void LoadLDSTable()
    {
        ResultSet rs = null;
        try {
            rs= DAO.Select("SELECT L.MALDS,L.HOTEN,L.PHAI,L.NGAYSINH,D.TENDV FROM QT.TOLAPDANHSACH L, QT.DONVI D WHERE L.MADV = D.MADV");
        } catch (SQLException ex) {
            Logger.getLogger(BanToChucController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<LapDanhSachDiBau> list = FXCollections.observableArrayList();
        try {
            while(rs.next()){
                list.add(new LapDanhSachDiBau(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getString(5)));
                maLDS.setCellValueFactory(new PropertyValueFactory<>("maLDS"));
                tenLDS.setCellValueFactory(new PropertyValueFactory<>("tenLDS"));
                phaiLDS.setCellValueFactory(new PropertyValueFactory<>("phaiLDS"));
                ngaySinhLDS.setCellValueFactory(new PropertyValueFactory<>("ngaySinhLDS"));
                donviLDS.setCellValueFactory(new PropertyValueFactory<>("donviLDS"));
                lapDSTable.setItems(list);
            }   } catch (SQLException ex) {
            Logger.getLogger(BanToChucController.class.getName()).log(Level.SEVERE, null, ex);
        }
        suaLDS.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                LapDanhSachDiBau temp = lapDSTable.getSelectionModel().getSelectedItem();
                if(temp!=null)
                {
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("SuaThongTin.fxml"));
                        Scene scene = new Scene(fxmlLoader.load(), 439, 436);
                        Stage stage = new Stage();
                        stage.setTitle("Sửa thông tin");
                        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
                        stage.setX((screenBounds.getWidth() - 439) / 2);
                        stage.setY((screenBounds.getHeight() - 436) / 2);
                        SuaThongTinController control = fxmlLoader.getController();
                        control.getParameter(temp.getMaLDS(),temp.getTenLDS(),temp.getPhaiLDS(),temp.getNgaySinhLDS(),temp.getDonviLDS(),"TOLAPDANHSACH","LDS");
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(QuanTriHomeController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        xoaLDS.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                LapDanhSachDiBau temp = lapDSTable.getSelectionModel().getSelectedItem();
                if(temp != null)
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    String query = "DELETE FROM QT.TOLAPDANHSACH WHERE MaLDS = '"+temp.getMaLDS()+"'";
                    try {
                        if(DAO.Insert(query))
                        {
                            alert.setHeaderText(null);
                            alert.setContentText("Xóa thành công.");
                            alert.show();
                            DBConnector.con.setAutoCommit(false);
                            DBConnector.con.commit();
                            Change("BanToChuc.fxml",1000,600,"Ban tổ chức");
                        }
                        else
                        {
                            alert.setHeaderText(null);
                            alert.setContentText("Xóa thất bại.");
                            alert.show();
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(BanToChucController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(BanToChucController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }
    public void LoadTDKQTable()
    {
        ResultSet rs = null;
        try {
            rs= DAO.Select("SELECT * FROM QT.TOTHEODOI");
        } catch (SQLException ex) {
            Logger.getLogger(BanToChucController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<TheoDoiKetQua> list = FXCollections.observableArrayList();
        try {
            while(rs.next()){
                list.add(new TheoDoiKetQua(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4)));
                maTDKQ.setCellValueFactory(new PropertyValueFactory<>("maTDKQ"));
                tenTDKQ.setCellValueFactory(new PropertyValueFactory<>("tenTDKQ"));
                phaiTDKQ.setCellValueFactory(new PropertyValueFactory<>("phaiTDKQ"));
                ngaySinhTDKQ.setCellValueFactory(new PropertyValueFactory<>("ngaySinhTDKQ"));
                theoDoiTable.setItems(list);
            }   } catch (SQLException ex) {
            Logger.getLogger(BanToChucController.class.getName()).log(Level.SEVERE, null, ex);
        }
        suaTDKQ.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                TheoDoiKetQua temp = theoDoiTable.getSelectionModel().getSelectedItem();
                if(temp!=null)
                {
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("SuaThongTin.fxml"));
                        Scene scene = new Scene(fxmlLoader.load(), 439, 436);
                        Stage stage = new Stage();
                        stage.setTitle("Sửa thông tin");
                        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
                        stage.setX((screenBounds.getWidth() - 439) / 2);
                        stage.setY((screenBounds.getHeight() - 436) / 2);
                        SuaThongTinController control = fxmlLoader.getController();

                        control.getParameter(temp.getMaTDKQ(),temp.getTenTDKQ(),temp.getPhaiTDKQ(),temp.getNgaySinhTDKQ(),"ẩn","TOTHEODOI","TTD");
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(QuanTriHomeController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        xoaTDKQ.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                TheoDoiKetQua temp = theoDoiTable.getSelectionModel().getSelectedItem();
                if(temp != null)
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    String query = "DELETE FROM QT.TOTHEODOI WHERE MaTTD = '"+temp.getMaTDKQ()+"'";
                    try {
                        if(DAO.Insert(query))
                        {
                            alert.setHeaderText(null);
                            alert.setContentText("Xóa thành công.");
                            alert.show();
                            DBConnector.con.setAutoCommit(false);
                            DBConnector.con.commit();
                            Change("BanToChuc.fxml",1000,600,"Ban tổ chức");
                        }
                        else
                        {
                            alert.setHeaderText(null);
                            alert.setContentText("Xóa thất bại.");
                            alert.show();
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(BanToChucController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(BanToChucController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }
    public void LoadGSTable()
    {
        ResultSet rs = null;
        try {
            rs= DAO.Select("SELECT * FROM QT.TOGIAMSAT");
        } catch (SQLException ex) {
            Logger.getLogger(BanToChucController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<GiamSat> list = FXCollections.observableArrayList();
        try {
            while(rs.next()){
                list.add(new GiamSat(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4)));
                maGS.setCellValueFactory(new PropertyValueFactory<>("maGS"));
                tenGS.setCellValueFactory(new PropertyValueFactory<>("tenGS"));
                phaiGS.setCellValueFactory(new PropertyValueFactory<>("phaiGS"));
                ngaySinhGS.setCellValueFactory(new PropertyValueFactory<>("ngaySinhGS"));
                giamSatTable.setItems(list);
            }   } catch (SQLException ex) {
            Logger.getLogger(BanToChucController.class.getName()).log(Level.SEVERE, null, ex);
        }
        suaGS.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                GiamSat temp = giamSatTable.getSelectionModel().getSelectedItem();
                if(temp!=null)
                {
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("SuaThongTin.fxml"));
                        Scene scene = new Scene(fxmlLoader.load(), 439, 436);
                        Stage stage = new Stage();
                        stage.setTitle("Sửa thông tin");
                        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
                        stage.setX((screenBounds.getWidth() - 439) / 2);
                        stage.setY((screenBounds.getHeight() - 436) / 2);
                        SuaThongTinController control = fxmlLoader.getController();

                        control.getParameter(temp.getMaGS(),temp.getTenGS(),temp.getPhaiGS(),temp.getNgaySinhGS(),"ẩn","TOGIAMSAT","TGS");
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(QuanTriHomeController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        xoaGS.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                GiamSat temp = giamSatTable.getSelectionModel().getSelectedItem();
                if(temp != null)
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    String query = "DELETE FROM QT.TOGIAMSAT WHERE MaTGS = '"+temp.getMaGS()+"'";
                    try {
                        if(DAO.Insert(query))
                        {
                            alert.setHeaderText(null);
                            alert.setContentText("Xóa thành công.");
                            alert.show();
                            DBConnector.con.setAutoCommit(false);
                            DBConnector.con.commit();
                            Change("BanToChuc.fxml",1000,600,"Ban tổ chức");
                        }
                        else
                        {
                            alert.setHeaderText(null);
                            alert.setContentText("Xóa thất bại.");
                            alert.show();
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(BanToChucController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(BanToChucController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.LoadUCVTable();
        this.LoadLDSTable();
        this.LoadTDKQTable();
        this.LoadGSTable();
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
