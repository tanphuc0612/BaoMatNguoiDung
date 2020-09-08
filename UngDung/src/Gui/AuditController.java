/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import DAO.DAO;
import Entity.AuditView;
import Entity.UngCuVien;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
 * @author mymy4
 */
public class AuditController implements Initializable {
    @FXML
    private Button btnHome;
    @FXML
    private Button btnDisable;
    @FXML
    private Button btnEnable;
    @FXML
    private TableView<AuditView> auditTable;
    @FXML
    private TableColumn<AuditView,String> table;
    @FXML
    private TableColumn<AuditView,Timestamp> time;
    @FXML
    private TableColumn<AuditView,String> username;
    @FXML
    private TableColumn<AuditView,String> query;
    
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
    
    public void TableView(){
        ResultSet rs = null;
        try {
            rs= DAO.Select("SELECT DBUSERNAME,SQL_TEXT,OBJECT_NAME,EVENT_TIMESTAMP FROM UNIFIED_AUDIT_TRAIL WHERE FGA_POLICY_NAME = 'AUDIT_THANHVIEN' order by EVENT_TIMESTAMP");
        } catch (SQLException ex) {
            Logger.getLogger(AuditController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<AuditView> list = FXCollections.observableArrayList();
        try {
            while(rs.next()){
                System.out.println();
                list.add(new AuditView(rs.getString(1),rs.getString(2),rs.getString(3),rs.getTimestamp(4)));
                username.setCellValueFactory(new PropertyValueFactory<>("username"));
                table.setCellValueFactory(new PropertyValueFactory<>("table"));
                query.setCellValueFactory(new PropertyValueFactory<>("query"));
                time.setCellValueFactory(new PropertyValueFactory<>("time"));
                auditTable.setItems(list);
            }   } catch (SQLException ex) {
            Logger.getLogger(BanToChucController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void HomeButton(){
        btnHome.setOnMouseClicked((MouseEvent event) -> {
            try {
                Change("QuanTriHome.fxml",1000,600,"Quản trị Home");
            } catch (IOException ex) {
                Logger.getLogger(ThemDoiTuongController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    public void DisableButton(){
        btnDisable.setOnMouseClicked((MouseEvent event) -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            try {
                DAO.DisableAudit("THANHVIEN", "AUDIT_THANHVIEN");
                alert.setContentText("disable thành công");
                alert.show();                
            } catch (SQLException ex) {
                Logger.getLogger(AuditController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    public void EnableButton(){
        btnEnable.setOnMouseClicked((MouseEvent event) -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            try {
                DAO.EnableAudit("THANHVIEN", "AUDIT_THANHVIEN");
                alert.setContentText("enable thành công");
                alert.show();
            } catch (SQLException ex) {
                Logger.getLogger(AuditController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.TableView();
        this.HomeButton();
        this.DisableButton();
        this.EnableButton();
    }    
    
}
