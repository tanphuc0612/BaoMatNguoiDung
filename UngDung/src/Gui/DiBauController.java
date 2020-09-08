/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import DAO.DAO;
import DAO.DBConnector;
import Entity.UngCuVien;
import Entity.ViewPhieuBau;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import ungdung.UngDung;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class DiBauController implements Initializable {
    @FXML 
    private Label username;
    @FXML 
    private Button btnHome;
    @FXML
    private Button btnXacNhan;
    @FXML
    private Button btnThem;
    @FXML
    private TableView<UngCuVien>dsUngCuVien;
    @FXML
    private TableColumn<UngCuVien, Integer> maUCV;
    @FXML
    private TableColumn<UngCuVien, String> tenUCV;
    @FXML
    private TableColumn<UngCuVien, String> phaiUCV;
    @FXML
    private TableColumn<UngCuVien, String> ngaySinhUCV;
    
    @FXML
    private TableView<ViewPhieuBau>phieu;
    @FXML
    private TableColumn<ViewPhieuBau, Integer> ma;
    @FXML
    private TableColumn<ViewPhieuBau, String> ten;
    @FXML
    private TableColumn<ViewPhieuBau, Date> ngay;   
    
    private ObservableList<ViewPhieuBau> phieu_bau = FXCollections.observableArrayList();
    private ObservableList<UngCuVien> list = FXCollections.observableArrayList();
    private int maTV;
    private int n = 0;
    public void Init(){
        username.setText(UngDung.getUsername());
        maUCV.setCellValueFactory(new PropertyValueFactory<>("maUCV"));
        tenUCV.setCellValueFactory(new PropertyValueFactory<>("tenUCV"));
        phaiUCV.setCellValueFactory(new PropertyValueFactory<>("phaiUCV"));
        ngaySinhUCV.setCellValueFactory(new PropertyValueFactory<>("ngaySinhUCV"));
        ma.setCellValueFactory(new PropertyValueFactory<>("ma"));
        ten.setCellValueFactory(new PropertyValueFactory<>("ten"));
        ngay.setCellValueFactory(new PropertyValueFactory<>("ngay"));
        TableViewUCV();
    }
    public void TableViewUCV(){
        ResultSet rs = null;
        try {
            rs = DAO.Select("SELECT * FROM QT.UNGCUVIEN");
        } catch (SQLException ex) {
            Logger.getLogger(DiBauController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while(rs.next()){
                list.add(new UngCuVien(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4)));
                dsUngCuVien.setItems(list);
            }   } catch (SQLException ex) {
            Logger.getLogger(DiBauController.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    public void ThemButton(){
        btnThem.setOnMouseClicked((MouseEvent event) -> {
            if(dsUngCuVien.getSelectionModel().getSelectedItem() != null){
                UngCuVien ucv = dsUngCuVien.getSelectionModel().getSelectedItem();
                phieu_bau.add(new ViewPhieuBau(ucv.getMaUCV(),ucv.getTenUCV(),Date.valueOf(java.time.LocalDate.now())));
                int i = 0;
                for(UngCuVien o:list){
                    if(o.getMaUCV() == ucv.getMaUCV())
                    {
                        list.remove(i);
                        break;
                    }
                    i++;
                }
            dsUngCuVien.setItems(list);
            phieu.setItems(phieu_bau);
            n++;
            }
        });
    }
    public void XacNhanButton(){
        btnXacNhan.setOnMouseClicked((MouseEvent event) -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            if(n == 0){
                alert.setContentText("Bạn cần chọn ứng cử viên");
            }else{
                try {
                    this.LayMaTV();
                    this.XoaLanBauCuoi();
                    this.ThemLanBauCuoi();
                    this.ThemLichSuBau();
                    alert.setContentText("thanh cong");
                    alert.show();
                    this.Change("DiBau.fxml", 1000, 600, "Đi bầu");
                } catch (SQLException ex) {
                    Logger.getLogger(DiBauController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(DiBauController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    public void HomeButton(){
        btnHome.setOnMouseClicked((MouseEvent event) -> {
            try {
                this.Change("UserHome.fxml", 541, 296, "Home");
            } catch (IOException ex) {
                Logger.getLogger(DiBauController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
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
    public void ThemLichSuBau() throws SQLException{
        for(ViewPhieuBau o:phieu_bau){
            DAO.Insert("INSERT INTO QT.LICHSUBAU VALUES(null," + maTV + "," + o.getMa() + ",TO_DATE('" + Date.valueOf(LocalDate.now()) + "','YYYY-MM-DD'))");
        }
    }
    public void ThemLanBauCuoi() throws SQLException{
        for(ViewPhieuBau o:phieu_bau){
            DAO.Insert("INSERT INTO QT.LANBAUCUOI VALUES(null," + maTV + "," + o.getMa() + ")");
        }
    }
    public void XoaLanBauCuoi() throws SQLException{
        DBConnector.setConnection("qt","1");
        String query = "DELETE FROM QT.LANBAUCUOI WHERE MATV = " + maTV;
        DAO.Insert(query);
        DBConnector.setConnection(UngDung.getUsername(),"1");
    }
    
    public int LayMaTV() throws SQLException{
        maTV = DAO.LayMaTV(UngDung.getUsername());
        return maTV;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.Init();
        this.ThemButton();
        this.XacNhanButton();
        this.HomeButton();
    }    
    
}
